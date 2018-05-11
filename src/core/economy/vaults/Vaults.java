package core.economy.vaults;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import core.exceptions.economy.InvalidVaultException;

public class Vaults<T> {
	
	private Map<String, T> _vaults;
	
	public Vaults() {
		this._vaults = new HashMap<>();
	}
	
	public T addVault(String key, T vault) {
		if (!this._vaults.containsKey(key)) {
			this._vaults.put(key, vault);
		} else {
			// TODO: Do some error
		}
		return vault;
	}
	
	public boolean hasVault(String key) {
		return this._vaults.containsKey(key);
	}
	
	public T getVault(String key) {
		if (this._vaults.containsKey(key)) {
			return this._vaults.get(key);
		} else {
			throw new InvalidVaultException(key);
		}
	}
	
	public boolean removeVault(String key) {
		if (this._vaults.containsKey(key)) {
			this._vaults.remove(key);
			return true;
		} else {
			return false;
		}
	}
	
	public void forEach(Consumer<T> doEach) {
		for (T vault : this._vaults.values()) {
			doEach.accept(vault);
		}
	}
}