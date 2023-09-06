package org.example.actions;

import org.example.utils.AppiumDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class  AppiumActions {

    public abstract void hideKeyboard();

    public void press(WebElement elm) {
        WebDriverWait wait = new WebDriverWait(AppiumDriverFactory.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(elm)).click();
    }

    public void sendKeys(WebElement elm, String input) {
        WebDriverWait wait = new WebDriverWait(AppiumDriverFactory.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(elm)).sendKeys(input);
    }

    public String getText(WebElement elm) {
        WebDriverWait wait = new WebDriverWait(AppiumDriverFactory.getDriver(), Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOf(elm)).getText();
    }

    public WebElement waitUntilElementVisible(WebElement elm) {
        WebDriverWait wait = new WebDriverWait(AppiumDriverFactory.getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(elm));
    }

    public boolean isElementDisplayed(WebElement elm) {
        return elm.isDisplayed();
    }

}
