package practice.appiumframework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	public static boolean gemulatorIsOn = false;

	public AppiumDriverLocalService startAppiumServer() {

		boolean flagServerIsOn = checkIfServerIsRunning(4723);
		if (!flagServerIsOn) {
			System.out.println("Appium Server is off..... Turning it on ");
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
			System.out.println("Appium Server is on");
		}

		return service;
	}

	public static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();

		} catch (Exception e) {
			isServerRunning = true;
			System.out.println("isServerRunning flag is set to true");
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void startEmulator(boolean emulatorIsOn) throws Exception {
		if (!emulatorIsOn) {
			System.out.println("Launching Emulator with .bat file execution");
			Runtime.getRuntime()
					.exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
			System.out.println("Waiting for 10 Seconds to launch Emulator");
			Thread.sleep(10000);
			gemulatorIsOn = true;
		}
	}

	public static void killEmulator() throws Exception {
		if (gemulatorIsOn) {
			System.out.println("Closing Emulator with .bat file execution");
			Runtime.getRuntime()
					.exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\killEmulator.bat");
			System.out.println("Waiting for 10 Seconds to close Emulator");
			Thread.sleep(10000);
			gemulatorIsOn = false;
		}
	}

	public static void getScreenShot(String testCaseName) throws Exception {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "//Screenshots//" + testCaseName + ".png"));

	}

	public static AndroidDriver<AndroidElement> capabilities(String appName) throws Exception {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\practice\\appiumframework\\global.properties");
		// above line in java will enable fis object to readd the content on the file
		// present in mentioned path.
		Properties prop = new Properties();
		prop.load(fis); // This will create mapping between key and value pair or mapping with the help
						// of fis object.

		File appDir = new File("src");
		File app = new File(appDir, (String) prop.getProperty(appName));

		//String deviceName = (String) prop.getProperty("device");
		String deviceName= System.getProperty("deviceName");
		if (deviceName.contains("Pixel")) {
			System.out.println("Test case will run in Emulator");
			startEmulator(gemulatorIsOn);
			System.out.println("Emulator launched successfully");

		}

		URL connectionToserver;
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		System.out.println("Setting MobileCapabilitis for DEVICE_NAME as:- " + deviceName);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		System.out.println("Setting MobileCapabilitis for AUTOMATION_NAME as:- uiautomator2 ");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		System.out.println("Setting MobileCapabilitis for Application as:- " + app.getAbsolutePath());
		connectionToserver = new URL("http://127.0.0.1:4723/wd/hub");
		System.out.println("Setting connnection to server link as " + connectionToserver);
		driver = new AndroidDriver<>(connectionToserver, cap);
		System.out.println("Setting AppiumDriver with ConnectionServer and Capabilities");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;

	}

}
