package com.kish2.preface;

import com.kish2.timemeasure.TimeMeasure;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @auth jiang ao ran
 * @date 2021/6/20
 * @email 875691208@qq.com
 * @classDesc
 */
public class Fib {

    public static final int SAMPLE = 80;

    // 传统fib
    public static long fibRecursive(int N) {
        if (N <= 0) return 0;
        if (N == 2 || N == 1) return 1;
        return fibRecursive(N - 1) + fibRecursive(N - 2);
    }

    public static long fibRecursiveWithRecorder(int N) {
        if (N <= 0) return 0;
        HashMap<Integer, Long> recorder = new HashMap<>(N + 1);
        return doFib2Actual(recorder, N);
    }

    private static long doFib2Actual(Map<Integer, Long> recorder, int N) {
        if (N == 2 || N == 1) return 1;
        if (recorder.get(N) != null) {
            return recorder.get(N);
        }
        long rec = doFib2Actual(recorder, N - 1) + doFib2Actual(recorder, N - 2);
        recorder.put(N, rec);
        return rec;
    }

    public static long fibDP(int N) {
        if (N <= 0) return 0;
        if (N == 1 || N == 2) return 1;
        long[] dp = new long[N + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    public static long fibLoop(int N) {
        if (N <= 0) return 0;
        if (N == 1 || N == 2) return 1;
        long l1 = 1, l2 = 1;
        long l3 = 0;
        for (int i = 3; i <= N; i++) {
            l3 = l1 + l2;
            l1 = l2;
            l2 = l3;
        }
        return l3;
    }

    public static void main(String[] args) {
        TimeUnit scale = TimeUnit.NANOSECONDS;
        TimeMeasure.measureTime(scale, () -> {
            long res = fibDP(SAMPLE);
            System.out.println(res);
        });
        TimeMeasure.measureTime(scale, () -> {
            long res = fibRecursiveWithRecorder(SAMPLE);
            System.out.println(res);
        });
        TimeMeasure.measureTime(scale, () -> {
            long res = fibLoop(SAMPLE);
            System.out.println(res);
        });
    }

}
