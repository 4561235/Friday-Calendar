package org.acme.quarkus.calendar;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CalendarEventsRestTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/calendar-events")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}