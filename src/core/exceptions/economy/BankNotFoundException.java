package core.exceptions.economy;

/**
 * When the accounts is not found
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class BankNotFoundException extends RuntimeException {

	public BankNotFoundException(String accountsName) {
		super("The accounts [" + accountsName + "] was not found!");
	}
}