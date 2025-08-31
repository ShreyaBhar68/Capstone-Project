package stepDefinitions;

import org.openqa.selenium.WebDriver;

import hooks.Hooks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.GiftCardPage;

public class GiftCardSteps {
	WebDriver driver = Hooks.getDriver();
	GiftCardPage giftcard=new GiftCardPage(driver);
	
	@When("User click on the {string} icon")
	public void user_click_on_the_icon(String string) {
	   giftcard.clickCheckBalanceIcon();
	}
	@When("User enter an invalid voucher code {string}")
	public void user_enter_an_invalid_voucher_code(String voucherCode) {
	    giftcard.enterVoucherCode(voucherCode);
	}
	@When("User click on the {string} button")
	public void user_click_on_the_button(String string) {
	    giftcard.clickCheckBalanceButton();
	}
	@Then("User should see an error message {string}")
	public void user_should_see_an_error_message(String exceptedErrorMessage) {
	    Assert.assertEquals(exceptedErrorMessage, giftcard.getErrorMessage());
	}
	
//	====================GiftCardNavigation======================
	@When("User navigate to the gift card section")
	public void user_navigate_to_the_section() {
	    giftcard.navigateToGiftCardSection();
	}
	@Then("User should see the {string} icon displayed")
	public void user_should_see_the_icon_displayed(String string) {
		Assert.assertTrue(giftcard.isCheckBalanceIconDisplayed());
	}
}