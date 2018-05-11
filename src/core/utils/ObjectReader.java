package core.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectInputStream;

/**
 * Allows you to read objects from strings
 * 
 * @author PhantomUnicorns
 */
public class ObjectReader extends MasterKeys {

	/**
	 * Turns a string in to a location
	 * 
	 * @param string
	 *            The string to turn in to a location
	 * @return The string as a location
	 */
	public Location fromString(String string) {
		return fromString(string, true);
	}

	/**
	 * Turns a string in to a location
	 * 
	 * @param string
	 *            The string to turn in to a location
	 * @param exact
	 *            If the cords are exact (doubles) or rounded (ints)
	 * @return The string as a location
	 */
	public Location fromString(String string, boolean exact) {
		String[] s = string.split(_OBJECT_SERILIZER_KEY);
		if (exact) {
			return new Location(Bukkit.getWorld(s[0]), Double.valueOf(s[1]), Double.valueOf(s[2]),
					Double.valueOf(s[3]));
		} else {
			return new Location(Bukkit.getWorld(s[0]), Integer.valueOf(s[1]), Integer.valueOf(s[2]),
					Integer.valueOf(s[3]));
		}
	}

	/**
	 * Turns a string to an object using base64
	 * 
	 * @param objectData
	 *            The string to turn in to an object
	 * @return The string as an object
	 */
	public Object stringToObject(String objectData) {
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(Base64.getDecoder().decode(objectData));
			BukkitObjectInputStream data = new BukkitObjectInputStream(stream);
			Object object = data.readObject();
			data.close();
			return object;
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}