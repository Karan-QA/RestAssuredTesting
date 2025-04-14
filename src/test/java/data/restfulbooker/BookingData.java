package data.restfulbooker;

import lombok.Builder;
import lombok.Getter;

/**
 * Created By Karan Arora on 13-04-2025
 */
@Getter
@Builder
public class BookingData {
    private String       firstname;
    private String       lastname;
    private int          totalprice;
    private boolean      depositpaid;
    private BookingDates bookingdates;
    private String       additionalneeds;
}