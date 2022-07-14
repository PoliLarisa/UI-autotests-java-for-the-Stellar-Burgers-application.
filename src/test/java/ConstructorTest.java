import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;
import static pages.MainPage.MAIN_PAGE_URL;

@Epic("Creating new user role")
@Feature("Constructor data")
public class ConstructorTest {

    private MainPage mainPage;

    @Before
    public void setUp() {
        // запуск теста в другом браузере
        Configuration.browser = "edge";
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }


    @Test
    @DisplayName("Check buns")
    @Description("Checking click buns button and is displayed")
    public void checkTransitionOfBunsButton() {
        MainPage main = open(MAIN_PAGE_URL, MainPage.class);
        assertTrue("After drag and drop the bun in the order basket must be visible", main.clickBunsButtonCheckTheSign());
    }


    @Test
    @DisplayName("Check sauces")
    @Description("Checking click sauces button and is displayed")
    public void checkTransitionOfSaucesButton() {
        MainPage main = open(MAIN_PAGE_URL, MainPage.class);
        assertTrue("After drag and drop the sauce in the order basket must be visible", main.clickSaucesButtonAndCheckTheSign());
    }


    @Test
    @DisplayName("Check meats")
    @Description("Checking click meats button and is displayed")
    public void checkTransitionOfFillingButton() {
        MainPage main = open(MAIN_PAGE_URL, MainPage.class);
        assertTrue("After drag and drop the filling in the order basket must be visible", main.clickFillingButtonAndCheckTheSign());
    }

}