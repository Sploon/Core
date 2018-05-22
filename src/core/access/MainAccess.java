package core.access;

import core.Core;
import core.CorePlugin;
import core.exceptions.core.AlreadySetCoreException;
import core.exceptions.core.CoreNotSetException;

/**
 * The main access to basic utensuls
 * 
 * @author PhantomUnicorns
 */
public class MainAccess {
	
	private static final Class<?> _THIS;
	private static boolean _initilzedCore, _initilzedPlugin;
	private static CorePlugin _plugin;
	private static Core _core;
	
	protected static final String _EMPTY_STRING, _MAIN_COLOR;

	static {
		_THIS = MainAccess.class;
		_EMPTY_STRING = "";
		_MAIN_COLOR = "&6";
		_initilzedCore = false;
		_initilzedPlugin = false;
	}
	
	/**
	 * Sets the core
	 * @param core The core
	 */
	public static void setCore(Core core) {
		if (!_initilzedCore) {
			_core = core;
			_initilzedCore = true;
		} else {
			throw new AlreadySetCoreException(_THIS, "core");
		}
	}
	
	/**
	 * @return Gets the core
	 */
	protected static Core getCore() {
		if (_initilzedCore) {
			return _core;
		} else {
			throw new CoreNotSetException(_THIS, "core");
		}
	}
	
	/**
	 * Sets the core
	 * @param core The core
	 */
	public static void setPlugin(CorePlugin plugin) {
		if (!_initilzedPlugin) {
			_plugin = plugin;
			_initilzedPlugin = true;
		} else {
			throw new AlreadySetCoreException(_THIS, "core plugin");
		}
	}
	
	/**
	 * @return Gets the core
	 */
	protected static CorePlugin getPlugin() {
		if (_initilzedPlugin) {
			return _plugin;
		} else {
			throw new CoreNotSetException(_THIS, "core plugin");
		}
	}
}