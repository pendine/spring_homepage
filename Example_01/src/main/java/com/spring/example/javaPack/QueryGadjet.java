package com.spring.example.javaPack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryGadjet {
	/*
	 * for delete special character method injection(input String, output String){
	 * delete special character = []{}()/\...etc }
	 */
	// 특수문자 없애기

	public static String injectionDetect(String input) {
		String output;
		if (input.length() > 0) {
			Pattern illegalPattern = Pattern.compile("[^A-Za-z0-9 ]");
			Matcher match = illegalPattern.matcher(input);
			output = match.replaceAll("");

		} else {
			output = "";
		}
		return output;
	}
	public static String textInjectionDetect(String input) {
		String output;
		if (input.length() > 0) {
			Pattern illegalPattern = Pattern.compile("[^A-Za-z0-9ㄱ-ㅎㅏ-ㅣ가-힣]");
			Matcher match = illegalPattern.matcher(input);
			output = match.replaceAll("");

		} else {
			output = "";
		}
		return output;
	}
}
