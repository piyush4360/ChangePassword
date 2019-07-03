package com.demo.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demo.password.ChangePassword;

public class ChangePassword_Test {
	ChangePassword cp;

	@BeforeMethod
	public void setup() {
		cp = new ChangePassword();
	}
	
	@AfterMethod
	public void tearDown() {
		cp.setOldPassword("CharlieTangoROmeo12#$");
	}

	@Test(description = "Change password valid test for more than 18 characters", priority = 0)
	public void verifyChangePassword_Valid01() {
		String oldPassword = cp.getOldPassword();
		String newPassword = "CharlieAlphaTango940!@#";
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertTrue(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), newPassword);
	}
	
	@Test(description = "Change password valid test for exact 18 characters", priority = 1)
	public void verifyChangePassword_Valid02() {
		String oldPassword = cp.getOldPassword();
		String newPassword = "changepassworD@!12";
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertTrue(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), newPassword);
	}

	@Test(description = "Change password invalid test for less than 18 characters", priority = 2)
	public void verifyChangePassword_InValid01() {
		String oldPassword = cp.getOldPassword();
		String newPassword = "changepassworD";
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertFalse(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), oldPassword);
	}
	
	@Test(description = "Change password invalid test for 1 uppercase character", priority = 3)
	public void verifyChangePassword_InValid02() {
		String oldPassword = cp.getOldPassword();
		String newPassword = "changepassword!@12309";
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertFalse(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), oldPassword);
	}
	
	@Test(description = "Change password invalid test for 1 lowercase character", priority = 4)
	public void verifyChangePassword_InValid03() {
		String oldPassword = cp.getOldPassword();
		String newPassword = "CHANGEPASSWORD!@12309";
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertFalse(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), oldPassword);
	}
	
	@Test(description = "Change password invalid test for 1 special character", priority = 5)
	public void verifyChangePassword_InValid04() {
		String oldPassword = cp.getOldPassword();
		String newPassword = "CHANGEPASSWORDsmall12309";
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertFalse(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), oldPassword);
	}
	
	@Test(description = "Change password invalid test for 1 digit in password", priority = 6)
	public void verifyChangePassword_InValid05() {
		String oldPassword = cp.getOldPassword();
		String newPassword = "CHANGEPASSWORDsmallchars#@";
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertFalse(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), oldPassword);
	}
	
	@Test(description = "Change password invalid test for no duplicate repeat character more than 4", priority = 7)
	public void verifyChangePassword_InValid06() {
		String oldPassword = cp.getOldPassword();
		String newPassword = "changePasswordaaa12$#"; // character 'a' is repeated 5 times
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertFalse(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), oldPassword);
	}
	
	@Test(description = "Change password invalid test for no more than 4 special characters", priority = 8)
	public void verifyChangePassword_InValid07() {
		String oldPassword = cp.getOldPassword();
		String newPassword = "changePassworda12$#@!*#"; // 5 special characters in string
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertFalse(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), oldPassword);
	}
	
	@Test(description = "Change password invalid test for password not contain more than 50% number", priority = 9)
	public void verifyChangePassword_InValid08() {
		String oldPassword = cp.getOldPassword();
		String newPassword = "changePas@1234567890"; // 10 digits and 10 non digits
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertFalse(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), oldPassword);
	}
	
	@Test(description = "Change password invalid test for new password not matching more than 80% of old password", priority = 10)
	public void verifyChangePassword_InValid09() {
		String oldPassword = cp.getOldPassword();
		String newPassword = "CharlieTangoROme19604%!@n"; // new password is > 80% match to old password 'CharlieTangoROme12#$'
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertFalse(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), oldPassword);
	}
	
	@Test(description = "Change password invalid test for new password exactly matching old password", priority = 11)
	public void verifyChangePassword_InValid10() {
		String oldPassword = cp.getOldPassword();
		String newPassword = cp.getOldPassword();
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertFalse(isPassChanged);
		Assert.assertEquals(cp.getOldPassword(), oldPassword);
	}
	
	@Test(description = "Change password invalid test for user entering wrong old password", priority = 12)
	public void verifyChangePassword_InValid11() {
		String oldPassword = cp.getOldPassword() + "xyz"; //Alter old password to be invalid
		String newPassword = "AlphaBetaGamma123$%!";
		boolean isPassChanged = cp.changePassword(oldPassword, newPassword);
		Assert.assertFalse(isPassChanged);
		Assert.assertNotSame(cp.getOldPassword(), oldPassword);
	}
}
