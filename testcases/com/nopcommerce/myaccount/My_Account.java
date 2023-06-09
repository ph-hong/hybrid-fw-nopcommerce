package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import common.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.MyAccountPageObject;

public class My_Account extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	MyAccountPageObject myAccountPage;
	LoginPageObject loginPage;

	String email, password;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		email = "hpatt@live.com";
		password = "hpattest";

		loginPage = homePage.clickLoginLink();
		loginPage.inputEmail(email);
		loginPage.inputPassword(password);
		loginPage.clickLoginBtn();

		Assert.assertTrue(loginPage.isLogoutLinkDisplayed());

		myAccountPage = homePage.clickMyAccountLink();

	}
	
	@Test
	public void My_Account_TC01_Update_Customer_Info() {
		myAccountPage.clickCustomerInfo();
		myAccountPage.selectFemaleGender();
		myAccountPage.inputFirstName("UpFirst");
		myAccountPage.inputLastName("UpLast");
		myAccountPage.selectDropdownByName(driver, "DateOfBirthDay", "20");
		myAccountPage.selectDropdownByName(driver, "DateOfBirthMonth", "March");
		myAccountPage.selectDropdownByName(driver, "DateOfBirthYear", "1995");
		myAccountPage.inputCompanyName("UpCom");
		myAccountPage.clickSaveBtn();

		Assert.assertEquals(myAccountPage.getUpdateSuccessMsg(), "The customer info has been updated successfully.");

	}
	@Test
	public void My_Account_TC02_Add_New_Address() {
		myAccountPage.clickAddressesLink();
		myAccountPage.clickAddNewBtn();
		myAccountPage.inputAddressFirstName("Add01 FirstN");
		myAccountPage.inputAddressLastName("Add01 LastN");
		myAccountPage.inputAddressEmail("hptest01@live.com");
		myAccountPage.inputAddressCompany("Add01 Com");
		myAccountPage.selectDropdownByName(driver, "Address.CountryId", "United States");
		myAccountPage.selectDropdownByName(driver, "Address.StateProvinceId", "Tennessee");
		myAccountPage.inputCity("Auburntown");
		myAccountPage.inputAddress1("222/33 Auburntown");
		myAccountPage.inputAddress2("332 Auburntown");
		myAccountPage.inputZipPostalCode("37016");
		myAccountPage.inputPhoneNumer("9987656534");
		myAccountPage.inputFaxNumber("9987345432");
		myAccountPage.clickSaveAddressBtn("");

		Assert.assertEquals(myAccountPage.getAddedAddressSuccessMsg(), "The new address has been added successfully.");
	}




	@Test
	public void My_Account_TC03_Change_Password() {

	}

	@Test
	public void My_Account_TC04_My_Product_Review() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
