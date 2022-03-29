package comp261.assig2;

/**
 * Simple data structure to represent Transportation statics
 */
public class Transport {
    public static final double BUS_SPEED_KPH = 30;
    public static final double BUS_SPEED_MPS = BUS_SPEED_KPH / 3.6;
    public static final double TRAIN_SPEED_KPH = 50;
    public static final double TRAIN_SPEED_MPS = TRAIN_SPEED_KPH / 3.6;
    public static final double WALKING_SPEED_KPH = 5;
    public static final double WALKING_SPEED_MPS = WALKING_SPEED_KPH / 3.6;
    public static final double MAX_WALKING = 100;
    public static final String WALKING_TRIP_ID = "walking";

    public static final String[] TRAINS = {
        "JVL_0",
        "JVL_1",
        "KPL_0",
        "KPL_1",
        "MEL_0",
        "MEL_1",
        "HVL_0",
        "HVL_1",
        "WRL_0",
        "WRL_1"
    };

    public static final String CABLECAR[] = {
        "CCL_0",
        "CCL_1"
    };

    public static final String FERRY[]= {
        "WHF_0",
        "WHF_1"
    };  

    /**
     * Returns if the given tripId is a train trip.
     * @param tripId trip_id
     * @return true if the tripId is a train trip
     */
    public static boolean isTrain(String tripId) {
        for (String train : TRAINS) {
            if (train.equals(tripId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if the given tripId is a cable car trip.
     * @param tripId trip_id
     * @return true if the tripId is a cable car trip
     */
    public static boolean isCableCar(String tripId) {
        for (String cableCar : CABLECAR) {
            if (cableCar.equals(tripId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if the given tripId is a ferry trip.
     * @param tripID trip_id
     * @return
     */
    public static boolean isFerry(String tripID) {
        for (String ferry : FERRY) {
            if (ferry.equals(tripID)) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * //get speed based on transportation type
     * @param tripID
     * @return speed in meters per second.
     */
    public static double getSpeedMPS(String tripID) {
        if (isTrain(tripID)) {
            return TRAIN_SPEED_MPS;
        } else if (isCableCar(tripID)) {
            return WALKING_SPEED_MPS;
        } else if (isFerry(tripID)) {
            return WALKING_SPEED_MPS;
        } else {
            return BUS_SPEED_MPS;
        }
    }

    public static String toTimeString(Double time){
        return String.format("%02d:%02d", (int)Math.floor(time/60), (int)Math.floor((time - Math.floor(time/60)*60)));
    }

}
