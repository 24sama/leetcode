package linkedList;

/**
 * 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 */
public class VerifyPalindromeLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        addNode(head, 2);
        addNode(head, 5);
        addNode(head, 5);
        addNode(head, 2);
        addNode(head, 1);

        System.out.println(isPalindrome(head));
    }

    public static boolean isPalindrome(ListNode head) {
        // 双指针
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;
        // 当快指针移动到链表尾部时，偶数情况：慢指针在链表n/2处； 奇数情况：慢指针在链表(n/2)+1处
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // 无须判断奇偶长度，只需将慢指针之后的链表反转
        slow = reverseListNode(slow.next);
        // 将前半段链表和后半段反转的链表相比较
        while (slow != null){
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    private static ListNode reverseListNode(ListNode head) {
        ListNode dummy = null;
        ListNode current = null;
        while (head != null){
            current = head;
            head = head.next;
            current.next = dummy;
            dummy = current;
        }
        return dummy;
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
