import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.generator.OrderRequestGenerator;
import ru.praktikum.methods.OrderMethods;
import ru.praktikum.request.OrderRequest;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final Integer rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;
    private OrderMethods orderMethods;

    @Before
    public void setUp() {
        orderMethods = new OrderMethods();
    }

    public CreateOrderTest(String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
    @Parameterized.Parameters
    public static Object[][] orderData() {
        return new Object[][] {
                { "Василий", "Алексеев", "Москва, ул. Ленина, д. 1 кв. 333", "Комсомольская", "+7999999999", 5, "2022-02-05", "коммент", List.of("BLACK", "GREY")},
                { "Юрий", "Иванов", "Санкт-Петербург, ул. Новая, д 1111", "Университет", "78888888888", 12, "2023-12-12", "123123", List.of("BLACK")},
                { "Николай", "Георгиев", "Казань, ул. Новая, д 1111", "Алма-Атинская", "888888888888", 652, "12.12.2022", "коммент123", List.of()},
        };
    }
    @Test
    public void createOrder() {
        OrderRequest newOrder = OrderRequestGenerator.createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        orderMethods.create(newOrder)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("track", notNullValue());

    }
}
