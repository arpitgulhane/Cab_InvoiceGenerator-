package cab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CabGenerator {
    public static final double COST_PER_KM = 10.0;
    public static final double COST_PER_MINUTE = 1.0;
    public static final double MINIMUM_FARE = 5.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();

        RideRepository rideRepository = new RideRepository();

        List<Ride> rides = rideRepository.getRidesByUserId(userId);

        double totalFare = calculateTotalFare(rides);
        double avgFarePerRide = calculateAvgFarePerRide(totalFare, rides.size());

        System.out.printf("Total number of rides: %d\n", rides.size());
        System.out.printf("Total fare: Rs. %.2f\n", totalFare);
        System.out.printf("Average fare per ride: Rs. %.2f\n", avgFarePerRide);

        scanner.close();
    }

    public static double calculateTotalFare(List<Ride> rides) {
        double totalFare = 0.0;

        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
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

    public static double calculateFare(double distance, double time) {
        double fare = distance * COST_PER_KM + time * COST_PER_MINUTE;
        return Math.max(fare, MINIMUM_FARE);
    }

    private static class Ride {
        private final double distance;
        private final double time;

        public Ride(double distance, double time) {
            this.distance = distance;
            this.time = time;
        }
    }

    private static class RideRepository {
        private final List<Ride> rides;

        public RideRepository() {
            rides = new ArrayList<>();

            rides.add(new Ride(10.0, 30.0));
            rides.add(new Ride(5.0, 15.0));
            rides.add(new Ride(20.0, 60.0));
        }

        public List<Ride> getRidesByUserId(String userId) {
            // In this example, the repository returns a fixed list of rides for any given user ID.
            return rides;
        }
    }
}

