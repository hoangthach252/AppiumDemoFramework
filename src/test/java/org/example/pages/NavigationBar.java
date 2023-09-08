package org.example.pages;

import org.example.actions.AppiumActions;
import org.example.test.BaseTest;
import org.example.utils.AppiumDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NavigationBar {
    AppiumDriver driver;
    AppiumActions appiumActions;

    @iOSXCUITFindBy(accessibility = "Forms")
    @AndroidFindBy(accessibility = "Forms")
    private WebElement formsTab;

    public NavigationBar() {
        driver = AppiumDriverFactory.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        appiumActions = BaseTest.platformAppiumActions;
    }

    public FormsPage navigateToForms() {
        appiumActions.press(formsTab);
        return new FormsPage();
    }
}
