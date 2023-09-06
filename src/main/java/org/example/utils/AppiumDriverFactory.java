package org.example.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;


public class AppiumDriverFactory {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<IOSDriver> iosDriver = new ThreadLocal<>();
    private static final ThreadLocal<AppiumDriverLocalService> appiumService = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static IOSDriver getIosDriver() {
        return iosDriver.get();
    }

    public static void startAppiumServer(String ip, int port) {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress(ip).usingPort(port)
                .withLogFile(new File(System.getProperty("user.dir") +"/appium_server.log"));
        AppiumDriverLocalService server = AppiumDriverLocalService.buildService(serviceBuilder);
        appiumService.set(server);
        server.start();
    }

    public static void appiumDriverInitialize(String platform) throws MalformedURLException {
        AppiumDriver appiumDriver = null;

        if (platform.equals("android")) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("ThachPixel2XL");
            options.setApp(
                    System.getProperty("user.dir") + "/src/test/resources/" + TestConfig.getAndroidAppName());
            options.setAppActivity("com.wdiodemoapp.MainActivity");
            appiumDriver = new AndroidDriver(appiumService.get().getUrl(), options);
        } else if (platform.equals("ios")) {
            // for real device, add these options: setUdid, setBundleId, setWdaLaunchTimeout);
            XCUITestOptions optionsIos = new XCUITestOptions();
            optionsIos.setDeviceName("iPhone 14 Pro");
            optionsIos.setPlatformName("iOS");
            optionsIos.setPlatformVersion("16.4");
            optionsIos.setAutomationName("XCUITest");
            optionsIos.setApp(
                    System.getProperty("user.dir") + "/src/test/resources/" + TestConfig.getIosAppName());
            IOSDriver driver = new IOSDriver(appiumService.get().getUrl(), optionsIos);
            iosDriver.set(driver);
            appiumDriver = driver;
        }
        driver.set(appiumDriver);
    }

    public static void tearDown() {
        driver.get().quit();
        driver.remove();
    }

    public static void stopAppiumServer() {
        appiumService.get().stop();
    }
}
