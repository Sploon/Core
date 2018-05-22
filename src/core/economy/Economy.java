package core.economy;

import java.util.ArrayList;
import java.util.List;

import core.access.MoneyAccess;
import core.economy.banks.Bank;
import core.economy.banks.PlayerBank;
import core.exceptions.economy.banks.BankModificationException;
import core.exceptions.economy.banks.BankNotFoundException;
import core.exceptions.economy.banks.ExistingBankException;

/**
 * Everything with economy, including player-accounts, bank-accounts, and vaults
 * 
 * @author PhantomUnicorns
 */
public class Economy extends MoneyAccess {

	private List<Bank<?>> _banks;

	public Economy() {
		this._banks = new ArrayList<>();
		this._banks.add(new PlayerBank());
	}

	/**
	 * Gets the account associated with name
	 * 
	 * @param name
	 *            The name of the account
	 * @return The account that associates with the name
	 */
	public Bank<?> addBank(Bank<?> bank) {
		for (Bank<?> b : this._banks) {
			if (b.getName().equals(bank.getName())) {
				throw new ExistingBankException(bank.getName());
			}
		}
		this._banks.add(bank);
		return bank;
	}

	/**
	 * Gets the account associated with name
	 * 
	 * @param name
	 *            The name of the account
	 * @return The account that associates with the name
	 */
	public Bank<?> getBank(String name) {
		for (Bank<?> bank : this._banks) {
			if (bank.getName().equals(name)) {
				return bank;
			}
		}
		throw new BankNotFoundException(name);
	}

	/**
	 * @return The player accounts
	 */
	public PlayerBank getPlayerBank() {
		return (PlayerBank) getBank(_PLAYER_BANK_NAME);
	}

	/**
	 * Removes an account that's associated with name
	 * 
	 * @param name
	 *            The name of that account
	 * @return If successful
	 */
	public boolean removeBank(String name) {
		if (name.equals(_PLAYER_BANK_NAME)) {
			throw new BankModificationException(name, "removed");
		}
		for (int i = 2; i < this._banks.size(); ++i) {
			if (this._banks.get(i).getName().equals(name)) {
				this._banks.remove(i);
				return true;
			}
		}
		return false;
	}
}