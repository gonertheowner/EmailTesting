package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
    public WebDriver driver;
    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id='passp-field-login']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement logInButton;

    @FindBy(xpath = "//input[@id='passp-field-passwd']")
    private WebElement passwordField;

    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void clickLogInButton() {
        logInButton.click();
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }
}