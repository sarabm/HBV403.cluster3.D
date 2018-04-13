package control;

import model.Person;
import model.Trip;
import storage.DBManager;

public class TripRequest {
    public TripRequest(){
    }
    public boolean createTrip(Trip trip, Person person) {
        return DBManager.addTrip(trip);
    }
}
