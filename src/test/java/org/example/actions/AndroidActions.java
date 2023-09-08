package org.example.actions;

import io.appium.java_client.android.AndroidDriver;
import org.example.utils.AppiumDriverFactory;

public class AndroidActions extends AppiumActions{
    private final AndroidDriver androidDriver = (AndroidDriver) AppiumDriverFactory.getDriver();

    @Override
    public void hideKeyboard() {
        androidDriver.hideKeyboard();
    }
}
