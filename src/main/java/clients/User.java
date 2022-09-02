package clients;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@Builder
public class User {

    private final String email;
    private final String password;
    private final String name;

    @Step("Creation users with random credentials")
    public static User getRandom() {

        final String email = RandomStringUtils.randomAlphabetic(6) + "@testdata.com";
        final String password = RandomStringUtils.randomAlphabetic(6);
        final String name = RandomStringUtils.randomAlphabetic(6);

        Allure.addAttachment("Login", email);
        Allure.addAttachment("Password", password);
        Allure.addAttachment("Name", name);

        return new User(email, password, name);
    }
}