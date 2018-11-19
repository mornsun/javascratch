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
	public int calculate(String s) {
		if (s==null || s.length()==0) {
			return 0;
		}
		//5*(5-(5+5+5)-5)/5 to
		//5 5 5 5 + 5 + - 5 - * 5 /
		LinkedList<String> stack = new LinkedList<>();
		LinkedList<String> ops = new LinkedList<>();
		for (int i=0; i<s.length(); ++i) {
			while (Character.isWhitespace(s.charAt(i)) && ++i<s.length());
			if (i == s.length())
				break;
			char ch = s.charAt(i);
			if (ch=='(') {
				ops.addLast("(");
			} else if (ch == ')') {
				if (!ops.isEmpty()) {
					String c = ops.pollLast();
					if (!"(".equals(c))
						stack.addLast(c);
				}
			} else if (ch=='+' || ch=='-') {
				if (!ops.isEmpty()) {
					String c = ops.pollLast();
					if (!"(".equals(c))
						stack.addLast(c);
				}
				ops.addLast(Character.toString(ch));
			} else if (ch=='*' || ch=='/') {
				ops.addLast(Character.toString(ch));
			} else {
				int start = i;
				while (Character.isDigit(s.charAt(i)) && ++i<s.length());
				if (start < i) {
					stack.addLast(s.substring(start, i));
					--i;
				}
			}
			System.out.println(ch+":"+stack+":"+ops);
		}

		LinkedList<Integer> ansStack = new LinkedList<>();
		int k = 0;
		while (!stack.isEmpty()) {
			String item = stack.pollFirst();
			if (Character.isDigit(item.charAt(0))) {
				ansStack.addLast(Integer.parseInt(item));
			} else {
				int r = ansStack.pollLast();
				int l = ansStack.pollLast();
				if ("+".equals(item))
					ansStack.addLast(l+r);
				else if ("-".equals(item))
					ansStack.addLast(l-r);
				else if ("*".equals(item))
					ansStack.addLast(l*r);
				else if ("/".equals(item))
					ansStack.addLast(l/r);
			}
		}
		return ansStack.peekLast();
	}

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
	
    public int calculate1(String s) {
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
		System.out.println(solution.calculate1("1*2-3/4+5*6-7*8+9/10"));
		System.out.println(solution.calculate("5*(5-(5+5+5)-5)/5"));

	}

}
