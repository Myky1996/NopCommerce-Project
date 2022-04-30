package pageUIs.nopCommerce;

public class CheckoutPageUI {
	public static final String SHIPPING_BILLING_ADDRESS_BY_TEXT = "//div[@class='%s']//li[@class='%s']";
	public static final String PAYMENT_METHOD_BY_TEXT = "//div[@class='payment-method-info']//span[@class='value']";
	public static final String SHIPPING_METHOD_BY_TEXT = "//div[@class='shipping-method-info']//span[@class='value']";
	public static final String ORDER_SUCCESSFULLY_MSG = "//div[@class='section order-completed']/div[@class='title']";
	public static final String ORDER_NUMBER = "//div[@class='order-number']";
	public static final String BUTTON_BY_ID_AND_TEXT = "//li[@id='%s']//button[text()='%s']";
}
