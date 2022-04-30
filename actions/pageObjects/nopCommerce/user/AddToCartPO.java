package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.BaseTest;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.LoginPageUI;
import pageUIs.nopCommerce.SearchPageUI;

public class AddToCartPO extends BasePage {
	WebDriver driver;

	public AddToCartPO(WebDriver driver) {
		this.driver = driver;
	}

}
