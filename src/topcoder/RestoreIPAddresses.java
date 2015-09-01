package topcoder;

import java.util.*;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

Hide Tags Backtracking String

 * @author Chauncey
 *
 */
public class RestoreIPAddresses
{
	private final boolean valide(String s, int start, int end) {
		int l = end-start;
		if (l==0 || l>3) return false;
		if (l==3) {
			char ch = s.charAt(start);
			if (ch > '2' || ch == '0') return false;
			if (ch == '2') {
				ch = s.charAt(start+1);
				if (ch > '5') return false;
				if (ch == '5') {
					ch = s.charAt(start+2);
					if (ch > '5') return false;
				}
			}
		} else if (l==2) {
			char ch = s.charAt(start);
			if (ch == '0') return false;
		}
		return true;
	}
	private final void dfs(String s, List<String> res, StringBuilder path, int start, int end, int level) {
		if (level == 0) {
			if (valide(s, start, end)) {
				int k = path.length();
				for (int i=start; i<end; ++i) path.append(s.charAt(i));
				res.add(path.toString());
				path.delete(k, path.length());
			}
		} else {
			for (int tail=start+1; tail<=end-level; ++tail) {
				if (valide(s, start, tail)) {
					int k = path.length();
					for (int i=start; i<tail; ++i) path.append(s.charAt(i));
					path.append('.');
			    	dfs(s, res, path, tail, end, level-1);
					path.delete(k, path.length());
				}
			}
		}
	}
    public List<String> restoreIpAddresses(String s) {
    	List<String> res = new ArrayList<String>();
    	if (s==null) return res;
    	int l = s.length();
    	if (l<4 || l>12) return res;
    	StringBuilder path = new StringBuilder();
    	dfs(s, res, path, 0, l, 3);
    	return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RestoreIPAddresses solution = new RestoreIPAddresses();
		System.out.println(solution.restoreIpAddresses("25525511135"));
		System.out.println(solution.restoreIpAddresses("0000"));
		System.out.println(solution.restoreIpAddresses("000256"));
	}

}
