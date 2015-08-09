/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author Chauncey
 *
 */
public class ValidParentheses
{
    public static boolean isValid(String s) {
    	Stack<Character> stack = new Stack<Character>();
    	int len = s.length();
    	try {
	    	for (int i=0; i<len; ++i) {
	    		char ch = s.charAt(i);
	    		switch(ch) {
	    		case '(':
	    		case '[':
	    		case '{':
	    			stack.push(ch);
	    			break;
	    		case ')':
	    			char c = stack.pop();
	    			if (c != '(')
	    				return false;
	    			break;
	    		case ']':
	    			c = stack.pop();
	    			if (c != '[')
	    				return false;
	    			break;
	    		case '}':
	    			c = stack.pop();
	    			if (c != '{')
	    				return false;
	    			break;
	    		}
	    	}
	    	return stack.isEmpty();
    	} catch (Exception e) {
    		return false;
    	}
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println(isValid("([)]"));
	}

}
