package core.economy;

import java.util.ArrayList;
import java.util.List;

import core.access.MoneyAccess;
import core.economy.accounts.Accounts;
import core.economy.accounts.BankAccounts;
import core.economy.accounts.PlayerAccounts;
import core.economy.vaults.Vault;
import core.economy.vaults.Vaults;
import core.exceptions.economy.AccountsModificationException;
import core.exceptions.economy.AccountsNotFoundException;

/**
 * Everything with economy, including player-accounts, bank-accounts, and vaults
 * 
 * @author PhantomUnicorns
 */
public class Economy extends MoneyAccess {

	private List<Accounts> _accounts;
	private Vaults<Vault> _vaults;

	public Economy() {
		this._accounts = new ArrayList<>();
		this._vaults = new Vaults<>();
		this._accounts.add(new PlayerAccounts());
		this._accounts.add(new BankAccounts());
	}

	/**
	 * Gets the account associated with name
	 * 
	 * @param name
	 *            The name of the account
	 * @return The account that associates with the name
	 */
	public Accounts getAccounts(String name) {
		for (Accounts account : this._accounts) {
			if (account.getName().equals(name)) {
				return account;
			}
		}
		throw new AccountsNotFoundException(name);
	}

	/**
	 * @return The player accounts
	 */
	public Accounts getPlayerAccounts() {
		return getAccounts(_PLAYER_ACCOUNTS_NAME);
	}

	/**
	 * @return The bank accounts
	 */
	public Accounts getBankAccounts() {
		return getAccounts(_BANK_ACCOUNTS_NAME);
	}

	/**
	 * @return The vaults
	 */
	public Vaults<Vault> getVaults() {
		return this._vaults;
	}

	/**
	 * Removes an account that's associated with name
	 * 
	 * @param name
	 *            The name of that account
	 * @return If successful
	 */
	public boolean removeAccounts(String name) {
		if (name.equals(_PLAYER_ACCOUNTS_NAME) || name.equals(_BANK_ACCOUNTS_NAME)) {
			throw new AccountsModificationException(name, "removed");
		}
		for (int i = 2; i < this._accounts.size(); ++i) {
			if (this._accounts.get(i).getName().equals(name)) {
				this._accounts.remove(i);
				return true;
			}
		}
		return false;
	}
}