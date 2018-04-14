package model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;


// Allt þetta cool stuff er til þess að tengjast eða búa til töfluna Review í hibernate
@Entity
@Table(name = "REVIEW")
public class Review {

        @Id
        @GeneratedValue(generator = "increment")
        @GenericGenerator(name = "increment", strategy = "increment")
        public Long reviewID;

        /*
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn
        */
        public Long tripID;

        public Date date;
        public String reviewText;


    public Review(){}

    public Review(Long tripID, Date date, String reviewText) {
        this.tripID = tripID;
        this.date = date;
        this.reviewText = reviewText;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", tripID=" + tripID +
                ", date=" + date +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}


