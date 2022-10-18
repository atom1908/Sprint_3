import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.generator.LoginRequestGenerator;
import ru.praktikum.methods.CourierMethods;
import ru.praktikum.request.CreateCourierRequest;
import ru.praktikum.request.LoginRequest;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static ru.praktikum.generator.CourierRequestGenerator.*;

public class LoginCourierTest {
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
                    .body("ok", equalTo(true));
        }
    }

    @Test
    public void loginFieldMissingError() {
        LoginRequest loginRequest = LoginRequestGenerator.loginMissing(loginMissing());

        courierMethods.login(loginRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void passwordFieldMissingError() {
        LoginRequest loginRequest = LoginRequestGenerator.passwordMissing(passwordMissing());

        courierMethods.login(loginRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void invalidPassword() {
        CreateCourierRequest randomCourier = getRandomCourierRequest();

        courierMethods.create(randomCourier)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));

        LoginRequest loginRequest = LoginRequestGenerator.invalidPassword(randomCourier);

        courierMethods.login(loginRequest)
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));

    }
}