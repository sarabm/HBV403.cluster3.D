package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "REVIEW")
public class Review {


        /**
         * Viðfangsbreytur
         * skoða UserProfile klasann
         */
        private Date date;

        @Id
        @GeneratedValue(generator = "increment")
        @GenericGenerator(name = "increment", strategy = "increment")
        private Long id;

        private String reviewText;

        //tengja trip DB hér!
        private Long tripID;
        private Boolean visability;

        public Review(Date date, String reviewText, Long tripID, Boolean visability) {
            this.date = date;
            this.reviewText = reviewText;
            this.tripID = tripID;
            this.visability = visability;
        }

        public Review() {
        }

        ;

        /**
         * Getters og setters
         *
         * @return
         */
        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getReviewText() {
            return reviewText;
        }

        public void setReviewText(String reviewText) {
            this.reviewText = reviewText;
        }

        public Long getTripID() {
            return tripID;
        }

        public void setTripID(Long tripID) {
            this.tripID = tripID;
        }

        public Boolean getVisability() {
            return visability;
        }

        public void setVisability(Boolean visability) {
            this.visability = visability;
        }

        @Override
        public String toString() {
            return "Review{" +
                    "date=" + date +
                    ", id=" + id +
                    ", reviewText='" + reviewText + '\'' +
                    ", tripID=" + tripID +
                    ", visability=" + visability +
                    '}';

        }
}


