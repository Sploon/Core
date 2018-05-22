package core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import core.access.MainAccess;

/**
 * Core class of the Core plugin
 * 
 * @author PhantomUnicorns
 */
public class CorePlugin extends JavaPlugin {

	private Core _core;

	public static void main(String[] args) {
		// TODO: Add config manager
		// Main.launch(file, null, args);
	}

	@Override
	public void onEnable() {
		MainAccess.setPlugin(this);
		MainAccess.setCore(this._core = new Core());
	}

	@Override
	public void onDisable() {
		this._core.saveConfigurations();
	}
	
	public Core getCore() {
		return this._core;
	}
	
	/**
	 * @return Returns the instance of the plugin
	 */
	public static CorePlugin getInstance() {
		return (CorePlugin) Bukkit.getPluginManager().getPlugin("Core");
	}
}