package core.utils;

import core.access.MainAccess;

/**
 * Contains all the keys
 * 
 * @author PhantomUnicorns
 */
public class MasterKeys extends MainAccess {

	protected static final String _OBJECT_SERILIZER_KEY;
	protected static final String _LORE_SEPARATOR;

	static {
		_OBJECT_SERILIZER_KEY = "<:>";
		_LORE_SEPARATOR = "<,>";
	}
}