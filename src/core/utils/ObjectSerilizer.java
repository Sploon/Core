package core.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;

/**
 * Allows you to serilizer objects to strings
 * 
 * @author PhantomUnicorns
 */
public class ObjectSerilizer extends MasterKeys {

	/**
	 * Turns a location to string
	 * 
	 * @param location
	 *            The location to turn to string
	 * @return The location as a string
	 */
	public String toString(Location location) {
		return toString(location, true);
	}

	/**
	 * Turns a location to string
	 * 
	 * @param location
	 *            The location to turn to string
	 * @param exact
	 *            If the block cords are exact (doubles) or rounded (ints)
	 * @return The location as a string
	 */
	public String toString(Location location, boolean exact) {
		if (exact) {
			return location.getWorld().getName() + _OBJECT_SERILIZER_KEY + location.getX() + _OBJECT_SERILIZER_KEY
					+ location.getY() + _OBJECT_SERILIZER_KEY + location.getZ();
		} else {
			return location.getWorld().getName() + _OBJECT_SERILIZER_KEY + location.getBlockX() + _OBJECT_SERILIZER_KEY
					+ location.getBlockY() + _OBJECT_SERILIZER_KEY + location.getBlockZ();
		}
	}

	/**
	 * Turns an itemstack to string using base64
	 * 
	 * @param itemstack
	 *            The itemstack to turn in to a string
	 * @return The itemstack as a string
	 */
	public String toString(ItemStack itemstack) {
		return objectToString(itemstack);
	}

	/**
	 * Turns an object to string
	 * 
	 * @param object
	 *            The object to turn in to a string
	 * @return The object as a string
	 */
	public String objectToString(Object object) {
		try {
			ByteArrayOutputStream str = new ByteArrayOutputStream();
			BukkitObjectOutputStream data = new BukkitObjectOutputStream(str);
			data.writeObject(object);
			data.close();
			return Base64.getEncoder().encodeToString(str.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}