package com.qiheng.util;

public class CharacterUtil {
	
	public static boolean isEmpty(String str) {
		if("".equals(str)){
			return false;
		}
		return true;
	}
	public static boolean isNumber(String str){
		for(int i=0;i<str.length();i++){
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	public static boolean isPort(String port){
		int temp=Integer.valueOf(port);
		if(temp>65535||temp<=1024){
			return false;
		}
		return true;
	}
}
