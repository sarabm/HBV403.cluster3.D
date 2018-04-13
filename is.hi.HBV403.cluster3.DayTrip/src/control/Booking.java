package control;

import model.*;

import org.hibernate.annotations.GenericGenerator;
import storage.DBManager;

import javax.persistence.*;
import java.awt.print.Book;

@Entity
@Table(name ="booking")
public class Booking {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long bookingNo;
    public Person personlInfo;
    //@OneToMany(mappedBy="booking", cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    public Person personalInfo;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn*/
    public Long tripID;
    private boolean isEmpty = true;
    public int noGuest;


    public Booking(){}

    public Booking(Person personID, Long tripId, int noGuest) {
        this.personalInfo = personID;
        this.tripID = tripId;
        this.noGuest = noGuest;
        this.isEmpty = false;
    }

    public boolean cancelBooking() {
        return DBManager.deleteBooking(this.bookingNo);
    }

    public Booking getBooking(long bookingNo) {
        try {
            Booking b = DBManager.getBooking(bookingNo);
            return b;
        } catch (Exception e) {
            return new Booking();
        }
    }

    public boolean submitBooking() {
        Long bn = DBManager.addBooking(this);
        if(bookingNo == null) {
            return false;
        }
        this.bookingNo = bn;
        return true;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + bookingNo +
                //", personID=" + personalInfo +
                ", tripId=" + tripID +
                ", noGuest=" + noGuest +
                '}';
    }

    @Embeddable
    public class Person {

    }
}