package pageObjects.nopCommerce.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.BaseTest;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.LoginPageUI;
import pageUIs.nopCommerce.SearchPageUI;

public class SearchPO extends BasePage {
	WebDriver driver;

	public SearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterTextToSearchBox(WebDriver driver, String searchValue) {
		waitForElementVisible(driver, SearchPageUI.SEARCHBOX_BY_ID);
		sendKeyToElement(driver, SearchPageUI.SEARCHBOX_BY_ID, searchValue);
	}

	public String getValueWarningMsg(WebDriver driver) {
		waitForElementVisible(driver, SearchPageUI.WARNING_MESSAGE_BY_CLASS);
		return getElementText(driver, SearchPageUI.WARNING_MESSAGE_BY_CLASS);
	}

	public String getValueNoResultMsg(WebDriver driver) {
		waitForElementVisible(driver, SearchPageUI.NO_PRODUCTS_MESSAGE_BY_CLASS);
		return getElementText(driver, SearchPageUI.NO_PRODUCTS_MESSAGE_BY_CLASS);
	}
	public void clickToSearchButton(WebDriver driver) {
		waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON_BY_CLASS);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON_BY_CLASS);
	}

	
}
