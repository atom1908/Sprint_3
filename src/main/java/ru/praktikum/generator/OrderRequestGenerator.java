package ru.praktikum.generator;

import ru.praktikum.request.OrderRequest;

import java.util.List;

public class OrderRequestGenerator {
    public static OrderRequest createOrder(String name, String lastname, String address, String metro, String phone, Integer renttime, String deliverydate, String comment, List<String> colour) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setFirstName(name);
        orderRequest.setLastName(lastname);
        orderRequest.setAddress(address);
        orderRequest.setMetroStation(metro);
        orderRequest.setPhone(phone);
        orderRequest.setRentTime(renttime);
        orderRequest.setDeliveryDate(deliverydate);
        orderRequest.setComment(comment);
        orderRequest.setColor(colour);
        return orderRequest;
    }

    public static OrderRequest cancelOrder(Integer id) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setTrack(id);
        return orderRequest;
    }
}
