package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *二叉树的层序遍历
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 */
public class LevelOrderBinaryTree {
    public static void main(String[] args) {
        Integer[] str = {1,2,2,3,4,4,3};
        TreeNode root = bfsCreate(str);
        System.out.println(levelOrder(root));

        Integer[] str2 = {3,9,20,null,null,15,7};
        TreeNode root2 = bfsCreate(str2);
        System.out.println(levelOrder(root2));

    }

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
