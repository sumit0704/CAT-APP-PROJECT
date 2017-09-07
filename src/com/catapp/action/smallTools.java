package com.catapp.action;

public class smallTools {
	
	public static String safeString(Object o) {
		String resultString;
		if(o!=null){
			resultString =o.toString(); }
		else {resultString = "";
			System.out.println("null object.");
		}
		return resultString;
	}
}