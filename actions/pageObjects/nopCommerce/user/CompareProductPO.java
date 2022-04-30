package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.BaseTest;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.CompareProductPageUI;
import pageUIs.nopCommerce.LoginPageUI;
import pageUIs.nopCommerce.SearchPageUI;
import pageUIs.nopCommerce.WishlistPageUI;

public class CompareProductPO extends BasePage {
	WebDriver driver;

	public CompareProductPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductTitleDisplayed(WebDriver driver, String productName) {
		waitForElementVisible(driver, CompareProductPageUI.PRODUCT_NAME_BY_TEXT, productName);
		return isElementDisplayed(driver, CompareProductPageUI.PRODUCT_NAME_BY_TEXT, productName);
	}

	public boolean isProductPriceDisplayed(WebDriver driver, String productName) {
		waitForElementVisible(driver, CompareProductPageUI.PRODUCT_PRICE_BY_TEXT, productName);
		return isElementDisplayed(driver, CompareProductPageUI.PRODUCT_PRICE_BY_TEXT, productName);
	}

	public void clickToClearListButton(WebDriver driver) {
		waitForElementClickable(driver, CompareProductPageUI.CLEAR_LIST_BUTTON_BY_TEXT);
		clickToElement(driver, CompareProductPageUI.CLEAR_LIST_BUTTON_BY_TEXT);
	}

}
