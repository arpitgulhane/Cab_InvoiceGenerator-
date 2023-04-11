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

        List<Ride> rides = new ArrayList<>();

        System.out.print("Enter the number of rides: ");
        int numRides = scanner.nextInt();

        for (int i = 1; i <= numRides; i++) {
            System.out.printf("Ride %d:\n", i);

            System.out.print("Enter the distance travelled in km: ");
            double distance = scanner.nextDouble();

            System.out.print("Enter the time taken in minutes: ");
            double time = scanner.nextDouble();

            Ride ride = new Ride(distance, time);
            rides.add(ride);
        }

        double totalFare = calculateTotalFare(rides);
        System.out.printf("Total fare for %d rides: Rs. %.2f\n", numRides, totalFare);

        scanner.close();
    }

    public static double calculateTotalFare(List<Ride> rides) {
        double totalFare = 0.0;

        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }

        return totalFare;
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
}
