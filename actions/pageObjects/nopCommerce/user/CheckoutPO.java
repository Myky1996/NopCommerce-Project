package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.AddYourReviewPageUI;
import pageUIs.nopCommerce.CheckoutPageUI;
import pageUIs.nopCommerce.HompageUI;

public class CheckoutPO extends BasePage {
	WebDriver driver;

	public CheckoutPO(WebDriver driver) {
		this.driver = driver;
	}

	public String getAddressValueByText(WebDriver driver, String address, String info) {
		waitForElementVisible(driver, CheckoutPageUI.SHIPPING_BILLING_ADDRESS_BY_TEXT, address, info);
		return getElementText(driver, CheckoutPageUI.SHIPPING_BILLING_ADDRESS_BY_TEXT, address, info);

	}

	public String getPaymentMethodType(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.PAYMENT_METHOD_BY_TEXT);
		return getElementText(driver, CheckoutPageUI.PAYMENT_METHOD_BY_TEXT);

	}

	public String getShippingMethodType(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.SHIPPING_METHOD_BY_TEXT);
		return getElementText(driver, CheckoutPageUI.SHIPPING_METHOD_BY_TEXT);

	}

	public String getOrderSuccessfullyMsg(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.ORDER_SUCCESSFULLY_MSG);
		return getElementText(driver, CheckoutPageUI.ORDER_SUCCESSFULLY_MSG);

	}

	public boolean IsOrderNumberDisplayed(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.ORDER_NUMBER);
		return isElementDisplayed(driver, CheckoutPageUI.ORDER_NUMBER);

	}

	public String getOrderNumber(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.ORDER_NUMBER);
		return getElementText(driver, CheckoutPageUI.ORDER_NUMBER);

	}

	public void clickToButtonAtCheckoutPage(WebDriver driver, String stepName, String buttonName) {
		waitForElementClickable(driver, CheckoutPageUI.BUTTON_BY_ID_AND_TEXT, stepName, buttonName);
		clickToElement(driver, CheckoutPageUI.BUTTON_BY_ID_AND_TEXT, stepName, buttonName);

	}

}
