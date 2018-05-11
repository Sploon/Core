package core.economy;

import core.access.MoneyAccess;

/**
 * An account, holds a key and amount
 * 
 * @author PhantomUnicorns
 */
public class Account extends MoneyAccess {

	private String _key;
	private double _amount;

	public Account(String key, double amount) {
		this._key = key;
		this._amount = amount;
	}

	/**
	 * @return The key to the account
	 */
	public String getKey() {
		return this._key;
	}

	/**
	 * @param key
	 *            The key to test for
	 * @return If keys are equal
	 */
	public boolean isKey(String key) {
		return this._key.equals(key);
	}

	/**
	 * @return The amount in this account (rounded)
	 */
	public int getAmount() {
		return (int) this._amount;
	}

	/**
	 * @return The amount in this account (exact)
	 */
	public double getExactAmount() {
		return this._amount;
	}

	/**
	 * Tests if this account has more or equal to amount
	 * 
	 * @param amount
	 *            The amount to test for
	 * @return If true
	 */
	public boolean hasAmount(double amount) {
		return this._amount >= amount;
	}

	/**
	 * Adds an amount to this account; cannot go over maximum
	 * 
	 * @param amount
	 *            The amount to add
	 * @return If successful
	 */
	public double addAmount(double amount) {
		if ((this._amount += amount) > _MAX_AMOUNT) {
			double remove = (this._amount - _MAX_AMOUNT);
			this._amount -= remove;
			return remove;
		}
		return 0;
	}

	/**
	 * Removes an amount from this account; cannot go below minimum
	 * 
	 * @param amount
	 *            The amount to remove
	 * @return If successful
	 */
	public boolean removeAmount(double amount) {
		return this._amount >= amount + _MIN_AMOUNT && (this._amount -= amount) >= _MIN_AMOUNT;
	}
}