package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.HompageUI;
import pageUIs.nopCommerce.RegisterPageUI;

public class registerPO extends BasePage {
	WebDriver driver;

	public registerPO(WebDriver driver) {
		this.driver = driver;
	}


	public String getEmptyErrorMessageText(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, RegisterPageUI.EMPTY_ERROR_MESSAGE, fieldID);
		return getElementText(driver, RegisterPageUI.EMPTY_ERROR_MESSAGE, fieldID);
	}
	
	public String getExistingEmailErrorMessageText(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}
	public String getInvalidPasswordMessage(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.INVALID_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.INVALID_PASSWORD_ERROR_MESSAGE);
	}
	public String getIncorrectConfirmPasswordMessage(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.INCORRECT_CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.INCORRECT_CONFIRM_PASSWORD_ERROR_MESSAGE);
	}
	
	public boolean isRegisterSuccessfullyMessageDisplayed() {
		waitForElementVisible(driver, HompageUI.REGISTER_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, HompageUI.REGISTER_SUCCESS_MESSAGE);
	}
}
