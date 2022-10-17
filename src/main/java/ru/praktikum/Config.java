package ru.praktikum;

import io.restassured.RestAssured;

public class Config {
    private static String BASE_URI;

    public static String getBaseUri() {
        BASE_URI = RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        return BASE_URI;
    }

}
