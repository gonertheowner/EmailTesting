package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SentMessagesPage {
    public WebDriver driver;
    public SentMessagesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//label[@class='mail-Toolbar-Item-Checkbox']")
    private WebElement chooseAllMessagesButton;

    @FindBy(xpath = "//div[@title='Удалить (Delete)']")
    private WebElement deleteButton;

    @FindBy(xpath = "//h1[@class='Text Text_weight_bold Text_typography_subheader-l messages-empty__header--2ws56']")
    private WebElement errorText;

    @FindBy(xpath = "//span[@title='just title']")
    private WebElement sentMessage;

    public void chooseAllMessagesButtonClick() {
        chooseAllMessagesButton.click();
    }

    public void deleteButtonClick() {
        deleteButton.click();
    }

    public String getErrorText() {
        return errorText.getText();
    }

    public String getSentMessage() {
        return sentMessage.getText();
    }
}
