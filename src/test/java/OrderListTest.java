import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.generator.LoginRequestGenerator;
import ru.praktikum.methods.CourierMethods;
import ru.praktikum.methods.OrderMethods;
import ru.praktikum.request.CreateCourierRequest;
import ru.praktikum.request.LoginRequest;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;
import static ru.praktikum.generator.CourierRequestGenerator.getRandomCourierRequest;

public class OrderListTest {

    private CourierMethods courierMethods;
    private OrderMethods orderMethods;
    private Integer id;


    @Before
    public void setUp() {
        courierMethods = new CourierMethods();
        orderMethods = new OrderMethods();
    }

    @After
    public void tearDown() {
        if (id != null) {
            courierMethods.delete(id)
                    .assertThat()
                    .body("ok",equalTo(true));
        }
    }

    @Test
    public void getOrderList() {
        CreateCourierRequest randomCourier = getRandomCourierRequest();

        courierMethods.create(randomCourier)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));

        LoginRequest loginRequest = LoginRequestGenerator.from(randomCourier);

        id = courierMethods.login(loginRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue())
                .extract()
                .path("id");

        orderMethods.orderList(id)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("orders", notNullValue());

    }
}
