package com.restfulbooker;

import static data.restfulbooker.BookingDataBuilder.getBookingData;
import static data.restfulbooker.BookingDataBuilder.getPartialBookingData;
import static data.restfulbooker.TokenBuilder.getToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import data.restfulbooker.BookingData;
import data.restfulbooker.PartialBookingData;
import data.restfulbooker.Tokencreds;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created By Karan Arora on 13-04-2025
 */
@Epic("Rest Assured POC - Example Tests")
@Feature("Writing End to End tests using rest-assured")
public class RestfulBookerE2ETests extends BaseSetup {

    private BookingData newBooking;
    private int bookingId;
    private String token;

    @BeforeTest
    public void testSetup() {
        newBooking = getBookingData();
    }
    @Test
    @Description("Example test for creating new booking - Post request")
    @Severity(SeverityLevel.BLOCKER)
    @Story("End to End tests using rest-assured")
    @Step("Create new booking")
    public void createBookingTest() {
        bookingId = given().body(newBooking)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo(newBooking.getFirstname()), "booking.lastname",
                        equalTo(newBooking.getLastname()), "booking.totalprice", equalTo(newBooking.getTotalprice()),
                        "booking.depositpaid", equalTo(newBooking.isDepositpaid()), "booking.bookingdates.checkin", equalTo(
                                newBooking.getBookingdates()
                                        .getCheckin()), "booking.bookingdates.checkout", equalTo(newBooking.getBookingdates()
                                .getCheckout()), "booking.additionalneeds", equalTo(newBooking.getAdditionalneeds()))
                .extract()
                .path("bookingid");
    }

    @Test
    @Description("Example test for retrieving a booking - Get request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("End to End tests using rest-assured")
    @Step("Get a the newly created booking")
    public void getBookingTest() {
    System.out.println("----Booking id 3---");
        given().get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("firstname", equalTo(newBooking.getFirstname()), "lastname", equalTo(newBooking.getLastname()),
                        "totalprice", equalTo(newBooking.getTotalprice()), "depositpaid",
                        equalTo(newBooking.isDepositpaid()), "bookingdates.checkin", equalTo(newBooking.getBookingdates()
                                .getCheckin()), "bookingdates.checkout", equalTo(newBooking.getBookingdates()
                                .getCheckout()), "additionalneeds", equalTo(newBooking.getAdditionalneeds()));

    }

    @Test
    @Description("Example test for updating a booking - Put request")
    @Severity(SeverityLevel.NORMAL)
    @Story("End to End tests using rest-assured")
    @Step("Update the booking")
    public void updateBookingTest() {
        BookingData updatedBooking = getBookingData();
        given().body(updatedBooking)
                .when()
                .header("Cookie", "token=" + token)
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("firstname", equalTo(updatedBooking.getFirstname()), "lastname",
                        equalTo(updatedBooking.getLastname()), "totalprice", equalTo(updatedBooking.getTotalprice()),
                        "depositpaid", equalTo(updatedBooking.isDepositpaid()), "bookingdates.checkin", equalTo(
                                updatedBooking.getBookingdates()
                                        .getCheckin()), "bookingdates.checkout", equalTo(updatedBooking.getBookingdates()
                                .getCheckout()), "additionalneeds", equalTo(updatedBooking.getAdditionalneeds()));
    }

    @Test
    @Description("Example test for updating a booking partially- Patch request")
    @Severity(SeverityLevel.NORMAL)
    @Story("End to End tests using rest-assured")
    @Step("Update the booking partially")
    public void updatePartialBookingTest() {
        PartialBookingData partialUpdateBooking = getPartialBookingData();
        given().body(partialUpdateBooking)
                .when()
                .header("Cookie", "token=" + token)
                .patch("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("firstname", equalTo(partialUpdateBooking.getFirstname()), "totalprice",
                        equalTo(partialUpdateBooking.getTotalprice()));

    }

    @Test
    @Description("Example test for deleting a booking - Delete request")
    @Severity(SeverityLevel.NORMAL)
    @Story("End to End tests using rest-assured")
    @Step("Delete the booking")
    public void deleteBookingTest() {
        given().header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);
    }

    @Test
    @Description("Example test for checking if booking is deleted by retrieving a deleted booking - Get request")
    @Severity(SeverityLevel.NORMAL)
    @Story("End to End tests using rest-assured")
    @Step("Check by retrieving deleted booking")
    public void checkBookingIsDeleted() {
        given().get("/booking/" + bookingId)
                .then()
                .statusCode(404);
    }

    @Description("Example test for fetching token value - Post request")
    @Severity(SeverityLevel.BLOCKER)
    @Story("End to End tests using rest-assured")
    @Step("Generate Token")
    @Test
    public void testTokenGeneration() {
        Tokencreds tokenCreds = getToken();
        token = given().body(tokenCreds)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .assertThat()
                .body("token", is(notNullValue()))
                .extract()
                .path("token").toString();
    }
}