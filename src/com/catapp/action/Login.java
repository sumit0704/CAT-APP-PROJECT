package com.catapp.action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class Login {
	
	public static final Logger logger = Logger.getLogger(Login.class.toString());
	
	
	public Boolean login(String username, String password) {
		Boolean isAuthenticated = false;
		String saltedPassword ="PWD"+password;
		String hashedPassword = generateHash(saltedPassword);

		String storedPasswordHash = "";
		if(hashedPassword.equals(storedPasswordHash)){
			isAuthenticated = true;
		}else{
			isAuthenticated = false;
		}
		return isAuthenticated;
	}

	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("Password Hashing Failed", e);
		}

		return hash.toString();
	}
	public void signup(String username, String password) {
		String saltedPassword = "PWD" + password;
		String hashedPassword = generateHash(saltedPassword);
		///Logic for save
	}
	
	public static void main(String [] args){
		String lPwd=generateHash("PWD"+"!v@n");
		String lPwd1="31d01e58afdaae8bfabc0156c0ba64cc590b37a6";
		
		logger.info(lPwd);
		logger.info(lPwd1);
	}
}
