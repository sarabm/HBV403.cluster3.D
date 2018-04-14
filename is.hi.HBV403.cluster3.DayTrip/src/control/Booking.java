package control;

import model.Person;
import model.Trip;
import org.hibernate.annotations.GenericGenerator;
import storage.DBManager;

import javax.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long bookingNo;

    @Embedded
    public Person personalInfo;
    public Long tripID;
    public boolean isEmpty = true;
    public int noGuest;


    public Booking() {
    }

    public Booking(Person personID, int noGuest, Long tripID) {
        this.tripID = tripID;
        this.personalInfo = personID;
        this.noGuest = noGuest;
        this.isEmpty = false;
    }

    public static Booking getBooking(long bookingNo) {
        Booking b;
        try {
            b = DBManager.getBooking(bookingNo);
        } catch (Exception e) {
            return new Booking();
        }
        if (b == null) {
            b = new Booking();
        }

        return b;

    }
    /*
    public static void main(String[] args) {
        // Prófa
        Person sara = new Person("sara@coolchick.io", "Sara Björk", "Másdóttir");
        Trip trip = new Trip();
        trip.tripID = Long.parseLong("1");


        Booking b = new Booking(sara, 1, trip.tripID);


        System.out.println("Tókst að bóka? " + b.submitBooking());
        System.out.println("Á að vera false: " + b.isEmpty);

        /*
        long x = b.bookingNo;
        System.out.println(x);
        long y = 100;
        b = getBooking(y);
        System.out.println("Á að vera true: " + b.isEmpty);
        b = getBooking(x);
        System.out.println("Á að vera false: " + b.isEmpty);


        //tengja trip Id við review ID

    }*/

    public boolean cancelBooking() {
        return DBManager.deleteBooking(this.bookingNo);
    }

    public boolean submitBooking() {
        Long bn = DBManager.addBooking(this);
        if (bookingNo == null) {
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

}