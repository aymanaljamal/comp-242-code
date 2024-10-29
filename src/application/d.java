package application;

import java.util.Stack;

public class d {
	  public boolean isValid(String s) {
	        Stack<Character> stack = new Stack<>();

	        for (char c : s.toCharArray()) {
	            if (c == '(' || c == '{' || c == '[') {
	                stack.push(c);
	            } else {
	                if (stack.isEmpty()) {
	                    return false;
	                }
	                char top = stack.pop();
	                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
	                    return false;
	                }
	            }
	        }

	        return stack.isEmpty();
	    }
	  public int longestValidParentheses(String s) {
	        Stack<Integer> stack = new Stack<>();
	        stack.push(-1); // Initialize stack with a dummy index

	        int maxLength = 0;
	        for (int i = 0; i < s.length(); i++) {
	            char c = s.charAt(i);
	            if (c == '(') {
	                stack.push(i);
	            } else { // c is ')'
	                stack.pop();
	                if (stack.isEmpty()) {
	                    stack.push(i); // Update the index of the last invalid parentheses
	                } else {
	                    maxLength = Math.max(maxLength, i - stack.peek()); // Update maxLength
	                }
	            }
	        }
	        return maxLength;
	    }
}
