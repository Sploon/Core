package core.utils;

public class MiscMethods {

	private static final char[] _INTEGERS;

	static {
		_INTEGERS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	}

	public static boolean isInteger(String input) {
		boolean decimal = false;
		for (char c : input.toCharArray()) {
			boolean isInteger = false;
			for (char integer : _INTEGERS) {
				if (c == integer) {
					isInteger = true;
					break;
				}
			}
			if (!isInteger && !decimal && c == '.') {
				decimal = true;
			} else if (isInteger) {
				return false;
			}
		}
		return true;
	}
}