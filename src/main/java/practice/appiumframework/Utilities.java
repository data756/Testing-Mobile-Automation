package practice.appiumframework;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities {
	
	AndroidDriver<AndroidElement> driver;
	
	public Utilities(AndroidDriver<AndroidElement> driver) {
		this.driver=driver;
	}
	
	public AndroidElement scrollToText(String text) {
		return driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text +"\"));");
	}
	
	public void scrollToTextSelect(String text) {
		scrollToText(text).click();
	}
}
