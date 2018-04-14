package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// Allt þetta cool stuff er til þess að tengjast eða búa til töfluna trip í hibernate
@Entity
@Table(name = "trip")
public class Trip {


    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long tripID;
    public String tripName;

    public Date tripStartDate;
    public Date tripEndDate;
    public String tripDescription;
    public double tripPrice;
    public int tripDifficulty;
    public boolean wheelchairAccess;
    public boolean familyFriendly;
    public boolean coupleFriendly;
    public boolean groupFriendly;

    @Embedded
    public Person tripOwner;


    //public List<Review> reviews;
    public String tripLocation;
    public int availableSeats;


    public Trip() {
    }

    ;

    public Trip(Long tripID, String tripName, Date tripStartDate, Date tripEndDate, String tripDescription, double tripPrice,
                int tripDifficulty, boolean wheelchairAccess, boolean familyFriendly, boolean coupleFriendly,
                boolean goupFriendly, String tripLocation, int availableSeats) {
        this.tripID = null;
        this.tripName = tripName;
        this.tripStartDate = null;
        this.tripEndDate = null;
        this.tripDescription = tripDescription;
        this.tripPrice = 0;
        this.tripDifficulty = 0;
        this.wheelchairAccess = wheelchairAccess;
        this.familyFriendly = familyFriendly;
        this.coupleFriendly = coupleFriendly;
        this.groupFriendly = goupFriendly;
        //this.reviews = reviews;
        this.tripLocation = tripLocation;
        this.availableSeats = 0;
    }


    @Override
    public String toString() {
        return "" + tripName;
    }

}

