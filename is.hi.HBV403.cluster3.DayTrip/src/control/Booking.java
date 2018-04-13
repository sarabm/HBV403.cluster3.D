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

    //@OneToMany(mappedBy="booking", cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "firstName", column = @Column(length = 10)),
            @AttributeOverride(name = "lastName", column = @Column(length = 20)),
            @AttributeOverride(name = "email", column = @Column(nullable = false))
    })
    public Person personalInfo;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn*/
    public Long tripID;
    public boolean isEmpty = true;
    public int noGuest;


    public Booking(){}

    public Booking(Person personID, int noGuest) {
        this.personalInfo = personID;
        this.noGuest = noGuest;
        this.isEmpty = false;
    }

    public boolean cancelBooking() {
        return DBManager.deleteBooking(this.bookingNo);
    }

    public static Booking getBooking(long bookingNo) {
        Booking b;
        try {
            b = DBManager.getBooking(bookingNo);
        } catch (Exception e) {
            return new Booking();
        }
            if(b == null) {
                b = new Booking();
            }

            return b;

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

    public static void main( String[] args) {
        // Prófa
        Person sara = new Person("sara@coolchick.io", "Sara Björk", "Másdóttir");
        Booking b = new Booking(sara, 1);
        System.out.println("Tókst að bóka? " + b.submitBooking());
        System.out.println("Á að vera false: " + b.isEmpty);
        long x = b.bookingNo;
        System.out.println(x);
        long y = 100;
        b = getBooking(y);
        System.out.println("Á að vera true: " + b.isEmpty);
        b = getBooking(x);
        System.out.println("Á að vera false: " + b.isEmpty);

    }

}