package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import pageUIs.nopCommerce.BasePageUI;

public class BasePage {

	public void openPagseUrl(WebDriver driver, String pageURL) {
		driver.get(pageURL);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
		sleepInSecond(2);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		waitForAlertPresence(driver).sendKeys(value);
	}

	public void switchWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowID = driver.getWindowHandles();
		for (String id : allWindowID) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}

	}

	public void switchWindowByTitle(WebDriver driver, String windowTitle) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for (String id : allWindowsID) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(windowTitle)) {
				break;
			}
		}
	}

	public void closeAllTabsWithoutParents(WebDriver driver, String parentID) {
		Set<String> allwindowsID = driver.getWindowHandles();
		for (String id : allwindowsID) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id).close();
			}
			driver.switchTo().window(parentID);

		}
	}

	public By getByLocator(String locatorType) {
		return By.xpath(locatorType);
	}

	public String getDynamicXpath(String locatorType, String... dynamicValues) {
		return locatorType = String.format(locatorType, (Object[]) dynamicValues);
	}

	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValue) {
		return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValue)));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValue) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).click();
	}

	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dyanmicValue) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dyanmicValue)), key).perform();
	}

	public void overrideImplicitTimeOut(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	public String getElementText(WebDriver driver, String locatorType, String... values) {
		return getWebElement(driver, getDynamicXpath(locatorType, values)).getText();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... values) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, values)));
		select.selectByVisibleText(textItem);
	}

	public String getSelectedItemInDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectedItemInDefaultDropdown(WebDriver driver, String locatorType, String... values) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, values)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	public void deselectAllInMultipleDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.deselectAll();
	}

	public void deselectAllInMultipleDropdown(WebDriver driver, String locatorType, String... values) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, values)));

		select.deselectAll();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXPath, String itemValue) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(2);
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		List<WebElement> list = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXPath)));
		for (WebElement item : list) {
			if (item.getText().trim().equals(itemValue)) {
				JavascriptExecutor jsexcutor = (JavascriptExecutor) driver;
				jsexcutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				item.click();
				break;
			}

		}
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... values) {
		return getWebElement(driver, getDynamicXpath(locatorType, values)).getAttribute(attributeName);
	}

	public String convertRgbaToHexa(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	public int getElementSize(WebDriver driver, String locatorType, String... values) {
		return getListWebElement(driver, getDynamicXpath(locatorType, values)).size();
	}

	public void checkToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckbox(WebDriver driver, String locatorType, String... values) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, values));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToDefaultCheckbox(WebDriver driver, String locatorType, String... values) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, values));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... values) {
		return getWebElement(driver, getDynamicXpath(locatorType, values)).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		List<WebElement> elements = getListWebElement(driver, locatorType);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType, String... values) {
		return getWebElement(driver, getDynamicXpath(locatorType, values)).isSelected();
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType, String... values) {
		return getWebElement(driver, getDynamicXpath(locatorType, values)).isEnabled();

	}

	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	public void switchToFrameIframe(WebDriver driver, String locatorType, String... values) {
		driver.switchTo().frame(getWebElement(driver, getDynamicXpath(locatorType, values)));
	}

	public void switchToDefaultContent(WebDriver driver, String locatorType) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType, String... values) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, values))).perform();
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	public void clickToElementByJS(WebDriver driver, String locatorType, String... values) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, values)));
	}

	public void scrollToBottomPage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		jsexecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void getElementValidationMsg(WebDriver driver, String locatorType) {
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		jsexecutor.executeScript(" return arguments[0].validationMassage;", getWebElement(driver, locatorType));
	}

	public void removerAttributeInDOM(WebDriver driver, String locatorType, String attributRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributRemove + "');",
				getWebElement(driver, locatorType));

	}

	public void removerAttributeInDOM(WebDriver driver, String locatorType, String attributRemove, String... values) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributRemove + "');",
				getWebElement(driver, getDynamicXpath(locatorType, values)));

	}

	public boolean isjQueryJaxLoadSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");

			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete "
				+ "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitForElementVisible(WebDriver driver, String locatorType, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, values))));
	}

	public void waitforAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	public void waitforAllElementVisible(WebDriver driver, String locatorType, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, values))));
	}

	public void waitforElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitforElementInvisible(WebDriver driver, String locatorType, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, values))));
	}

	public void waitForAllElementsInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));

	}

	public void waitForAllElementsInvisible(WebDriver driver, String locatorType, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, values))));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, values))));
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	// User
	public void openHeaderFooterPageByText(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.HEADER_FOOTER_PAGE_BY_TEXT, pageName);
		clickToElement(driver, BasePageUI.HEADER_FOOTER_PAGE_BY_TEXT, pageName);
	}

	public void clickToButtonByText(WebDriver driver, String buttonName) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_TEXT, buttonName);
		clickToElement(driver, BasePageUI.BUTTON_BY_TEXT, buttonName);
		sleepInSecond(2);
	}

	public void enterTextToTextboxByName(WebDriver driver, String textboxName, String textValue) {
		waitForElementClickable(driver, BasePageUI.TEXTBOX_FIELD_BY_NAME, textboxName);
		sendKeyToElement(driver, BasePageUI.TEXTBOX_FIELD_BY_NAME, textValue, textboxName);
	}

	public void enterTextToTextAreaByName(WebDriver driver, String textareaName, String textValue) {
		waitForElementClickable(driver, BasePageUI.TEXTAREA_FIELD_BY_NAME, textareaName);
		sendKeyToElement(driver, BasePageUI.TEXTAREA_FIELD_BY_NAME, textValue, textareaName);
	}

	public void clickToRadioButtonByLabel(WebDriver driver, String radioName) {
		waitForElementClickable(driver, BasePageUI.RADIO_BUTTON_BY_ID, radioName);
		checkToDefaultCheckbox(driver, BasePageUI.RADIO_BUTTON_BY_ID, radioName);
	}

	public void selectOptionInDropdownByText(WebDriver driver, String dropdownName, String selectValue) {
		// waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownName);
		selectItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_NAME, selectValue, dropdownName);
		sleepInSecond(2);

	}

	public String getSelectedOption(WebDriver driver, String dropdownName) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownName);
		return getSelectedItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownName);
	}

	public boolean isRadiobuttonSelected(WebDriver driver, String radioButton) {
		waitForElementVisible(driver, BasePageUI.RADIO_BUTTON_BY_ID, radioButton);
		return isElementSelected(driver, BasePageUI.RADIO_BUTTON_BY_ID, radioButton);
	}

	public void openSidePageByText(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.SIDE_PAGE_BY_TEXT, pageName);
		clickToElement(driver, BasePageUI.SIDE_PAGE_BY_TEXT, pageName);
	}

	public String getSuccessMsgOnNotiBarByClass(WebDriver driver, String alertCLass) {
		waitForElementVisible(driver, BasePageUI.SUCCESS_ALERT_BY_CLASS, alertCLass);
		return getElementText(driver, BasePageUI.SUCCESS_ALERT_BY_CLASS, alertCLass);
	}

	public void closeSuccesNotiBarByText(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CLOSE_SUCCESS_NOTI_BAR_BUTTON);
		clickToElement(driver, BasePageUI.CLOSE_SUCCESS_NOTI_BAR_BUTTON);
	}

	public void openTopMenuPageByText(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.TOP_MENU_PAGE_BY_TEXT);
		clickToElement(driver, BasePageUI.TOP_MENU_PAGE_BY_TEXT);
	}

	public void openTopMenuSubPageByText(WebDriver driver, String topMenuPageName, String topMenuSubpageName) {
		hoverMouseToElement(driver, BasePageUI.TOP_MENU_PAGE_BY_TEXT, topMenuPageName);
		waitForElementClickable(driver, BasePageUI.TOP_MENU_SUBPAGE_BY_TEXT, topMenuPageName, topMenuSubpageName);
		clickToElement(driver, BasePageUI.TOP_MENU_SUBPAGE_BY_TEXT, topMenuPageName, topMenuSubpageName);
	}

	public void clickToProductTitleByText(WebDriver driver, String productTitle) {
		waitForElementClickable(driver, BasePageUI.PRODUCT_TITLE_BY_TEXT, productTitle);
		clickToElement(driver, BasePageUI.PRODUCT_TITLE_BY_TEXT, productTitle);
	}

	public String getTextProductReviewByClass(WebDriver driver, String textboxName) {
		waitForElementVisible(driver, BasePageUI.DISPLAYED_REVIEW_INFO, textboxName);
		return getElementText(driver, BasePageUI.DISPLAYED_REVIEW_INFO, textboxName);
	}

	public void checkToCheckboxByLabel(WebDriver driver, String checkboxName) {
		waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_TEXT_LABEL, checkboxName);
		checkToDefaultCheckbox(driver, BasePageUI.CHECKBOX_BY_TEXT_LABEL, checkboxName);
	}

	public void uncheckToCheckboxByLabel(WebDriver driver, String checkboxName) {
		waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_TEXT_LABEL, checkboxName);
		unCheckToDefaultCheckbox(driver, BasePageUI.CHECKBOX_BY_TEXT_LABEL, checkboxName);
	}

	public String getValueInTableAtColumnTableAndRowIndex(WebDriver driver, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_NAME_BY_ACTION_AND_NAME, headerName) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_NAME_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_NAME_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex));
	}

	public void checkToCheckboxAtTableByRowIndex(WebDriver driver, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_NAME_BY_ACTION_AND_NAME, headerName) + 1;
		waitForElementClickable(driver, BasePageUI.TABLE_CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex));
		checkToDefaultCheckbox(driver, BasePageUI.TABLE_CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex));
	}

	public void clickToButtonxAtTableByRowIndex(WebDriver driver, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_NAME_BY_ACTION_AND_NAME, headerName) + 1;
		waitForElementClickable(driver, BasePageUI.TABLE_BUTTON_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex));
		clickToElement(driver, BasePageUI.TABLE_BUTTON_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex));
	}

	public void clickToLinkAtTableByRowIndex(WebDriver driver, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_NAME_BY_ACTION_AND_NAME, headerName) + 1;
		waitForElementClickable(driver, BasePageUI.TABLE_LINK_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex));
		clickToElement(driver, BasePageUI.TABLE_LINK_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex));
	}

	public void enterTextInTableByRowIndex(WebDriver driver, String headerName, String rowIndex, String textValue) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_NAME_BY_ACTION_AND_NAME, headerName) + 1;
		waitForElementClickable(driver, BasePageUI.TABLE_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex));
		sendKeyToElement(driver, BasePageUI.TABLE_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, textValue, rowIndex,
				String.valueOf(columnIndex));
	}

	public boolean ischeckIconDisplayed(WebDriver driver, String headerName, String rowIndex, String iconStatus) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_NAME_BY_ACTION_AND_NAME, headerName) + 1;
		waitForElementVisible(driver, BasePageUI.STATUS_ICON_AT_TABLE_ROW_NAME_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex), iconStatus);
		return isElementDisplayed(driver, BasePageUI.STATUS_ICON_AT_TABLE_ROW_NAME_BY_COLUMN_INDEX_AND_ROW_INDEX,
				rowIndex, String.valueOf(columnIndex), iconStatus);
	}

	public String getEmptyMessageValue(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.EMPTY_DATA_MESSAGE);
		return getElementText(driver, BasePageUI.EMPTY_DATA_MESSAGE);
	}

	public void clickToButtonAtProductListByProductTitleAndButtonName(WebDriver driver, String productTitle,
			String buttonName) {
		waitForElementClickable(driver, BasePageUI.BUTTONS_AT_PRODUCT_LIST_BY_TEXT, productTitle, buttonName);
		clickToElement(driver, BasePageUI.BUTTONS_AT_PRODUCT_LIST_BY_TEXT, productTitle, buttonName);
	}

	public boolean isNumberOfProductsCorrect(WebDriver driver, int itemNumber) {
		List<WebElement> elements = getListWebElement(driver, BasePageUI.SEARCH_RESULTS_BY_CLASS);
		if (elements.size() == itemNumber) {
			return true;
		} else if (elements.size() < itemNumber) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyProductTitleDisplay(WebDriver driver, String productTitle) {
		List<WebElement> elements = getListWebElement(driver, BasePageUI.SEARCH_RESULTS_BY_CLASS);
		boolean flag = false;
		for (WebElement item : elements) {
			if (item.getText().trim().contains(productTitle)) {
				flag = true;
			}
		}
		return flag;
	}

	public boolean isjQueryAJAXLoadSucess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public boolean isProductSortByAscending(WebDriver driver) {
		ArrayList<String> arrayList = new ArrayList<>();

		List<WebElement> elementList = getListWebElement(driver, BasePageUI.PRODUCT_NAME_SORT);

		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);
		return sortedList.equals(arrayList);

	}

	public boolean isProductSortedByDescending(WebDriver driver) {
		ArrayList<String> arrayList = new ArrayList<>();
		List<WebElement> elementList = getListWebElement(driver, BasePageUI.PRODUCT_NAME_SORT);
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		return sortedList.equals(arrayList);

	}

	public boolean isPriceSortLowToHigh(WebDriver driver) {
		ArrayList<Float> arrayList = new ArrayList<>();

		List<WebElement> elementList = getListWebElement(driver, BasePageUI.PRODUCT_PRICE_SORT);
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "")));

		}
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);
		return sortedList.equals(arrayList);
	}

	public boolean isPriceSortHighToLow(WebDriver driver) {
		ArrayList<Float> arrayList = new ArrayList<>();

		List<WebElement> elementList = getListWebElement(driver, BasePageUI.PRODUCT_PRICE_SORT);
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "")));

		}
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);

		return sortedList.equals(arrayList);
	}

	public boolean isNextIconDisplayed(WebDriver driver) {

		List<WebElement> pageList = getListWebElement(driver, BasePageUI.PAGELIST);
		boolean flag = false;
		for (int i = 0; i < pageList.size(); i++) {
			pageList.get(i).click();
			flag = isElementDisplayed(driver, BasePageUI.NEXT_PAGE_ICON);
		}
		return flag;
	}

	public boolean isPreviousIconDisplayed(WebDriver driver) {
		List<WebElement> pageList = getListWebElement(driver, BasePageUI.PAGELIST);
		boolean flag = false;
		for (WebElement page : pageList) {
			if (!page.getText().trim().equals("1")) {
				page.click();
				flag = isElementDisplayed(driver, BasePageUI.PREVIOUS_PAGE_ICON);
			}
			return flag;
		}
		return flag;
	}

	public boolean isPagingUndisplayed(WebDriver driver) {
		return isElementUndisplayed(driver, BasePageUI.PAGELIST);
	}

	// cart:
	public String getCartQuantity(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.CART_QUANTITY);
		return getElementText(driver, BasePageUI.CART_QUANTITY);
	}

	public String getProductQuantityInTooltip(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.CART_COUNT);
		return getElementText(driver, BasePageUI.CART_COUNT);
	}

	public String getProductNameInTooltip(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.PRODUCT_NAME_IN_CART);
		return getElementText(driver, BasePageUI.PRODUCT_NAME_IN_CART);
	}

	public String getProductInfoInTooltip(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.PRODUCT_INFO_IN_CART);
		return getElementText(driver, BasePageUI.PRODUCT_INFO_IN_CART);
	}

	public String getUnitPriceInTooltip(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.UNIT_PRICE_IN_CART);
		return getElementText(driver, BasePageUI.UNIT_PRICE_IN_CART);
	}

	public String getQuantityPerItem(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.QUANTITY_PER_ITEM_IN_CART);
		return getElementText(driver, BasePageUI.QUANTITY_PER_ITEM_IN_CART);
	}

	public String getSubTotalPrice(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.SUB_TOTAL_IN_CART);
		return getElementText(driver, BasePageUI.SUB_TOTAL_IN_CART);
	}

	public void hoverShoppingCartTooltip(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.CART_QUANTITY);
		hoverMouseToElement(driver, BasePageUI.CART_QUANTITY);
	}

	public String getGiftWrappingOptionInOrderDetail(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.GIFT_WRAPPING_OPTION);
		return getElementText(driver, BasePageUI.GIFT_WRAPPING_OPTION);
	}

	public String getOrderSummaryInfo(WebDriver driver, String info) {
		waitForElementVisible(driver, BasePageUI.ORDER_SUMMARY_INFO_VALUE, info);
		return getElementText(driver, BasePageUI.ORDER_SUMMARY_INFO_VALUE, info);
	}

	public void clicktoLinkByText(WebDriver driver, String linkvalue) {
		waitForElementClickable(driver, BasePageUI.LINK_BY_TEXT, linkvalue);
		clickToElement(driver, BasePageUI.LINK_BY_TEXT, linkvalue);
		sleepInSecond(2);
	}

	public void clickToDetailOrderByText(WebDriver driver, String orderNumber) {
		waitForElementClickable(driver, BasePageUI.DETAILS_ORDER_BUTTON, orderNumber);
		clickToElement(driver, BasePageUI.DETAILS_ORDER_BUTTON, orderNumber);
	}
//	Admin:

	public void openSideMenuPageByText(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.SIDE_MENU_BY_TEXT, pageName);
		clickToElement(driver, BasePageUI.SIDE_MENU_BY_TEXT, pageName);
	}

	public void openSubSideMenuPageByText(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.SUB_SIDE_MENU_BY_TEXT, pageName);
		clickToElement(driver, BasePageUI.SUB_SIDE_MENU_BY_TEXT, pageName);
	}

	public String getEmptyMsgAtTableByText(WebDriver driver, String cardName) {
		waitForElementVisible(driver, BasePageUI.NO_DATA_MSG_AT_TABLE_BY_TEXT, cardName);
		return getElementText(driver, BasePageUI.NO_DATA_MSG_AT_TABLE_BY_TEXT, cardName);
	}

	public String getPageTitle(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.PAGE_TITLE);
		return getElementText(driver, BasePageUI.PAGE_TITLE);
	}

	public String getTextboxValueByName(WebDriver driver, String textboxName) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_FIELD_BY_NAME, textboxName);
		return getElementAttribute(driver, BasePageUI.TEXTBOX_FIELD_BY_NAME, "value", textboxName);
	}

	public String getTextareaValueByName(WebDriver driver, String textareaName) {
		waitForElementVisible(driver, BasePageUI.TEXTAREA_FIELD_BY_NAME, textareaName);
		return getElementText(driver, BasePageUI.TEXTAREA_FIELD_BY_NAME, textareaName);
	}

	public boolean isCheckboxSelectedByID(WebDriver driver, String checkboxName) {
		waitForElementVisible(driver, BasePageUI.CHECK_BOX_BY_ID_ADMIN, checkboxName);
		return isElementSelected(driver, BasePageUI.CHECK_BOX_BY_ID_ADMIN, checkboxName);
	}

	public void clickToExtendButtonByCardName(WebDriver driver, String cardPanel) {
		waitForElementVisible(driver, BasePageUI.TOGGLE_ICON_BY_CARDNAME, cardPanel);
		String toggleIconStatus = getElementAttribute(driver, BasePageUI.TOGGLE_ICON_BY_CARDNAME, "class", cardPanel);
		if (toggleIconStatus.contains("fa-plus")) {
			waitForElementClickable(driver, BasePageUI.TOGGLE_ICON_BY_CARDNAME, cardPanel);
			clickToElement(driver, BasePageUI.TOGGLE_ICON_BY_CARDNAME, cardPanel);
		}
	}

	public void clickToExtendButtonSearchBox(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.TOGGLE_ICON_SEARCH_BOX);
		String toggleIconStatus = getElementAttribute(driver, BasePageUI.TOGGLE_ICON_SEARCH_BOX, "class");
		if (toggleIconStatus.contains("fa-angle-down")) {
			waitForElementClickable(driver, BasePageUI.TOGGLE_ICON_SEARCH_BOX);
			clickToElement(driver, BasePageUI.TOGGLE_ICON_SEARCH_BOX);
		}
	}

	public void selectOptionInTagList(WebDriver driver, String selectedValue) {
		waitForElementClickable(driver, BasePageUI.TAG_LIST_TEXTBOX);
		pressKeyToElement(driver, BasePageUI.TAG_LIST_TEXTBOX, Keys.BACK_SPACE);
		sendKeyToElement(driver, BasePageUI.TAG_LIST_TEXTBOX, selectedValue);
		sleepInSecond(2);
		pressKeyToElement(driver, BasePageUI.TAG_LIST_TEXTBOX, Keys.ENTER);

	}

	public String getSelectedOptionInTagList(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.SELECTED_TAG_IN_TEXTBOX);
		return getElementText(driver, BasePageUI.SELECTED_TAG_IN_TEXTBOX);
	}

	public void clickToLinkButtonAtTableByRowIndex(WebDriver driver, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_NAME_BY_ACTION_AND_NAME, headerName) + 1;
		waitForElementClickable(driver, BasePageUI.TABLE_LINK_BUTTON_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex));
		clickToElement(driver, BasePageUI.TABLE_LINK_BUTTON_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex,
				String.valueOf(columnIndex));
	}

	public boolean isSuccessAlertDisplayedAdmin(WebDriver driver, String alertText) {
		waitForElementVisible(driver, BasePageUI.ALERT_MESSAGE_BY_TEXT, alertText);
		return isElementDisplayed(driver, BasePageUI.ALERT_MESSAGE_BY_TEXT, alertText);
	}

	public String getValueInTableAtColumnTableAndRowIndexAndCardTitle(WebDriver driver, String cardTitleByID,
			String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_NAME_BY_ACTION_AND_NAME_AND_CARDTITLE,
				cardTitleByID, headerName) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_NAME_BY_COLUMN_INDEX_AND_ROW_INDEX_AND_CARDTITLE,
				cardTitleByID, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_NAME_BY_COLUMN_INDEX_AND_ROW_INDEX_AND_CARDTITLE,
				cardTitleByID, rowIndex, String.valueOf(columnIndex));
	}

}