package math;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Fizz Buzz
 *
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 *
 * 示例：
 * n = 15,
 * 返回:
 * [
 *     "1",
 *     "2",
 *     "Fizz",
 *     "4",
 *     "Buzz",
 *     "Fizz",
 *     "7",
 *     "8",
 *     "Fizz",
 *     "Buzz",
 *     "11",
 *     "Fizz",
 *     "13",
 *     "14",
 *     "FizzBuzz"
 * ]
 *
 * @author liyaozong
 * @date 2020/9/7 19:46
 */
public class FizzBuzz {
    public static void main(String[] args) {
        System.out.println(fizzBuzz(15));
        System.out.println(fizzBuzz2(15));
    }

    public static List<String> fizzBuzz(int n) {
        if (n<=0) {
            return null;
        }

        List<String> stringList = new ArrayList<>();
        for(int i=1;i<=n;i++) {
            String str;
            if(i%3 == 0 && i%5 == 0) {
                str = "FizzBuzz";
            } else if (i%3 == 0) {
                str = "Fizz";
            } else if (i%5 == 0) {
                str = "Buzz";
            } else {
                str = i + "";
            }
            stringList.add(str);
        }
        return stringList;
    }

    /**
     * 散列表存储游戏规则, 添加规则时可以不用增加条件判断
     */
    public static List<String> fizzBuzz2(int n) {
        if (n<=0) {
            return null;
        }

        List<String> stringList = new ArrayList<>();
        Map<Integer, String> map = new LinkedHashMap<Integer, String>() {
            {
                put(3, "Fizz");
                put(5, "Buzz");
            }
        };

        for(int i=1;i<=n;i++) {
            String str = "";
            for (Integer key : map.keySet()) {
                if (i % key == 0) {
                    str += map.get(key);
                }
            }

            if (str.equals("")) {
                str = i + "";
            }
            stringList.add(str);
        }
        return stringList;
    }
}
