package control;

import model.Filter;
import model.Trip;
import storage.DBManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripController {
    public TripController() {
    }

    public static List<Trip> searchTrips(Filter filter)
    {
        List<Trip> allTrips = DBManager.getAllTrips();
        List<Trip> trips =  new ArrayList<>();

        for(Trip trip : allTrips) {
            if(filter.coupleFriendly != null && filter.coupleFriendly != trip.coupleFriendly) continue;
            if(filter.familyFriendly != null && filter.familyFriendly != trip.coupleFriendly) continue;
            if(filter.groupFriendly != null && filter.groupFriendly != trip.groupFriendly) continue;
            if(filter.wheelChairAccess != null && filter.wheelChairAccess != trip.wheelchairAccess) continue;
            if(filter.tripDifficulty != null && filter.tripDifficulty < trip.tripDifficulty) continue;
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

    public static void main(String[] args) throws ParseException {
        // Prófa TripController
        Filter f = new Filter();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        f.tripStartDate = sdf.parse("2018-04-09 16:00:07");
        f.tripEndDate = sdf.parse("2018-04-10 16:03:07");
        System.out.println(searchTrips(f));

    }

}
