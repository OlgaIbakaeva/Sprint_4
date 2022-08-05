package dropdownlist;

import scooter.pageobjects.DropDownItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestDropDownList {

    private WebDriver driver;
    // Массив вопросов для выпадающего списка в разделе "Вопросы о важном"
    private final String[] expectedQuestionsDropDownList={"Сколько это стоит? И как оплатить?","Хочу сразу несколько самокатов! Так можно?","Как рассчитывается время аренды?","Можно ли заказать самокат прямо на сегодня?","Можно ли продлить заказ или вернуть самокат раньше?","Вы привозите зарядку вместе с самокатом?","Можно ли отменить заказ?","Я жизу за МКАДом, привезёте?"};
    // Массив ответов для выпадающего списка в разделе "Вопросы о важном"
    private final String[] expectedResponsesDropDownList={"Сутки — 400 рублей. Оплата курьеру — наличными или картой.","Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.","Только начиная с завтрашнего дня. Но скоро станем расторопнее.","Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.","Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.","Да, обязательно. Всем самокатов! И Москве, и Московской области."};


    @Test
    public void DropDownTestInBrowsers() {
        // Создаём драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        System.out.println("Результаты автотестирования выпадающего списка в разделе 'Вопросы о важном' в браузере Google Chrome");
        // Вызываем тестовый метод
        DropDownListShouldBeClickable();
        System.out.println("Тест в браузере Google Chrome успешно завершен");
        // Закрываем браузер Chrome
        driver.quit();
        // Создаём драйвер для браузера FireFox
        driver = new FirefoxDriver();
        System.out.println("Результаты автотестирования выпадающего списка в разделе 'Вопросы о важном' в браузере Mozilla FireFox");
        // Вызываем тестовый метод
        DropDownListShouldBeClickable();
        System.out.println("Тест в браузере Mozilla FireFox успешно завершен");
        // Закрываем браузер FireFox
        driver.quit();
    }

    public void DropDownListShouldBeClickable() {
        // Проверяем в цикле каждый пункт выпадающего списка отдельно
        for (int i=0; i<expectedQuestionsDropDownList.length; i++) {
            // Создаем экземпляр POM-класса для i-го пункта выпадающего списка
            DropDownItem dropDownItem = new DropDownItem(driver, i, expectedQuestionsDropDownList[i], expectedResponsesDropDownList[i]);
            // Проверяем кликабельность i-го пункта выпадающего списка и соответствие текстов
            dropDownItem.shouldBeClickableAndCheckTexts();
        }
    }
}