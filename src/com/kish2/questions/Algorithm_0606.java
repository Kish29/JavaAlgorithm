package com.kish2.questions;

import com.kish2.basic.ListNode;

public class Algorithm_0606 {

    // 反转链表
    // 2021/6/6
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        // second首先初始化
        ListNode second = head.next, third;
        // head的next置空，防止形成环
        head.next = null;
        // 一次仅对两个元素做操作
        while (second != null) {
            third = second.next;
            second.next = head;
            head = second;
            second = third;
        }
        return head;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null) return null;
        ListNode first, second, third;
        // initialize
        first = null;
        second = head;
        while (second != null) {
            third = second.next;
            second.next = first;
            first = second;
            second = third;
        }
        return first;
    }

}
