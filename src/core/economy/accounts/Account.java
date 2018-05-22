package core.economy.accounts;

import core.access.MoneyAccess;

public class Account extends MoneyAccess {
	
	protected String _key;

	protected Account(String key) {
		this._key = key;
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
}