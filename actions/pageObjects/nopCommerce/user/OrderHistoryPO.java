package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.HompageUI;
import pageUIs.nopCommerce.OrderHistoryPageUI;

public class OrderHistoryPO extends BasePage {
	WebDriver driver;
	
	public OrderHistoryPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getOrderNumber(WebDriver driver) {
		waitForElementVisible(driver, OrderHistoryPageUI.ORDER_NUMBER);
		return getElementText(driver, OrderHistoryPageUI.ORDER_NUMBER);
	}


}
