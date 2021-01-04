package string;

/**
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 * 说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class AchieveStrStr {

    public static void main(String[] args) {
        String h1 = "hello";
        String n1 = "ll";
        System.out.println(strStr(h1, n1));
        String h2 = "aaaaa";
        String n2 = "bba";
        System.out.println(strStr(h2, n2));
        String h3 = "a";
        String n3 = "a";
        System.out.println(strStr(h3, n3));
        String h4 = "mississippi";
        String n4 = "issip";
        System.out.println(strStr(h4, n4));
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int p1 = 0;
        int p2 = 0;
        int flag = 0;

        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        while (p1 < h.length && p2 < n.length) {
            if (h[p1] == n[p2]) {
                p1++;
                p2++;
                flag++;
            } else {
                p1 = p1-flag+1;
                p2 = 0;
                flag = 0;

            }
            if (p2 == n.length && flag == n.length) {
                return p1 - n.length;
            }
        }
        return -1;
    }
}
