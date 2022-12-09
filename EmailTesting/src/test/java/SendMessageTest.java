import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.util.concurrent.TimeUnit;

public class SendMessageTest {

    public static LogInPage loginPage;
    public static LogInPage loginPage1;
    public static MainPage mainPage;
    public static MainPage mainPage1;
    public static WriteMessagePage writeMessagePage;

    public static SentMessagesPage sentMessagesPage;
    public static WebDriver driver;
    public static WebDriver driver1;

    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        //создание экземпляра драйвера
        driver = new ChromeDriver();
        driver1 = new ChromeDriver();

        loginPage = new LogInPage(driver);
        loginPage1 = new LogInPage(driver1);

        mainPage = new MainPage(driver);
        mainPage1 = new MainPage(driver1);

        sentMessagesPage = new SentMessagesPage(driver);

        writeMessagePage = new WriteMessagePage(driver);

        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        driver1.manage().window().maximize();

        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //получение ссылки на страницу из файла настроек
        driver.get(ConfProperties.getProperty("login-page"));
        driver1.get(pages.ConfProperties.getProperty("login-page"));

        loginPage.inputEmail(ConfProperties.getProperty("email2"));
        loginPage.clickLogInButton();
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        loginPage.clickLogInButton();

        loginPage1.inputEmail(pages.ConfProperties.getProperty("email1"));
        loginPage1.clickLogInButton();
        loginPage1.inputPassword(pages.ConfProperties.getProperty("password"));
        loginPage1.clickLogInButton();
    }

    @Test
    public void sendMessageTest() {
        mainPage.clickWriteButton();
        writeMessagePage.inputReceiverEmail(ConfProperties.getProperty("email1"));
        writeMessagePage.inputSubject(ConfProperties.getProperty("message-title"));
        writeMessagePage.inputText(ConfProperties.getProperty("message-text"));
        writeMessagePage.clickSend();
        //здесь нужно подождать
        mainPage.clickSentButton();
        Assert.assertEquals(ConfProperties.getProperty("message-title"), sentMessagesPage.getSentMessage());
    }

    @Test
    public void receivedMessageTest() {
        Assert.assertEquals(ConfProperties.getProperty("message-title"), mainPage1.getReceivedMessageTitle());
    }
}
