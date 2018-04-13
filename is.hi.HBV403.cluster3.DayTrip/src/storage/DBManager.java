package storage;

import model.*;
import control.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DBManager {

    public static void main(String[] args) {

        //Búum til nýja ferð

        Trip trip = new Trip();
        trip.availableSeats = 50;
        trip.coupleFriendly = true;
        trip.tripDifficulty = 9000;
        trip.tripDescription = "bless";


        //Bætum henni við gagnagrunninn
        addTrip(trip);

        //Sækjum allar ferðir í gagnagrunni
       // List<Trip> trips = getAllTrips();

        //Prentum út niðurstöður

        /*for ( Trip t : (List<Trip>) trips ) {
            System.out.println(t);
        }*/


        //Eyðum út ferðinni sem við bjuggum til áðan
        //deleteTrip(trip.tripID);
    }


    public static boolean addPerson(Person person) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try{

            session.beginTransaction();

            session.save(person);

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

    public static boolean addTrip(Trip trip) {

        SessionFactory factory = new Configuration()
                                 .configure()
                                 .addAnnotatedClass(Trip.class)
                                 .buildSessionFactory();

        Session session = factory.openSession();

        try{
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

    public static boolean addReview(Review review) {

        SessionFactory factory = new Configuration()
                                 .configure()
                                 .addAnnotatedClass(Review.class)
                                 .buildSessionFactory();

        Session session = factory.openSession();

        try{
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
    public static Person getPerson(String emailAddress){

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();

        Person person = new Person();

        try {
            person = session.get(Person.class, emailAddress);

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
        return person;
        }
    }

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


    public static List<Trip> getAllTrips(){

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
