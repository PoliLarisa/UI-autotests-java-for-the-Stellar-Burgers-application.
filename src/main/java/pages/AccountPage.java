package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;

public class AccountPage {

    public static final String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    // локаторы
    // поле "Имя"
    @FindBy(how = How.XPATH, using = "//*[contains (text(), 'Имя')]")
    private SelenideElement nameField;

    // поле "Логин"
    @FindBy(how = How.XPATH, using = "//*[contains (text(), 'Логин')]")
    private SelenideElement loginField;

    // поле "Пароль"
    @FindBy(how = How.XPATH, using = "//*[contains (text(), 'Пароль')]")
    private SelenideElement passwordField;

    // кнопка "Профиль"
    @FindBy(how = How.XPATH, using = "//*[contains (text(), 'Профиль')]")
    private SelenideElement profileButton;

    // кнопка "История заказов"
    @FindBy(how = How.XPATH, using = "//*[contains (text(), 'История заказов')]")
    private SelenideElement historyOrdersButton;

    // кнопка "Выход"
    @FindBy(how = How.XPATH, using = "//*[contains (text(), 'Выход')]")
    private SelenideElement exitButton;

    // кнопка "Сохранить"
    @FindBy(how = How.XPATH, using = "//*[contains (text(), 'Сохранить')]")
    private SelenideElement saveButton;

    // кнопка "Конструктор"
    @FindBy(how = How.XPATH, using = "//*[contains (text(), 'Конструктор')]")
    private SelenideElement constructorButton;

    // логотип сайта "Stellar Burgers"
    @FindBy(how = How.XPATH, using = "//*[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement logoImage;

    // методы
    @Step("Set name")
    public AccountPage setNameField(String name) {
        nameField.setValue(name);
        return this;
    }

    @Step("Set login")
    public AccountPage setLoginField(String login) {
        loginField.setValue(login);
        return this;
    }

    @Step("Set password")
    public AccountPage setPasswordField(String password) {
        passwordField.setValue(password);
        return this;
    }

    @Step("Click button 'Профиль'")
    public AccountPage clickProfileButton() {
        profileButton.click();
        return this;
    }


    @Step("Click button 'Выход'")
    public LoginPage clickExitButton() {
        exitButton.click();
        return page(LoginPage.class);
    }

    @Step("Click button 'Сохранить'")
    public AccountPage clickSaveButton() {
        profileButton.click();
        return this;
    }

    @Step("Click button 'Конструктор'")
    public MainPage clickConstructorButton() {
        constructorButton.click();
        return page(MainPage.class);
    }

    @Step("Click logo image")
    public MainPage clickLogoImage() {
        logoImage.click();
        return page(MainPage.class);
    }
}