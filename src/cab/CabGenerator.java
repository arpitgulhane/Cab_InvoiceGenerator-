package cab;

import java.util.Scanner;

public class CabGenerator {
        public static final double COST_PER_KM = 10.0;
        public static final double COST_PER_MINUTE = 1.0;
        public static final double MINIMUM_FARE = 5.0;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the distance travelled in km: ");
            double distance = scanner.nextDouble();

            System.out.print("Enter the time taken in minutes: ");
            double time = scanner.nextDouble();

            double fare = calculateFare(distance, time);
            System.out.printf("Total fare: Rs. %.2f\n", fare);

            scanner.close();
        }

        public static double calculateFare(double distance, double time) {
            double fare = distance * COST_PER_KM + time * COST_PER_MINUTE;
            return Math.max(fare, MINIMUM_FARE);
        }

}
