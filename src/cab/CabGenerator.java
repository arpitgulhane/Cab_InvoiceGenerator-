package cab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CabGenerator {
    public static final double NORMAL_COST_PER_KM = 10.0;
    public static final double NORMAL_COST_PER_MINUTE = 1.0;
    public static final double NORMAL_MINIMUM_FARE = 5.0;

    public static final double PREMIUM_COST_PER_KM = 15.0;
    public static final double PREMIUM_COST_PER_MINUTE = 2.0;
    public static final double PREMIUM_MINIMUM_FARE = 20.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();

        RideRepository rideRepository = new RideRepository();

        List<Ride> rides = rideRepository.getRidesByUserId(userId);

        double normalTotalFare = calculateTotalFare(rides, RideType.NORMAL);
        double normalAvgFarePerRide = calculateAvgFarePerRide(normalTotalFare, getNumRidesByType(rides, RideType.NORMAL));
        System.out.printf("Total number of normal rides: %d\n", getNumRidesByType(rides, RideType.NORMAL));
        System.out.printf("Total normal fare: Rs. %.2f\n", normalTotalFare);
        System.out.printf("Average normal fare per ride: Rs. %.2f\n", normalAvgFarePerRide);

        double premiumTotalFare = calculateTotalFare(rides, RideType.PREMIUM);
        double premiumAvgFarePerRide = calculateAvgFarePerRide(premiumTotalFare, getNumRidesByType(rides, RideType.PREMIUM));
        System.out.printf("Total number of premium rides: %d\n", getNumRidesByType(rides, RideType.PREMIUM));
        System.out.printf("Total premium fare: Rs. %.2f\n", premiumTotalFare);
        System.out.printf("Average premium fare per ride: Rs. %.2f\n", premiumAvgFarePerRide);

        scanner.close();
    }

    public static double calculateTotalFare(List<Ride> rides, RideType rideType) {
        double costPerKm;
        double costPerMinute;
        double minimumFare;

        if (rideType == RideType.NORMAL) {
            costPerKm = NORMAL_COST_PER_KM;
            costPerMinute = NORMAL_COST_PER_MINUTE;
            minimumFare = NORMAL_MINIMUM_FARE;
        } else {
            costPerKm = PREMIUM_COST_PER_KM;
            costPerMinute = PREMIUM_COST_PER_MINUTE;
            minimumFare = PREMIUM_MINIMUM_FARE;
        }

        double totalFare = 0.0;

        for (Ride ride : rides) {
            if (ride.type == rideType) {
                totalFare += calculateFare(ride.distance, ride.time, costPerKm, costPerMinute, minimumFare);
            }
        }

        return totalFare;
    }

    public static double calculateAvgFarePerRide(double totalFare, int numRides) {
        if (numRides == 0) {
            return 0.0;
        } else {
            return totalFare / numRides;
        }
    }

    public static double calculateFare(double distance, double time, double costPerKm, double costPerMinute, double minimumFare) {
        double fare = distance * costPerKm + time * costPerMinute;
        return Math.max(fare, minimumFare);
    }

    public static int getNumRidesByType(List<Ride> rides, RideType rideType) {
        int count = 0;

        for (Ride ride : rides) {
            if (ride.type == rideType) {
                count++;
            }
        }

        return count;
    }

    public enum RideType {
        NORMAL, PREMIUM
    }

    public static class Ride {
        public final double distance;
        public final double time;
        public final RideType type;

        public Ride(double distance, double time, RideType type) {
            this.distance = distance;
            this.time = time;
            this.type = type;
        }
    }

    public static class RideRepository {
        private final List<Ride> rides;

        public RideRepository() {
            this.rides = new ArrayList<>();
            this.rides.add(new Ride(10.0, 20.0, RideType.NORMAL));
            this.rides.add(new Ride(5.0, 10.0, RideType.NORMAL));
            this.rides.add(new Ride(15.0, 30.0, RideType.PREMIUM));
            this.rides.add(new Ride(20.0, 40.0, RideType.PREMIUM));
            this.rides.add(new Ride(30.0, 60.0, RideType.PREMIUM));
        }

        public List<Ride> getRidesByUserId(String userId) {
            // In this example, we only have one user, so we return all the rides.
            // In a real application, we would look up the rides for the specified user.
            return rides;
        }
    }
}
