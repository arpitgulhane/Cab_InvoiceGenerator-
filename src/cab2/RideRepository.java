package cab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideRepository {
    private final Map<Integer, List<Ride>> userRides;

    public RideRepository() {
        userRides = new HashMap<>();
    }

    public void addRide(int userId, Ride ride) {
        List<Ride> rides = userRides.getOrDefault(userId, new ArrayList<>());
        rides.add(ride);
        userRides.put(userId, rides);
    }

    public List<Ride> getRidesForUser(int userId) {
        return userRides.getOrDefault(userId, new ArrayList<>());
    }
}
