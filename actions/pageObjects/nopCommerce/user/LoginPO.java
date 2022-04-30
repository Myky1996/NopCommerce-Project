package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.BaseTest;
import pageUIs.nopCommerce.LoginPageUI;

public class LoginPO extends BasePage {
	WebDriver driver;
	
	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getErrorMessageAtEmailTextbox(WebDriver driver) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE_BY_ID);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE_BY_ID);
	}
	public String getUnsuccesfullLoginMessage(WebDriver driver) {
		waitForElementVisible(driver, LoginPageUI.NOT_FOUND_ACCOUNT_MESSAGE);
		return getElementText(driver, LoginPageUI.NOT_FOUND_ACCOUNT_MESSAGE);
	}

}
