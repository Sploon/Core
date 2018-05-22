package core.exceptions.economy;

/**
 * When you modifiy a main accounts
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class BankModificationException extends RuntimeException {

	public BankModificationException(String accountsName, String action) {
		super("The accounts [" + accountsName + "] cannot be " + action + "!");
	}
}