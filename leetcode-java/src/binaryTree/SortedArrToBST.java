package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 将有序数组转换为二叉搜索树
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 */
public class SortedArrToBST {
    public static void main(String[] args) {
        int[] arr = new int[]{-10,-3,0,5,9};
        TreeNode root = sortedArrayToBST(arr);
        System.out.println(levelOrder(root));
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        // 二分法
        if (nums.length == 0) {
            return null;
        }
        return helper(nums, 0, nums.length-1);
    }

    public static TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, left, mid-1);
        node.right = helper(nums, mid+1, right);
        return node;
    }

    // 层序遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        // 队列 循环
        if (root == null) {
            return new ArrayList<>();
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> tree = new ArrayList<>();

        queue.add(root);
        while (queue.size() > 0) {
            List<Integer> level = new ArrayList<>();
            int len = queue.size();
            for (int i=0;i<len;i++) {
                root = queue.poll();
                level.add(root.val);
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            tree.add(level);
        }
        return tree;
    }

    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }

        public void setLeft(TreeNode leftNode) {
            this.left = leftNode;
        }

        public void setRight(TreeNode rightNode) {
            this.right = rightNode;
        }
    }



    private static TreeNode bfsCreate(Integer[] str) {
        //层序创建二叉树
        LinkedList<TreeNode> queue = new LinkedList<>();

        TreeNode root = new TreeNode(str[0]);
        queue.offer(root);
        int i = 1;
        TreeNode current = null;
        Integer value = null;
        while (i < str.length) {
            //从链表中移除并获取第一个节点
            current = queue.poll();
            value = str[i++];
            if (value != null) {
                TreeNode left = new TreeNode(value);
                //创建当前节点的左孩子
                current.setLeft(left);
                // 在链表尾部 左孩子入队
                queue.offer(left);
            }
            value = str[i++];
            if (value != null) {
                TreeNode right = new TreeNode(value);
                //创建当前节点的右孩子
                current.setRight(right);
                // 在链表尾部 右孩子入队
                queue.offer(right);
            }
        }
        return root;
    }
}
