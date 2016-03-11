package utils;

/**
 * Created by Julien Hatin on 30/06/15.
 */
public class TimeHelper {

    public static Integer getHour(long timestamp) {
        return (int) Math.floor(timestamp / (60 * 60)) % 24;
    }

}
