package data.restfulbooker;

import lombok.Builder;
import lombok.Getter;

/**
 * Created By Karan Arora on 13-04-2025
 */
@Getter
@Builder
public class BookingDates {
    private String checkin;
    private String checkout;
}