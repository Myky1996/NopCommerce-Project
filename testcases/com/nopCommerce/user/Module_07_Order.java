package com.nopCommerce.user;

import java.util.Random;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopCommerce.common.Common_01_Register_to_system;

import commons.BaseTest;
import commons.PageGeneratorManager;
import enviromentConfig.Enviroment;
import pageObjects.nopCommerce.user.AddToCartPO;
import pageObjects.nopCommerce.user.CheckoutPO;
import pageObjects.nopCommerce.user.HomePageObject;
import pageObjects.nopCommerce.user.LoginPO;
import pageObjects.nopCommerce.user.MyAccountPO;
import pageObjects.nopCommerce.user.OrderHistoryPO;
import pageObjects.nopCommerce.user.ProductDetailPO;
import pageObjects.nopCommerce.user.Product_CatergoryListPO;
import pageObjects.nopCommerce.user.TopMenuSubPagePO;
import pageObjects.nopCommerce.user.WishlistPO;

public class Module_07_Order extends BaseTest {
	Enviroment enviroment;
	WebDriver driver;
	HomePageObject homePage;
	LoginPO loginPage;
	TopMenuSubPagePO topMenuSubPage;
	MyAccountPO myAccountPage;
	ProductDetailPO productDetailPage;
	WishlistPO wishlistPage;
	AddToCartPO addToCartPage;
	Product_CatergoryListPO Product_CatergoryListPage;
	CheckoutPO checkoutPage;
	OrderHistoryPO OrderHistoryPage;

	String password, emailAddress, country1, zipcode, phoneNumber, address1, city, country2, state2, firstName,
			lastName, userName;
	String SKU, productName, price, Quantity, Total, cardNumber;

	@Parameters({ "envName", "severName", "browser", "ipAddress", "portNumber", "osName" })
	@BeforeClass
	public void BeforeClass(@Optional("local") String envName, @Optional("testing") String severName,
			@Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows 10") String osName) {
		ConfigFactory.setProperty("env", severName);
		enviroment = ConfigFactory.create(Enviroment.class);

		country1 = "Viet Nam";
		zipcode = "55000";
		phoneNumber = "01234567899";
		address1 = "100 Trung Hoa Street";
		city = "Hanoi";
		country2 = "United States";
		state2 = "Alabama";
		cardNumber = "4242424242424242";
		SKU = "AP_MBP_13";
		productName = "Apple MacBook Pro 13-inch";
		price = "$1,800.00";
		Quantity = "2";
		Total = "$3,600.00";

		enviroment = ConfigFactory.create(Enviroment.class);
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + enviroment.userAppUrl()
				+ "' ");

		emailAddress = Common_01_Register_to_system.emailAddress;
		password = Common_01_Register_to_system.password;
		firstName = Common_01_Register_to_system.firstName;
		lastName = Common_01_Register_to_system.lastName;
		userName = Common_01_Register_to_system.firstName + Common_01_Register_to_system.lastName;

		driver = getBrowserDriver(envName, enviroment.userAppUrl(), browserName, ipAddress, portNumber, osName);
		homePage = PageGeneratorManager.getHompageObject(driver);

		log.info("Pre-condition - Step 02: Open 'Login' page");
		homePage.openHeaderFooterPageByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPageObject(driver);

		log.info("Pre-condition - Step 03: Set cookies and reload page");
		loginPage.setCookies(driver, Common_01_Register_to_system.loginPageCookie);

		loginPage.refreshCurrentPage(driver);
		homePage = PageGeneratorManager.getHompageObject(driver);
		loginPage.closeNotiBarByText(driver);

		log.info("Pre-condition - Step 04: Go to product sub category page");
		homePage.openTopMenuSubPageByText(driver, "Computers", "Desktops");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Pre-condition - Step 05: Open a product detail page");
		Product_CatergoryListPage.clickToProductTitleByText(driver, "Build your own computer");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

	}

	@Test
	public void TC_01_Add_To_Wishlist() {
		log.info("Add to cart - Step 01: Select processor");
		productDetailPage.selectOptionInDropdownByText(driver, "product_attribute_1",
				"2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]");

		log.info("Add to cart - Step 02: Select RAM");
		productDetailPage.selectOptionInDropdownByText(driver, "product_attribute_2", "8GB [+$60.00]");

		log.info("Add to cart - Step 02: Check to HDD radio button");
		productDetailPage.clickToRadioButtonByLabel(driver, "400 GB [+$100.00]");

		log.info("Add to cart - Step 02: Select OS");
		productDetailPage.clickToRadioButtonByLabel(driver, "Vista Premium [+$60.00]");

		log.info("Add to cart - Step 02: Select Software");
		productDetailPage.checkToCheckboxByLabel(driver, "Microsoft Office [+$50.00]");
		productDetailPage.checkToCheckboxByLabel(driver, "Acrobat Reader [+$10.00]");
		productDetailPage.checkToCheckboxByLabel(driver, "Total Commander [+$5.00]");

		log.info("Add to cart - Step 02: Click to 'Add to cart' button");
		productDetailPage.clickToButtonByText(driver, "Add to cart");

		log.info("Add to cart - Step 0: verify 'Add to cart' success message");
		verifyEquals(productDetailPage.getSuccessMsgOnNotiBarByClass(driver, "bar-notification success"),
				"The product has been added to your shopping cart");

		productDetailPage.closeNotiBarByText(driver);

		log.info("Add to cart - Step 0: verify product info at shopping cart page");
		productDetailPage.hoverShoppingCartTooltip(driver);

		verifyEquals(productDetailPage.getCartQuantity(driver), "(1)");
		verifyEquals(productDetailPage.getProductQuantityInTooltip(driver), "There are 1 item(s) in your cart.");
		verifyEquals(productDetailPage.getProductNameInTooltip(driver), "Build your own computer");
		verifyEquals(productDetailPage.getProductInfoInTooltip(driver),
				"Processor: 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]\nRAM: 8GB [+$60.00]\nHDD: 400 GB [+$100.00]\nOS: Vista Premium [+$60.00]\nSoftware: Microsoft Office [+$50.00]"
						+ "\nSoftware: Acrobat Reader [+$10.00]\nSoftware: Total Commander [+$5.00]");
		verifyEquals(productDetailPage.getUnitPriceInTooltip(driver), "$1,500.00");
		verifyEquals(productDetailPage.getQuantityPerItem(driver), "1");
		verifyEquals(productDetailPage.getSubTotalPrice(driver), "Sub-Total: $1,500.00");
	}

	@Test
	public void TC_02_Edit_Product_In_Shopping_Cart() {
		productDetailPage.openHeaderFooterPageByText(driver, "Shopping cart");
		productDetailPage.clickToLinkAtTableByRowIndex(driver, "Product(s)", "1");

		log.info("Add to cart - Step 01: Edit processor");
		productDetailPage.selectOptionInDropdownByText(driver, "product_attribute_1",
				"2.2 GHz Intel Pentium Dual-Core E2200");

		log.info("Add to cart - Step 02: Edit RAM");
		productDetailPage.selectOptionInDropdownByText(driver, "product_attribute_2", "4GB [+$20.00]");

		log.info("Add to cart - Step 02: Edit HDD radio button");
		productDetailPage.clickToRadioButtonByLabel(driver, "320 GB");

		log.info("Add to cart - Step 02: Edit OS");
		productDetailPage.clickToRadioButtonByLabel(driver, "Vista Home [+$50.00]");

		log.info("Add to cart - Step 02: Edit Software");
		productDetailPage.uncheckToCheckboxByLabel(driver, "Acrobat Reader [+$10.00]");
		productDetailPage.uncheckToCheckboxByLabel(driver, "Total Commander [+$5.00]");

		log.info("Add to cart - Step 02: Edit Number of items");
		productDetailPage.sendkeyToQuantityTextbox(driver, "2");

		log.info("Add to cart - Step 02: Verify price = $1320");
		verifyEquals(productDetailPage.getPriceValue(driver), "$1,320.00");

		productDetailPage.clickToButtonByText(driver, "Update");

		log.info("Add to cart - Step 0: verify 'Add to cart' success message");
		verifyEquals(productDetailPage.getSuccessMsgOnNotiBarByClass(driver, "bar-notification success"),
				"The product has been added to your shopping cart");
		productDetailPage.closeNotiBarByText(driver);

		log.info("Add to cart - Step 0: verify updated info at shopping cart page");
		productDetailPage.hoverShoppingCartTooltip(driver);

		verifyEquals(productDetailPage.getCartQuantity(driver), "(2)");
		verifyEquals(productDetailPage.getProductQuantityInTooltip(driver), "There are 2 item(s) in your cart.");
		verifyEquals(productDetailPage.getProductNameInTooltip(driver), "Build your own computer");
		verifyEquals(productDetailPage.getProductInfoInTooltip(driver),
				"Processor: 2.2 GHz Intel Pentium Dual-Core E2200\nRAM: 4GB [+$20.00]\nHDD: 320 GB\nOS: Vista Home [+$50.00]\nSoftware: Microsoft Office [+$50.00]");
		verifyEquals(productDetailPage.getUnitPriceInTooltip(driver), "$1,320.00");
		verifyEquals(productDetailPage.getQuantityPerItem(driver), "2");
		verifyEquals(productDetailPage.getSubTotalPrice(driver), "Sub-Total: $2,640.00");
	}

	@Test
	public void TC_03_Remove_Product_From_Cart() {
		productDetailPage.openHeaderFooterPageByText(driver, "Shopping cart");
		productDetailPage.clickToButtonxAtTableByRowIndex(driver, "Remove", "1");
		verifyEquals(productDetailPage.getEmptyMessageValue(driver), "Your Shopping Cart is empty!");
	}

//	@Test
	public void TC_04_Update_Shopping_Cart() {
		log.info("Pre-condition - Step 04: Go to product sub category page");
		homePage.openTopMenuSubPageByText(driver, "Computers", "Desktops");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Pre-condition - Step 05: Open a product detail page");
		Product_CatergoryListPage.clickToProductTitleByText(driver, "Lenovo IdeaCentre 600 All-in-One PC");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);
		productDetailPage.clickToButtonByText(driver, "Add to cart");
		productDetailPage.openHeaderFooterPageByText(driver, "Shopping cart");
		productDetailPage.enterTextInTableByRowIndex(driver, "Qty.", "1", "5");
		Product_CatergoryListPage.clickToButtonByText(driver, "Update shopping cart");

		verifyEquals(productDetailPage.getValueInTableAtColumnTableAndRowIndex(driver, "Total", "1"), "$2,500.00");
		productDetailPage.clickToButtonxAtTableByRowIndex(driver, "Remove", "1");
	}

	@Test
	public void TC_05_Check_out() {
		log.info("Pre-condition - Step 04: Go to product sub category page");
		productDetailPage.openTopMenuSubPageByText(driver, "Computers", "Notebooks");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Pre-condition - Step 05: Open a product detail page");
		Product_CatergoryListPage.clickToProductTitleByText(driver, "Apple MacBook Pro 13-inch");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

		log.info("Pre-condition - Step 05: Click to Add to cart button ");
		productDetailPage.clickToButtonByText(driver, "Add to cart");

		log.info("Pre-condition - Step 05: Open Shopping cart ");
		productDetailPage.openHeaderFooterPageByText(driver, "Shopping cart");

		log.info("Pre-condition - Step 05: Open Shopping cart ");
		productDetailPage.clicktoLinkByText(driver, " Estimate shipping ");

		log.info("Pre-condition - Step 05: Select Country ");
		productDetailPage.selectOptionInDropdownByText(driver, "CountryId", "Viet Nam");

		log.info("Pre-condition - Step 05: Enter zipcode ");
		productDetailPage.enterTextToTextboxByName(driver, "ZipPostalCode", "55000");
		productDetailPage.sleepInSecond(3);
		log.info("Pre-condition - Step 05: Click Apply button");
		productDetailPage.clickToButtonByText(driver, "Apply");

		log.info("Pre-condition - Step 05: Check to agree to service term checkbox ");
		productDetailPage.checkToCheckboxByLabel(driver,
				"I agree with the terms of service and I adhere to them unconditionally");

		log.info("Pre-condition - Step 05: Click Checkout button ");
		productDetailPage.clickToButtonByText(driver, " Checkout ");
		checkoutPage = PageGeneratorManager.getCheckoutPage(driver);

		log.info("Pre-condition - Step 05: Enter info in Billing Address ");
		checkoutPage.uncheckToCheckboxByLabel(driver, "Ship to the same address");
		checkoutPage.enterTextToTextboxByName(driver, "BillingNewAddress.FirstName", firstName);
		checkoutPage.enterTextToTextboxByName(driver, "BillingNewAddress.LastName", lastName);
		checkoutPage.enterTextToTextboxByName(driver, "BillingNewAddress.Email", emailAddress);
		checkoutPage.sleepInSecond(3);
		checkoutPage.selectOptionInDropdownByText(driver, "BillingNewAddress.CountryId", country1);
		checkoutPage.enterTextToTextboxByName(driver, "BillingNewAddress.City", city);
		checkoutPage.enterTextToTextboxByName(driver, "BillingNewAddress.Address1", address1);
		checkoutPage.enterTextToTextboxByName(driver, "BillingNewAddress.ZipPostalCode", zipcode);
		checkoutPage.enterTextToTextboxByName(driver, "BillingNewAddress.PhoneNumber", phoneNumber);
		checkoutPage.clickToButtonAtCheckoutPage(driver, "opc-billing", "Continue");

		log.info("Pre-condition - Step 05: Enter info in Shipping Address ");
		checkoutPage.selectOptionInDropdownByText(driver, "shipping_address_id", "New Address");
		checkoutPage.sleepInSecond(3);

		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.FirstName", firstName);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.LastName", lastName);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.Email", emailAddress);
		checkoutPage.selectOptionInDropdownByText(driver, "ShippingNewAddress.CountryId", country2);
		checkoutPage.selectOptionInDropdownByText(driver, "ShippingNewAddress.StateProvinceId", state2);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.City", city);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.Address1", address1);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.ZipPostalCode", zipcode);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.PhoneNumber", phoneNumber);
		checkoutPage.clickToButtonAtCheckoutPage(driver, "opc-shipping", "Continue");

		log.info("Pre-condition - Step 05: Select shipping method ");
		checkoutPage.clickToButtonAtCheckoutPage(driver, "opc-shipping_method", "Continue");

		log.info("Pre-condition - Step 05: Select payment method ");
		checkoutPage.clickToButtonAtCheckoutPage(driver, "opc-payment_method", "Continue");

		log.info("Pre-condition - Step 05: Verify payment info ");
		checkoutPage.clickToButtonAtCheckoutPage(driver, "opc-payment_info", "Continue");

		log.info("Pre-condition - Step 05: Verify Billing address info");
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "name"), firstName + " " + lastName);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "email"), "Email: " + emailAddress);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "phone"), "Phone: " + phoneNumber);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "address1"), address1);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "city-state-zip"),
				city + "," + zipcode);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "country"), country1);

		log.info("Pre-condition - Step 05: Verify Shipping address info");
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "name"), userName);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "email"), "Email: " + emailAddress);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "phone"), "Phone: " + phoneNumber);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "address1"), address1);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "city-state-zip"),
				city + "," + state2 + "," + zipcode);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "country"), country2);

		log.info("Pre-condition - Step 05: Verify Payment method");
		verifyEquals(checkoutPage.getPaymentMethodType(driver), "Check / Money Order");

		log.info("Pre-condition - Step 05: Verify Shipping method");
		verifyEquals(checkoutPage.getShippingMethodType(driver), "Ground");
		verifyEquals(checkoutPage.getValueInTableAtColumnTableAndRowIndex(driver, "SKU", "1"), SKU);
		verifyEquals(checkoutPage.getValueInTableAtColumnTableAndRowIndex(driver, "Product(s)", "1"), productName);
		verifyEquals(checkoutPage.getValueInTableAtColumnTableAndRowIndex(driver, "Price", "1"), price);
		verifyEquals(checkoutPage.getValueInTableAtColumnTableAndRowIndex(driver, "Qty.", "1"), Quantity);
		verifyEquals(checkoutPage.getValueInTableAtColumnTableAndRowIndex(driver, "Total", "1"), Total);
		verifyEquals(checkoutPage.getGiftWrappingOptionInOrderDetail(driver), "Gift wrapping: No");

		verifyEquals(checkoutPage.getOrderSummaryInfo(driver, "order-subtotal"), Total);
		verifyEquals(checkoutPage.getOrderSummaryInfo(driver, "shipping-cost"), "$0.00");
		verifyEquals(checkoutPage.getOrderSummaryInfo(driver, "tax-value"), "$0.00");
		verifyEquals(checkoutPage.getOrderSummaryInfo(driver, "order-total"), Total);

		log.info("Pre-condition - Step 05: click Confirm button ");
		checkoutPage.clickToButtonByText(driver, "Confirm");

		verifyEquals(checkoutPage.getOrderSuccessfullyMsg(driver), "Your order has been successfully processed!");
		verifyTrue(checkoutPage.IsOrderNumberDisplayed(driver));
		String[] orderNumberSplit = checkoutPage.getOrderNumber(driver).split(": ", 2);
		String orderNumber = orderNumberSplit[1];

		log.info("Pre-condition - Step 05: open My account page ");
		checkoutPage.openHeaderFooterPageByText(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPageObject(driver);
		myAccountPage.openSidePageByText(driver, "Orders");
		OrderHistoryPage = PageGeneratorManager.getOrderHistoryPage(driver);

		verifyEquals(OrderHistoryPage.getOrderNumber(driver), "Order Number: " + orderNumber);
		OrderHistoryPage.clickToDetailOrderByText(driver, orderNumber);
	}

	@Test
	public void TC_06_Check_out_Payment_With_Card() {
		log.info("Pre-condition - Step 04: Go to product sub category page");
		productDetailPage.openTopMenuSubPageByText(driver, "Computers", "Notebooks");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Pre-condition - Step 05: Open a product detail page");
		Product_CatergoryListPage.clickToProductTitleByText(driver, "Apple MacBook Pro 13-inch");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

		log.info("Pre-condition - Step 05: Click to Add to cart button ");
		productDetailPage.clickToButtonByText(driver, "Add to cart");

		log.info("Pre-condition - Step 05: Open Shopping cart ");
		productDetailPage.openHeaderFooterPageByText(driver, "Shopping cart");

		log.info("Pre-condition - Step 05: Open Shopping cart ");
		productDetailPage.clicktoLinkByText(driver, " Estimate shipping ");

		log.info("Pre-condition - Step 05: Select Country ");
		productDetailPage.selectOptionInDropdownByText(driver, "CountryId", "Viet Nam");

		log.info("Pre-condition - Step 05: Enter zipcode ");
		productDetailPage.enterTextToTextboxByName(driver, "ZipPostalCode", "55000");
		productDetailPage.sleepInSecond(3);
		log.info("Pre-condition - Step 05: Click Apply button");
		productDetailPage.clickToButtonByText(driver, "Apply");

		log.info("Pre-condition - Step 05: Check to agree to service term checkbox ");
		productDetailPage.checkToCheckboxByLabel(driver,
				"I agree with the terms of service and I adhere to them unconditionally");

		log.info("Pre-condition - Step 05: Click Checkout button ");
		productDetailPage.clickToButtonByText(driver, " Checkout ");
		checkoutPage = PageGeneratorManager.getCheckoutPage(driver);
		checkoutPage.uncheckToCheckboxByLabel(driver, "Ship to the same address");
		checkoutPage.clickToButtonAtCheckoutPage(driver, "opc-billing", "Continue");

		log.info("Pre-condition - Step 05: Enter info in Shipping Address ");
		checkoutPage.selectOptionInDropdownByText(driver, "shipping_address_id", "New Address");
		checkoutPage.sleepInSecond(3);

		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.FirstName", firstName);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.LastName", lastName);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.Email", emailAddress);
		checkoutPage.selectOptionInDropdownByText(driver, "ShippingNewAddress.CountryId", country2);
		checkoutPage.selectOptionInDropdownByText(driver, "ShippingNewAddress.StateProvinceId", state2);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.City", city);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.Address1", address1);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.ZipPostalCode", zipcode);
		checkoutPage.enterTextToTextboxByName(driver, "ShippingNewAddress.PhoneNumber", phoneNumber);
		checkoutPage.clickToButtonAtCheckoutPage(driver, "opc-shipping", "Continue");

		log.info("Pre-condition - Step 05: Select shipping method ");

		checkoutPage.clickToButtonAtCheckoutPage(driver, "opc-shipping_method", "Continue");

		log.info("Pre-condition - Step 05: Select payment method ");

		checkoutPage.clickToRadioButtonByLabel(driver, "Credit Card");
		checkoutPage.clickToButtonAtCheckoutPage(driver, "opc-payment_method", "Continue");

		log.info("Pre-condition - Step 05: Enter payment info ");
		checkoutPage.enterTextToTextboxByName(driver, "CardholderName", userName);
		checkoutPage.enterTextToTextboxByName(driver, "CardNumber", cardNumber);
		checkoutPage.selectOptionInDropdownByText(driver, "ExpireMonth", "08");
		checkoutPage.enterTextToTextboxByName(driver, "CardCode", "123");
		checkoutPage.clickToButtonAtCheckoutPage(driver, "opc-payment_info", "Continue");

		log.info("Pre-condition - Step 05: Verify Billing address info");
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "name"), firstName + " " + lastName);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "email"), "Email: " + emailAddress);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "phone"), "Phone: " + phoneNumber);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "address1"), address1);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "city-state-zip"),
				city + "," + zipcode);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "billing-info", "country"), country1);

		log.info("Pre-condition - Step 05: Verify Shipping address info");
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "name"), userName);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "email"), "Email: " + emailAddress);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "phone"), "Phone: " + phoneNumber);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "address1"), address1);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "city-state-zip"),
				city + "," + state2 + "," + zipcode);
		verifyEquals(checkoutPage.getAddressValueByText(driver, "shipping-info", "country"), country2);

		log.info("Pre-condition - Step 05: Verify Payment method");
		verifyEquals(checkoutPage.getPaymentMethodType(driver), "Credit Card");

		log.info("Pre-condition - Step 05: Verify Shipping method");
		verifyEquals(checkoutPage.getShippingMethodType(driver), "Ground");
		verifyEquals(checkoutPage.getValueInTableAtColumnTableAndRowIndex(driver, "SKU", "1"), SKU);
		verifyEquals(checkoutPage.getValueInTableAtColumnTableAndRowIndex(driver, "Product(s)", "1"), productName);
		verifyEquals(checkoutPage.getValueInTableAtColumnTableAndRowIndex(driver, "Price", "1"), price);
		verifyEquals(checkoutPage.getValueInTableAtColumnTableAndRowIndex(driver, "Qty.", "1"), Quantity);
		verifyEquals(checkoutPage.getValueInTableAtColumnTableAndRowIndex(driver, "Total", "1"), Total);
		verifyEquals(checkoutPage.getGiftWrappingOptionInOrderDetail(driver), "Gift wrapping: No");

		verifyEquals(checkoutPage.getOrderSummaryInfo(driver, "order-subtotal"), Total);
		verifyEquals(checkoutPage.getOrderSummaryInfo(driver, "shipping-cost"), "$0.00");
		verifyEquals(checkoutPage.getOrderSummaryInfo(driver, "tax-value"), "$0.00");
		verifyEquals(checkoutPage.getOrderSummaryInfo(driver, "order-total"), Total);

		log.info("Pre-condition - Step 05: click Confirm button ");
		checkoutPage.clickToButtonByText(driver, "Confirm");

		verifyEquals(checkoutPage.getOrderSuccessfullyMsg(driver), "Your order has been successfully processed!");
		verifyTrue(checkoutPage.IsOrderNumberDisplayed(driver));
		String[] orderNumberSplit = checkoutPage.getOrderNumber(driver).split(": ", 2);
		String orderNumber = orderNumberSplit[1];

		log.info("Pre-condition - Step 05: open My account page ");
		checkoutPage.openHeaderFooterPageByText(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPageObject(driver);
		myAccountPage.openSidePageByText(driver, "Orders");
		OrderHistoryPage = PageGeneratorManager.getOrderHistoryPage(driver);

		verifyEquals(OrderHistoryPage.getOrderNumber(driver), "Order Number: " + orderNumber);
		OrderHistoryPage.clickToDetailOrderByText(driver, orderNumber);

		log.info("Pre-condition - Step 05: open My account page ");
		checkoutPage.openHeaderFooterPageByText(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPageObject(driver);
		myAccountPage.openSidePageByText(driver, "Orders");
		OrderHistoryPage = PageGeneratorManager.getOrderHistoryPage(driver);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
