package com.qcap.cac.tools;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Ognl {
	public static boolean isEmpty(Object o) throws IllegalArgumentException {
		if (o == null)
			return true;

		if ((o instanceof String)) {
            return ((String) o).length() == 0;
		} else if ((o instanceof Collection)) {
            return ((Collection) o).isEmpty();
		} else if (o.getClass().isArray()) {
            return Array.getLength(o) == 0;
		} else if ((o instanceof Map)) {
            return ((Map) o).isEmpty();
		} else {
			return false;
		}

    }

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	public static boolean isNotBlank(Object o) {
		return !isBlank(o);
	}

	public static boolean isNumber(Object o) {
		if (o == null)
			return false;
		if ((o instanceof Number)) {
			return true;
		}
		if ((o instanceof String)) {
			String str = (String) o;
			if (str.length() == 0)
				return false;
			if (str.trim().length() == 0)
				return false;
			return StringUtils.isNumeric(str);
		}
		return false;
	}

	public static boolean isBlank(Object o) {
		if (o == null)
			return true;
		if ((o instanceof String)) {
			String str = (String) o;
			return isBlank(str);
		}
		return false;
	}

	public static boolean isBlank(String str) {
		if ((str == null) || (str.length() == 0)) {
			return true;
		}

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean equalToOne(String str) {
		if ((str == null) || (str.length() == 0)) {
			return false;
		}

        return "1".equals(str);
    }

	public static boolean equalToTwo(String str) {
		if ((str == null) || (str.length() == 0)) {
			return false;
		}

        return "2".equals(str);
    }

	public static boolean equalToSeven(String str) {
		if ((str == null) || (str.length() == 0)) {
			return false;
		}

        return "7".equals(str);
    }

	public static boolean notEqualToSeven(String str) {
        return !"7".equals(str);
    }
}