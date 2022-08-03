package OrderingScooter;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestOrderingScooter {
    private WebDriver driver;
    private OrderPage orderPage;

    @Test

    public void OrderScooterTestInBrowsers() {
        // Создаём драйвер для браузера FireFox
        driver = new FirefoxDriver();
        System.out.println("Результаты автотестирования заказа самоката в браузере Mozilla FireFox");
        // Создаем экземпляр класса страницы заказа и вызываем объединенный метод сценариев тестов
        orderPage = new OrderPage(driver);
        orderPage.OrderShouldBePlaced_Scripts();
        // Закрываем браузер FireFox
        driver.quit();
        // Создаём драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        System.out.println("Результаты автотестирования заказа самоката в браузере Chrome");
        // Создаем экземпляр класса страницы заказа и вызываем объединенный метод сценариев тестов
        orderPage = new OrderPage(driver);
        orderPage.OrderShouldBePlaced_Scripts();
        // Закрываем браузер Chrome
        driver.quit();
    }
}

