package designProblem;

import java.util.Stack;

/**
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *  
 * 示例:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class MinimumStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    public static class MinStack {
        private Stack<Integer> mainStarck;
        private Stack<Integer> helpStack;


        /**
         * 辅助栈解法
         */
        public MinStack() {
            this.mainStarck = new Stack<>();
            this.helpStack = new Stack<>();
        }

        public void push(int x) {
            this.mainStarck.push(x);
            if (this.helpStack.isEmpty() || this.helpStack.peek() >= x) {
                this.helpStack.push(x);
            }
        }

        public void pop() {
            Integer res = this.mainStarck.pop();
            if (!this.helpStack.isEmpty() && this.helpStack.peek().equals(res)) {
                this.helpStack.pop();
            }
        }

        public int top() {
            return this.mainStarck.peek();
        }

        public int getMin() {
            return this.helpStack.peek();
        }
    }
}
