package org.example.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.example.test.BaseTest;

import java.net.MalformedURLException;


public class AppiumDriverFactory {
    protected static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    protected static final ThreadLocal<IOSDriver> iosDriver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static IOSDriver getIosDriver() {
        return iosDriver.get();
    }

    public static void appiumDriverInitialize(String platform) throws MalformedURLException {
        AppiumDriver appiumDriver = null;

        if (platform.equals("android")) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(BaseTest.deviceName.get());
            options.setApp(
                    System.getProperty("user.dir") + "/src/test/resources/" + TestConfig.getAndroidAppName());
            options.setAppActivity("com.wdiodemoapp.MainActivity");
            appiumDriver = new AndroidDriver(AppiumServer.appiumService.getUrl(), options);
        } else if (platform.equals("ios")) {
            // for real device, add these options: setUdid, setBundleId, setWdaLaunchTimeout);
            XCUITestOptions optionsIos = new XCUITestOptions();
            optionsIos.setDeviceName(BaseTest.deviceName.get());
            optionsIos.setPlatformName("iOS");
            optionsIos.setPlatformVersion("16.4");
            optionsIos.setAutomationName("XCUITest");
            optionsIos.setApp(
                    System.getProperty("user.dir") + "/src/test/resources/" + TestConfig.getIosAppName());
            IOSDriver driver = new IOSDriver(AppiumServer.appiumService.getUrl(), optionsIos);
            iosDriver.set(driver);
            appiumDriver = driver;
        }
        driver.set(appiumDriver);
    }

    public static void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
