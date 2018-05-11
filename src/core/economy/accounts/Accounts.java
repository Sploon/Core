package core.economy.accounts;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import core.access.MoneyAccess;
import core.config.Database;
import core.economy.Account;
import core.exceptions.economy.ExistingAccountException;
import core.exceptions.economy.InvalidAccountException;

/**
 * Handles the money of different entities
 * 
 * @author PhantomUnicorns
 */
public abstract class Accounts extends MoneyAccess {

	private List<Account> _accounts;
	private Database _database;

	public Accounts() {
		this._accounts = new ArrayList<>();
		this._database = new Database("Economy-" + getClass().getSimpleName());
		for (String key : this._database.getConfiguration().getKeys(false)) {
			this._accounts.add(new Account(key, this._database.getConfiguration().getDouble(key)));
		}
		this._database.addSaver((file, config) -> {
			for (Account account : this._accounts) {
				config.set(account.getKey(), account.getExactAmount());
			}
		});
	}

	/**
	 * Adds an account to the list of existing accounts
	 * 
	 * @param key
	 *            The account key
	 * @return The Account
	 */
	public Account addAccount(String key) {
		for (Account Account : this._accounts) {
			if (Account.isKey(key)) {
				throw new ExistingAccountException(key);
			}
		}
		Account Account = new Account(key, 0);
		this._accounts.add(Account);
		return Account;
	}

	/**
	 * Tests if an account has the key
	 * 
	 * @param key
	 *            The key to test for
	 * @return If found
	 */
	public boolean hasAccount(String key) {
		for (Account account : this._accounts) {
			if (account.isKey(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the account from a key; returns null if none found
	 * 
	 * @param key
	 *            The key to the account from
	 * @return The account
	 */
	public Account getAccount(String key) {
		for (Account account : this._accounts) {
			if (account.isKey(key)) {
				return account;
			}
		}
		throw new InvalidAccountException(key);
	}

	/**
	 * Removes an existing account
	 * 
	 * @param key
	 *            The key to the account
	 * @return If successful
	 */
	public boolean removeAccount(String key) {
		for (int i = 0; i < this._accounts.size(); ++i) {
			if (this._accounts.get(i).isKey(key)) {
				this._accounts.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Runs the consumer code for each account
	 * 
	 * @param toRun
	 *            The code to run
	 */
	public void forEach(Consumer<Account> toRun) {
		for (Account account : this._accounts) {
			toRun.accept(account);
		}
	}

	public abstract String getName();
}