package pageUIs.nopCommerce;

public class BasePageUI {
	public final static String HEADER_FOOTER_PAGE_BY_TEXT = "//a[text()='%s']";
	public final static String SIDE_PAGE_BY_TEXT = "//div[@class='block block-account-navigation']//a[string()='%s']";
	public final static String TEXTBOX_FIELD_BY_NAME = "//input[@name='%s']";
	public final static String TEXTAREA_FIELD_BY_NAME = "//textarea[@name='%s']";
	public final static String CHECKBOX_BY_TEXT_LABEL = "//label[text()='%s']//preceding-sibling::input";
	public final static String BUTTON_BY_TEXT = "//button[contains(string(),'%s')]";
	public final static String LINK_BY_TEXT = "//a[contains(string(),'%s')]";
	public final static String BUTTONS_AT_PRODUCT_LIST_BY_TEXT = "//a[text()='%s']/parent::h2//following-sibling::div[@class='add-info']//button[text()='%s']";
	public final static String RADIO_BUTTON_BY_TEXT = "//label[contains(string(),'%s')]//preceding-sibling::input";
	public final static String DROPDOWN_BY_NAME = "//select[@name='%s']";
	public final static String SUCCESS_ALERT_BY_CLASS = "//div[@class='%s']/p";
	public final static String CLOSE_NOTI_BAR_BUTTON= "//div[@class='bar-notification success']/span";
	public final static String TOP_MENU_PAGE_BY_TEXT= "//ul[@class='top-menu notmobile']//a[contains(string(),'%s')]";
	public final static String TOP_MENU_SUBPAGE_BY_TEXT= "//ul[@class='top-menu notmobile']//a[contains(string(),'%s')]/following-sibling::ul//a[contains(string(),'%s')]";
	public final static String PRODUCT_TITLE_BY_TEXT= "//h2[@class='product-title']/a[text()='%s']";
	public final static String DISPLAYED_REVIEW_INFO= "//div[@class='%s']";
	public final static String TABLE_HEADER_NAME_BY_ACTION_AND_NAME= "//th[contains(text(),'%s')]/preceding-sibling::th";
	public final static String TABLE_ROW_NAME_BY_COLUMN_INDEX_AND_ROW_INDEX= "//table[@class='cart']//tbody/tr[%s]/td[%s]";
	public final static String TABLE_CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX= "//table[@class='cart']//tbody/tr[%s]/td[%s]/input";
	public final static String TABLE_BUTTON_BY_COLUMN_INDEX_AND_ROW_INDEX= "//tbody//tr[%s]/td[%s]/button";
	public final static String TABLE_LINK_BY_COLUMN_INDEX_AND_ROW_INDEX= "//tbody//tr[%s]//td[%s]/div[@class='edit-item']/a";
	public final static String TABLE_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX= "//tbody//tr[%s]/td[%s]/input";
	public final static String EMPTY_DATA_MESSAGE= "//div[@class='no-data']";
	public final static String SEARCH_RESULTS_BY_CLASS= "//h2[@class='product-title']/a";
	public final static String PRODUCT_NAME_SORT = "//h2[@class='product-title']/a";
	public final static String PRODUCT_PRICE_SORT = "//h2[@class='prices']/span";
	public static final String PAGELIST = "//li[@class='current-page' or @class='individual-page']";
	public static final String NEXT_PAGE_ICON = "//li[@class='next-page']";
	public static final String PREVIOUS_PAGE_ICON = "//li[@class='previous-page']";
	public static final String CART_QUANTITY = "//span[@class='cart-qty']";
	public static final String CART_COUNT = "//div[@class='mini-shopping-cart']/div[@class='count']";
	public static final String PRODUCT_NAME_IN_CART = "//div[@class='name']/a";
	public static final String PRODUCT_INFO_IN_CART = "//div[@class='attributes']";
	public static final String UNIT_PRICE_IN_CART = "//div[@class='price']/span";
	public static final String QUANTITY_PER_ITEM_IN_CART = "//div[@class='quantity']/span";
	public static final String SUB_TOTAL_IN_CART = "//div[@class='totals']";
	public static final String GIFT_WRAPPING_OPTION = "//div[@class='selected-checkout-attributes']";
	public static final String ORDER_SUMMARY_INFO_VALUE = "//tr[@class='%s']//span[@class='value-summary']";
	public static final String DETAILS_ORDER_BUTTON = "//div[contains(string(),'%s')]//following-sibling::div[@class='buttons']/button";
	
	
//	Admin:
	public final static String SIDE_MENU_BY_TEXT = "//p[contains(string(),'%s')]/i";
	public final static String SUB_SIDE_MENU_BY_TEXT = "//p[text()='%s']";
	public final static String STATUS_ICON_AT_TABLE_ROW_NAME_BY_BY_COLUMN_INDEX_AND_ROW_INDEX_AND_CARDTITLE = "//div[@id='%s']//tbody/tr[%s]/td[%s]/i[contains(@class,'%s-icon')]";
	public final static String CHECKBOX_BY_NAME = "//input[@name='%s']";
	public final static String NO_DATA_MSG_AT_TABLE_BY_TEXT = "//div[@id='%s']//td[@class='dataTables_empty']";
	public final static String PAGE_TITLE = "//h1[contains(text(),'%s')]";
	public final static String CHECK_BOX_BY_ID_ADMIN = "//input[@id='%s']";
	public final static String TOGGLE_ICON_BY_CARDNAME = "//div[@class='card-title' and contains(string(),'%s')]/following-sibling::div//i";
	public final static String TOGGLE_ICON_SEARCH_BOX = "//div[@class='icon-collapse']/i";
	public final static String TAG_LIST_TEXTBOX = "//input[@aria-describedby='SelectedCustomerRoleIds_taglist']";
	public final static String SELECTED_TAG_IN_TEXTBOX = "//ul[@id='SelectedCustomerRoleIds_taglist']/li";
	public final static String TABLE_LINK_BUTTON_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody//tr[%s]//td[%s]/a";
	public final static String ALERT_MESSAGE_BY_TEXT = "//div[contains(@class, 'alert-success') and contains(string(),'%s')]";
	public final static String TABLE_HEADER_NAME_BY_ACTION_AND_NAME_AND_CARDTITLE= "//div[@id='%s']//th[contains(text(),'%s')]/preceding-sibling::th";
	public final static String TABLE_ROW_NAME_BY_COLUMN_INDEX_AND_ROW_INDEX_AND_CARDTITLE= "//div[@id='%s']//tbody//tr[%s]/td[%s]";
	


	
}
