package core;

import java.util.ArrayList;
import java.util.List;

import core.access.MainAccess;
import core.command.CmdExecutor;
import core.configuration.Configuration;
import core.economy.Economy;

/**
 * Core class of the Core plugin
 * 
 * @author PhantomUnicorns
 */
public class Core extends MainAccess {

	private List<Configuration> _configs;
	private Economy _economy;

	public void init() {
		this._configs = new ArrayList<>();
		this._economy = new Economy();
		new core.command.implementations.Configuration();
	}

	/**
	 * Saves the configuration with file name: name
	 */
	public boolean saveConfiguration(String name) {
		for (Configuration config : this._configs) {
			if (config.getFileName().equalsIgnoreCase(name)) {
				config.onDisable();
				return true;
			}
		}
		return false;
	}

	/**
	 * Saves the configurations that are registered
	 */
	public void saveConfigurations() {
		for (Configuration config : this._configs) {
			config.onDisable();
		}
	}

	/**
	 * Registers a command
	 * 
	 * @param cmdExecutor
	 *            The command executor to register
	 */
	public void registerCommand(CmdExecutor cmdExecutor) {
		getPlugin().getCommand(cmdExecutor.getClass().getSimpleName().toLowerCase()).setExecutor(cmdExecutor);
	}

	/**
	 * Registers a configuration
	 * 
	 * @param config
	 *            The configuration to register
	 */
	public void registerConfiguration(Configuration config) {
		this._configs.add(config);
	}

	/**
	 * Unregisters a configuration
	 * 
	 * @param config
	 *            The configuration to unregister
	 */
	public void unregisterConfiguration(Configuration config) {
		this._configs.remove(config);
	}

	/**
	 * @return The economy; used to handle money
	 */
	public Economy getEconomy() {
		return this._economy;
	}

	/**
	 * @return The economy; used to handle money
	 */
	public List<Configuration> getConfigurations() {
		return this._configs;
	}
	
	/**
	 * @return Returns the instance of the core
	 */
	public static Core getInstance() {
		return CorePlugin.getInstance().getCore();
	}
}