package control;

import model.Person;
import model.Trip;
import org.hibernate.annotations.GenericGenerator;
import storage.DBManager;

import javax.persistence.*;


// Allt þetta cool stuff er til þess að tengjast eða búa til töfluna booking í hibernate

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

    /**
     * Þegar búið er til bókun þarf að búa til tilvik af bókun
     * Síðan þarf að staðfersta hana með submitBooking aðferðinni
     * t.d
     *      Booking b = new Booking(...)
     *      b.submitBooking();
     */

    // tóm bókun, notað ef gagnagrunnur kastar exception, þá er isEmpty = true
    public Booking(){}

    public Booking(Person personID, int noGuest, Long tripID) {
        this.tripID = tripID;
        this.personalInfo = personID;
        this.noGuest = noGuest;
        this.isEmpty = false;
    }


    public boolean cancelBooking() {
        return DBManager.deleteBooking(this.bookingNo);
    }

    // skilar "tómri bókun" ef gagnagrunnur kastar exception (t.d gagnagrunnur niðri) eða ef
    // enginn færsla í töflu hefur viðeigandi bookingNo

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