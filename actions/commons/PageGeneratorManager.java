package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.nopCommerce.admin.AddNewAddressPO;
import pageObjects.nopCommerce.admin.AddNewCustomerPO;
import pageObjects.nopCommerce.admin.CustomerDetailPO;
import pageObjects.nopCommerce.admin.CustomerListPO;
import pageObjects.nopCommerce.admin.HomepageAdminPO;
import pageObjects.nopCommerce.admin.LoginAdminPO;
import pageObjects.nopCommerce.admin.ProductListPO;
import pageObjects.nopCommerce.user.AddToCartPO;
import pageObjects.nopCommerce.user.AddYourReviewPO;
import pageObjects.nopCommerce.user.CheckoutPO;
import pageObjects.nopCommerce.user.CompareProductPO;
import pageObjects.nopCommerce.user.HomePageObject;
import pageObjects.nopCommerce.user.LoginPO;
import pageObjects.nopCommerce.user.MyAccountPO;
import pageObjects.nopCommerce.user.OrderHistoryPO;
import pageObjects.nopCommerce.user.ProductDetailPO;
import pageObjects.nopCommerce.user.Product_CatergoryListPO;
import pageObjects.nopCommerce.user.SearchPO;
import pageObjects.nopCommerce.user.TopMenuSubPagePO;
import pageObjects.nopCommerce.user.WishlistPO;
import pageObjects.nopCommerce.user.registerPO;

public class PageGeneratorManager {
	public static LoginPO getLoginPageObject(WebDriver driver) {
		return new LoginPO(driver);
	}

	public static HomePageObject getHompageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static registerPO getRegisterPageObject(WebDriver driver) {
		return new registerPO(driver);
	}

	public static MyAccountPO getMyAccountPageObject(WebDriver driver) {
		return new MyAccountPO(driver);
	}

	public static ProductDetailPO getProductDetailPageObject(WebDriver driver) {
		return new ProductDetailPO(driver);
	}

	public static AddYourReviewPO getAddYourReviewPageObject(WebDriver driver) {
		return new AddYourReviewPO(driver);
	}

	public static TopMenuSubPagePO getTopMenuSubPage(WebDriver driver) {
		return new TopMenuSubPagePO(driver);
	}

	public static SearchPO getSearchPage(WebDriver driver) {
		return new SearchPO(driver);
	}

	public static WishlistPO getWishlistPage(WebDriver driver) {
		return new WishlistPO(driver);
	}

	public static AddToCartPO getAddToCartPage(WebDriver driver) {
		return new AddToCartPO(driver);
	}

	public static CompareProductPO getCompareProductPage(WebDriver driver) {
		return new CompareProductPO(driver);
	}

	public static Product_CatergoryListPO getProduct_CatergoryListPage(WebDriver driver) {
		return new Product_CatergoryListPO(driver);
	}

	public static CheckoutPO getCheckoutPage(WebDriver driver) {
		return new CheckoutPO(driver);
	}

	public static OrderHistoryPO getOrderHistoryPage(WebDriver driver) {
		return new OrderHistoryPO(driver);
	}

	public static LoginAdminPO getLoginAdminPage(WebDriver driver) {
		return new LoginAdminPO(driver);
	}

	public static HomepageAdminPO gethomepageAdmin(WebDriver driver) {
		return new HomepageAdminPO(driver);
	}

	public static ProductListPO getProductListPage(WebDriver driver) {
		return new ProductListPO(driver);
	}

	public static pageObjects.nopCommerce.admin.ProductDetailPO getProductDetailPageAdmin(WebDriver driver) {
		return new pageObjects.nopCommerce.admin.ProductDetailPO(driver);
	}
	
	public static CustomerListPO getCustomerListPage(WebDriver driver) {
		return new CustomerListPO(driver);
	}
	public static CustomerDetailPO getCustomerDetailPage(WebDriver driver) {
		return new CustomerDetailPO(driver);
	}
	public static AddNewCustomerPO getAddCustomerPage(WebDriver driver) {
		return new AddNewCustomerPO(driver);
	}
	public static AddNewAddressPO getAddNewAddressPage(WebDriver driver) {
		return new AddNewAddressPO(driver);
	}

}
