package control;

import model.Person;
import model.Trip;
import storage.DBManager;

public class TripRequest {
    public TripRequest() {
    }

    public static boolean createTrip(Trip trip, Person person) {
        return DBManager.addTrip(trip);
    }

}
