package string;

/**
 * 验证回文字符串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 *
 */

public class VerifyPalindromeString {
    public static void main(String[] args) {
        String test1 = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(test1));
        String test2 = "race a car";
        System.out.println(isPalindrome(test2));
        String test3 = "aa";
        System.out.println(isPalindrome(test3));
        String test4 = "[Marge], let's \"[went].\" I await {news} telegram.";
        System.out.println(isPalindrome(test4));
    }

    public static boolean isPalindrome(String s) {
        if (s.isEmpty()){
            return true;
        }

        s = s.toLowerCase();
        int start = 0;
        int end = s.length()-1;
        char[] c = s.toCharArray();
        while(start<end){
            if (c[start]<48 || 57<c[start]&&c[start]<97 || 122<c[start]){
                start++;
                continue;
            }
            if (c[end]<48 || 57<c[end]&&c[end]<97 || 122<c[end]){
                end--;
                continue;
            }
            if (c[start]!=c[end]){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
