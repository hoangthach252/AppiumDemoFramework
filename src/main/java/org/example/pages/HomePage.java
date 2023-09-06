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
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;

public class HomePage {
    AppiumDriver driver;
    AppiumActions appiumActions;

    @iOSXCUITFindBy(accessibility = "Demo app for the appium-boilerplate")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Demo app for the appium-boilerplate\")")
    private WebElement homePageTitle;

    public HomePage() {
        driver = AppiumDriverFactory.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void verifyHomePageTitleDisplayed() {
        String actualTitle = new IOSActions().getText(homePageTitle);
        assertEquals(actualTitle, "Demo app for the appium-boilerplate");
    }

}
