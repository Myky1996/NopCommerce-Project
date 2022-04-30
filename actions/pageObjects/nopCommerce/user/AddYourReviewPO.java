package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.AddYourReviewPageUI;
import pageUIs.nopCommerce.HompageUI;

public class AddYourReviewPO extends BasePage {
	WebDriver driver;
	
	public AddYourReviewPO(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToRadioRatingButton(WebDriver driver, String score){
		waitForElementClickable(driver, AddYourReviewPageUI.RATING_RADIO_BUTTON_BY_ID, score);
		checkToDefaultCheckbox(driver, AddYourReviewPageUI.RATING_RADIO_BUTTON_BY_ID, score);
		
	}

}
