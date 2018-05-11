package core.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Used to create a database
 * 
 * @author PhantomUnicorns
 */
public class Database extends Configuration {

	private List<BiConsumer<File, FileConfiguration>> _operations;

	public Database(String fileName) {
		super(fileName);
		this._operations = new ArrayList<>();
		try {
			if (this._file.exists()) {
				this._file.delete();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Adds a saver which is called onDisable to save the file
	 * 
	 * @param operator
	 *            The saver; this is called onDisable and saves stuff to the
	 *            fileconfiguration
	 */
	public void addSaver(BiConsumer<File, FileConfiguration> operator) {
		this._operations.add(operator);
	}

	@Override
	public void onDisable() {
		try {
			if (!this._file.exists()) {
				_core.getDataFolder().mkdirs();
				this._file.createNewFile();
			}
			this._fileConfiguration = YamlConfiguration.loadConfiguration(this._file);
			for (BiConsumer<File, FileConfiguration> operator : this._operations) {
				operator.accept(this._file, this._fileConfiguration);
			}
			this._fileConfiguration.save(this._file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}