package ru.praktikum.methods;

import io.restassured.response.ValidatableResponse;
import ru.praktikum.request.OrderRequest;
import ru.praktikum.RestClient;

import static io.restassured.RestAssured.given;

public class OrderMethods extends RestClient {
    public ValidatableResponse create(OrderRequest orderRequest) {
        return given().log().all()
                .spec(getDefaultRequest())
                .body(orderRequest)
                .post("/api/v1/orders")
                .prettyPeek()
                .then();
    }

    public ValidatableResponse orderList(Integer id) {
        return given().log().all()
                .spec(getDefaultRequest())
                //.queryParam("courierId", id)
                .get("/api/v1/orders")
                .prettyPeek()
                .then();
    }

    public ValidatableResponse cancel(OrderRequest orderRequest) {
        return given().log().all()
                .spec(getDefaultRequest())
                .body(orderRequest)
                .put("/api/v1/orders/cancel")
                .prettyPeek()
                .then();
    }
}
