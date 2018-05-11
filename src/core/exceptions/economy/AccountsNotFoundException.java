package core.exceptions.economy;

/**
 * When the accounts is not found
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class AccountsNotFoundException extends RuntimeException {

	public AccountsNotFoundException(String accountsName) {
		super("The accounts [" + accountsName + "] was not found!");
	}
}