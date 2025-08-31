package stepDefinitions;

import hooks.Hooks;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import constants.FrameworkConstants;
import io.cucumber.java.en.*;
import pages.LoginPage;

public class LoginSteps {
    WebDriver driver = Hooks.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    
    @Given("User is on home page and select city {string}")
    public void User_is_on_home_page_and_select_city(String city) {
    	driver.get(FrameworkConstants.BASE_URL);
    	loginPage.selectCity(city);
    }

    @When("User is on Login Page")
    public void user_is_on_login_page() {
        loginPage.goToLoginPage();
    }

    @When("User enters mobile number {string}")
    public void user_enters_mobile_number(String mobile) throws InterruptedException {
        loginPage.enterMobileNumber(mobile);
    }

    @And("Clicks on Continue")
    public void clicks_on_continue() {
        loginPage.clickContinue();
    }

    @Then("Verify Otp page")
    public void Verify_Otp_page() {
    	if(loginPage.verifyOTPBoxIsVisible()) {
    		Assert.assertTrue(true);
    	}else {
    		Assert.assertTrue(false);
    	}
    }
    @Then("back to login page")
    public void back_to_login_page() {
    	loginPage.backToLogin();
    }
    
    @Then("Verify message {string}")
    public void Verify_message(String errMsg){
    	String actualErrorMsg=loginPage.verifyErrorMessage();
    	Assert.assertEquals(actualErrorMsg, errMsg);
    }
    
//    =======================UI validation=====================

    @Then("Verify that mobile number field is visible")
    public void verify_that_mobile_number_field_is_visible() {
        Assert.assertTrue(loginPage.isMobileNumberFieldVisible(), "Mobile number field is not visible");
    }

    @Then("Verify that Continue button is not visible")
    public void verify_that_continue_button_is_not_visible() {
        Assert.assertFalse(loginPage.isContinueButtonVisible());
    }

    @Then("verify continue button is visible but should disable")
    public void verify_continue_button_is_visible_but_should_disable() {
        Assert.assertTrue(loginPage.isContinueButtonVisible(),"Continue button is not visible");
        Assert.assertTrue(loginPage.isContinueButtonDisbled(),"Continue button is not disabled");
    }
    
//    @Then("clean input box")
//    public void clean_input_box() {
//    	loginPage.cleanMobileInput();
//    }
    
    @Then("Verify continue button should enabled")
    public void then_verify_continue_button_should_enabled() {
    	Assert.assertTrue(loginPage.isContinueButtonEnabled(),"Continue Button is disabled");
    }

    @Then("close popup")
    public void close_popup() {
    	loginPage.closeLoginPopup();
    }
}