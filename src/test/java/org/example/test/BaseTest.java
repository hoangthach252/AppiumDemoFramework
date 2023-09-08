package org.example.test;

import ch.qos.logback.classic.Logger;
import org.example.actions.AndroidActions;
import org.example.actions.AppiumActions;
import org.example.actions.IOSActions;
import org.example.utils.AppiumDriverFactory;
import org.example.utils.AppiumServer;
import org.example.utils.TestConfig;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class BaseTest {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BaseTest.class);

	public static AppiumActions platformAppiumActions;
	public static ThreadLocal <String> deviceName = new ThreadLocal<>();

	@BeforeSuite
	public void startAppiumServer() throws Exception {
		if(!AppiumServer.checkIfAppiumServerIsRunnning(4723)) {
			AppiumServer.startAppiumServer(TestConfig.getAppiumServerIp(), TestConfig.getAppiumServerPort());
			logger.info("Appium server started");
		} else {
			logger.info("Appium server already running");
		}

	}

	@Parameters({ "platform", "deviceName" })
	@BeforeMethod(groups = {"ios","android"})
	public void appiumSetup(String platform, String deviceName) throws MalformedURLException {
		BaseTest.deviceName.set(deviceName);
		if (platform.equals("ios")) {
			AppiumDriverFactory.appiumDriverInitialize("ios");
			platformAppiumActions = new IOSActions();
		} else if (platform.equals("android")) {
			AppiumDriverFactory.appiumDriverInitialize("android");
			platformAppiumActions = new AndroidActions();
		}
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		AppiumDriverFactory.tearDown();
	}

	@AfterSuite(alwaysRun = true)
	public void stopAppiumServer() {
		AppiumServer.stopAppiumServer();
	}

}
