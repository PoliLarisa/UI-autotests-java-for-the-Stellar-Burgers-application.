import clients.User;
import clients.UserClient;
import clients.UserCredentials;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertTrue;
import static pages.LoginPage.LOGIN_PAGE_URL;

@Epic("Creating new user role")
@Feature("Registration")
public class RegistrationTest{

    private User user;
    private UserClient userClient;
    private MainPage mainPage;
    private String auth;



    @Before
    public void setUp() {
        // запуск теста в другом браузере
        Configuration.browser = "edge";
        userClient = new UserClient();
        user = User.getRandom();
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void tearDown() {
        if (auth != null) {
            userClient.deletUser(auth);
        }

    }

    @Test
    @DisplayName("Positive registration")
    @Description("Positive registration and check LOGIN_PAGE_URL")
    public void positiveRegistrationTest() {
        mainPage.clickAccountEntryButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegistrationButton();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.enterName(user.getName());
        registrationPage.enterEmail(user.getEmail());
        registrationPage.enterPassword(user.getPassword());
        registrationPage.register();
        webdriver().shouldHave(url(LOGIN_PAGE_URL));
        auth = userClient.loginUser(user).extract().body().path("accessToken");
    }

    @Test
    @DisplayName("Negative registration")
    @Description("Check error field for password {<5}")
    public void checkErrorFieldPasswordTest() {
        mainPage.clickAccountEntryButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegistrationButton();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.enterName(user.getName());
        registrationPage.enterEmail(user.getEmail());
        registrationPage.enterPassword(RandomStringUtils.randomAlphabetic(5));
        registrationPage.register();
        if (userClient.loginUser(user).extract().statusCode() == 200) {
            auth = userClient.loginUser(user).extract().body().path("accessToken");

        }
        assertTrue("Error password field not displayed", registrationPage.errorPasswordFieldIsDisplayed());


    }
}
