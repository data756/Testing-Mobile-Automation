package practice.appiumframework.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FillingForm {
	
	public FillingForm(AndroidDriver<AndroidElement> driver) {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement radioBtnnFemale;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement countryDropDown;
	
	
	//@AndroidFindBy(uiAutomator ="new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));")
	//public WebElement argentinaSelect;
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement submitBtn;
	
	public WebElement getNameField() {
		return nameField;
	}
	
	public WebElement getRadioButtonFemale() {
		return radioBtnnFemale;
	}
	
	public WebElement getCountryDropDown() {
		return countryDropDown;
	}
	
	public WebElement getSubmitBtn() {
		return submitBtn;
	}
}
