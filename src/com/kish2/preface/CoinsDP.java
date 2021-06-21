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
public class CoinsDP {

    // 普通递归
    public static int coinsDPNormal(int N, int[] values) {
        if (N == 0) return 0;
        if (N < 0) return -1;
        int res = N;
        for (int value : values) {
            int sub = coinsDPNormal(N - value, values);
            if (sub == -1) continue;
            // update
            res = Math.min(res, 1 + sub);
        }
        return res;
    }

    // 带备忘录
    public static int coinsDPWithRecorder(int N, int[] values) {
        return doDP(new HashMap<>(N + 1), N, values);
    }

    private static int doDP(Map<Integer, Integer> recorder, int N, int[] values) {
        if (N == 0) return 0;
        if (N < 0) return -1;
        if (recorder.get(N) != null) return recorder.get(N);
        int res = N;
        for (int value : values) {
            int sub = doDP(recorder, N - value, values);
            if (sub == -1) continue;
            res = Math.min(res, 1 + sub);
        }
        recorder.put(N, res);
        return res;
    }

    // 迭代
    public static int coinsDPIterable(int N, int[] values) {
        if (N == 0) return 0;
        if (N < 0) return -1;
        int[] dp = new int[N + 1];
        // initialize
        // 初始化非常重要，取极端情况，即需要N个面值为1的硬币
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            for (int value : values) {
                if (i - value < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - value]);
            }
        }
        return dp[N];
    }

    public static void main(String[] args) {
        int N = 11;
        int[] values = {1, 2, 5};
        TimeMeasure.measureTime(TimeUnit.MICROSECONDS, () -> {
            System.out.println("at least coins are " + coinsDPNormal(N, values));
        });
        TimeMeasure.measureTime(TimeUnit.MICROSECONDS, () -> {
            System.out.println("at least coins are " + coinsDPWithRecorder(N, values));
        });
        TimeMeasure.measureTime(TimeUnit.MICROSECONDS, () -> {
            System.out.println("at least coins are " + coinsDPIterable(N, values));
        });
    }

}
