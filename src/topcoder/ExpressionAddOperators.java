package topcoder;

import java.util.*;

/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
Credits:
Special thanks to @davidtan1890 for adding this problem and creating all test cases.

Hide Tags Divide and Conquer
Hide Similar Problems (M) Evaluate Reverse Polish Notation (M) Basic Calculator (M) Basic Calculator II (M) Different Ways to Add Parentheses

 * @author Chauncey
 *
 */
public class ExpressionAddOperators
{
	private final class SubEvaluation {
		public char operator;
		public String expression;
		public int val;
		public SubEvaluation(int v, char op, String exp) {val=v; operator=op; expression=exp;}
		public String toString() { return "[" + expression + "]"; }
		@Override
		public int hashCode() {
			return expression.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SubEvaluation other = (SubEvaluation) obj;
			if (val != other.val)
				return false;
			if (expression == null) {
				if (other.expression != null)
					return false;
			} else if (!expression.equals(other.expression))
				return false;
			return true;
		}
	}
	
    public List<String> addOperators(String num, int target) {
    	List<String> res = new ArrayList<String>();
    	if (num == null || num.length()==0) return res;
    	int[] nums = new int[num.length()];
    	for (int i=0; i<nums.length; ++i) {
    		nums[i] = num.charAt(i) - '0';
    	}
    	ArrayList<ArrayList<HashSet<SubEvaluation>>> dp = new ArrayList<ArrayList<HashSet<SubEvaluation>>>();
    	for (int d=0; d<nums.length; ++d) {
    		ArrayList<HashSet<SubEvaluation>> ddp = new ArrayList<HashSet<SubEvaluation>>();
    		for (int s=0; s+d<nums.length; ++s) {
    			ddp.add(new HashSet<SubEvaluation>());
    		}
    		dp.add(ddp);
    	}
    	for (int d=0; d<nums.length; ++d) {
    		for (int s=0; s+d<nums.length; ++s) {
    			if (d == 0) {
            		dp.get(d).get(s).add(new SubEvaluation(nums[s], '#', String.valueOf(nums[s])));
    			} else if (d <= 10 && nums[s]>0) {
	    			long v = nums[s];
	    			for (int dd=1; dd<=d; ++dd) {
	    				v = v*10+nums[s+dd];
	    			}
	    			if (v>0 && v<=Integer.MAX_VALUE)
	            		dp.get(d).get(s).add(new SubEvaluation((int)v, '#', num.substring(s, s+d+1)));
    			}
    			int lmax = 1;
    			if (s>0) {
    				lmax = nums[0];
        			for (int i=1; i<s; ++i) {
        				lmax = lmax*10+nums[i];
        			}
    			}
    			if (lmax==0) lmax = 1;
    			int rmax = 1;
    			if (s+d+1<nums.length) {
	    			rmax = nums[s+d+1];
	    			for (int i=s+d+2; i<nums.length; ++i) {
	    				rmax = rmax*10+nums[i];
	    			}
    			}
    			if (rmax==0) rmax = 1;
    			for (int dd=0; dd<d; ++dd) {
    				evaluate(dp, s, s+dd, s+d, target, lmax, rmax);
    			}
    		}
    	}
    	for (SubEvaluation eval : dp.get(nums.length-1).get(0)) {
    		if (eval.val == target) {
    			res.add(eval.expression);
    		}
    	}
    	return res;
    }
    private void evaluate(ArrayList<ArrayList<HashSet<SubEvaluation>>> dp, int s, int k, int e, int target, int lmax, int rmax) {
    	HashSet<SubEvaluation> curr = dp.get(e-s).get(s);
    	for (SubEvaluation left : dp.get(k-s).get(s)) {
    		for (SubEvaluation right : dp.get(e-k-1).get(k+1)) {
    			//1+0-0+0+1
    			long v = left.val + right.val;
    			long max = lmax + (v>0?v:-v) + rmax;
    			if (v<=Integer.MAX_VALUE && max>=target && -max<=target)
    				curr.add(new SubEvaluation((int)v, '+', left.expression+'+'+right.expression));
    			if (right.operator=='*'||right.operator=='#') {
    				v = left.val-right.val;
        			max = lmax + (v>0?v:-v) + rmax;
        			if (v<=Integer.MAX_VALUE && max>=target && -max<=target)
        				curr.add(new SubEvaluation(left.val-right.val, '-', left.expression+'-'+right.expression));
    			}
    			if ((left.operator=='*'||left.operator=='#') && (right.operator=='*'||right.operator=='#')) {
        			v = left.val*right.val;
        			max = lmax * (v==0?1:v) * rmax;
        			max = Math.max(max, lmax + (v==0?1:v) * rmax);
        			max = Math.max(max, lmax * (v==0?1:v) + rmax);
        			max = Math.max(max, lmax + (v==0?1:v) + rmax);
        			if (v<=Integer.MAX_VALUE && max>=target && -lmax-v-rmax<=target)
        				curr.add(new SubEvaluation((int)v, '*', left.expression+'*'+right.expression));
    			}
    		}
    	}
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ExpressionAddOperators solution = new ExpressionAddOperators();

		System.out.println(solution.addOperators("10001",2));
		System.out.println(solution.addOperators("2147483647", 2147483647));
		System.out.println(solution.addOperators("999999999",80));
		System.out.println(solution.addOperators("123", 6));
		System.out.println(solution.addOperators("232", 8));
		System.out.println(solution.addOperators("105", 5));
		System.out.println(solution.addOperators("00", 0));
		System.out.println(solution.addOperators("3456237490", 9191));
		System.out.println(solution.addOperators("123456789", 45));
	}

}
