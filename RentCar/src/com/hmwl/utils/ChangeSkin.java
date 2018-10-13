package com.hmwl.utils;

public class ChangeSkin {
	private static String [] skins = {
			"/Img/bg.jpg","/Img/bu.jpg","/Img/sha.jpg"
	};
	private static int index = 0;
	public static String setSking() {
		if(index<skins.length) {
			return skins[index++];
		}else {
			return skins[index = 0];
		}
		
		
	}
	public static String getCurrSkin() {
		if(index!=0) {
			return skins[index-1];
		}else {
			return skins[index];
		}
		
	}
}
