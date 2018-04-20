package storage;

import control.Booking;
import model.Trip;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


/**
 * Sér um að setja, sækja og eyða gögnum í gagnagrunn.
 */
public class DBManager {

    /**
     * Býr til factory til þess að opna tengingu við gagnagrunninn
     */
    private static SessionFactory factory;
    static {
        factory = new Configuration()
                .configure()
                .addAnnotatedClass(Booking.class)
                .addAnnotatedClass(Trip.class)
                .buildSessionFactory();
    }

    /**
     * Sækir current session
     * @return
     */
    public static Session getSession() {
        return factory.openSession();
    }


    /**
     * Lokar tengingunni við gagnagrunninn
     */
    public static void close() {
        factory.close();
    }

    /**
     * Bætir við bókun í gagnagrunn
     * Skilar Long gildi með bókunarnúmeri
     * @param booking
     * @return
     */
    public static Long addBooking(Booking booking) {

        Session session = getSession();

        try {

            session.beginTransaction();
            session.save(booking);

            session.getTransaction().commit();
            session.close();

        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            } finally {
            return booking.bookingNo;
        }
    }


    /**
     * Bætir við ferð í gagnagrunninn
     * Skilar true/false ef gekk/gekk ekki
     * @param trip
     * @return
     */
    public static boolean addTrip(Trip trip) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Trip.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            session.beginTransaction();

            session.persist(trip);

            session.getTransaction().commit();
            session.close();

        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            } finally {
            return true;
        }
        }


    /**
     * Eyðir ferð úr gagnagrunni
     * Skilar true/false ef gekk/gekk ekki
     * @param tripId
     * @return
     */
    public static boolean deleteTrip(Long tripId) {

        Session session = getSession();

        session.beginTransaction();
        try {
            Trip trip = session.get(Trip.class, tripId);

            session.delete(trip);

            session.getTransaction().commit();

            session.close();

        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            return true;
        }
    }

    /**
     * Eyðir ferð úr gagnagrunni
     * Skilar true/false ef gekk/gekk ekki
     * @param bookingNo
     * @return
     */
    public static boolean deleteBooking(Long bookingNo) {


        Session session = getSession();

        session.beginTransaction();

        try {
            Booking booking = session.get(Booking.class, bookingNo);

            session.delete(booking);

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            return true;
        }

    }

    /**
     * Sækir bókun í gagnagrunninn út frá bókunarnúmeri
     * @param bookingNo
     * @return Skilar hluti af taginu Booking
     */

    public static Booking getBooking(Long bookingNo) {


        Session session = getSession();

        session.beginTransaction();

        Booking booking = new Booking();

        try {
            booking = session.get(Booking.class, bookingNo);

            session.getTransaction().commit();
            session.close();


        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            } finally {
            return booking;
        }
    }

    /**
     * Sækir ferð í gagnagrunn út frá tripId
     * @param tripId
     * @return Skilar hlut af taginu Trip
     */
    public static Trip getTrip(Long tripId) {

        Session session = getSession();

        session.beginTransaction();

        Trip trip = new Trip();
        try {
            trip = session.get(Trip.class, tripId);

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            return trip;
        }

    }


    /**
     * Sækir allar ferðir í gagnagrunn
     * @return lista með Trip hlutum
     */
    public static List<Trip> getAllTrips() {

        Session session = getSession();
        session.beginTransaction();

        //Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        //Create CriteriaQuery
        CriteriaQuery<Trip> criteria = builder.createQuery(Trip.class);

        //Specify criteria root
        criteria.from(Trip.class);

        //Execute query
        List<Trip> result = session.createQuery(criteria).getResultList();

        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Uppfærir fjölsa lausra sæta í ferð
     * @param tripId
     * @param noGuests
     * @return
     */
    public static boolean updateTrip(Long tripId, int noGuests){

        Session session = getSession();
        session.beginTransaction();

        try {
            Trip t = session.get(Trip.class, tripId);
            int available = t.availableSeats;
            int seats = available - noGuests;

            if (seats > 0){
                t.availableSeats = seats;
            }
            else {
                throw new IndexOutOfBoundsException();
            }

            session.getTransaction().commit();
            session.close();

        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            return  true;
        }
    }
}

