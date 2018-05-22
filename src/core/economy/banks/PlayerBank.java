package core.economy.banks;

import core.economy.accounts.MoneyAccount;

/**
 * Handles the money of player accounts
 * 
 * @author PhantomUnicorns
 */
public class PlayerBank extends Bank<MoneyAccount> {

	@Override
	public String getName() {
		return _PLAYER_BANK_NAME;
	}
}