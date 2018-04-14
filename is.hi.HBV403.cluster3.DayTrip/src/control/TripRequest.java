package control;

import model.Person;
import model.Trip;
import storage.DBManager;

// býr til ferð með aðferðinni createTrip, viðföng eru af tagi Trip og Person
public class TripRequest {
    public TripRequest(){
    }
    public boolean createTrip(Trip trip, Person person) {
        return DBManager.addTrip(trip);
    }
}
