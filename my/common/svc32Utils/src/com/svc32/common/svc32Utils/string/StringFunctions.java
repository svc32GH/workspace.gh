package com.svc32.common.svc32Utils.string;

public class StringFunctions {
	
	public static String repeatStr(String str, int repeatNumb) {
		StringBuffer sb = new StringBuffer ("");
		for (int i = 1; i <= repeatNumb; i++) {
			sb.append(str);
		}
		return new String(sb);
	}
	
	public static String intArrayToString(int[] intArray, String separator) {
		String res = "";
		for (int i = 0; i < intArray.length; i++) {
			String iS = (new Integer(intArray[i])).toString();
			switch (i) {
			case 0:
				res = iS;
				break;

			default:
				res += separator + iS;
				break;
			}
		}
		return res;
	}

	public static String intArrayToString(int[] intArray) {
		String separator = " ";
		return intArrayToString(intArray, separator);
	}
}
