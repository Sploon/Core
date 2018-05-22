package core.exceptions.economy;

/**
 * When you create an account with key and key is already in use
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class ExistingBankException extends RuntimeException {

	public ExistingBankException(String key) {
		super("The account with key: [" + key + "] already exist!");
	}
}