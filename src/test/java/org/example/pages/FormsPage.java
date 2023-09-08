package org.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.example.actions.AppiumActions;
import org.example.test.BaseTest;
import org.example.utils.AppiumDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;


public class FormsPage {
    AppiumDriver driver;
    AppiumActions appiumActions;

    @iOSXCUITFindBy(accessibility = "text-input")
    @AndroidFindBy(accessibility = "text-input")
    private WebElement textInputField;

    @iOSXCUITFindBy(accessibility = "switch")
    @AndroidFindBy(accessibility = "switch")
    private WebElement switchBtn;

    @iOSXCUITFindBy(accessibility = "Dropdown")
    @AndroidFindBy(accessibility = "Dropdown")
    private WebElement dropdownSelect;

    @iOSXCUITFindBy(className = "XCUIElementTypePickerWheel")
    private WebElement pickerWheel;

    @iOSXCUITFindBy(accessibility = "done_button")
    private WebElement pickerDoneBtn;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == \"Active\"`]")
    @AndroidFindBy(accessibility = "button-Active")
    private WebElement activateBtn;

    @iOSXCUITFindBy(accessibility = "This button is active")
    @AndroidFindBy(id = "android:id/message")
    private WebElement activateConfirmPopup;

    public FormsPage() {
        driver = AppiumDriverFactory.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        appiumActions = BaseTest.platformAppiumActions;
    }

    public FormsPage fillTextInput(String input) {
        appiumActions.sendKeys(textInputField, input);
        appiumActions.hideKeyboard();
        return this;
    }

    public FormsPage toogleSwitch() {
        appiumActions.press(switchBtn);
        return this;
    }

    public FormsPage selectDropdown(String platform, String option) {
        appiumActions.press(dropdownSelect);
        if (platform.equals("ios")) {
            appiumActions.waitUntilElementVisible(pickerWheel);
            pickerWheel.sendKeys(option);
            appiumActions.press(pickerDoneBtn);
        } else {
            String dropdownOptionAndroid = "//android.widget.CheckedTextView[@text='%s']";
            String dropdownOption = String.format(dropdownOptionAndroid, option);
            WebElement elm = AppiumDriverFactory.getDriver().findElement(By.xpath(dropdownOption));
            appiumActions.press(elm);
        }
        return this;
    }

    public FormsPage activateForm() {
        appiumActions.press(activateBtn);
        return this;
    }

    public void verifyActivePopup() {
        assertThat(appiumActions.isElementDisplayed(activateConfirmPopup)).isEqualTo(true);
    }
}
