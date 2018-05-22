package core.exceptions.economy.banks;

/**
 * When you modifiy a main accounts
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class BankModificationException extends RuntimeException {

	public BankModificationException(String accountsName, String action) {
		super("The bank [" + accountsName + "] cannot be " + action + "!");
	}
}