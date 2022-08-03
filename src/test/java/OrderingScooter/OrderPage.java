package OrderingScooter;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertTrue;

public class OrderPage {
    private WebDriver driver;
    private Cookie newCookie;
    // переменная для результата теста
    private boolean result;
    // URL главной страницы Яндекс.Самоката
    private final String openPage = "https://qa-scooter.praktikum-services.ru/";
    // локатор кнопки "Заказать" вверху главной страницы»
    private final By buttonOrderTop = By.className("Button_Button__ra12g");
    // локатор кнопки "Заказать" посредине главной страницы»
    private final By buttonOrderMiddle = By.xpath(".//div[@id='root']/div/div/div[4]/div[2]/div[5]/button");
    // локатор формы заказа "Для кого самокат"
    private final By OrderWindow = By.className("Order_Header__BZXOb");
    // локатор поля "Имя"
    private final By name = By.xpath(".//div[@id='root']/div/div[2]/div[2]/div/input");
    // локатор поля "Фамилия"
    private final By surname = By.xpath(".//div[@id='root']/div/div[2]/div[2]/div[2]/input");
    // локатор поля "Адрес: куда привезти самокат"
    private final By address = By.xpath(".//div[@id='root']/div/div[2]/div[2]/div[3]/input");
    // локатор поля "Станция метро"
    private final By metro = By.xpath(".//div[@id='root']/div/div[2]/div[2]/div[4]/div/div/input");
    // локатор поля "Телефон: на него позвонит курьер"
    private final By telephone = By.xpath(".//div[@id='root']/div/div[2]/div[2]/div[5]/input");
    // локатор кнопки "Далее" в форме "Для кого самокат"
    private final By buttonFurther = By.xpath(".//div[@id='root']/div/div[2]/div[3]/button");
    // локатор формы заказа "Про аренду"
    private final By OrderWindow2 = By.className("Order_Header__BZXOb");
    // локатор поля "Когда привезти самокат"
    private final By date = By.xpath(".//div[@id='root']/div/div[2]/div/div/div/div/input");
    // локатор поля "Срок аренды"
    private final By rentalPeriod = By.xpath(".//div[@id='root']/div/div[2]/div/div[2]/div/div");
    // локатор поля "Комментарий для курьера"
    private final By comment = By.xpath(".//div[@id='root']/div/div[2]/div[2]/div[4]/input");
    // локатор кнопки "Заказать"
    private final By buttonOrder = By.xpath(".//div[@id='root']/div/div[2]/div[3]/button[2]");
    // локатор формы заказа "Хотите оформить заказ?"
    private final By OrderWindow3 = By.className("Order_ModalHeader__3FDaJ");
    // локатор кнопки подтверждения "Да"
    private final By buttonYes = By.xpath(".//div[@id='root']/div/div[2]/div[5]/div[2]/button[2]");
    // локатор всплывающего окна "Заказ оформлен"
    private final By OrderHasBeenPlaced = By.className("Order_Modal__YZ-d3");

    // ####### Первый набор данных "Маша Петрова" (используется в первом и третьем сценариях) #######
    private final String nameForMasha = "Маша";
    private final String surnameForMasha = "Петрова";
    private final String addressForMasha = "ул. Правды, д.4";
    private final String metroTextForMasha = "Белорусская";
    // локатор значения "Белорусская" поля "Станция метро"
    private final By metroValueForMasha = By.xpath(".//div[@class='select-search__select']/ul//li/button[@value='29']");
    private final String telephoneForMasha = "+79809112222";
    // локатор навигации на месяц вперед
    private final By dateMonthForMasha = By.xpath(".//div[@class='react-datepicker']/button[2]");
    // локатор 16 числа выбранного месяца
    private final By dateNumberForMasha = By.xpath(".//div[@class='react-datepicker__day react-datepicker__day--016']");
    // локатор значения периода аренды "сутки"
    private final By rentalPeriodValueForMasha = By.xpath(".//div[@class='Dropdown-option'][1]");
    // локатор чекбокса "черный цвет" поля "Цвет самоката"
    private final By colorBlack = By.xpath(".//input[@id='black']");
    private final String commentForMasha = "Не звонить!";

    // ####### Второй набор данных "Вася Пупкин" (используется во втором и четвертом сценариях) #######
    private final String nameForVasya = "Вася";
    private final String surnameForVasya = "Пупкин";
    private final String addressForVasya = "ул. Большая Академическая, 50";
    private final String metroTextForVasya = "Тимирязевская";
    // локатор значения "Тимирязевская" поля "Станция метро"
    private final By metroValueForVasya = By.xpath(".//div[@class='select-search__select']/ul//li/button[@value='157']");
    private final String telephoneForVasya = "+79113333333";
    // локатор текущего месяца
    //private final By dateMonthForVasya = By.xpath(".//div[@class='react-datepicker__current-month]");
    // локатор 30 числа текущего месяца
    private final By dateNumberForVasya = By.xpath(".//div[@class='react-datepicker__day react-datepicker__day--030']");
    // локатор значения периода аренды "трое суток"
    private final By rentalPeriodValueForVasya = By.xpath(".//div[@class='Dropdown-option'][3]");
    // локатор чекбокса "серый цвет" поля "Цвет самоката"
    private final By colorGrey = By.xpath(".//input[@id='grey']");
    private final String commentForVasya = "Предварительно позвонить не менее, чем за полчаса";

    // конструктор для страницы заказа
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // объединение методов-сценариев
    public void OrderShouldBePlaced_Scripts() {
        OrderShouldBePlaced_Script1();
        OrderShouldBePlaced_Script2();
        OrderShouldBePlaced_Script3();
        OrderShouldBePlaced_Script4();
    }

    // ########## Методы-сценарии ############
    public void OrderShouldBePlaced_Script1() {
        System.out.println("Первый сценарий: ");
        System.out.println("- клик по кнопке 'Заказать' вверху главной страницы,");
        System.out.println("- заполнение формы заказа первым набором данных 'Маша Петрова',");
        System.out.println("- проверка появления всплывающего окна с сообщением об успешном создании заказа");
        // Открываем главную страницу сервиса Яндекс.Самокат
        driver.get(openPage);
        // Страхуемся от помех из-за куки, добавляем свой куки
        newCookie = new Cookie("my_own_cookie", "olga");
        driver.manage().addCookie(newCookie);
        // Кликаем на верхней кнопке "Заказать"
        driver.findElement(buttonOrderTop).click();
        // Ожидаем отображение формы заказа "Для кого самокат"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow));
        // Заполняем поля ввода формы "Для кого самокат"
        driver.findElement(name).sendKeys(nameForMasha);
        driver.findElement(surname).sendKeys(surnameForMasha);
        driver.findElement(address).sendKeys(addressForMasha);
        // Выбор станции метро из выпадающего списка
        driver.findElement(metro).click();
        driver.findElement(metro).sendKeys(metroTextForMasha);
        driver.findElement(metroValueForMasha).click();
        driver.findElement(telephone).sendKeys(telephoneForMasha);
        // Кликаем по кнопке "Далее" в форме "Для кого самокат"
        driver.findElement(buttonFurther).click();
        // Ожидаем отображение формы заказа "Про аренду"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow2));
        // Заполняем поля ввода формы "Про аренду"
        // Кликаем в поле ввода даты начала аренды
        driver.findElement(date).click();
        // Выбираем следующий месяц кликом по правой навигационной стрелке
        driver.findElement(dateMonthForMasha).click();
        // В выбранном месяце кликаем на дату 16
        driver.findElement(dateNumberForMasha).click();
        // Кликаем в поле выбора периода аренды из выпадающего списка
        driver.findElement(rentalPeriod).click();
        // Выбираем период "сутки"
        driver.findElement(rentalPeriodValueForMasha).click();
        // Выбор цвета самоката: черный
        driver.findElement(colorBlack).click();
        // Вносим текст комментария
        driver.findElement(comment).sendKeys(commentForMasha);
        // Кликаем по кнопке "Заказать" в форме "Про аренду"
        driver.findElement(buttonOrder).click();
        // Ожидаем отображение формы "Хотите оформить заказ?"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow3));
        // Кликаем по кнопке подтверждения "Да"
        driver.findElement(buttonYes).click();
        // Проверяем отображение всплывающего окна "Заказ оформлен"
        result = driver.findElement(OrderHasBeenPlaced).isDisplayed();
        if (result) {
            System.out.println("Заказ по первому сценарию успешно оформлен");
        } else {
            System.out.println("Тест по первому сценарию завершился с ошибкой");
        }
        assertTrue(result);
        // Удаляем свой куки
        driver.manage().deleteCookie(newCookie);

    }

    public void OrderShouldBePlaced_Script2() {
        System.out.println("Второй сценарий: ");
        System.out.println("- клик по кнопке 'Заказать' вверху главной страницы,");
        System.out.println("- заполнение формы заказа вторым набором данных 'Вася Пупкин',");
        System.out.println("- проверка появления всплывающего окна с сообщением об успешном создании заказа");
        driver.get(openPage);
        newCookie = new Cookie("my_own_cookie", "olga");
        driver.manage().addCookie(newCookie);
        // Кликаем на верхней кнопке "Заказать"
        driver.findElement(buttonOrderTop).click();
        // Ожидаем отображение формы заказа "Для кого самокат"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow));
        // Заполняем поля ввода формы "Для кого самокат"
        driver.findElement(name).sendKeys(nameForVasya);
        driver.findElement(surname).sendKeys(surnameForVasya);
        driver.findElement(address).sendKeys(addressForVasya);
        // Выбор станции метро из выпадающего списка
        driver.findElement(metro).click();
        driver.findElement(metro).sendKeys(metroTextForVasya);
        driver.findElement(metroValueForVasya).click();
        driver.findElement(telephone).sendKeys(telephoneForVasya);
        // Кликаем по кнопке "Далее" в форме "Для кого самокат"
        driver.findElement(buttonFurther).click();
        // Ожидаем отображение формы заказа "Про аренду"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow2));
        // Заполняем поля ввода формы "Про аренду"
        // Кликаем в поле ввода даты начала аренды
        driver.findElement(date).click();
        // Выбираем по умолчанию текущий месяц
        // В выбранном месяце кликаем на дату
        driver.findElement(dateNumberForVasya).click();
        // Кликаем в поле выбора периода аренды из выпадающего списка
        driver.findElement(rentalPeriod).click();
        // Выбираем период "трое суток"
        driver.findElement(rentalPeriodValueForVasya).click();
        // Выбор цвета самоката: серый
        driver.findElement(colorGrey).click();
        // Вносим текст комментария
        driver.findElement(comment).sendKeys(commentForVasya);
        // Кликаем по кнопке "Заказать" в форме "Про аренду"
        driver.findElement(buttonOrder).click();
        // Ожидаем отображение формы "Хотите оформить заказ?"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow3));
        // Кликаем по кнопке подтверждения "Да"
        driver.findElement(buttonYes).click();
        // Проверяем отображение всплывающего окна "Заказ оформлен"
        result = driver.findElement(OrderHasBeenPlaced).isDisplayed();
        if (result) {
            System.out.println("Заказ по второму сценарию успешно оформлен");
        } else {
            System.out.println("Тест по второму сценарию завершился с ошибкой");
        }
        assertTrue(result);
        // Удаляем свой куки
        driver.manage().deleteCookie(newCookie);
    }

    public void OrderShouldBePlaced_Script3() {
        System.out.println("Третий сценарий: ");
        System.out.println("- клик по кнопке 'Заказать' посредине главной страницы,");
        System.out.println("- заполнение формы заказа первым набором данных 'Маша Петрова',");
        System.out.println("- проверка появления всплывающего окна с сообщением об успешном создании заказа");
        // Открываем главную страницу сервиса Яндекс.Самокат
        driver.get(openPage);
        // Страхуемся от помех из-за куки, добавляем свой куки
        newCookie = new Cookie("my_own_cookie", "olga");
        driver.manage().addCookie(newCookie);
        // Ожидаем отображение кнопки "Заказать" посредине главной страницы
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(buttonOrderMiddle));
        // Скроллим до кнопки "Заказать" посредине главной страницы
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(buttonOrderMiddle));
        // Кликаем на кнопке "Заказать" посредине главной страницы
        driver.findElement(buttonOrderMiddle).click();
        // Ожидаем отображение формы заказа "Для кого самокат"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow));
        // Заполняем поля ввода формы "Для кого самокат"
        driver.findElement(name).sendKeys(nameForMasha);
        driver.findElement(surname).sendKeys(surnameForMasha);
        driver.findElement(address).sendKeys(addressForMasha);
        // Выбор станции метро из выпадающего списка
        driver.findElement(metro).click();
        driver.findElement(metro).sendKeys(metroTextForMasha);
        driver.findElement(metroValueForMasha).click();
        driver.findElement(telephone).sendKeys(telephoneForMasha);
        // Кликаем по кнопке "Далее" в форме "Для кого самокат"
        driver.findElement(buttonFurther).click();
        // Ожидаем отображение формы заказа "Про аренду"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow2));
        // Заполняем поля ввода формы "Про аренду"
        // Кликаем в поле ввода даты начала аренды
        driver.findElement(date).click();
        // Выбираем следующий месяц кликом по правой навигационной стрелке
        driver.findElement(dateMonthForMasha).click();
        // В выбранном месяце кликаем на дату 16
        driver.findElement(dateNumberForMasha).click();
        // Кликаем в поле выбора периода аренды из выпадающего списка
        driver.findElement(rentalPeriod).click();
        // Выбираем период "сутки"
        driver.findElement(rentalPeriodValueForMasha).click();
        // Выбор цвета самоката: черный
        driver.findElement(colorBlack).click();
        // Вносим текст комментария
        driver.findElement(comment).sendKeys(commentForMasha);
        // Кликаем по кнопке "Заказать" в форме "Про аренду"
        driver.findElement(buttonOrder).click();
        // Ожидаем отображение формы "Хотите оформить заказ?"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow3));
        // Кликаем по кнопке подтверждения "Да"
        driver.findElement(buttonYes).click();
        // Проверяем отображение всплывающего окна "Заказ оформлен"
        result = driver.findElement(OrderHasBeenPlaced).isDisplayed();
        if (result) {
            System.out.println("Заказ по третьему сценарию успешно оформлен");
        } else {
            System.out.println("Тест по третьему сценарию завершился с ошибкой");
        }
        assertTrue(result);
        // Удаляем свой куки
        driver.manage().deleteCookie(newCookie);
    }

    public void OrderShouldBePlaced_Script4() {
        System.out.println("Четвертый сценарий: ");
        System.out.println("- клик по кнопке 'Заказать' посредине главной страницы,");
        System.out.println("- заполнение формы заказа вторым набором данных 'Вася Пупкин',");
        System.out.println("- проверка появления всплывающего окна с сообщением об успешном создании заказа");
        driver.get(openPage);
        newCookie = new Cookie("my_own_cookie", "olga");
        driver.manage().addCookie(newCookie);
        // Ожидаем отображение кнопки "Заказать" посредине главной страницы
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(buttonOrderMiddle));
        // Скроллим до кнопки "Заказать" посредине главной страницы
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(buttonOrderMiddle));
        // Кликаем на кнопке "Заказать" посредине главной страницы
        driver.findElement(buttonOrderMiddle).click();
        // Ожидаем отображение формы заказа "Для кого самокат"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow));
        // Заполняем поля ввода формы "Для кого самокат"
        driver.findElement(name).sendKeys(nameForVasya);
        driver.findElement(surname).sendKeys(surnameForVasya);
        driver.findElement(address).sendKeys(addressForVasya);
        // Выбор станции метро из выпадающего списка
        driver.findElement(metro).click();
        driver.findElement(metro).sendKeys(metroTextForVasya);
        driver.findElement(metroValueForVasya).click();
        driver.findElement(telephone).sendKeys(telephoneForVasya);
        // Кликаем по кнопке "Далее" в форме "Для кого самокат"
        driver.findElement(buttonFurther).click();
        // Ожидаем отображение формы заказа "Про аренду"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow2));
        // Заполняем поля ввода формы "Про аренду"
        // Кликаем в поле ввода даты начала аренды
        driver.findElement(date).click();
        // Выбираем по умолчанию текущий месяц
        // В выбранном месяце кликаем на дату
        driver.findElement(dateNumberForVasya).click();
        // Кликаем в поле выбора периода аренды из выпадающего списка
        driver.findElement(rentalPeriod).click();
        // Выбираем период "трое суток"
        driver.findElement(rentalPeriodValueForVasya).click();
        // Выбор цвета самоката: серый
        driver.findElement(colorGrey).click();
        // Вносим текст комментария
        driver.findElement(comment).sendKeys(commentForVasya);
        // Кликаем по кнопке "Заказать" в форме "Про аренду"
        driver.findElement(buttonOrder).click();
        // Ожидаем отображение формы "Хотите оформить заказ?"
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(OrderWindow3));
        // Кликаем по кнопке подтверждения "Да"
        driver.findElement(buttonYes).click();
        // Проверяем отображение всплывающего окна "Заказ оформлен"
        result = driver.findElement(OrderHasBeenPlaced).isDisplayed();
        if (result) {
            System.out.println("Заказ по четвертому сценарию успешно оформлен");
        } else {
            System.out.println("Тест по четвертому сценарию завершился с ошибкой");
        }
        assertTrue(result);
        // Удаляем свой куки
        driver.manage().deleteCookie(newCookie);
    }
}
