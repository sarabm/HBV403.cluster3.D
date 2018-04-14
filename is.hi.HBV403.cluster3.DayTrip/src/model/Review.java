package model;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Review {


    public Long reviewID;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    */
    public Long tripID;

    public Date date;
    public String reviewText;


    public Review() {
    }

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


