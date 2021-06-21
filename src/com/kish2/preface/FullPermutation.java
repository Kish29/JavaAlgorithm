package com.kish2.preface;

import com.kish2.timemeasure.TimeMeasure;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @auth jiang ao ran
 * @date 2021/6/21
 * @email 875691208@qq.com
 * @classDesc
 */
public class FullPermutation {

    private final List<List<Integer>> result = new LinkedList<>();

    public void permute(int[] nums) {
        result.clear();
        // 记录路径
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(track, nums);
    }

    private void backtrack(LinkedList<Integer> track, int[] choices) {
        // 如果此时路径长度与choices长度相同，说明选择完成，️满足条件
        if (track.size() == choices.length) {
            // 知道为什么这句代码不行吗？
            // 因为添加的始终是track的引用，指向同一个list
//            result.add(track);
            result.add(new LinkedList<>(track));
            return;
        }
        for (int choice : choices) {
            // 排除已选择
            // 这里是O(n)的耗时方法
            if (track.contains(choice)) {
                continue;
            }
            // 做选择
            track.add(choice);
            // 进入下一层决策树
            backtrack(track, choices);
            // 撤销选择
            track.removeLast();
        }
    }

    public List<List<Integer>> getResult() {
        return result;
    }


    public static void main(String[] args) {
        FullPermutation fullPermutation = new FullPermutation();
        TimeMeasure.measureTime(TimeUnit.MICROSECONDS, () -> {
            fullPermutation.permute(new int[]{1, 2, 3});
            for (List<Integer> integers : fullPermutation.result) {
                System.out.println(integers);
            }
        });
    }

}
