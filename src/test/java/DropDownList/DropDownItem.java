package DropDownList;
import OrderingScooter.OrderPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

public class DropDownItem {
    // переменная для драйвера браузера
    private final WebDriver driver;
    // переменная для куки
    private Cookie newCookie;
    // стартовая веб-страница
    private final String openPage = "https://qa-scooter.praktikum-services.ru/";
    // переменная для номера пункта выпадающего списка
    private final int i;
    // переменная для ОР теста вопроса
    private final String textQuestion;
    // переменная для ОР теста ответа
    private final String textResponse;
    // локатор раздела «Вопросы о важном»
    private By divQuestions = By.xpath(".//div[@class='Home_FourPart__1uthg']/div[@class='Home_SubHeader__zwi_E']");
    // локатор-переменная вопроса пункта i выпадающего списка
    private By fieldQuestion;
    // локатор-переменная ответа пункта i выпадающего списка
    private By fieldResponse;
    // конструктор
    public DropDownItem(WebDriver driver, int i, String textQuestion, String textResponse){
        this.driver = driver;
        this.i = i;
        this.textQuestion = textQuestion;
        this.textResponse = textResponse;
    }
    public void shouldBeClickableAndCheckTexts(){
        // Формируем локатор для вопроса пункта i
        fieldQuestion = By.id("accordion__heading-"+i);
        // Формируем локатор для ответа пункта i
        fieldResponse = By.id("accordion__panel-"+i);
        // Открываем главную страницу сервиса Яндекс.Самокат
        driver.get(openPage);
        // Страхуемся от помех из-за куки, добавляем свой куки
        newCookie = new Cookie("my_own_cookie", "olga");
        driver.manage().addCookie(newCookie);
        // Страхуемся от помех. Скроллим до раздела «Вопросы о важном»
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(divQuestions));
        // Ожидаем отображение i-го пункта выпадающего списка
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(fieldQuestion));
        // Скроллим до i-го пункта выпадающего списка
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(fieldQuestion));
        System.out.println((i+1) + ". " + driver.findElement(fieldQuestion).getText());
        // Проверяем текст вопроса i-го пункта выпадающего списка
        assertEquals(textQuestion, driver.findElement(fieldQuestion).getText());
        // Кликаем на i-м пункте выпадающего списка
        driver.findElement(fieldQuestion).click();
        // Ожидаем появления ответа на i-ый вопрос
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(fieldResponse));
        System.out.println("       " + driver.findElement(fieldResponse).getText());
        // Получаем текст i-го ответа и сравниваем с правильным текстом
        assertEquals(textResponse, driver.findElement(fieldResponse).getText());

        // Удаляем свой куки
        driver.manage().deleteCookie(newCookie);

    }
}
