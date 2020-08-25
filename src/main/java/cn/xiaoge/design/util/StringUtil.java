package cn.xiaoge.design.util;

public class StringUtil {
	private StringUtil(){
	}

	/**
	* 驼峰转下划线
	* @param camelCaseName 驼峰字符串
	* @return 下划线字符串
	*/
	public static String underscoreName(String camelCaseName) {
		StringBuilder result = new StringBuilder();
		if (camelCaseName != null && camelCaseName.length() > 0) {
			result.append(camelCaseName.substring(0, 1).toLowerCase());
			for (int i = 1; i < camelCaseName.length(); i++) {
				char ch = camelCaseName.charAt(i);
				if (Character.isUpperCase(ch)) {
					result.append("_");
					result.append(Character.toLowerCase(ch));
				} else {
					result.append(ch);
				}
			}
		}
		return result.toString();
	}

	/**
	* 下划线转驼峰
	* @param underscoreName 下划线字符串
	* @return 驼峰字符串
	*/
	public static String camelCaseName(String underscoreName) {
		StringBuilder result = new StringBuilder();
		if (underscoreName != null && underscoreName.length() > 0) {
			boolean flag = false;
			for (int i = 0; i < underscoreName.length(); i++) {
				char ch = underscoreName.charAt(i);
				if ("_".charAt(0) == ch) {
					flag = true;
				} else {
					if (flag) {
						result.append(Character.toUpperCase(ch));
						flag = false;
					} else {
						result.append(ch);
					}
				}
			}
		}
		return result.toString();
	}
 
}