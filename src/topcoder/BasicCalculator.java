package topcoder;

import java.util.*;

/**
 * Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.

Hide Tags Stack Math
Hide Similar Problems (M) Evaluate Reverse Polish Notation (M) Basic Calculator II (M) Different Ways to Add Parentheses

 * @author Chauncey
 *
 */
public class BasicCalculator
{
    public int calculate(String s) {
    	if (s == null || s.length()==0) return 0;
    	s = s+"=";
    	int num = -1;
    	LinkedList<Integer> num_stack = new LinkedList<Integer>();
    	LinkedList<Character> op_stack = new LinkedList<Character>();
        for (char ch : s.toCharArray()) {
        	if (ch == ' ') continue;
        	if (ch >= '0' && ch <= '9') {
        		if (num == -1) {
        			num = ch-'0';
        		} else {
        			num = num*10 + (ch-'0');
        		}
        	} else {
        		if (num != -1) {
        			Character op = op_stack.peek();
        			if (op == null || op == '(') {
        				num_stack.push(num);
        			} else {
        				if (op == '+') {
	        				int prev_num = num_stack.pop();
	        				num_stack.push(prev_num+num);
	        			} else if (op == '-') {
	        				int prev_num = num_stack.pop();
	        				num_stack.push(prev_num-num);
	        			}
        				op_stack.pop();
        			}
        			num = -1;
        		}
        		if (ch == '+' || ch == '-' || ch == '(') {
        			op_stack.push(ch);
        		} else if (ch == ')') {
        			Character op = op_stack.pop();
        			if (op != '(') {
        				throw new RuntimeException("Expression error");
        			}
        			op = op_stack.peek();
        			if (op == null || op == '(') {
        				// finished remove '(' on the top
        			} else {
        				if (op == '+') {
	        				int r_num = num_stack.pop();
	        				int prev_num = num_stack.pop();
	        				num_stack.push(prev_num+r_num);
	        			} else if (op == '-') {
	        				int r_num = num_stack.pop();
	        				int prev_num = num_stack.pop();
	        				num_stack.push(prev_num-r_num);
	        			}
        				op_stack.pop();
        			}
        		}
        	}
        	//System.out.println(num_stack+":"+op_stack);
        }
        return num_stack.peek();
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BasicCalculator solution = new BasicCalculator();
		
		System.out.println(solution.calculate("0"));
	}

}
