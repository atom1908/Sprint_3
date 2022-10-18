import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.generator.LoginRequestGenerator;
import ru.praktikum.methods.CourierMethods;
import ru.praktikum.request.CreateCourierRequest;
import ru.praktikum.request.LoginRequest;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static ru.praktikum.generator.CourierRequestGenerator.*;

public class CreateCourierTest {

    private CourierMethods courierMethods;
    private Integer id;

    @Before
    public void setUp() {
        courierMethods = new CourierMethods();
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
    @DisplayName("Создание курьера")
    @Description("Тест создания курьера")
    public void addCourier() {
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
    }

    @Step("Отсутствует поле логин")
    public void loginFieldMissingError() {
        CreateCourierRequest randomCourier = loginMissing();

        courierMethods.create(randomCourier)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }

    @Test
    public void passwordFieldMissingError() {
        CreateCourierRequest randomCourier = passwordMissing();

        courierMethods.create(randomCourier)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }

    @Test
    public void userExistsError() {
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

        courierMethods.create(randomCourier)
                .assertThat()
                .statusCode(SC_CONFLICT)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

}

