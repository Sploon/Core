package core.access;

import core.Core;
import core.exceptions.core.AlreadySetCoreException;
import core.exceptions.core.CoreNotSetException;

/**
 * The main access to basic utensuls
 * 
 * @author PhantomUnicorns
 */
public class MainAccess {
	
	private static final Class<?> _THIS;
	private static boolean _initilzed;
	
	protected static final String _EMPTY_STRING, _MAIN_COLOR;
	protected static Core _core;

	static {
		_THIS = MainAccess.class;
		_EMPTY_STRING = "";
		_MAIN_COLOR = "&6";
		_initilzed = false;
	}
	
	/**
	 * Sets the core
	 * @param core The core
	 */
	protected static void setCore(Core core) {
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
	protected static Core getCore() {
		if (_initilzed) {
			return _core;
		} else {
			throw new CoreNotSetException(_THIS);
		}
	}
}