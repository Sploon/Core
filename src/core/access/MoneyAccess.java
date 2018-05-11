package core.access;

import core.economy.Economy;

/**
 * Used to access money constants and other utensuls that invole money
 * 
 * @author PhantomUnicorns
 */
public class MoneyAccess extends MainAccess {

	protected static final String _PLAYER_ACCOUNTS_NAME, _BANK_ACCOUNTS_NAME;

	protected static final double _MAX_AMOUNT, _MIN_AMOUNT;

	static {
		_PLAYER_ACCOUNTS_NAME =  "Player Accounts";
		_BANK_ACCOUNTS_NAME = "Bank Accounts";
		
		_MAX_AMOUNT = 1000000;
		_MIN_AMOUNT = 0;
	}

	/**
	 * @return The maximum amount (rounded) an account can have
	 */
	public static int getMaxAmount() {
		return (int) _MAX_AMOUNT;
	}

	/**
	 * @return The maximum amount (exact) an account can have
	 */
	public static double getExactMaxAmount() {
		return _MAX_AMOUNT;
	}

	/**
	 * @return The minimum amount (rounded) an account can have
	 */
	public static int getMinAmount() {
		return (int) _MIN_AMOUNT;
	}

	/**
	 * @return The minimum amount (exact) an account can have
	 */
	public static double getExactMinAmount() {
		return _MIN_AMOUNT;
	}
	
	/**
	 * @return The economy; used to handle money
	 */
	public static Economy getEconomy() {
		return _core.getEconomy();
	}
}
