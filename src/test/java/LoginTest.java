import clients.User;
import clients.UserClient;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoverPage;
import pages.RegistrationPage;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static pages.MainPage.MAIN_PAGE_URL;

@Epic("Creating new user role")
@Feature("Validation")
public class LoginTest {

    private User user;
    private UserClient userClient;
    private String auth;
    private MainPage mainPage;

    @Before
    public void setUp() {
        // запуск теста в другом браузере
        Configuration.browser = "edge";
        userClient = new UserClient();
        user = User.getRandom();
        ValidatableResponse response = userClient.userCreate(user);
        auth = response.extract().path("accessToken");
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void tearDown() {
        if (auth != null) {
            userClient.deletUser(auth);
        }
    }

    @Test
    @DisplayName("Validation test")
    @Description("Validation test with click enter button from Main page")
    public void enterButtonMainPageTest() {
        mainPage.clickAccountEntryButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickEnterButton();

        webdriver().shouldHave(url(MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Validation test")
    @Description("Validation test with click personal account button from Main page")
    public void personalAccountButtonMainPageTest() {
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickEnterButton();

        webdriver().shouldHave(url(MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Validation test")
    @Description("Validation test with click enter button from Registration page")
    public void enterButtonRegistrationPageTest() {
        mainPage.clickAccountEntryButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegistrationButton();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.clickEnterReference();

        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickEnterButton();

        webdriver().shouldHave(url(MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Validation test")
    @Description("Validation test with click enter button from Recover page")
    public void enterButtonRecoverPageTest() {
        mainPage.clickAccountEntryButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRecoverButton();

        RecoverPage recoverPage = page(RecoverPage.class);
        recoverPage.clickEnterButton();

        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickEnterButton();

        webdriver().shouldHave(url(MAIN_PAGE_URL));
    }
}