package linkedList;

import java.util.LinkedList;

/**
 * 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeTwoOrderedLinkedLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        addNode(l1, 2);
        addNode(l1, 4);
        ListNode l2 = new ListNode(1);
        addNode(l2, 3);
        addNode(l2, 4);

        ListNode res = mergeTwoLists(l1, l2);
        traverseNode(res);

        System.out.println();

        ListNode l3 = new ListNode(1);
        addNode(l3, 2);
        addNode(l3, 4);
        ListNode l4 = new ListNode(1);
        addNode(l4, 3);
        addNode(l4, 4);

        ListNode res2 = mergeTwoLists2(l3, l4);
        traverseNode(res2);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // dummy = move游标，start = 链表开头的哑节点
        ListNode dummy = new ListNode(0), start = dummy;
        while (l1 != null && l2 != null){
            if (l1.val > l2.val){
                dummy.next = l2;
                dummy = dummy.next;
                l2 = l2.next;
            }else {
                dummy.next = l1;
                dummy = dummy.next;
                l1 = l1.next;
            }
        }
        // 没用完的元素需要继续拼接
        if (l1 == null) {
            dummy.next = l2;
        }else {
            dummy.next = l1;
        }
        return start.next;
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        // 递归求解
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    private static class ListNode {
        private int val;
        private ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private static void traverseNode(ListNode node){
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }

    private static void addNode(ListNode node, int val) {
        ListNode newNode = new ListNode(val);
        while (node.next != null) {
            node = node.next;
        }
        node.next = newNode;
    }
}
