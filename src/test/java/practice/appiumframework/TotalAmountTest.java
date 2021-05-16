package practice.appiumframework;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
//	import junit.framework.Assert;
import practice.appiumframework.pageobject.CheckOutPage;
import practice.appiumframework.pageobject.FillingForm;
import practice.appiumframework.pageobject.ShoppingList;

public class TotalAmountTest extends Base{

	@BeforeTest
	public void killAllNodes() throws Exception
	{
		Runtime.getRuntime()
		.exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\killAllOpenNodes.bat");
		Thread.sleep(5000);
		System.out.println("All nodes are cleared");
	}
	
	

	
	
	@Test(dataProvider="InputDataForName",dataProviderClass=TestData.class)
	public void validateTotal(String input) throws Exception  {
		
		System.out.println("Starting Appium Server");
		service=startAppiumServer();
		System.out.println("Setting Capabilities for Test Case "+this.getClass());
		AndroidDriver <AndroidElement> driver= capabilities("GeneralStoreApp");
		FillingForm form= new FillingForm(driver);
		ShoppingList shoppingList= new ShoppingList(driver);
		CheckOutPage checkOutPage= new CheckOutPage(driver);
		Utilities utility = new Utilities(driver);
		form.getNameField().sendKeys(input);
		
		driver.hideKeyboard();
		form.getRadioButtonFemale().click();
		form.getCountryDropDown().click();
		utility.scrollToTextSelect("Argentina");
		form.getSubmitBtn().click();
		
		shoppingList.getProducts().get(0).click();
		shoppingList.getProducts().get(1).click();
		shoppingList.getCartBtn().click();		
		
		Thread.sleep(5000);
		
		List<WebElement> productPriceCart= checkOutPage.getProductPriceList();
		double amount1= Double.parseDouble(productPriceCart.get(0).getText().substring(1));
		double amount2= Double.parseDouble(productPriceCart.get(1).getText().substring(1));
		
		double totalAmount=amount1+amount2;
		System.out.println(totalAmount);
		
		double orderAmount=Double.parseDouble(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(1));
		System.out.println(orderAmount);
		
		org.testng.Assert.assertEquals(totalAmount, orderAmount);
		
		//MobileGestures
		
		WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction t= new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
		
		WebElement lgpress=driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		t.longPress(longPressOptions().withElement(element(lgpress)).withDuration(ofSeconds(4))).release().perform();
		
		Thread.sleep(5000);
		
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();	
		
		service.stop();
	}
	
	@AfterTest
	public void killOpenedEmulator() {
		try {
			killEmulator();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
