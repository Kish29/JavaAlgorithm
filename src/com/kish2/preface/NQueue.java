package com.kish2.preface;

import com.kish2.timemeasure.TimeMeasure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @auth jiang ao ran
 * @date 2021/6/21
 * @email 875691208@qq.com
 * @classDesc
 */
public class NQueue {

    private static final char FLAG_QUEUE = 'Q';

    private static final char FLAG_GRID = 'X';

    private final List<List<String>> result = new ArrayList<>();

    public List<List<String>> getResult() {
        return result;
    }

    public void buildNQueues(int N) {
        if (N <= 0) return;
        char[][] board = new char[N][N];
        // initialize
        for (int i = 0; i < N; i++) {
            board[i] = String.valueOf(FLAG_GRID).repeat(N).toCharArray();
        }
        backtrack(board, 0);
    }

    private void backtrack(char[][] board, int row) {
        // 因为row从0开始，当row==boardRow时，说明棋盘遍历完成
        if (board.length == row) {
            // 生成一个新的棋盘
            ArrayList<String> res = new ArrayList<>();
            for (char[] chars : board) {
                res.add(String.valueOf(chars));
            }
            result.add(res);
            return;
        }
        int n = board[row].length;
        for (int i = 0; i < n; i++) {
            // 剔除非法位置
            if (!isValidHere(board, row, i)) {
                continue;
            }
            // 做选择
            board[row][i] = FLAG_QUEUE;
            backtrack(board, row + 1);
            board[row][i] = FLAG_GRID;
        }
    }

    private boolean isValidHere(char[][] board, int row, int column) {
        // 查找当前列
        for (char[] chars : board) {
            if (chars[column] == FLAG_QUEUE) {
                return false;
            }
        }
        // 查找左上斜列
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == FLAG_QUEUE) {
                return false;
            }
        }
        // 查找右上斜列
        for (int i = row - 1, j = column + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == FLAG_QUEUE) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NQueue nQueue = new NQueue();
        TimeMeasure.measureTime(TimeUnit.MICROSECONDS, () -> {
            nQueue.buildNQueues(10);
        });
        for (List<String> strings : nQueue.result) {
            for (String string : strings) {
                System.out.println(string);
            }
            System.out.println("---------------------------------------");
        }
        System.out.println("The num of methods are " + nQueue.result.size());
    }
}
