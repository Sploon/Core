package core.access;

import core.economy.Economy;

/**
 * Used to access money constants and other utensuls that invole money
 * 
 * @author PhantomUnicorns
 */
public class MoneyAccess extends ObjectAccess {

	protected static final String _PLAYER_BANK_NAME;

	protected static final double _MAX_AMOUNT, _MIN_AMOUNT;

	static {
		_PLAYER_BANK_NAME =  "Player Bank";
		
		_MAX_AMOUNT = 1000000;
		_MIN_AMOUNT = 0;
	}

	/**
	 * @return The maximum amount (rounded) an account can have
	 */
	protected static int getMaxAmount() {
		return (int) _MAX_AMOUNT;
	}

	/**
	 * @return The maximum amount (exact) an account can have
	 */
	protected static double getExactMaxAmount() {
		return _MAX_AMOUNT;
	}

	/**
	 * @return The minimum amount (rounded) an account can have
	 */
	protected static int getMinAmount() {
		return (int) _MIN_AMOUNT;
	}

	/**
	 * @return The minimum amount (exact) an account can have
	 */
	protected static double getExactMinAmount() {
		return _MIN_AMOUNT;
	}
	
	/**
	 * @return The economy; used to handle money
	 */
	protected static Economy getEconomy() {
		return getCore().getEconomy();
	}
}
