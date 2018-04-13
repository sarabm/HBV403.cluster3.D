package control;

import model.Filter;
import model.Trip;
import storage.DBManager;

import java.util.ArrayList;
import java.util.List;

public class TripController {
    private List<Trip> trips;

    public TripController() {
    }

    public List<Trip> searchTrips(Filter filter){
        return null;
    }
    public Trip getTrip(Long tripID) {
        return DBManager.getTrip(tripID);
    }
    public List<Trip> showTrips() {
        return this.trips;
    }
    public void getAllTrips(){
        this.trips =  DBManager.getAllTrips();
    }
}
