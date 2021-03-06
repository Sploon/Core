package core.exceptions.core;

/**
 * This means the core has not been set
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class CoreNotSetException extends RuntimeException {

	public CoreNotSetException(Class<?> clazz, String type) {
		super("The " + type + " for the class: [" + clazz.getName() + "] has not been set!");
	}
}