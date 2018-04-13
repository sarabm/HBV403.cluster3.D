package control;

import model.*;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.awt.print.Book;

@Entity
@Table(name ="booking")
public class Booking {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long bookingNo;




    //@OneToMany(mappedBy="booking", cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    //public Person personalInfo;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn*/
    public Long tripID;

    public int noGuest;


    public Booking(){}

    public Booking(Person personID, Long tripId, int noGuest) {
        //this.personalInfo = personID;
        this.tripID = tripId;
        this.noGuest = noGuest;
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

    /*
    @Embeddable
    public class PersonalInfo{

    }*/
}