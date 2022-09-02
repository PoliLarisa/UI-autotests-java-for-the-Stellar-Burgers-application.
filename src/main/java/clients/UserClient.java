package clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {

    private static final String USER_PATH = "/api/auth/";

    @Step("Creating user")
    public ValidatableResponse userCreate(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_PATH + "register")
                .then().log().all();
    }

    @Step("User login")
    public ValidatableResponse loginUser(User user) {

        HashMap<String,String> dataBody = new HashMap<String,String>();

        dataBody.put("email", user.getEmail());
        dataBody.put("password", user.getPassword());

        return given()
                .spec(getBaseSpec())
                .body(dataBody)
                .when()
                .post(USER_PATH + "login")
                .then();
    }

    @Step("Logout user")
    public ValidatableResponse logout(String refreshToken) {
        return given()
                .spec(getBaseSpec())
                .body(refreshToken)
                .when()
                .post(USER_PATH + "logout")
                .then().log().all();
    }

    @Step("Deleting user")

    public ValidatableResponse deletUser(String auth) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", auth)
                .when()
                .delete(USER_PATH + "user")
                .then();
    }

}