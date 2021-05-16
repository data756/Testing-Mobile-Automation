package practice.appiumframework;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="InputDataForName")
	public Object[][] getDataForName() {
		Object[][] obj= new Object[][] {
			{"hello"},{"Argument"}
		};
		return obj;
	}

}
