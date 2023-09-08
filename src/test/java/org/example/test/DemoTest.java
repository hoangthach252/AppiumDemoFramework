package org.example.test;

import org.example.pages.FormsPage;
import org.example.pages.HomePage;
import org.example.pages.NavigationBar;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoTest extends BaseTest {
	
	@Test(groups = {"ios","android"}, enabled = false)
	public void firstTest() throws InterruptedException {
		HomePage homePage = new HomePage();
		homePage.verifyHomePageTitleDisplayed();
		Thread.sleep(5000);
	}

	@Parameters({ "platform" })
	@Test(groups = {"ios","android"})
	public void fillFormsAndActivate(String platform) throws InterruptedException {
		NavigationBar navigationBar = new NavigationBar();
		FormsPage formsPage = navigationBar
				.navigateToForms()
				.fillTextInput("Thach Hoang")
				.toogleSwitch()
				.selectDropdown(platform, "Appium is awesome")
				.activateForm();

		formsPage.verifyActivePopup();

		Thread.sleep(5000);
	}

}
