package binaryTree;
import java.util.LinkedList;

/**
 * 104.二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3
 */

public class BinaryTreeMaxDepth {
    public static void main(String[] args) {
        Integer[] str = {3,9,20,null,null,15,7};
        TreeNode root = bfsCreate(str);

        System.out.println(maxDepth(root));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public void setLeft(TreeNode leftNode) {
            this.left = leftNode;
        }

        public void setRight(TreeNode rightNode) {
            this.right = rightNode;
        }
    }



    public static TreeNode bfsCreate(Integer[] str) {
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
