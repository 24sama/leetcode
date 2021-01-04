package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 1 !@#  2 abc 3 def
 * 4 ghi  5 jkl 6 mno
 * 7 pqrs 8 tuv 9 wxyz
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * @author liyaozong
 * @date 2020/9/23 16:05
 */
public class LetterCombinationOfPhoneNumber {
    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
        System.out.println(letterCombinations2("23"));
    }

    /**
     * 回溯
     */
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || "".equals(digits)) {
            return res;
        }

        String[] letter = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        char[] arr = digits.toCharArray();
        dfs(arr, 0, letter, res, new StringBuilder());
        return res;
    }

    public static void dfs(char[] arr, int begin, String[] letter, List<String> res, StringBuilder path) {
        if (path.length() == arr.length) {
            res.add(path.toString());
            return;
        }
        for(int i = begin; i<arr.length; i++) {
            // 获取当前数字对应的英文字母数组
            char[] numLetter = letter[(int) arr[i]-'0'].toCharArray();
            for (int j=0;j<numLetter.length;j++) {
                // 字符串拼接
                path.append(numLetter[j]);
                dfs(arr, i+1, letter, res, path);
                // 删除最后一个 即状态重置 深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
                path.deleteCharAt(path.length()-1);
            }
        }
    }

    /**
     * 回溯
     * leetcode题解写法
     */
    public static List<String> letterCombinations2(String digits) {
        String[] letterMap = {
            " ",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
        };
        List<String> res = new ArrayList<>();
        if("".equals(digits)) {
            return res;
        }
        dfs2(digits, 0, letterMap, res, new StringBuilder());
        return res;
    }

    public static void dfs2(String digits, int index, String[] letterMap, List<String> res, StringBuilder path) {
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }
        char c = digits.charAt(index);
        String letter = letterMap[c - '0'];
        for (int i=0; i<letter.length(); i++) {
            path.append(letter.charAt(i));
            dfs2(digits, index+1, letterMap, res, path);
            path.deleteCharAt(path.length()-1);
        }
    }
}
