package string;

/**
 * 外观数列
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 *
 * 注意：整数序列中的每一项将表示为一个字符串。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 * 1
 * 11
 * 21
 * 1211
 * 111221
 * 第一项是数字 1
 * 描述前一项，这个数是 1 即 “一个 1 ”，记作 11
 * 描述前一项，这个数是 11 即 “两个 1 ” ，记作 21
 * 描述前一项，这个数是 21 即 “一个 2 一个 1 ” ，记作 1211
 * 描述前一项，这个数是 1211 即 “一个 1 一个 2 两个 1 ” ，记作 111221
 *
 * 示例 1:
 * 输入: 1
 * 输出: "1"
 * 解释：这是一个基本样例。
 *
 * 示例 2:
 * 输入: 4
 * 输出: "1211"
 * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；
 * 类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
 */
public class AppearanceSeries {
    public static void main(String[] args) {
        System.out.println(countAndSay(1));
        System.out.println(countAndSay(2));
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
        System.out.println(countAndSay(5));
        System.out.println(countAndSay(6));
        System.out.println(countAndSay(7));
        System.out.println(countAndSay(8));
        System.out.println(countAndSay(9));
    }

    public static String countAndSay(int n) {
        if (n == 1){
            return "1";
        }
        // 获得上一行字符串
        String lastStr = countAndSay(n-1);
        return nextStr(lastStr);
    }

    public static String nextStr(String lastStr){
        if (lastStr.length() == 0){
            return "";
        }

        StringBuilder s = new StringBuilder();
        int num = repeatNum(lastStr);
        // 下一行字符串的构造规律是： 上一行字符串的一个重复数字个数 + 上一行字符串的重复数字的值 + 之后的字符串
        return s.append(num).append(lastStr.charAt(0)).append(nextStr(lastStr.substring(num))).toString();
    }

    /**
     * 计算字符串中的第一个重复数字个数
     * @param lastStr
     * @return
     */
    public static int repeatNum(String lastStr){
        int count = 1;
        char same = lastStr.charAt(0);
        for (int i = 1;i<lastStr.length();i++){
            if (same == lastStr.charAt(i)){
                count++;
            }else {
                break;
            }
        }
        return count;
    }
}
