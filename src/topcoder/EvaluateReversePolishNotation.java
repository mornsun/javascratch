package topcoder;

import java.util.*;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
Hide Tags Stack
Hide Similar Problems (M) Basic Calculator

 * @author Chauncey
 *
 */
public class EvaluateReversePolishNotation
{
    public int evalRPN(String[] tokens) {
    	if (tokens == null || tokens.length == 0) return 0;
    	LinkedList<Integer> stack = new LinkedList<Integer>();
    	for (String token : tokens) {
    		int len = token.length();
    		if (len==0) continue;
    		char ch = token.charAt(0);
    		if (len==1 && (ch<'0' || ch>'9')) {
    			int r = stack.pop();
    			int l = stack.pop();
    			switch (token.charAt(0)) {
    			case '+':
        			stack.push(l+r);
        			break;
    			case '-':
        			stack.push(l-r);
        			break;
    			case '*':
        			stack.push(l*r);
        			break;
    			case '/':
        			stack.push(l/r);
        			break;
    			}
    		} else {
    			stack.push(Integer.parseInt(token));
    		}
    	}
    	return stack.pop();
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		EvaluateReversePolishNotation solution = new EvaluateReversePolishNotation();
		
		System.out.println(solution.evalRPN(new String[]{"3","-4","+"}));
	}

}
