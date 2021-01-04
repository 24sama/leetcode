package others;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 *
 * @author liyaozong
 * @date 2020/9/11 17:22
 */
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("{[]}"));
        System.out.println(isValid("){[]}"));
        System.out.println(isValid("({[)"));
    }

    /**
     * 栈存储括号
     */
    public static boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (stack.isEmpty()) {
                stack.addFirst(c);
            } else {
                if (c - 1 == stack.peek() || c - 2 == stack.peek()) {
                    stack.removeFirst();
                } else {
                    stack.addFirst(c);
                }
            }
        }
        return stack.isEmpty();
    }
}
