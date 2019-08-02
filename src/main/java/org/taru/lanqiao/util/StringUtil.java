package org.taru.lanqiao.util;

public class StringUtil {
	public static String valueOf(Object obj) {
		if(obj == null) {
			return null;
		}
		return obj.toString();
	}
}
