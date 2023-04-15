package cab2;

import cab.CabGenerator;

public class Ride {
    public final double distance;
    public final double time;
    public final CabGenerator.RideType type;

    public Ride(double distance, double time, CabGenerator.RideType type) {
        this.distance = distance;
        this.time = time;
        this.type = type;
    }
}
