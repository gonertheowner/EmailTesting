import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LogInPage;
import pages.MainPage;
import pages.SentMessagesPage;
import pages.WriteMessagePage;

import java.util.concurrent.TimeUnit;

public class SendMessageTest {

    public static LogInPage loginPage;
    public static MainPage mainPage;
    public static WriteMessagePage writeMessagePage;

    public static SentMessagesPage sentMessagesPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        //создание экземпляра драйвера
        driver = new ChromeDriver();

        loginPage = new LogInPage(driver);

        mainPage = new MainPage(driver);

        sentMessagesPage = new SentMessagesPage(driver);

        writeMessagePage = new WriteMessagePage(driver);

        //окно разворачивается на полный экран
        driver.manage().window().maximize();

        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //получение ссылки на страницу из файла настроек
        driver.get(ConfProperties.getProperty("login-page"));

        loginPage.inputEmail(ConfProperties.getProperty("email2"));
        loginPage.clickLogInButton();
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        loginPage.clickLogInButton();
    }

    @Test
    public void sendMessageTest() {
        mainPage.clickWriteButton();
        writeMessagePage.inputReceiverEmail(ConfProperties.getProperty("email1"));
        writeMessagePage.inputSubject("subject");
        writeMessagePage.inputText("text");
        writeMessagePage.clickSend();
        //здесь нужно подождать
        mainPage.clickSentButton();
        Assert.assertEquals(sentMessagesPage.getSentMessage(), "subject");
    }
}
