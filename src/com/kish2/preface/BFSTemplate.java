package com.kish2.preface;

import org.w3c.dom.Node;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @auth jiang ao ran
 * @date 2021/6/21
 * @email 875691208@qq.com
 * @classDesc
 */
public class BFSTemplate {


    public static int BFS(Node start, Node target) {
        // 核心数据结构
        // 记录当前访问节点
        Queue<Node> q = new LinkedBlockingDeque<>();
        // 避免走重复的路
        Set<Node> visited = new HashSet<>();

        // 起点加入队列
        q.offer(start);
        visited.add(start);
        // 记录扩散步数
        int step = 0;

        while (!q.isEmpty()) {
            int sz = q.size();
            // 将队列中的所有节点向四周扩散
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                /* 判断是否到达终点 */
                if (cur == target) {
                    return step;
                }
                /*for (Node x : cur.adj()) {
                    if (!visited.contains(x)) {
                        q.offer(x);
                        visited.add(x);
                    }
                }*/
            }
            step++;
        }
        return step;
    }

}
