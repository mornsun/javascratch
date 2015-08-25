package topcoder;

import java.util.*;

/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.

Hide Tags Divide and Conquer
Hide Similar Problems (M) Unique Binary Search Trees II (M) Basic Calculator

 * @author Chauncey
 *
 */
public class xDifferentWaystoAddParentheses
{
	private final int one_operation(int lnum, int rnum, char op) {
        int ans = 0;
    	switch(op) {
    	case '+':
    		ans = lnum + rnum;
    		break;
    	case '-':
    		ans = lnum - rnum;
    		break;
    	case '*':
    		ans = lnum * rnum;
    		break;
    	}
    	return ans;
	}
	private final List<Integer> add_parentheses(ArrayList<Integer> numberlist, ArrayList<Character> oplist, int lo, int hi)
	{
    	LinkedList<Integer> res = new LinkedList<Integer>();
		if (hi-lo == 1) {
			res.add(one_operation(numberlist.get(lo), numberlist.get(lo+1), oplist.get(lo)));
			return res;
		} else if (hi-lo == 0) {
			res.add(numberlist.get(lo));
			return res;
		}
    	for (int i=lo; i<hi; ++i) {
    		for (int leftnum : add_parentheses(numberlist, oplist, lo, i)) {
        		for (int rightnum : add_parentheses(numberlist, oplist, i+1, hi)) {
        			res.add(one_operation(leftnum, rightnum, oplist.get(i)));
        		}
    		}
    	}
    	return res;
	}
    public List<Integer> diffWaysToCompute(String input) {
    	if (input == null || input.length()==0) return null;
    	int num = -1;
    	int l = input.length();
    	ArrayList<Integer> numberlist = new ArrayList<Integer>();
    	ArrayList<Character> oplist = new ArrayList<Character>();
    	//get next number
        for (int i = 0; i<l; ++i) {
        	char ch = input.charAt(i);
        	if (ch == ' ') continue;
        	if (ch >= '0' && ch <= '9') {
        		if (num == -1) {
        			num = ch-'0';
        		} else {
        			num = num*10 + (ch-'0');
        		}
        	} else {
        		if (num != -1) {
        			numberlist.add(num);
        			num = -1;
        		}
            	switch(ch) {
            	case '+':
            	case '-':
            	case '*':
            		oplist.add(ch);
            		break;
            	}
        	}
        }
		if (num != -1) {
			numberlist.add(num);
			num = -1;
		}
		if (numberlist.size() == 0 || numberlist.size() != oplist.size()+1)
			return null;
        return add_parentheses(numberlist, oplist, 0, oplist.size());
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
	private final void next_operation(String s, int l, int idx, List<Integer> res, List<Integer> path, int deep) {
		int num = -1;
    	int i = idx;
    	//get next operator
    	char op = '#';
        for (; i<l; ++i) {
        	char ch = s.charAt(i);
        	if (ch == ' ') continue;
        	switch(ch) {
        	case '+':
        		op = '+';
        		break;
        	case '-':
        		op = '-';
        		break;
        	case '*':
        		op = '*';
        		break;
        	}
        	break;
        }
        //get next number
        ++i;
        for (; i<l; ++i) {
        	char ch = s.charAt(i);
        	if (ch == ' ') continue;
        	if (ch >= '0' && ch <= '9') {
        		if (num == -1) {
        			num = ch-'0';
        		} else {
        			num = num*10 + (ch-'0');
        		}
        	} else {
        		break;
        	}
        }
        if (i>=l) {
        	if (deep==1) {
            	System.out.println( path+":"+op+num);
        		for (int prev_num : path) {
	        		res.add(one_operation(prev_num, num, op));
        		}
        	} else {
            	List<Integer> prev_path = new LinkedList<Integer>(path);
            	path.clear();
        		for (int prev_num : prev_path) {
        			path.add(one_operation(prev_num, num, op));
        		}
        	}
        } else {
        	LinkedList<Integer> next_path = new LinkedList<Integer>();
            // calculate, and then move forward
        	List<Integer> prev_path = new LinkedList<Integer>(path);
        	for (int prev_num : prev_path) {
        		next_path.add( one_operation(prev_num, num, op));
        	}
            next_operation(s, l, i, res, next_path, deep);
            
            // move forward, and then calculate
        	next_path.clear();
        	next_path.add(num);
            next_operation(s, l, i, res, next_path, deep+1);
            if (deep == 1) {
            	System.out.println( prev_path+":"+op+next_path);
            	for (int next_num : next_path) {
            		for (int prev_num : prev_path) {
            			prev_num = one_operation(prev_num, next_num, op);
	                	res.add(prev_num);
            		}
            	}
            } else {
            	path.clear();
            	for (int next_num : next_path) {
            		for (int prev_num : prev_path) {
            			prev_num = one_operation(prev_num, next_num, op);
            			path.add(prev_num);
            		}
            	}
            }
        }

	}
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xDifferentWaystoAddParentheses solution = new xDifferentWaystoAddParentheses();
		
		System.out.println(solution.diffWaysToCompute("2*3-4*5"));
		System.out.println(solution.diffWaysToCompute(" "));
		System.out.println(solution.diffWaysToCompute("0"));
	}

}
