package control;

import model.Filter;
import model.Trip;
import storage.DBManager;

import java.util.ArrayList;
import java.util.List;

public class TripController {
    public TripController() {
    }

    public List<Trip> searchTrips(Filter filter)
    {
        List<Trip> allTrips = DBManager.getAllTrips();
        List<Trip> trips =  new ArrayList<>();

        for(Trip trip : allTrips) {
            if(filter.coupleFriendly != null && filter.coupleFriendly != trip.coupleFriendly) continue;
            if(filter.familyFriendly != null && filter.familyFriendly != trip.coupleFriendly) continue;
            if(filter.groupFriendly != null && filter.groupFriendly != trip.groupFriendly) continue;
            if(filter.wheelChairAccess != null && filter.wheelChairAccess != trip.wheelchairAccess) continue;
            if(filter.tripDifficulty != null && filter.tripDifficulty > trip.tripDifficulty) continue;
            if(filter.priceMax != null && filter.priceMax < trip.tripPrice) continue;
            if(filter.priceMin != null && filter.priceMin > trip.tripPrice) continue;
            if(filter.tripStartDate != null && filter.tripStartDate.after(trip.tripStartDate)) continue;
            if(filter.tripEndDate != null && filter.tripEndDate.before(trip.tripEndDate)) continue;
            trips.add(trip);
        }
        return trips;
    }
    public Trip getTrip(Long tripID) {
        return DBManager.getTrip(tripID);
    }
    public List<Trip> getAllTrips(){
        return DBManager.getAllTrips();
    }
}
