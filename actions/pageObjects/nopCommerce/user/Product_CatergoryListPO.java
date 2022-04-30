package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.BaseTest;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.LoginPageUI;
import pageUIs.nopCommerce.SearchPageUI;
import pageUIs.nopCommerce.WishlistPageUI;

public class Product_CatergoryListPO extends BasePage {
	WebDriver driver;

	public Product_CatergoryListPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToLinkShare(WebDriver driver) {
		waitForElementClickable(driver, WishlistPageUI.LINK_SHARE_BY_CLASS);
		clickToElement(driver, WishlistPageUI.LINK_SHARE_BY_CLASS);
	}
	public String getPersonalHeaderText(WebDriver driver) {
		waitForElementVisible(driver, WishlistPageUI.WISHLIST_PERSONAL_HEADER_BY_CLASS);
		return getElementText(driver, WishlistPageUI.WISHLIST_PERSONAL_HEADER_BY_CLASS);
	}

}
