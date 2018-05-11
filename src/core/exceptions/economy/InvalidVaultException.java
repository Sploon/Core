package core.exceptions.economy;

/**
 * When you try accessing key but key does not exist
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class InvalidVaultException extends RuntimeException {

	public InvalidVaultException(String key) {
		super("The vault with key: [" + key + "] does not exist!");
	}
}