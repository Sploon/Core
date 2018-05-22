package core.economy.accounts;

public class MoneyAccount extends ValueAccount<Double> {

	public MoneyAccount(String key, double amount) {
		super(key, amount);
	}

	/**
	 * @return The amount in this account (rounded)
	 */
	public int getAmount() {
		return (int) this._value.doubleValue();
	}

	/**
	 * @return The amount in this account (exact)
	 */
	public double getExactAmount() {
		return this._value;
	}

	/**
	 * Tests if this account has more or equal to amount
	 * 
	 * @param amount
	 *            The amount to test for
	 * @return If true
	 */
	public boolean hasAmount(double amount) {
		return this._value >= amount;
	}

	/**
	 * Adds an amount to this account; cannot go over maximum
	 * 
	 * @param amount
	 *            The amount to add
	 * @return If successful
	 */
	public double addAmount(double amount) {
		if ((this._value += amount) > _MAX_AMOUNT) {
			double remove = (this._value - _MAX_AMOUNT);
			this._value -= remove;
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
		return this._value >= amount + _MIN_AMOUNT && (this._value -= amount) >= _MIN_AMOUNT;
	}
}