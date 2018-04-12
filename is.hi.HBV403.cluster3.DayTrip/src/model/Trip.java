package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "TRIP")
public class Trip {



        /**
         * Viðfangsbreytur
         */

        @Id
        @GeneratedValue(generator = "increment")
        @GenericGenerator(name = "increment", strategy = "increment")
        public Long tripID;

        public Date tripStartDate;
        public Date tripEndDate;
        public String tripDescription;
        public double tripPrice;
        public int tripDifficulty;
        public boolean wheelchairAccess;
        public boolean familyFriendly;
        public boolean coupleFriendly;
        public boolean groupFriendly;
        public ArrayList<Review> reviews;
        public  String tripLocation;
        public int availableSeats;

        /**
         * Smiður
         */

        public Trip(){};
        public Trip(Long tripID, Date tripStartDate, Date tripEndDate, String tripDescription, double tripPrice,
                    int tripDifficulty, boolean wheelchairAccess, boolean familyFriendly, boolean coupleFriendly,
                    boolean goupFriendly, ArrayList<Review> reviews, String tripLocation, int availableSeats) {
            this.tripID = null;
            this.tripStartDate = null;
            this.tripEndDate = null;
            this.tripDescription = tripDescription;
            this.tripPrice = 0;
            this.tripDifficulty = 0;
            this.wheelchairAccess = wheelchairAccess;
            this.familyFriendly = familyFriendly;
            this.coupleFriendly = coupleFriendly;
            this.groupFriendly = goupFriendly;
            this.reviews = reviews;
            this.tripLocation = tripLocation;
            this.availableSeats = 0;
        }

        @Override
        public String toString() {
            return "Trip{" +
                    "tripID=" + tripID +
                    ", tripStartDate=" + tripStartDate +
                    ", tripEndDate=" + tripEndDate +
                    ", tripDescription='" + tripDescription + '\'' +
                    ", tripPrice=" + tripPrice +
                    ", tripDifficulty=" + tripDifficulty +
                    ", wheelchairAccess=" + wheelchairAccess +
                    ", familyFriendly=" + familyFriendly +
                    ", coupleFriendly=" + coupleFriendly +
                    ", goupFriendly=" + groupFriendly +
                    ", reviews=" + reviews +
                    ", tripLocation='" + tripLocation + '\'' +
                    ", availableSeats=" + availableSeats +
                    '}';
        }
}

