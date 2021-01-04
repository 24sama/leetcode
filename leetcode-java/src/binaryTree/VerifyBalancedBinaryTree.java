package binaryTree;

import java.util.LinkedList;

/**
 * 对称二叉树
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 */
public class VerifyBalancedBinaryTree {
    public static void main(String[] args) {
        Integer[] str = {1,2,2,3,4,4,3};
        TreeNode root = bfsCreate(str);
        System.out.println(isSymmetric(root));

        Integer[] str2 = {1,2,2,2,null,2,null};
        TreeNode root2 = bfsCreate(str2);
        System.out.println(isSymmetric(root2));

        System.out.println(isSymmetric2(root));
        System.out.println(isSymmetric2(root2));
    }

    public static boolean isSymmetric(TreeNode root) {
        // 递归
        if (root == null) {
            return true;
        }
        return check(root, root);
    }

    // 递归检查根节点的左子树和右子树
    public static boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }

    public static boolean isSymmetric2(TreeNode root) {
        // 队列 循环
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size()>0) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
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
