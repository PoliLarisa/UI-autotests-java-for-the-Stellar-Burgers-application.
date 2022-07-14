package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;

public class RecoverPage {

    public static final String RECOVER_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    // локаторы
    // кнопка "Восстановить"
    @FindBy(how = How.XPATH, using = "//*[contains (text(), 'Восстановить')]")
    private SelenideElement recoverButton;

    // кнопка "Войти"
    @FindBy(how = How.XPATH, using = "//*[contains (text(), 'Войти')]")
    private SelenideElement enterButton;


    @Step("Click button 'Войти'")
    public LoginPage clickEnterButton () {
        enterButton.click();
        return page(LoginPage.class);
    }
}
