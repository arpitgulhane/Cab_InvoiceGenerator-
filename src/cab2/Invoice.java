package cab2;

import java.util.List;

public class Invoice {
    private final int userId;
    private final List<Ride> rides;
    private final double totalFare;
    private final double avgFarePerRide;

    public Invoice(int userId, List<Ride> rides, double totalFare, double avgFarePerRide) {
        this.userId = userId;
        this.rides = rides;
        this.totalFare = totalFare;
        this.avgFarePerRide = avgFarePerRide;
    }

    public int getUserId() {
        return userId;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public double getAvgFarePerRide() {
        return avgFarePerRide;
    }

    public int getNumRides() {
        return rides.size();
    }
}
