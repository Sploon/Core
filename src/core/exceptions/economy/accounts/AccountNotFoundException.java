package core.exceptions.economy.accounts;

/**
 * When the accounts is not found
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(String accountsName) {
		super("The account [" + accountsName + "] was not found!");
	}
}