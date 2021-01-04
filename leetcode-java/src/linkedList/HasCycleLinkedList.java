package linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 环形链表
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 */
public class HasCycleLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        addNode(head, 2);
        head.next.next = head;
        System.out.println(hasCycle(head));

        ListNode head2 = new ListNode(3);
        addNode(head2, 2);
        addNode(head2, 0);
        addNode(head2, -4);
        head2.next.next.next.next = head2.next;
        System.out.println(hasCycle(head2));

        ListNode head3 = new ListNode(1);
        addNode(head3, 2);
        head3.next.next = head3;
        System.out.println(hasCycle2(head3));

        ListNode head4 = new ListNode(3);
        addNode(head4, 2);
        addNode(head4, 0);
        addNode(head4, -4);
        head4.next.next.next.next = head4.next;
        System.out.println(hasCycle2(head4));
    }

    public static boolean hasCycle(ListNode head) {
        // 双指针
        if (head == null) {
            return false;
        }
        // 快慢指针遍历
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCycle2(ListNode head) {
        // hashmap法
        if (head == null) {
            return false;
        }

        Map<ListNode, ListNode> map = new HashMap<>();
        while (head.next != null) {
            if (map.containsKey(head)) {
                return true;
            }
            map.put(head, head);
            head = head.next;
        }
        return false;
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
