package topcoder;

import java.util.*;

/**
 * Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
Hide Tags Stack String

 * @author Chauncey
 *
 */
public class SimplifyPath
{
    public String simplifyPath(String path) {
        if (path==null) return ".";
        path = path.trim();
        int l = path.length();
        if (l==0) return ".";
        LinkedList<String> stack = new LinkedList<String>();
        int i = 0;
        while (i < l) {
        	int next = path.indexOf('/', i);
        	if (next==-1) next = l;
        	if (next>i) {
        		String dir = path.substring(i, next);
        		if (".".equals(dir)) {
        		} else if ("..".equals(dir)) {
        			stack.poll();
        		} else {
        			stack.push(dir);
        		}
        	}
        	i = next+1;
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
        	res.append('/');
        	res.append(stack.pollLast());
        }
        return res.length()==0 ? "/" : res.toString();
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SimplifyPath solution = new SimplifyPath();
		
		System.out.println(solution.simplifyPath("/a/./b//../../c/"));
	}

}
