package linkedList;

/**
 *删除链表的倒数第N个节点
 *给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        addNode(head, 2);
        addNode(head, 3);
        addNode(head, 4);
        addNode(head, 5);

        traverseNode(head);
        System.out.println();

        ListNode list = removeNthFromEnd2(head, 5);
        traverseNode(list);
    }

    static class ListNode {
        private int val;
        private ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void traverseNode(ListNode node){
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }

    public static void addNode(ListNode node, int val) {
        ListNode newNode = new ListNode(val);
        while (node.next != null) {
            node = node.next;
        }
        node.next = newNode;
    }

    public static ListNode getNode(ListNode node, int val) {
        while (node != null) {
            if (node.val == val){
                break;
            }
            node = node.next;
        }
        return node;
    }

    // 两次遍历
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 添加一个哑巴节点作为头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = head;
        int count = 0;
        while (first != null) {
            count++;
            first = first.next;
        }

        count -= n;
        first = dummy;
        while (count > 0) {
            count--;
            first = first.next;
        }

        first.next = first.next.next;
        return dummy.next;
    }

    //双指针一次遍历
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        // 添加一个哑巴节点作为头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < n+1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
