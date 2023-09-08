package org.example.actions;

import io.appium.java_client.ios.IOSDriver;
import org.example.utils.AppiumDriverFactory;

public class IOSActions extends AppiumActions{
    private final IOSDriver iosDriver = AppiumDriverFactory.getIosDriver();

    @Override
    public void hideKeyboard() {
        iosDriver.hideKeyboard("return");
    }
}
