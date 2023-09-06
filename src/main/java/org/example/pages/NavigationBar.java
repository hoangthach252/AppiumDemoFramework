package org.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.example.actions.AndroidActions;
import org.example.actions.AppiumActions;
import org.example.actions.IOSActions;
import org.example.utils.AppiumDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBar {
    AppiumDriver driver;
    AppiumActions appiumActions;
    String platform;

    @iOSXCUITFindBy(accessibility = "Forms")
    @AndroidFindBy(accessibility = "Forms")
    private WebElement formsTab;

    public NavigationBar(String platform) {
        this.platform = platform;
        driver = AppiumDriverFactory.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        if (platform.equals("ios")) {
            appiumActions = new IOSActions();
        } else if (platform.equals("android")) {
            appiumActions = new AndroidActions();
        }
    }

    public FormsPage navigateToForms() {
        appiumActions.press(formsTab);
        return new FormsPage(platform);
    }
}
