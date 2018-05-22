package core.access;

import core.utils.ObjectReader;
import core.utils.ObjectSerilizer;

public class ObjectAccess extends MainAccess {

	protected static final ObjectSerilizer _OBJECT_SERILZER;
	protected static final ObjectReader _OBJECT_READER;

	static {
		_OBJECT_SERILZER = new ObjectSerilizer();
		_OBJECT_READER = new ObjectReader();
	}
}