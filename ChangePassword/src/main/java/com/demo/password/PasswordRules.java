package com.demo.password;

import java.util.HashMap;
import java.util.Map;

public class PasswordRules {

	public static String special_chars=" !@#$&*";

	public static boolean verifyPasswordForRules(String oldPassword, String password_to_validate) {
		ChangePassword cp = new ChangePassword();
		boolean flag = false;
		if((password_to_validate.length()) < 18) {
			System.out.println("Password length should be greater than 18 characters");
			return flag;
		}else if(oldPassword.equals(password_to_validate)) {
			System.out.println("New password cannot match exactly to old password");
			return flag;
		}
		else if(verifyPasswordBasicRules(password_to_validate) &&
				verifyPasswordFourRepeatCharacters(password_to_validate) &&
				hasMoreThanFourSpclChars(password_to_validate) &&
				moreThan50percentDigit(password_to_validate) &&
				isEightyPercentOldPassword(cp.getOldPassword(), password_to_validate))
		{
			flag = true;
		}
		return flag;
	}

	public static boolean verifyPasswordBasicRules(String password_to_validate) {
		String isUppercasePattern="(?s).*[A-Z].*";
		String isLowercasePattern="(?s).*[a-z].*";
		String isDigitPattern="(?s).*[0-9].*";
		String isSpecialPattern="(?s).*[" + special_chars + "].*";
		if(!password_to_validate.matches(isUppercasePattern)) {
			System.out.println("Password should have atleast one uppercase character");
			return false;
		}
		if(!password_to_validate.matches(isLowercasePattern)) {
			System.out.println("Password should have atleast one lowercase character");
			return false;
		}
		if(!password_to_validate.matches(isDigitPattern)) {
			System.out.println("Password should have atleast one digit");
			return false;
		}
		if(!password_to_validate.matches(isSpecialPattern)) {
			System.out.println("Password should have atleast one of '!@#$&' special character");
			return false;
		}
		return true;
	}

	public static boolean verifyPasswordFourRepeatCharacters(String password) {
		int count=1, val =0;
		HashMap<Character, Integer> hmap = new HashMap<Character, Integer>();
		char[] ch = password.toLowerCase().toCharArray();
		for(int i=0; i< ch.length; i++) {
			if(!hmap.containsKey(ch[i])) {
				hmap.put(ch[i], count);
			}else {
				val = hmap.get(ch[i]) + 1;
				hmap.put(ch[i], val);
			}
		}
		for(Map.Entry<Character, Integer> entryset : hmap.entrySet()) {
			if(entryset.getValue() > 4) {
				System.out.println("No more than 4 duplicate characters allowed");
				return false;
			}
		}
		return true;
	}

	private static boolean hasMoreThanFourSpclChars(String s){
		int count = 0;
		for(int i=0;i<s.length(); i++) {
			if(special_chars.contains(Character.toString(s.charAt(i)))) {
				count++;
			}
		}
		if(count>4) {
			System.out.println("No more than 4 special characters are allowed");
			return false;
		}			
		else
			return true;
	}

	private static boolean moreThan50percentDigit(String s) {
		int len = (s.length()*50) / 100;
		int digit_length = s.replaceAll("\\D", "").length();
		if(digit_length < len) {
			return true;
		}else {
			System.out.println("Digits cannot be equal to or greater than 50% of password length");
			return false;}
	}

	private static boolean isEightyPercentOldPassword(String old, String newPass){
		char[] oldPassword = old.toCharArray();
		char[] newPassword = newPass.toCharArray();
		int subseqLength = checkSubsequence(oldPassword, newPassword, oldPassword.length, newPassword.length);
		int eightyPercentLen = (old.length()*80)/100;
		if(!(subseqLength < eightyPercentLen)) {
			System.out.println("New Password should not contain more than 80% of old password");
			return false;
		}
		return true;	 
	}

	private static int checkSubsequence(char[] oldPassword, char[] newPassword, int m, int n ) 
	{ 
		int L[][] = new int[m+1][n+1]; 

		for (int i=0; i<=m; i++) 
		{ 
			for (int j=0; j<=n; j++) 
			{ 
				if (i == 0 || j == 0) 
					L[i][j] = 0; 
				else if (oldPassword[i-1] == newPassword[j-1]) 
					L[i][j] = L[i-1][j-1] + 1; 
				else
					L[i][j] = max(L[i-1][j], L[i][j-1]); 
			} 
		} 
		return L[m][n]; 
	} 

	static int max(int a, int b) 
	{ 
		return (a > b)? a : b; 
	} 
}
