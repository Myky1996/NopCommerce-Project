package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.HompageUI;
import pageUIs.nopCommerce.ProductDetailPageUI;

public class ProductDetailPO extends BasePage {
	WebDriver driver;

	public ProductDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public AddYourReviewPO clickToAddYourReviewLinkByText(WebDriver driver) {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_YOUR_REVIEWLINK_BY_TEXT);
		clickToElement(driver, ProductDetailPageUI.ADD_YOUR_REVIEWLINK_BY_TEXT);
		return PageGeneratorManager.getAddYourReviewPageObject(driver);
	}
	
	public void sendkeyToQuantityTextbox(WebDriver driver, String quantity) {
		waitForElementClickable(driver, ProductDetailPageUI.PRODUCT_QUANTITY);
		sendKeyToElement(driver, ProductDetailPageUI.PRODUCT_QUANTITY, quantity);
	}

	public String getPriceValue(WebDriver driver) {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_PRICE);
		return getElementText(driver, ProductDetailPageUI.PRODUCT_PRICE);
	}
}
