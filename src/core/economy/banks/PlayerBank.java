package core.economy.banks;

import core.economy.bank.account.MoneyAccount;

/**
 * Handles the money of player accounts
 * 
 * @author PhantomUnicorns
 */
public class PlayerBank extends Bank<MoneyAccount> {

	@Override
	public String getName() {
		return _PLAYER_ACCOUNTS_NAME;
	}
}