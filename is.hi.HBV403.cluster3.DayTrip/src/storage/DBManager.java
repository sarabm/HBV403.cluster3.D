package storage;

import model.*;
import control.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBManager {

    public static void main(String[] args) {

        System.out.println(personControl());

        System.out.println(reviewControl());

        System.out.println(tripControl());

        System.out.println(bookingControl());


    }


    public static Person personControl() {
        Configuration con = new Configuration().configure().addAnnotatedClass(Person.class);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory(reg);

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();


        Person sara = new Person();

        sara.setEmail("sbm5@hi.is");
        sara.setFirstName("Sara");
        sara.setLastName("Másdóttir");

        session.save(sara);
        tx.commit();
        session.close();

        return sara;
    }

    public static Review reviewControl(){

        Configuration con = new Configuration().configure().addAnnotatedClass(Review.class);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory(reg);

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();


        Review one = new Review();
        one.setReviewText("Halló");

        session.save(one);
        tx.commit();
        session.close();

        return one;

    }

    public static Trip tripControl() {
        Configuration con = new Configuration().configure().addAnnotatedClass(Trip.class);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory(reg);

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();


        Trip test = new Trip();

        test.groupFriendly = true;

        session.save(test);
        tx.commit();
        session.close();

        return test;
    }

    public static Booking bookingControl() {
        Configuration con = new Configuration().configure().addAnnotatedClass(Booking.class);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory(reg);

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();


        Booking test = new Booking();

        test.setNoGuest(5);

        session.save(test);
        tx.commit();
        session.close();

        return test;
    }
}
