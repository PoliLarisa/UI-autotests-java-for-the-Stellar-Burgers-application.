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
import pages.AccountPage;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static pages.LoginPage.LOGIN_PAGE_URL;
import static pages.MainPage.MAIN_PAGE_URL;
import static pages.AccountPage.PROFILE_PAGE_URL;

@Epic("Creating new user role")
@Feature("Profile")
public class AccountTest {

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
            userClient.deletingUser(auth);
        }

    }

    @Test
    @DisplayName("Profile test")
    @Description("Check Profile page with validation")
    public void personalAccountTest() {
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickEnterButton();

        mainPage.clickPersonalAccountButton();

        webdriver().shouldHave(url(PROFILE_PAGE_URL));
    }

    @Test
    @DisplayName("Click constructor button")
    @Description("Check click constructor button from Profile page")
    public void constructorFromPersonalAccountTest() {
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickEnterButton();

        mainPage.clickPersonalAccountButton();

        AccountPage profilePage = page(AccountPage.class);
        profilePage.clickConstructorButton();

        webdriver().shouldHave(url(MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Click logo image")
    @Description("Check click logo image from Profile page")
    public void constructorLogoImageFromPersonalAccountTest() {
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickEnterButton();

        mainPage.clickPersonalAccountButton();

        AccountPage profilePage = page(AccountPage.class);
        profilePage.clickLogoImage();

        webdriver().shouldHave(url(MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Exit from account")
    @Description("Check exit from Profile page")
    public void exitFromAccountTest() {
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickEnterButton();

        mainPage.clickPersonalAccountButton();

        AccountPage profilePage = page(AccountPage.class);
        profilePage.clickExitButton();

        webdriver().shouldHave(url(LOGIN_PAGE_URL));
    }
}