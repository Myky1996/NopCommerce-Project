package com.nopCommerce.admin;

import java.util.Random;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import enviromentConfig.Enviroment;
import pageObjects.nopCommerce.admin.AddNewAddressPO;
import pageObjects.nopCommerce.admin.AddNewCustomerPO;
import pageObjects.nopCommerce.admin.CustomerDetailPO;
import pageObjects.nopCommerce.admin.CustomerListPO;
import pageObjects.nopCommerce.admin.HomepageAdminPO;
import pageObjects.nopCommerce.admin.LoginAdminPO;
import reportConfig.ExtentTestManager;

public class Module_02_Customer extends BaseTest {
	Enviroment enviroment;
	WebDriver driver;
	HomepageAdminPO homePage;
	LoginAdminPO adminLoginPage;
	CustomerListPO CustomerListPage;
	CustomerDetailPO CustomerDetailPage;
	AddNewCustomerPO AddNewCustomerPage;
	AddNewAddressPO AddNewAddressPage;
	String emailAddress, Password, Firstname, Lastname, gender, DOB, company, customerRole, adminComment;
	String editEmailAddress, editFirstname, editLastname, editDOB, editCompany, editAdminComment;
	String country, city, address1, address2, zipNumber, phoneNumber, faxNumber, editCountry, editState, editCity,
			editAddress1, editAddress2, editZipNumber, editPhoneNumber, editFaxNumber;

	@Parameters({ "envName", "severName", "browser", "ipAddress", "portNumber", "osName" })
	@BeforeClass
	public void BeforeClass(@Optional("local") String envName, @Optional("testing") String severName,
			@Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows 10") String osName) {
		ConfigFactory.setProperty("env", severName);
		enviroment = ConfigFactory.create(Enviroment.class);
		emailAddress = "automationfc" + getRandomNumber() + "@gmail.com";
		Password = "Automation123";
		Firstname = "Automation";
		Lastname = "FC";
		gender = "Male";
		DOB = "1/1/2000";
		company = "Automation Enterprise";
		customerRole = "Guests";
		adminComment = "Add new Customer [Guest]";

		editEmailAddress = "editautomationfc" + getRandomNumber() + "@gmail.com";

		editFirstname = "Edit Automation";
		editLastname = "Edit FC";
		editDOB = "2/2/2000";
		editCompany = "Edit Automation Enterprise";
		editAdminComment = "Edit Customer [Guest]";
		country = "Viet Nam";
		city = "Ho Chi Minh";
		address1 = "743 Le Loi";
		address2 = "453 Le Lai";
		zipNumber = "650000";
		phoneNumber = "0987654555";
		faxNumber = "+84987654555";
		editCountry = "United States";
		editState = "California";
		editCity = "Albany";
		editAddress1 = "123 PO Box";
		editAddress2 = "356 Los Bancos";
		editZipNumber = "986589";
		editPhoneNumber = "0987654666";
		editFaxNumber = "+4416199998888";

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '"
				+ enviroment.adminAppUrl() + "' ");

		driver = getBrowserDriver(envName, enviroment.adminAppUrl(), browserName, ipAddress, portNumber, osName);
		adminLoginPage = PageGeneratorManager.getLoginAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 02: Login with Admin account");
		adminLoginPage.enterTextToTextboxByName(driver, "Email", enviroment.adminAppname());
		adminLoginPage.enterTextToTextboxByName(driver, "Password", enviroment.adminAppPassword());
		adminLoginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.gethomepageAdmin(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 03: Open Customer page");
		homePage.openSideMenuPageByText(driver, "Customers");
		homePage.openSubSideMenuPageByText(driver, " Customers");
		CustomerListPage = PageGeneratorManager.getCustomerListPage(driver);

	}

	@Test
	public void TC_07_Create_New_Customer() {

		ExtentTestManager.getTest().log(Status.INFO, "TC_07 Step 01: Click 'Add new' button");
		CustomerListPage.clicktoLinkByText(driver, "Add new");

		AddNewCustomerPage = PageGeneratorManager.getAddCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_07 - Step 02: Enter info in fields");

		AddNewCustomerPage.clickToExtendButtonByCardName(driver, "Customer info");
		AddNewCustomerPage.enterTextToTextboxByName(driver, "Email", emailAddress);
		AddNewCustomerPage.enterTextToTextboxByName(driver, "Password", Password);
		AddNewCustomerPage.enterTextToTextboxByName(driver, "FirstName", Firstname);
		AddNewCustomerPage.enterTextToTextboxByName(driver, "LastName", Lastname);
		AddNewCustomerPage.clickToRadioButtonByLabel(driver, gender);
		AddNewCustomerPage.enterTextToTextboxByName(driver, "DateOfBirth", DOB);
		AddNewCustomerPage.enterTextToTextboxByName(driver, "Company", company);

		AddNewCustomerPage.selectOptionInTagList(driver, customerRole);
		AddNewCustomerPage.enterTextToTextAreaByName(driver, "AdminComment", adminComment);

		ExtentTestManager.getTest().log(Status.INFO, "TC_07 - Step 03: Click Save and Continue button");

		AddNewCustomerPage.clickToButtonByText(driver, "Save and Continue Edit");
		AddNewCustomerPage.sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, "TC_07 - Step 04: Verify customer is added successfully");

		verifyTrue(AddNewCustomerPage.isSuccessAlertDisplayedAdmin(driver,
				"The new customer has been added successfully."));
		verifyEquals(AddNewCustomerPage.getTextboxValueByName(driver, "Email"), emailAddress);
		verifyEquals(AddNewCustomerPage.getTextboxValueByName(driver, "FirstName"), Firstname);
		verifyEquals(AddNewCustomerPage.getTextboxValueByName(driver, "LastName"), Lastname);
		verifyTrue(AddNewCustomerPage.isRadiobuttonSelected(driver, gender));
		verifyEquals(AddNewCustomerPage.getTextboxValueByName(driver, "DateOfBirth"), DOB);
		verifyEquals(AddNewCustomerPage.getTextboxValueByName(driver, "Company"), company);
		verifyEquals(AddNewCustomerPage.getSelectedOptionInTagList(driver), customerRole);
		verifyTrue(AddNewCustomerPage.isCheckboxSelectedByID(driver, "Active"));
		verifyEquals(AddNewCustomerPage.getTextareaValueByName(driver, "AdminComment"), adminComment);

		ExtentTestManager.getTest().log(Status.INFO, "TC_07 - Step 05: Click Go back to customer list");

		AddNewCustomerPage.clicktoLinkByText(driver, "back to customer list");
		CustomerListPage = PageGeneratorManager.getCustomerListPage(driver);
		CustomerListPage.clickToExtendButtonSearchBox(driver);
		CustomerListPage.enterTextToTextboxByName(driver, "SearchFirstName", Firstname);
		CustomerListPage.selectOptionInTagList(driver, customerRole);
		CustomerListPage.clickToButtonByText(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "TC_07 - Step 06: Verify user info is displayed correctly at Customer List Page");
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Name", "1"), Firstname + " " + Lastname);
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Customer roles", "1"), customerRole);
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Company name", "1"), company);
		verifyTrue(CustomerListPage.ischeckIconDisplayed(driver,"customers-grid_wrapper", "Active", "1", "true"));

	}

	@Test
	public void TC_08_Search_Customer_With_Email() {
		ExtentTestManager.getTest().log(Status.INFO, "TC_08 - Step 01: Enter email address into search field");
		CustomerListPage.enterTextToTextboxByName(driver, "SearchEmail", emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "TC_08 - Step 02: Select guests tag");
		CustomerListPage.selectOptionInTagList(driver, "Guests");

		ExtentTestManager.getTest().log(Status.INFO, "TC_08 - Step 03: Click search button");
		CustomerListPage.clickToButtonByText(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "TC_08 - Step 04: Verify customer info");
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Name", "1"), Firstname + " " + Lastname);
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Customer roles", "1"), customerRole);
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Company name", "1"), company);
		verifyTrue(CustomerListPage.ischeckIconDisplayed(driver,"customers-grid_wrapper", "Active", "1", "true"));
	}

	@Test
	public void TC_09_Search_Customer_With_Firstname_and_Lastname() {
		ExtentTestManager.getTest().log(Status.INFO, "TC_09 - Step 01: Enter firstname into Firstname textbox");
		CustomerListPage.enterTextToTextboxByName(driver, "SearchFirstName", Firstname);

		ExtentTestManager.getTest().log(Status.INFO, "TC_09 - Step 02: Enter Lastname into Lastname textbox");
		CustomerListPage.enterTextToTextboxByName(driver, "SearchLastName", Lastname);

		ExtentTestManager.getTest().log(Status.INFO, "TC_09 - Step 03: Select guests tag");
		CustomerListPage.selectOptionInTagList(driver, "Guests");

		ExtentTestManager.getTest().log(Status.INFO, "TC_09 - Step 04: Click search button");
		CustomerListPage.clickToButtonByText(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "TC_09 - Step 05: Verify customer info");
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Name", "1"), Firstname + " " + Lastname);
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Customer roles", "1"), customerRole);
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Company name", "1"), company);
		verifyTrue(CustomerListPage.ischeckIconDisplayed(driver,"customers-grid_wrapper", "Active", "1", "true"));

	}

	@Test
	public void TC_10_Search_Customer_With_Company() {
		ExtentTestManager.getTest().log(Status.INFO, "TC_10 - Step 01: Enter text into Company textbox");
		CustomerListPage.enterTextToTextboxByName(driver, "SearchCompany", company);

		ExtentTestManager.getTest().log(Status.INFO, "TC_10 - Step 02: Select guests tag");
		CustomerListPage.selectOptionInTagList(driver, "Guests");

		ExtentTestManager.getTest().log(Status.INFO, "TC_10 - Step 03: Click search button");
		CustomerListPage.clickToButtonByText(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "TC_10 - Step 04: Verify customer info");
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Name", "1"), Firstname + " " + Lastname);
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Customer roles", "1"), customerRole);
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Company name", "1"), company);
		verifyTrue(CustomerListPage.ischeckIconDisplayed(driver, "customers-grid_wrapper", "Active", "1", "true"));
		CustomerListPage.refreshCurrentPage(driver);
	}

	@Test
	public void TC_11_Search_Customer_With_Full_Data() {
		ExtentTestManager.getTest().log(Status.INFO, "TC_11 - Step 01: Enter info into search fields");
		CustomerListPage.enterTextToTextboxByName(driver, "SearchEmail", emailAddress);
		CustomerListPage.enterTextToTextboxByName(driver, "SearchFirstName", Firstname);
		CustomerListPage.enterTextToTextboxByName(driver, "SearchLastName", Lastname);
		CustomerListPage.selectOptionInDropdownByText(driver, "SearchMonthOfBirth", "1");
		CustomerListPage.selectOptionInDropdownByText(driver, "SearchDayOfBirth", "1");
		CustomerListPage.enterTextToTextboxByName(driver, "SearchCompany", company);

		ExtentTestManager.getTest().log(Status.INFO, "TC_11 - Step 02: Select guests tag");
		CustomerListPage.selectOptionInTagList(driver, "Guests");

		ExtentTestManager.getTest().log(Status.INFO, "TC_11 - Step 03: Click search button");
		CustomerListPage.clickToButtonByText(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "TC_11 - Step 04: Verify customer info");
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Name", "1"), Firstname + " " + Lastname);
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Customer roles", "1"), customerRole);
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"customers-grid_wrapper", "Company name", "1"), company);
		verifyTrue(CustomerListPage.ischeckIconDisplayed(driver,"customers-grid_wrapper", "Active", "1", "true"));

	}

	@Test
	public void TC_12_Edit_Customer() {
		ExtentTestManager.getTest().log(Status.INFO, "TC_12 - Step 01: Click Edit link");
		CustomerListPage.clickToLinkButtonAtTableByRowIndex(driver, "Edit", "1");
		CustomerDetailPage = PageGeneratorManager.getCustomerDetailPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_12 - Step 02: Click Extend button");
		CustomerDetailPage.clickToExtendButtonByCardName(driver, "Customer info");

		ExtentTestManager.getTest().log(Status.INFO, "TC_12 - Step 03: Enter text into textbox");
		CustomerDetailPage.enterTextToTextboxByName(driver, "Email", editEmailAddress);
		CustomerDetailPage.enterTextToTextboxByName(driver, "FirstName", editFirstname);
		CustomerDetailPage.enterTextToTextboxByName(driver, "LastName", editLastname);
		CustomerDetailPage.enterTextToTextboxByName(driver, "DateOfBirth", editDOB);
		CustomerDetailPage.enterTextToTextboxByName(driver, "Company", editCompany);
		CustomerDetailPage.enterTextToTextAreaByName(driver, "AdminComment", editAdminComment);

		ExtentTestManager.getTest().log(Status.INFO, "TC_12 - Step 04: Click Save button");
		CustomerDetailPage.clickToButtonByText(driver, "Save");

		ExtentTestManager.getTest().log(Status.INFO, "TC_12 - Step 05: Verify success message display");
		verifyTrue(
				CustomerDetailPage.isSuccessAlertDisplayedAdmin(driver, "The customer has been updated successfully."));
		CustomerListPage = PageGeneratorManager.getCustomerListPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_12 - Step 06: Enter text into search fields");

		CustomerListPage.enterTextToTextboxByName(driver, "SearchEmail", editEmailAddress);
		CustomerListPage.enterTextToTextboxByName(driver, "SearchFirstName", editFirstname);
		CustomerListPage.enterTextToTextboxByName(driver, "SearchLastName", editLastname);
		CustomerListPage.selectOptionInDropdownByText(driver, "SearchMonthOfBirth", "2");
		CustomerListPage.selectOptionInDropdownByText(driver, "SearchDayOfBirth", "2");
		CustomerListPage.enterTextToTextboxByName(driver, "SearchCompany", editCompany);

		ExtentTestManager.getTest().log(Status.INFO, "TC_12 - Step 07: Select Guest tag");
		CustomerListPage.selectOptionInTagList(driver, "Guests");

		ExtentTestManager.getTest().log(Status.INFO, "TC_12 - Step 08: Click search button");
		CustomerListPage.clickToButtonByText(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "TC_12 - Step 09: Verify info displayed correctly");

		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,"customers-grid_wrapper", "Name", "1"),
				editFirstname + " " + editLastname);
		verifyEquals(
				CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,"customers-grid_wrapper", "Customer roles", "1"),
				customerRole);
		verifyEquals(CustomerListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,"customers-grid_wrapper", "Company name", "1"),
				editCompany);
		verifyTrue(CustomerListPage.ischeckIconDisplayed(driver,"customers-grid_wrapper", "Active", "1", "true"));
	}

	@Test
	public void TC_13_Add_New_Address_Customer_Detail() {

		ExtentTestManager.getTest().log(Status.INFO, "TC_13 - Step 01: Click Edit link");
		CustomerListPage.clickToLinkButtonAtTableByRowIndex(driver, "Edit", "1");
		CustomerDetailPage = PageGeneratorManager.getCustomerDetailPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_13 - Step 02: Click Extend button");
		CustomerDetailPage.clickToExtendButtonByCardName(driver, "Addresses");

		ExtentTestManager.getTest().log(Status.INFO, "TC_13 - Step 03: Click 'Add new address' button");
		CustomerDetailPage.clickToButtonByText(driver, "Add new address");
		AddNewAddressPage = PageGeneratorManager.getAddNewAddressPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_13 - Step 04: Enter info into textboxes");
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.FirstName", Firstname);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.LastName", Lastname);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.Email", emailAddress);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.Company", company);

		AddNewAddressPage.selectOptionInDropdownByText(driver, "Address.CountryId", country);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.City", city);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.Address1", address1);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.Address2", address2);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.ZipPostalCode", zipNumber);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.PhoneNumber", phoneNumber);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.FaxNumber", faxNumber);

		ExtentTestManager.getTest().log(Status.INFO, "TC_13 - Step 05: Click Save button");
		AddNewAddressPage.clickToButtonByText(driver, "Save");

		ExtentTestManager.getTest().log(Status.INFO, "TC_13 - Step 06: Verify success message displayed");
		verifyTrue(AddNewCustomerPage.isSuccessAlertDisplayedAdmin(driver,
				"The new address has been added successfully."));

		ExtentTestManager.getTest().log(Status.INFO, "TC_13 - Step 07: Click Back to customer details link");
		AddNewAddressPage.clicktoLinkByText(driver, "back to customer details");
		CustomerDetailPage = PageGeneratorManager.getCustomerDetailPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_13 - Step 08: Click extend button");
		CustomerDetailPage.clickToExtendButtonByCardName(driver, "Addresses");

		ExtentTestManager.getTest().log(Status.INFO, "TC_13 - Step 09: Verify info displayed correctly");
		verifyEquals(CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
				"First name", "1"), Firstname);
		verifyEquals(CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
				"Last name", "1"), Lastname);
		verifyEquals(CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
				"Email", "1"), emailAddress);
		verifyEquals(CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
				"Phone number", "1"), phoneNumber);
		verifyEquals(CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
				"Fax number", "1"), faxNumber);
		verifyEquals(
				CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
						"Address", "1"),
				company + "\n" + address1 + "\n" + address2 + "\n" + city + "," + zipNumber + "\n" + country);
	}

	@Test
	public void TC_14_Edit_Address_Customer_Detail() {
		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 01: Open Customer page");
		CustomerDetailPage.openSideMenuPageByText(driver, "Customers");
		CustomerDetailPage.openSubSideMenuPageByText(driver, " Customers");
		CustomerListPage = PageGeneratorManager.getCustomerListPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 02: Click Extend button");
		CustomerListPage.clickToExtendButtonSearchBox(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 03: Enter info into textboxes");
		CustomerListPage.enterTextToTextboxByName(driver, "SearchEmail", editEmailAddress);
		CustomerListPage.enterTextToTextboxByName(driver, "SearchFirstName", editFirstname);
		CustomerListPage.enterTextToTextboxByName(driver, "SearchLastName", editLastname);
		CustomerListPage.selectOptionInDropdownByText(driver, "SearchMonthOfBirth", "2");
		CustomerListPage.selectOptionInDropdownByText(driver, "SearchDayOfBirth", "2");
		CustomerListPage.enterTextToTextboxByName(driver, "SearchCompany", editCompany);
		CustomerListPage.sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 04: Select Guest tag");
		CustomerListPage.selectOptionInTagList(driver, "Guests");

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 05: Click search button");
		CustomerListPage.clickToButtonByText(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 06: Click Edit button");
		CustomerListPage.clickToLinkButtonAtTableByRowIndex(driver, "Edit", "1");
		CustomerDetailPage = PageGeneratorManager.getCustomerDetailPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 07: Click Extend button");
		CustomerDetailPage.clickToExtendButtonByCardName(driver, "Addresses");

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 08: Click Edit button");
		CustomerDetailPage.clickToLinkButtonAtTableByRowIndex(driver, "Edit", "1");

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 09: Enter text into textboxes");
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.FirstName", editFirstname);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.LastName", editLastname);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.Email", emailAddress);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.Company", editCompany);

		AddNewAddressPage.selectOptionInDropdownByText(driver, "Address.CountryId", editCountry);
		AddNewAddressPage.selectOptionInDropdownByText(driver, "Address.StateProvinceId", editState);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.City", editCity);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.Address1", editAddress1);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.Address2", editAddress2);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.ZipPostalCode", editZipNumber);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.PhoneNumber", editPhoneNumber);
		AddNewAddressPage.enterTextToTextboxByName(driver, "Address.FaxNumber", editFaxNumber);

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 10: Click Save button");
		AddNewAddressPage.clickToButtonByText(driver, "Save");

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 11: Verify success message displayed");
		verifyTrue(
				AddNewAddressPage.isSuccessAlertDisplayedAdmin(driver, "The address has been updated successfully."));

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 12: Click Go back to customer details link");
		AddNewAddressPage.clicktoLinkByText(driver, "back to customer details");
		CustomerDetailPage = PageGeneratorManager.getCustomerDetailPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 13: Click Extend button");
		CustomerDetailPage.clickToExtendButtonByCardName(driver, "Addresses");

		ExtentTestManager.getTest().log(Status.INFO, "TC_14 - Step 14: Verify info displayed correctly");
		verifyEquals(CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
				"First name", "1"), editFirstname);
		verifyEquals(CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
				"Last name", "1"), editLastname);
		verifyEquals(CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
				"Email", "1"), emailAddress);
		verifyEquals(CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
				"Phone number", "1"), editPhoneNumber);
		verifyEquals(CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
				"Fax number", "1"), editFaxNumber);
		verifyEquals(
				CustomerDetailPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver, "customer-address",
						"Address", "1"),
				editCompany + "\n" + editAddress1 + "\n" + editAddress2 + "\n" + editCity + "," + editState + ","
						+ editZipNumber + "\n" + editCountry);
	}

	@Test
	public void TC_15_Delete_Address_Detail() {

		ExtentTestManager.getTest().log(Status.INFO, "TC_15 - Step 01: Open Customer page");
		CustomerDetailPage.openSideMenuPageByText(driver, "Customers");
		CustomerDetailPage.openSubSideMenuPageByText(driver, " Customers");
		CustomerListPage = PageGeneratorManager.getCustomerListPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_15 - Step 02: Click search button");
		CustomerListPage.clickToExtendButtonSearchBox(driver);
		CustomerListPage.enterTextToTextboxByName(driver, "SearchEmail", editEmailAddress);
		CustomerListPage.enterTextToTextboxByName(driver, "SearchFirstName", editFirstname);
		CustomerListPage.enterTextToTextboxByName(driver, "SearchLastName", editLastname);
		CustomerListPage.selectOptionInDropdownByText(driver, "SearchMonthOfBirth", "2");
		CustomerListPage.selectOptionInDropdownByText(driver, "SearchDayOfBirth", "2");
		CustomerListPage.enterTextToTextboxByName(driver, "SearchCompany", editCompany);

		ExtentTestManager.getTest().log(Status.INFO, "TC_15 - Step 03: Select Guest tag");
		CustomerListPage.selectOptionInTagList(driver, "Guests");

		ExtentTestManager.getTest().log(Status.INFO, "TC_15 - Step 04: Click Search button");
		CustomerListPage.clickToButtonByText(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "TC_15 - Step 05: Click Edit button");
		CustomerListPage.clickToLinkButtonAtTableByRowIndex(driver, "Edit", "1");
		CustomerDetailPage = PageGeneratorManager.getCustomerDetailPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_15 - Step 06: Click Extend button");
		CustomerDetailPage.clickToExtendButtonByCardName(driver, "Addresses");

		ExtentTestManager.getTest().log(Status.INFO, "TC_15 - Step 07: Click Delete button");
		CustomerDetailPage.clickToLinkButtonAtTableByRowIndex(driver, "Delete", "1");

		ExtentTestManager.getTest().log(Status.INFO, "TC_15 - Step 08: Accept alert");
		CustomerDetailPage.acceptAlert(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC_15 - Step 09: Verify No data message in table");
		verifyEquals(CustomerDetailPage.getEmptyMsgAtTableByText(driver, "customer-address"),
				"No data available in table");
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
