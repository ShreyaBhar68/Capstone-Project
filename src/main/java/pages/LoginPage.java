package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;

	// ===== Constructor =====
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// ===== Page Elements =====
	@FindBy(xpath = "//input[@placeholder='Search for your city']")
	private WebElement citySearchBox;

	@FindBy(xpath = "//div[@class='sc-fv93km-0 cPDWyb']//div[1]//div[1]")
	private WebElement firstSuggestedCity;

	@FindBy(xpath = "//div[text()='Sign in']")
	private WebElement loginIcon;

	@FindBy(id = "userMobileNumber")
	private WebElement mobileField;

	@FindBy(xpath = "//div[text()='Continue']")
	private WebElement continueBtn;

	@FindBy(xpath = "//div[@class='sc-zgl7vj-7 iFxnzW']")
	private WebElement disabledContinueBtn;
	@FindBy(xpath = "//div[@class='sc-zgl7vj-7 cCdQFC']")
	private WebElement enabledContinueBtn;

	@FindBy(xpath = "//input[@type='tel']")
	List<WebElement> otpField;

	@FindBy(xpath = "//div[text()='Invalid mobile number']")
	private WebElement errMsg;

	@FindBy(xpath = "//div[@class='sc-1ydq0aj-6 gnsbYm']")
	private WebElement closePopupButton;
	@FindBy(xpath = "//div[@class='sc-1ydq0aj-0 bIaakI']")
	private WebElement popupBackBtn;

	// ===== Actions =====
	public void selectCity(String cityName) {
		wait.until(ExpectedConditions.visibilityOf(citySearchBox)).sendKeys(cityName);
		wait.until(ExpectedConditions.elementToBeClickable(firstSuggestedCity)).click();
	}

	public void goToLoginPage() {
		wait.until(ExpectedConditions.elementToBeClickable(loginIcon)).click();
	}
	public void clickContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
	}

	public boolean verifyOTPBoxIsVisible() {
		return wait.until(ExpectedConditions.visibilityOf(otpField.get(0))).isDisplayed();
	}

	public void backToLogin() {
		wait.until(ExpectedConditions.visibilityOf(popupBackBtn)).click();
	}

	public String verifyErrorMessage() {
		return errMsg.getText();
	}

	public boolean isMobileNumberFieldVisible() {
		return wait.until(ExpectedConditions.visibilityOf(mobileField)).isDisplayed();
	}

	public boolean isContinueButtonVisible() {
		try {
			return continueBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isContinueButtonDisbled() {
		return wait.until(ExpectedConditions.visibilityOf(disabledContinueBtn)).isDisplayed();
	}

	public boolean isContinueButtonEnabled() {
		return wait.until(ExpectedConditions.visibilityOf(enabledContinueBtn)).isDisplayed();
	}

	public void enterMobileNumber(String number) throws InterruptedException {
		WebElement mobileField = driver.findElement(By.id("userMobileNumber"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", mobileField);
		System.out.println("deleted");
		Thread.sleep(3000);
	    mobileField.sendKeys(number);
	}


	public void closeLoginPopup() {
		closePopupButton.click();
	}
}