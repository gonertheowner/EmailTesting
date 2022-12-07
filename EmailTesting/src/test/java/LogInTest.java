import com.sun.tools.javac.Main;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LogInPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;
public class LogInTest {
    public static LogInPage loginPage;
    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        //создание экземпляра драйвера
        driver = new ChromeDriver();

        loginPage = new LogInPage(driver);

        mainPage = new MainPage(driver);

        //окно разворачивается на полный экран
        driver.manage().window().maximize();

        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //получение ссылки на страницу из файла настроек
        driver.get(ConfProperties.getProperty("login-page"));
    }

    @Test
    public void CorrectLogInTest() {
        loginPage.inputEmail(ConfProperties.getProperty("email2"));
        loginPage.clickLogInButton();
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        loginPage.clickLogInButton();
        Assert.assertEquals(mainPage.getUserName(), ConfProperties.getProperty("username2"));
    }

    @Test
    public void NotCorrectLoginTest() {
        loginPage.inputEmail("notcorrectemail");
        loginPage.clickLogInButton();
        Assert.assertEquals("Такого аккаунта нет", loginPage.getErrorLoginText());
    }

    @Test
    public void NotCorrectPasswordTest() {
        loginPage.inputEmail(ConfProperties.getProperty("email2"));
        loginPage.clickLogInButton();
        loginPage.inputPassword("notcorrectpassword");
        loginPage.clickLogInButton();
        Assert.assertEquals("Неверный пароль", loginPage.getErrorPasswordText());
    }
}