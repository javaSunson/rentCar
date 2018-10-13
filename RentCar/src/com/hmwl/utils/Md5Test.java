package com.hmwl.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Test {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		String res = Md5Tools.stringToMD5("888888");
		System.out.println(res);
	}
	
	
}
