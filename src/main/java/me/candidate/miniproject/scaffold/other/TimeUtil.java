package me.candidate.miniproject.scaffold.other;

import java.util.concurrent.TimeUnit;

public class TimeUtil {

    /**
     * Format Milliseconds into xh xm xs time format
     * @param milliseconds Millseconds
     * @return String in format xh xm xs format
     */
    public static String convertMillisecondsToHMmSs(long milliseconds) {
        return String.format("%02dh %02dm %02ds", TimeUnit.MILLISECONDS.toHours(milliseconds),
                TimeUnit.MILLISECONDS.toMinutes(milliseconds) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(milliseconds) % TimeUnit.MINUTES.toSeconds(1));
    }

}