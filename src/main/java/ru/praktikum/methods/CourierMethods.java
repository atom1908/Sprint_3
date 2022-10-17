package ru.praktikum.methods;

import io.restassured.response.ValidatableResponse;
import ru.praktikum.request.CreateCourierRequest;
import ru.praktikum.request.LoginRequest;
import ru.praktikum.RestClient;

import static io.restassured.RestAssured.given;

public class CourierMethods extends RestClient {
    public ValidatableResponse create(CreateCourierRequest courierRequest) {
        return given().log().all()
                .spec(getDefaultRequest())
                .body(courierRequest)
                .post("/api/v1/courier")
                .prettyPeek()
                .then();
    }

    public ValidatableResponse login(LoginRequest loginRequest) {
        return given().log().all()
                .spec(getDefaultRequest())
                .body(loginRequest)
                .post("/api/v1/courier/login")
                .prettyPeek()
                .then();
    }

    public ValidatableResponse delete(int id) {
        return given().log().all()
                .spec(getDefaultRequest())
                .delete("/api/v1/courier/" + id)
                .prettyPeek()
                .then();
    }
}
