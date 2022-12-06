import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LogInPage;

import java.util.concurrent.TimeUnit;
public class LogInTest {
    public static LogInPage loginPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        //создание экземпляра драйвера
        driver = new ChromeDriver();

        loginPage = new LogInPage(driver);

        //окно разворачивается на полный экран
        driver.manage().window().maximize();

        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //получение ссылки на страницу из файла настроек
        driver.get(ConfProperties.getProperty("login-page"));
    }

    @Test
    public void logInTest() {
        loginPage.inputEmail(ConfProperties.getProperty("email2"));
        loginPage.clickLogInButton();
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        loginPage.clickLogInButton();
    }
}