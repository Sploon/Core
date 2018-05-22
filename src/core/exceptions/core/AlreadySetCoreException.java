package core.exceptions.core;

/**
 * This means the core was already set
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class AlreadySetCoreException extends RuntimeException {

	public AlreadySetCoreException(Class<?> clazz, String type) {
		super("The " + type + " for the class: [" + clazz.getName() + "] has already been set!");
	}
}