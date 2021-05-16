package practice.appiumframework;

import practice.appiumframework.pageobject.APIDemosHomePage;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class PreferencesTest extends Base {

	@Test
	public void APIDemos () throws Exception {
		
		service=startAppiumServer();
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver=capabilities("ApiDemoApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//xpath,id class name is main locators in AndroidUIAutomator
		
		APIDemosHomePage homePage= new APIDemosHomePage(driver);
		
		homePage.Preferences.click();
		service.stop();
	
		}

	}

