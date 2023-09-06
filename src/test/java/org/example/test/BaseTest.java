package org.example.test;

import org.example.utils.AppiumDriverFactory;
import org.example.utils.TestConfig;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class BaseTest {

	@BeforeSuite
	public void startAppiumServer() {
		AppiumDriverFactory.startAppiumServer(TestConfig.getAppiumServerIp(), TestConfig.getAppiumServerPort());
	}

	@Parameters({ "platform" })
	@BeforeMethod(groups = {"ios","android"})
	public void appiumSetup(String platform) throws MalformedURLException {
		if (platform.equals("ios")) {
			AppiumDriverFactory.appiumDriverInitialize("ios");
		} else if (platform.equals("android")) {
			AppiumDriverFactory.appiumDriverInitialize("android");
		}
	}
	
	
	@AfterClass
	public void tearDown() {
		AppiumDriverFactory.tearDown();
	}

	@AfterSuite
	public void stopAppiumServer() {
		AppiumDriverFactory.stopAppiumServer();
	}

}
