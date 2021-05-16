package practice.appiumframework.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckOutPage {

	public CheckOutPage(AndroidDriver<AndroidElement> driver) {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPriceList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private String totalAmountLabel;
	
	public List<WebElement> getProductPriceList (){
		return productPriceList;
	}
	
	public String getTotalAmountLael() {
		return totalAmountLabel;
	}
}
