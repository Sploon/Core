package core.economy.accounts;

/**
 * An account, holds a key and amount
 * 
 * @author PhantomUnicorns
 */
public class ValueAccount<T> extends Account {

	protected T _value;

	public ValueAccount(String key, T value) {
		super(key);
		this._value = value;
	}

	/**
	 * @return The value in this account
	 */
	public T getValue() {
		return this._value;
	}
}