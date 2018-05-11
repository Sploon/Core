package core.access;

import core.Core;
import core.exceptions.core.AlreadySetCoreException;

/**
 * The main access to basic utensuls
 * 
 * @author PhantomUnicorns
 */
public class MainAccess {
	
	private static final Class<?> _THIS;
	private static boolean _initilzed;
	
	protected static final String _EMPTY_STRING;
	protected static Core _core;
	
	public static final String MAIN_COLOR;

	static {
		_THIS = MainAccess.class;
		_EMPTY_STRING = "";
		MAIN_COLOR = "&6";
		_initilzed = false;
	}
	
	/**
	 * Sets the core
	 * @param core The core
	 */
	public static void setCore(Core core) {
		if (!_initilzed) {
			_core = core;
			_initilzed = true;
		} else {
			throw new AlreadySetCoreException(_THIS);
		}
	}
	
	/**
	 * @return Gets the core
	 */
	public static Core getCore() {
		if (_initilzed) {
			return _core;
		} else {
			throw new AlreadySetCoreException(_THIS);
		}
	}
}