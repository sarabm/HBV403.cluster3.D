package control;

import model.*;
import storage.*;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.awt.print.Book;

@Entity
@Table(name ="BOOKING")
public class Booking {


    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long bookingNo;
    //gera foreign key's
    // private Person personID;
    //private Long tripId;
    private int noGuest;


    public Booking(){};

    public Booking(Person personID, Long tripId, int noGuest) {
        // this.personID = personID;
        //  this.tripId = tripId;
        this.noGuest = noGuest;
    }

    public Long getId() {
        return bookingNo;
    }

    public void setId(Long id) {
        this.bookingNo = id;
    }
    /*
    public Person getPersonID() {
        return personID;
    }

    public void setPersonID(Person personID) {
        this.personID = personID;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
   */
    public int getNoGuest() {
        return noGuest;
    }

    public void setNoGuest(int noGuest) {
        this.noGuest = noGuest;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + bookingNo +
                //", personID=" + personID +
                // ", tripId=" + tripId +
                ", noGuest=" + noGuest +
                '}';
    }
}