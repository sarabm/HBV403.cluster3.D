package storage;

import model.*;
import control.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import java.util.List;

public class DBManager {

    public static void main(String[] args) {

        System.out.println(addPerson());

        System.out.println(addReview());

        System.out.println(addTrip());

        System.out.println(addBooking());

        //getBooking();


    }


    public static Person addPerson() {
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

    public static boolean addReview(){

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

        return true;

    }

    public static boolean addTrip() {
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

        return true;
    }

    public static boolean addBooking() {

        SessionFactory factory = new Configuration()
                                 .configure()
                                 .addAnnotatedClass(Booking.class)
                                 .buildSessionFactory();

        Session session = factory.openSession();

        try{

            Booking test = new Booking();

            test.setNoGuest(5);

            session.beginTransaction();

            session.save(test);

            session.getTransaction().commit();

            //setja inní try/catch
            return true;
        }
        finally {
            factory.close();
        }
    }


    //dæmi um hvernig við sækjum gögn úr booking með createQuery


    /*
    public static List<Booking> getBooking(Long id){

        Configuration con = new Configuration().configure().addAnnotatedClass(Booking.class);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory(reg);

        Session session = sf.openSession();

        session.beginTransaction();

        List result = session.createQuery( "from Booking" ).list();
        for ( Booking booking : (List<Booking>) result ) {
            System.out.println( "Booking (" + booking.getNoGuest()+ ") : " + booking.getId());
        }
        session.getTransaction().commit();
        session.close();

        return result;
    }
    */

    public static boolean deleteBooking(Long id){
        return true;
    }

}
