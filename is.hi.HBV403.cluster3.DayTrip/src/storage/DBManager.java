package storage;

import control.Booking;
import model.Person;
import model.Review;
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
     * Keyra í til þess að búa til töflur í gagnagrunni
     * @param args
     */
    public static void main(String[] args) {

       Trip trip = new Trip();

       Booking booking = new Booking();

       addTrip(trip);
       addBooking(booking);
    }


    /**
     * Bætir við bókun í gagnagrunn
     * Skilar Long gildi með bókunarnúmeri
     * @param booking
     * @return
     */
    public static Long addBooking(Booking booking) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Booking.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {

            session.beginTransaction();

            session.save(booking);

            session.getTransaction().commit();

        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
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

            session.save(trip);

            session.getTransaction().commit();

        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            return true;
        }
    }

    /**
     * Bætir við Review í gagnagrunninn
     * Skilar true/false ef gekk/gekk ekki
     * @param review
     * @return
     */
    public static boolean addReview(Review review) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            session.beginTransaction();

            session.save(review);

            session.getTransaction().commit();

        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
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

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Booking.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();
        try {
            Trip trip = session.get(Trip.class, tripId);

            session.delete(trip);

            session.getTransaction().commit();
        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
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

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Booking.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();
        try {
            Booking booking = session.get(Booking.class, bookingNo);

            session.delete(booking);

            session.getTransaction().commit();
        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            return true;
        }
    }

    /**
     * Sækir bókun í gagnagrunninn út frá bókunarnúmeri
     * @param bookingNo
     * @return Skilar hluti af taginu Booking
     */

    public static Booking getBooking(Long bookingNo) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Booking.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();

        Booking booking = new Booking();

        try {
            booking = session.get(Booking.class, bookingNo);

            session.getTransaction().commit();

        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            return booking;
        }
    }

    /**
     * Sækir ferð í gagnagrunn út frá tripId
     * @param tripId
     * @return Skilar hlut af taginu Trip
     */
    public static Trip getTrip(Long tripId) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Trip.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();

        Trip trip = new Trip();
        try {
            trip = session.get(Trip.class, tripId);

            session.getTransaction().commit();
        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            return trip;
        }
    }

    /**
     *
     * @param reviewId
     * @return
     */

    public static Review getReview(Long reviewId) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();

        Review review = new Review();

        try {
            review = session.get(Review.class, reviewId);
            session.getTransaction().commit();
        } catch (HibernateException hibernateEx) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            return review;
        }
    }

    /**
     * Sækir allar ferðir í gagnagrunn
     * @return lista með Trip hlutum
     */
    public static List<Trip> getAllTrips() {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Trip.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();

        //List result = session.createQuery("from trip")getResultList();
        // UPDATED: Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // UPDATED: Create CriteriaQuery
        CriteriaQuery<Trip> criteria = builder.createQuery(Trip.class);

        // UPDATED: Specify criteria root
        criteria.from(Trip.class);

        // UPDATED: Execute query
        List<Trip> result = session.createQuery(criteria).getResultList();

        session.getTransaction().commit();
        session.close();
        return result;
    }


}
