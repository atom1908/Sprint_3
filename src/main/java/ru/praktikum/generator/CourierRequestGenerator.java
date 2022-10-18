package ru.praktikum.generator;

import org.apache.commons.lang3.RandomStringUtils;
import ru.praktikum.request.CreateCourierRequest;


public class CourierRequestGenerator {
    public static CreateCourierRequest getRandomCourierRequest() {
        CreateCourierRequest courierRequest = new CreateCourierRequest();
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(5));
        courierRequest.setPassword("1234");
        courierRequest.setFirstname("familiya");
        return courierRequest;
    }
    public static CreateCourierRequest loginMissing() {
        CreateCourierRequest courierRequest = new CreateCourierRequest();
        courierRequest.setPassword("1234");
        courierRequest.setFirstname("familiya");
        return courierRequest;
    }

    public static CreateCourierRequest passwordMissing() {
        CreateCourierRequest courierRequest = new CreateCourierRequest();
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(5));
        courierRequest.setFirstname("familiya");
        return courierRequest;
    }
}
