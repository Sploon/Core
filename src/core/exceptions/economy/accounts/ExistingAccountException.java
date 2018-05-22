package core.exceptions.economy.accounts;

/**
 * When you create an account with key and key is already in use
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class ExistingAccountException extends RuntimeException {

	public ExistingAccountException(String key) {
		super("The account with key: [" + key + "] already exist!");
	}
}