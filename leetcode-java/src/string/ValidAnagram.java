package string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * 说明:
 * 你可以假设字符串只包含小写字母。
 */
public class ValidAnagram {
    public static void main(String[] args) {
        String s = "cat";
        String t = "rac";
        System.out.println(isAnagram(s, t));
        System.out.println(isAnagram2(s, t));

    }
    public static boolean isAnagram(String s, String t) {
        char[] arrS = s.toCharArray();
        char[] arrT = t.toCharArray();

        int[] count1 = new int[26];
        for (int i=0;i<arrS.length;i++){
            count1[arrS[i] - 'a']+=1;
        }
        int[] count2 = new int[26];
        for (int i=0;i<arrT.length;i++){
            count2[arrT[i] - 'a']+=1;
        }

        for (int i=0;i<count1.length;i++){
            if (count1[i] != count2[i]){
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagram2(String s, String t) {
        char[] arrS = s.toCharArray();
        char[] arrT = t.toCharArray();

        Arrays.sort(arrS);
        Arrays.sort(arrT);

        return arrS.equals(arrT);
    }
}
