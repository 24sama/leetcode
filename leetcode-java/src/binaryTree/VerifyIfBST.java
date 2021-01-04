package binaryTree;

import java.util.LinkedList;

/**
 * 验证二叉搜索树
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class VerifyIfBST {
    public static void main(String[] args) {
        Integer[] str = {2,1,3};
        TreeNode root = bfsCreate(str);
        System.out.println(isValidBST(root));

        Integer[] str2 = {10,5,15,null,null,6,20};
        TreeNode root2 = bfsCreate(str2);
        System.out.println(isValidBST(root2));

        System.out.println(isValidBST2(root));

        System.out.println(isValidBST2(root2));
    }

    public static boolean isValidBST(TreeNode root) {
        // 递归
        return myVerify(root, null, null);
    }

    // min和max相当于区间上界和下界
    public static boolean myVerify(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        // 超过区间界限则为false
        if (min != null && root.val <= min) {
            return false;
        }
        if (max != null && root.val >= max) {
            return false;
        }

        // 前序遍历
        // 左子树均小于根节点，则递归时上界传入根结点的值
        if (!myVerify(root.left,min,root.val)) {
            return false;
        }
        // 右子树均大于根节点，则递归时下界传入更节点的值
        if (!myVerify(root.right,root.val,max)) {
            return false;
        }
        return true;
    }

    static long pre = Long.MIN_VALUE;
    public static boolean isValidBST2(TreeNode root) {
        // 中序遍历 二叉搜索树 中序遍历结果一定是升序序列
        if (root == null) {
            return true;
        }

        // 访问左子树
        if (!isValidBST2(root.left)) {
            return false;
        }

        // 访问当前节点
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;

        // 访问右子树
        return isValidBST2(root.right);
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
