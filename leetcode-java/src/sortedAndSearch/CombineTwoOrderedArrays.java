package sortedAndSearch;

import java.util.Arrays;

/**
 * 合并两个有序数组
 *
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class CombineTwoOrderedArrays {
    public static void main(String[] args) {
        int[] num1 = {1,2,3,0,0,0};
        int[] num2 = {2,5,6};

        merge(num1, 3, num2, 3);
        System.out.println(Arrays.toString(num1));

        int[] num3 = {1,2,3,0,0,0};
        int[] num4 = {2,5,6};

        merge2(num3, 3, num4, 3);
        System.out.println(Arrays.toString(num3));
    }

    // 双指针从前往后遍历
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m];
        System.arraycopy(nums1,0,tmp,0,m);

        int p1 = 0,p2 = 0,i = 0;
        while (p1 < m && p2 < n) {
            nums1[i++] = tmp[p1] < nums2[p2] ? tmp[p1++] : nums2[p2++];
        }
        if (p1<m) {
            System.arraycopy(tmp,p1,nums1,i,m-p1);
        }
        if (p2<n) {
            System.arraycopy(nums2,p2,nums1,i,n-p2);
        }
    }

    // 双指针从后往前遍历
    public static void merge2(int[] nums1, int m,int[] nums2,int n) {
        int p1 = m-1;
        int p2 = n-1;
        int i = m+n-1;
        while (p2>=0) {
            if (p1>=0&&nums1[p1]<nums2[p2]) {
                nums1[i--] = nums2[p2--];
            } else {
                nums1[i--] = nums1[p1--];
            }
        }
    }
}
