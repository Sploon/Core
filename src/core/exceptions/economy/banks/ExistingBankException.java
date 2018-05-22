package core.exceptions.economy.banks;

/**
 * When you create an account with key and key is already in use
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class ExistingBankException extends RuntimeException {

	public ExistingBankException(String key) {
		super("The bank with name: [" + key + "] already exist!");
	}
}