package core.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import core.access.ObjectAccess;

/**
 * Used to create different types of configurations
 * 
 * @author PhantomUnicorns
 */
public abstract class Configuration extends ObjectAccess {

	protected File _file;
	protected FileConfiguration _fileConfiguration;
	protected String _fileName;

	public Configuration(String fileName) {
		try {
			this._fileName = fileName;
			this._file = new File(getPlugin().getDataFolder() + File.separator + this._fileName.replaceAll("SEP", File.separator) + ".yml");
			if (!this._file.exists()) {
				getPlugin().getDataFolder().mkdirs();
				this._file.createNewFile();
			}
			this._fileConfiguration = YamlConfiguration.loadConfiguration(this._file);
			this._fileConfiguration.save(_file);
			getCore().registerConfiguration(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return The file associated with this configuration
	 */
	public File getFile() {
		return this._file;
	}

	/**
	 * @return The fileconfiguration associated with this configuration
	 */
	public FileConfiguration getConfiguration() {
		return this._fileConfiguration;
	}

	/**
	 * @return The file name
	 */
	public String getFileName() {
		return this._fileName;
	}

	/**
	 * Reads a location from the fileconfiguration
	 * 
	 * @param path
	 *            The path to the location
	 * @return The location
	 */
	public Location getLocation(String path) {
		return _OBJECT_READER.fromString(this._fileConfiguration.getString(path));
	}

	/**
	 * Sets the path to the location
	 * 
	 * @param path
	 *            The path to the location
	 * @param location
	 *            The location
	 */
	public void setLocation(String path, Location location) {
		this._fileConfiguration.set(path, _OBJECT_SERILZER.toString(location));
	}
	
	/**
	 * Reads a message from the fileconfiguration
	 * 
	 * @param path
	 *            The path to the message
	 * @return The message
	 */
	public String getMessage(String path) {
		return ChatColor.translateAlternateColorCodes('&', this._fileConfiguration.getString(path));
	}
	
	/**
	 * Reads a messages from the fileconfiguration
	 * 
	 * @param path
	 *            The path to the messages
	 * @return The messages
	 */
	public List<String> getMessages(String path) {
		List<String> messages = new ArrayList<>();
		for (String message : this._fileConfiguration.getStringList(path)) {
			messages.add(ChatColor.translateAlternateColorCodes('&', message));
		}
		return messages;
	}

	public abstract void onDisable();
}