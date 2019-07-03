package com.demo.password;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ChangePassword {
	Properties prop;
	String configFile = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";

	public ChangePassword() {
		try {
			FileInputStream Locator = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");		
			prop = new Properties();
			prop.load(Locator);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean changePassword(String oldPassword, String newPassword)
	{ 
		if(verifyOldPassword(oldPassword)) 
		{ 
			if(PasswordRules.verifyPasswordForRules(oldPassword,newPassword)) {
				this.setOldPassword(newPassword);
				System.out.println("Password Changed Successfully");
				return true;
			}
		}else {
			System.out.println("Old password does not match the system database");
		}
		return false;
	}

	public boolean verifyOldPassword(String actualPassword) {
		if(this.getOldPassword().equals(actualPassword)) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setOldPassword(String newPassword) {
		try {
			FileOutputStream out = new FileOutputStream(configFile);
			prop.setProperty("userPassword", newPassword);
			prop.store(out, null);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getOldPassword() {
		return prop.getProperty("userPassword");
	}

}
