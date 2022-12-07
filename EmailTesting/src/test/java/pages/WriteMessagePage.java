package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WriteMessagePage {

    public WebDriver driver;
    public WriteMessagePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@title='Кому']")
    private WebElement receiverEmailField;

    @FindBy(xpath = "//input[@id='compose-field-subject-4']")
    private WebElement subjectField;


    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement textField;

    @FindBy(xpath = "//button[@class='Button2 Button2_pin_circle-circle Button2_view_default Button2_size_l']")
    private WebElement sendButton;


    public void inputReceiverEmail(String receiverEmail) {
        receiverEmailField.sendKeys(receiverEmail);
    }

    public void inputSubject(String subject) {
        subjectField.sendKeys(subject);
    }

    public void inputText(String text) {
        textField.sendKeys(text);
    }

    public void clickSend() {
        sendButton.click();
    }
}
