package string;

import java.util.HashMap;
import java.util.Map;

/**
 * 387. 字符串中的第一个唯一字符
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 示例：
 * s = "leetcode"
 * 返回 0
 * s = "loveleetcode"
 * 返回 2
 *
 * 提示：你可以假定该字符串只包含小写字母。
 */
public class FirstUniqChar {
    public static void main(String[] args) {
        String s = "loveleetcode";
        int result = FirstUniqChar(s);
        System.out.println(result);

        String s2 = "leetcode";
        int result2 = FirstUniqChar2(s2);
        System.out.println(result2);
    }

    public static int FirstUniqChar2(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }

        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int FirstUniqChar(String s) {
        // 因为只会出现小写字母，所以仅需26位
        int[] count = new int[26];
        char[] chars = s.toCharArray();

        // 字母每出现一次就在对应下标位置的数组中加1
        for(char c : chars){
            count[c - 'a']+=1;
        }

        for(int i=0;i<chars.length;i++){
            //表示只出现一次的字母
            if(count[chars[i] - 'a']==1){
                return i;
            }
        }
        return -1;
    }
}
