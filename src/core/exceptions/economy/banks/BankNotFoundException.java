package core.exceptions.economy.banks;

/**
 * When the accounts is not found
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class BankNotFoundException extends RuntimeException {

	public BankNotFoundException(String accountsName) {
		super("The bank [" + accountsName + "] was not found!");
	}
}