package core.exceptions.economy;

/**
 * When you try accessing key but key does not exist
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class InvalidAccountException extends RuntimeException {

	public InvalidAccountException(String key) {
		super("The account with key: [" + key + "] does not exist!");
	}
}