package linkedList;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ReverseList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        addNode(head, 2);
        addNode(head, 3);
        addNode(head, 4);
        addNode(head, 5);

        traverseNode(head);
        System.out.println();

        ListNode newList = reverseList(head);
        traverseNode(newList);
    }

    public static ListNode reverseList(ListNode head) {
        // 作为新节点的尾部
        ListNode dummy = null;
        // 指向当前节点
        ListNode current;
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
