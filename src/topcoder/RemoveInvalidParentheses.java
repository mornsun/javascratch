package topcoder;

import java.util.*;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
Credits:
Special thanks to @hpplayer for adding this problem and creating all test cases.

Subscribe to see which companies asked this question

Hide Tags Depth-first Search Breadth-first Search
Hide Similar Problems (E) Valid Parentheses

 * @author Chauncey
 *
 */
public class RemoveInvalidParentheses
{
    public List<String> removeInvalidParentheses(String s) {
    	HashSet<String> res = new HashSet<String>();
    	if (null == s) return new ArrayList<String>(res);
    	StringBuilder path = new StringBuilder();
    	int[] min = new int[1];
    	min[0] = Integer.MAX_VALUE;
    	dfs(res, path, s, 0, 0, 0, min);
    	return new ArrayList<String>(res);
    }
    
    private void dfs(HashSet<String> res, StringBuilder path, String s, int idx, int level, int deleted, int[] min) {
    	if (deleted > min[0]) return;
    	if (idx == s.length()) {
    		if (level == 0) {
    			min[0] = deleted;
    			res.add(path.toString());
    		}
    		return;
    	}
    	switch(s.charAt(idx)) {
    	case '(':
    		//keep '('
    		path.append('(');
    		dfs(res, path, s, idx+1, level+1, deleted, min);
    		path.deleteCharAt(path.length()-1);
    		//delete '('
    		dfs(res, path, s, idx+1, level, deleted+1, min);
    		break;
    	case ')':
    		if (level > 0) { //keep ')'
	    		path.append(')');
	    		dfs(res, path, s, idx+1, level-1, deleted, min);
	    		path.deleteCharAt(path.length()-1);
	    	}
    		//delete ')'
    		dfs(res, path, s, idx+1, level, deleted+1, min);
    		break;
    	default:
    		path.append(s.charAt(idx));
    		dfs(res, path, s, idx+1, level, deleted, min);
    		path.deleteCharAt(path.length()-1);
    	}
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RemoveInvalidParentheses solution = new RemoveInvalidParentheses();
		//["()()()", "(())()"]
		System.out.println(solution.removeInvalidParentheses("()())()"));
		//"(a)())()" -> ["(a)()()", "(a())()"]
		System.out.println(solution.removeInvalidParentheses("(a)())()"));
		//")(" -> [""]
		System.out.println(solution.removeInvalidParentheses(")("));
		//"" -> [""]
		System.out.println(solution.removeInvalidParentheses(""));
		//")(f" - > ["f"]
		System.out.println(solution.removeInvalidParentheses(")(f"));
	}

}
