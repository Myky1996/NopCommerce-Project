package com.nopCommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.AddToCartPO;
import pageObjects.nopCommerce.user.CompareProductPO;
import pageObjects.nopCommerce.user.HomePageObject;
import pageObjects.nopCommerce.user.LoginPO;
import pageObjects.nopCommerce.user.MyAccountPO;
import pageObjects.nopCommerce.user.ProductDetailPO;
import pageObjects.nopCommerce.user.Product_CatergoryListPO;
import pageObjects.nopCommerce.user.TopMenuSubPagePO;
import pageObjects.nopCommerce.user.WishlistPO;

public class Module_06_Wishlist_Compare_Recent_View extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	LoginPO loginPage;
	TopMenuSubPagePO topMenuSubPage;
	MyAccountPO myAccountPage;
	ProductDetailPO productDetailPage;
	WishlistPO wishlistPage;
	AddToCartPO addToCartPage;
	Product_CatergoryListPO Product_CatergoryListPage;
	CompareProductPO CompareProductPage;

	String password, existingEmail, newPassword, userName, SKU, productTitle, productPrice;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {

		existingEmail = "kinu@yopmail.com";
		password = "kinu@yopmail.com";
		newPassword = "buinhuhoa";
		userName = "my ky";
		SKU = "DS_VA3_PC";
		productTitle = "Digital Storm VANQUISH 3 Custom Performance PC";
		productPrice = "$1,259.00";
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "' ");

		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHompageObject(driver);

		log.info("Pre-condition - Step 02: Open 'Login' page");
		homePage.openHeaderFooterPageByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPageObject(driver);

		log.info("Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", existingEmail);

		log.info("Register - Step 02: Fill in password textbox");
		loginPage.enterTextToTextboxByName(driver, "Password", password);

		log.info("Register - Step 03: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");

		homePage = PageGeneratorManager.getHompageObject(driver);

	}

	@Test
	public void TC_01_Add_To_Wishlist() {

		log.info("Compare - Step 01: Go to product sub category page");
		homePage.openTopMenuSubPageByText(driver, "Computers", "Desktops");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Compare - Step 02: view product 1");
		Product_CatergoryListPage.clickToProductTitleByText(driver, "Digital Storm VANQUISH 3 Custom Performance PC");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

		log.info("Wishlist 01 - Step 02: click to Add to wishlist button");
		productDetailPage.clickToButtonByText(driver, "Add to wishlist");

		log.info("Wishlist 01 - Step 03: close noti bar");
		productDetailPage.closeSuccesNotiBarByText(driver);

		log.info("Wishlist 01 - Step 04: open Wishlist page");
		productDetailPage.openHeaderFooterPageByText(driver, "Wishlist");
		wishlistPage = PageGeneratorManager.getWishlistPage(driver);

		log.info("Wishlist 01 - Step 05: verify product is added succesfully");
		verifyEquals(wishlistPage.getValueInTableAtColumnTableAndRowIndex(driver, "SKU", "1"), SKU);
		verifyEquals(wishlistPage.getValueInTableAtColumnTableAndRowIndex(driver, "Product(s)", "1"),
				productTitle);
		verifyEquals(wishlistPage.getValueInTableAtColumnTableAndRowIndex(driver, "Price", "1"),
				productPrice);

		log.info("Wishlist 01 - Step 06: click to share link");
		wishlistPage.clickToLinkShare(driver);

		log.info("Wishlist 01 - Step 07: verify personal header displayed");
		verifyEquals(wishlistPage.getPersonalHeaderText(driver), "Wishlist of '" + userName + "'");
	}

	@Test
	public void TC_02_Add_To_Cart_From_WishList_Page() {

		log.info("Add to cart 01 - Step 01: open main wishlist page");
		wishlistPage.backToPage(driver);

		log.info("Add to cart 01 - Step 02: select item to add to cart");
		wishlistPage.checkToCheckboxAtTableByRowIndex(driver, "Add to cart", "1");

		log.info("Add to cart 01 - Step 03: click to Add to cart button");
		wishlistPage.clickToButtonByText(driver, "Add to cart");
		addToCartPage = PageGeneratorManager.getAddToCartPage(driver);

		log.info("Add to cart 01 - Step 05: verify product is added to Shopping cart");
		verifyEquals(addToCartPage.getValueInTableAtColumnTableAndRowIndex(driver, "SKU", "1"), SKU);
		verifyEquals(addToCartPage.getValueInTableAtColumnTableAndRowIndex(driver, "Product(s)", "1"),
				productTitle);
		verifyEquals(addToCartPage.getValueInTableAtColumnTableAndRowIndex(driver, "Price", "1"),
				productPrice);
		log.info("Add to cart 01 - Step 06: open wishlist page");
		wishlistPage.openHeaderFooterPageByText(driver, "Wishlist");

		wishlistPage = PageGeneratorManager.getWishlistPage(driver);
		log.info("Add to cart 01 - Step 07: verify product is removed from wishlist");
		verifyEquals(wishlistPage.getEmptyMessageValue(driver), "The wishlist is empty!");

	}

	@Test
	public void TC_03_Remove_Product_From_Wishlist_Page() {
		log.info("Compare - Step 01: Go to product sub category page");
		wishlistPage.openTopMenuSubPageByText(driver, "Computers", "Desktops");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Compare - Step 02: view product 1");
		Product_CatergoryListPage.clickToProductTitleByText(driver, "Digital Storm VANQUISH 3 Custom Performance PC");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

		log.info("Wishlist 01 - Step 02: click to Add to wishlist button");
		productDetailPage.clickToButtonByText(driver, "Add to wishlist");

		log.info("Wishlist 01 - Step 03: close noti bar");
		productDetailPage.closeSuccesNotiBarByText(driver);

		log.info("Wishlist 01 - Step 04: open Wishlist page");
		productDetailPage.openHeaderFooterPageByText(driver, "Wishlist");
		wishlistPage = PageGeneratorManager.getWishlistPage(driver);

		log.info("Add to cart 01 - Step 02: select item to remove");
		wishlistPage.clickToButtonxAtTableByRowIndex(driver, "Remove", "1");
		verifyTrue(wishlistPage.isjQueryAJAXLoadSucess(driver));

		log.info("Add to cart 01 - Step 07: verify product is removed from wishlist");
		verifyEquals(wishlistPage.getEmptyMessageValue(driver), "The wishlist is empty!");
		verifyTrue(wishlistPage.isjQueryAJAXLoadSucess(driver));

	}

	@Test
	public void TC_04_Add_Product_To_Compare() {

		log.info("Compare - Step 01: Go to product sub category page");
		wishlistPage.openTopMenuSubPageByText(driver, "Computers", "Desktops");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Compare - Step 02: click to Add to compare product 1");
		Product_CatergoryListPage.clickToButtonAtProductListByProductTitleAndButtonName(driver,
				"Digital Storm VANQUISH 3 Custom Performance PC", "Add to compare list");
		verifyTrue(Product_CatergoryListPage.isjQueryAJAXLoadSucess(driver));
		log.info("Compare - Step 03: verify success message");
		verifyEquals(
				Product_CatergoryListPage.getSuccessMsgOnNotiBarByClass(driver,
						"bar-notification success"),
				"The product has been added to your product comparison");

		log.info("Compare - Step 03: close noti bar");
		Product_CatergoryListPage.closeSuccesNotiBarByText(driver);

		log.info("Compare - Step 02: click to Add to compare product 2");
		Product_CatergoryListPage.clickToButtonAtProductListByProductTitleAndButtonName(driver,
				"Lenovo IdeaCentre 600 All-in-One PC", "Add to compare list");
		verifyTrue(Product_CatergoryListPage.isjQueryAJAXLoadSucess(driver));

		log.info("Compare - Step 03: verify success message");
		verifyEquals(
				Product_CatergoryListPage.getSuccessMsgOnNotiBarByClass(driver,
						"bar-notification success"),
				"The product has been added to your product comparison");

		log.info("Compare - Step 03: close noti bar");
		Product_CatergoryListPage.closeSuccesNotiBarByText(driver);

		log.info("Compare - Step 03: open Compare product list page");
		Product_CatergoryListPage.openHeaderFooterPageByText(driver, "Compare products list");
		CompareProductPage = PageGeneratorManager.getCompareProductPage(driver);

		log.info("Compare - Step 03: verify product 1 display");
		verifyTrue(CompareProductPage.isProductPriceDisplayed(driver, "$1,259.00"));
		verifyTrue(
				CompareProductPage.isProductTitleDisplayed(driver, "Digital Storm VANQUISH 3 Custom Performance PC"));

		log.info("Compare - Step 03: verify product 2 display");
		verifyTrue(CompareProductPage.isProductPriceDisplayed(driver, "$500.00"));
		verifyTrue(CompareProductPage.isProductTitleDisplayed(driver, "Lenovo IdeaCentre 600 All-in-One PC"));

		log.info("Compare - Step 03: Click 'Clear list' button");
		CompareProductPage.clickToClearListButton(driver);

		log.info("Compare - Step 03: verify empty data message");

		verifyEquals(CompareProductPage.getEmptyMessageValue(driver), "You have no items to compare.");

	}

	@Test
	public void TC_05_Recently_Viewed_Product() {

		log.info("Compare - Step 01: Go to product sub category page");
		CompareProductPage.openTopMenuSubPageByText(driver, "Computers", "Desktops");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Compare - Step 02: view product 1");
		Product_CatergoryListPage.clickToProductTitleByText(driver, "Build your own computer");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

		log.info("Compare - Step 01: Go to product sub category page");
		productDetailPage.openTopMenuSubPageByText(driver, "Computers", "Desktops");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Compare - Step 02: view product 2");
		Product_CatergoryListPage.clickToProductTitleByText(driver, "Digital Storm VANQUISH 3 Custom Performance PC");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

		log.info("Compare - Step 01: Go to product sub category page");
		productDetailPage.openTopMenuSubPageByText(driver, "Computers", "Desktops");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Compare - Step 02: view product 3");
		Product_CatergoryListPage.clickToProductTitleByText(driver, "Lenovo IdeaCentre 600 All-in-One PC");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

		log.info("Compare - Step 01: Go to product sub category page");
		productDetailPage.openTopMenuSubPageByText(driver, "Computers", "Notebooks");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Compare - Step 02: view product 1");
		Product_CatergoryListPage.clickToProductTitleByText(driver, "HP Envy 6-1180ca 15.6-Inch Sleekbook");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

		log.info("Compare - Step 01: Go to product sub category page");
		productDetailPage.openTopMenuSubPageByText(driver, "Computers", "Notebooks");
		Product_CatergoryListPage = PageGeneratorManager.getProduct_CatergoryListPage(driver);

		log.info("Compare - Step 02: view product 1");
		Product_CatergoryListPage.clickToProductTitleByText(driver, "Asus N551JK-XO076H Laptop");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

		log.info("Compare - Step 02: open Recently viewed products page");
		productDetailPage.openHeaderFooterPageByText(driver, "Recently viewed products");

		log.info("Compare - Step 02: verify 3 last products displayed");
		verifyTrue(productDetailPage.isNumberOfProductsCorrect(driver, 3));
		verifyTrue(productDetailPage.verifyProductTitleDisplay(driver, "Lenovo IdeaCentre 600 All-in-One PC"));
		verifyTrue(productDetailPage.verifyProductTitleDisplay(driver, "HP Envy 6-1180ca 15.6-Inch Sleekbook"));
		verifyTrue(productDetailPage.verifyProductTitleDisplay(driver, "Asus N551JK-XO076H Laptop"));
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
