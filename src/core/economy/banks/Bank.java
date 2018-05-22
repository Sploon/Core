package core.economy.banks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import core.access.MoneyAccess;
import core.configuration.Database;
import core.economy.accounts.Account;
import core.exceptions.economy.accounts.AccountNotFoundException;
import core.exceptions.economy.accounts.ExistingAccountException;

/**
 * Handles the money of different entities
 * 
 * @author PhantomUnicorns
 */
public abstract class Bank<T extends Account> extends MoneyAccess {

	private List<T> _accounts;
	private Database _database;

	@SuppressWarnings("unchecked")
	public Bank() {
		this._accounts = new ArrayList<>();
		this._database = new Database("Economy-" + getClass().getSimpleName());
		for (String key : this._database.getConfiguration().getKeys(false)) {
			this._accounts.add((T) _OBJECT_READER.stringToObject(this._database.getConfiguration().getString(key)));
		}
		this._database.addSaver((file, config) -> {
			for (T account : this._accounts) {
				config.set(account.getKey(), _OBJECT_SERILZER.objectToString(account));
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
	public T addAccount(T toAdd) {
		for (T account : this._accounts) {
			if (account.isKey(toAdd.getKey())) {
				throw new ExistingAccountException(toAdd.getKey());
			}
		}
		this._accounts.add(toAdd);
		return toAdd;
	}

	/**
	 * Tests if an account has the key
	 * 
	 * @param key
	 *            The key to test for
	 * @return If found
	 */
	public boolean hasAccount(String key) {
		for (T account : this._accounts) {
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
	public T getAccount(String key) {
		for (T account : this._accounts) {
			if (account.isKey(key)) {
				return account;
			}
		}
		throw new AccountNotFoundException(key);
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
	public void forEach(Consumer<T> toRun) {
		for (T account : this._accounts) {
			toRun.accept(account);
		}
	}

	public abstract String getName();
}