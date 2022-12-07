package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public WebDriver driver;
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@class='user-account__name']")
    private WebElement userName;

    @FindBy(xpath = "//a[@class='Button2 Button2_type_link Button2_view_action Button2_size_m Layout-m__compose--pTDsx qa-LeftColumn-ComposeButton ComposeButton-m__root--fP-o9']")
    private WebElement writeButton;

    @FindBy(xpath = "//a[@aria-label='Отправленные, папка']")
    private WebElement sentButton;

    //span[@title='subject']
    @FindBy(xpath = "//span[@title='subject']")
    private WebElement sentMessage;


    public String getUserName() {
        return userName.getText();
    }

    public void clickWriteButton() {
        writeButton.click();
    }

    public void clickSentButton() {
        sentButton.click();
    }

    public String getSentMessage() {
        return sentMessage.getText();
    }
}
