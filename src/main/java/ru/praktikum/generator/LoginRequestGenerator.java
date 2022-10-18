package ru.praktikum.generator;

import ru.praktikum.request.CreateCourierRequest;
import ru.praktikum.request.LoginRequest;

public class LoginRequestGenerator {
    public static LoginRequest from(CreateCourierRequest courierRequest) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(courierRequest.getLogin());
        loginRequest.setPassword(courierRequest.getPassword());
        return loginRequest;
    }

    public static LoginRequest loginMissing(CreateCourierRequest courierRequest) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(courierRequest.getPassword());
        return loginRequest;
    }

    public static LoginRequest passwordMissing(CreateCourierRequest courierRequest) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(courierRequest.getLogin());
        return loginRequest;
    }

    public static LoginRequest invalidPassword(CreateCourierRequest courierRequest) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(courierRequest.getLogin());
        loginRequest.setPassword("4321");
        return loginRequest;
    }
}
