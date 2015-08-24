package topcoder;

import java.util.*;

/**
 * Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / (, ) operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

Hide Tags String
Hide Similar Problems (M) Basic Calculator

 * @author Chauncey
 *
 */
public class xBasicCalculatorII
{
	private final void operate(LinkedList<Integer> num_stack, LinkedList<Character> op_stack) {
		Character op = op_stack.peek();
		if (op == null || op == '(') {
			return;
		} else {
			int r_num = num_stack.pop();
			int prev_num = num_stack.pop();
			if (op == '+') {
				num_stack.push(prev_num+r_num);
			} else if (op == '-') {
				num_stack.push(prev_num-r_num);
			} else if (op == '*') {
				num_stack.push(prev_num*r_num);
			} else if (op == '/') {
				num_stack.push(prev_num/r_num);
			}
			op_stack.pop();
		}
	}
	
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
        			num_stack.push(num);
        			num = -1;
        		}
        		if (ch == '(') {
        			op_stack.push(ch);
        		} else if (ch == '+' || ch == '-') {
    				operate(num_stack, op_stack);
    				operate(num_stack, op_stack);
        			op_stack.push(ch);
        		} else if (ch == '*' || ch == '/') {
        			Character op = op_stack.peek();
        			if (op != null && (op == '*' || op == '/')) {
        				operate(num_stack, op_stack);
        			}
        			op_stack.push(ch);
        		} else if (ch == ')') {
    				operate(num_stack, op_stack);
        			Character op = op_stack.pop();
        			if (op != '(') {
        				throw new RuntimeException("Expression error");
        			}
        			operate(num_stack, op_stack);
        		} else if (ch == '=') {
    				operate(num_stack, op_stack);
        		}
        	}
        	//System.out.println(ch + ":"+num_stack+":"+op_stack);
        }
		operate(num_stack, op_stack);
        return num_stack.peek();
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xBasicCalculatorII solution = new xBasicCalculatorII();
		
		System.out.println(solution.calculate("1*2-3/4+5*6-7*8+9/10"));
	}

}
