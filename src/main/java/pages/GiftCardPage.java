package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class GiftCardPage {

    WebDriver driver;
    // Constructor
    public GiftCardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators using PageFactory
    @FindBy(xpath = "//div[@class='sc-1or3vea-19 bfNncl']//a[@href='/giftcards' and text()='Gift Cards']")
    private WebElement giftCardSectionLink;

    @FindBy(xpath = "//div[text()='Check Gift Card Balance']")
    private WebElement checkBalanceIcon;

    @FindBy(xpath = "//input[@id='gift-voucher']")
    private WebElement voucherCodeInput;

    @FindBy(xpath = "//div[@class='sc-zgl7vj-7 dMHyDB']")
    private WebElement checkBalanceButton;

    @FindBy(xpath = "//div[contains(@id,'error-gift-voucher')]")
    private WebElement errorMessage;

    // Page Actions
    public void navigateToGiftCardSection() {
    	try {
            WaitUtils.waitForClickability(driver, giftCardSectionLink).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", giftCardSectionLink);
        }
    }

    public boolean isCheckBalanceIconDisplayed() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", checkBalanceIcon);
        return checkBalanceIcon.isDisplayed();
    }

    public void clickCheckBalanceIcon() {
    	try {
            WaitUtils.waitForClickability(driver, checkBalanceIcon).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkBalanceIcon);
        }
    }

    public void enterVoucherCode(String code) {
    	WaitUtils.waitForVisibility(driver, voucherCodeInput).clear();
        voucherCodeInput.sendKeys(code);
    }

    public void clickCheckBalanceButton() {
    	try {
            WaitUtils.waitForClickability(driver, checkBalanceButton).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkBalanceButton);
        }
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}