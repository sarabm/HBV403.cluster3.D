package model;

import java.util.Date;

/**
 * Klasi sem heldur utan um leitarskilyrði fyrir ferðir í viðmótinu
 */

public class Filter {
    public Date tripStartDate;
    public Date tripEndDate;
    public Float priceMin;
    public Float priceMax;
    public Integer tripDifficulty;
    public Integer customers;
    public Boolean wheelChairAccess;
    public Boolean familyFriendly;
    public Boolean coupleFriendly;
    public Boolean groupFriendly;
    public String searchString;

    public Filter() {
    }

}
