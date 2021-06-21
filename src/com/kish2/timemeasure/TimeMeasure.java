package com.kish2.timemeasure;

import java.util.concurrent.TimeUnit;

/**
 * @auth jiang ao ran
 * @date 2021/6/20
 * @email 875691208@qq.com
 * @classDesc
 */
public class TimeMeasure {

    public static long measureTime(TimeMeasureExe exe) {
        return measureTime(TimeUnit.MICROSECONDS, exe);
    }

    public static long measureTime(TimeUnit timeScale, TimeMeasureExe exe) {
        long startTime = System.nanoTime();
        exe.exe();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        switch (timeScale) {
            case MICROSECONDS:
                duration /= 1000;
                System.out.println("duration -> " + duration + " µs");
                break;
            case MILLISECONDS:
                duration /= 1000000;
                System.out.println("duration -> " + duration + " ms");
                break;
            case SECONDS:
                duration /= 1000000000;
                System.out.println("duration -> " + duration + " s");
                break;
            case NANOSECONDS:
            default:
                System.out.println("duration -> " + duration + " ns");
        }
        return duration;
    }
}
