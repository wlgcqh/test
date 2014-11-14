package com.qiheng.util;

import java.io.ObjectInputStream.GetField;
import java.util.Iterator;
import java.util.Map;

/**
 * @author acer
 *
 */
/**
 * @author acer
 *
 */
/**
 * @author acer
 *
 */
/**
 * @author acer
 *
 */
/**
 * @author acer
 *
 */
public class CharacterUtil {
	
	public static final String ERROR="ERROR";
	public static final String SUCCESS="SUCCESS";
	
	public static  String CLIENT_NAME;
	public static  String SERVER_HOST;
	public static int RANDOMPORT1=generatePort();
	public static int RANDOMPORT2=generatePort();
	
	public static int PORT1=generatePort();
	public static int PORT2=generatePort();
	
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
	private static int generatePort(){
		int port=(int) (Math.random()*60000+1024);
		return port;
	}
	/**
	 * 判断是否重名,是返回true
	 */
	public static boolean isDuplicated(Map<String,String> map,String username){
		for(Iterator<String> iter=map.keySet().iterator();iter.hasNext();){
			String key=(String) iter.next();
			if(username.equals(key)){
				return true;
			}
		}
		return false;
	}
	
 }
