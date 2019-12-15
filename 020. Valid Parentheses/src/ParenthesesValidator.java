import java.util.Stack;

/**
 * <p>https://leetcode.com/problems/valid-parentheses/
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 * Input: "()"
 * Output: true
 *
 * Example 2:
 * Input: "()[]{}"
 * Output: true</p>
 */
public class ParenthesesValidator {
    public boolean isValid(String s) {
        int length = s.length();
        Stack<Character> stack = new Stack<>();
        if (length == 0) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (isMatch(stack.peek(), ch)) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }

    private boolean isMatch(char left, char right) {
        return !((left == '(' && right == ')') || (left == '[' && right == ']') || (left == '{' && right == '}'));
    }
}
