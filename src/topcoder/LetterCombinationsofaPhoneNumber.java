/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class LetterCombinationsofaPhoneNumber
{
    private final static char[][] dmap = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
    
    private static void dfs(String digits, int index, StringBuilder path, LinkedList<String> res) {
        if (digits.length() == index) {
            if (path.length() != 0) res.add(path.toString());
            return;
        }
        int digit = digits.charAt(index) - '0';
        if (digit < 0 || digit > 9) return;
        char[] options = dmap[digit];
        for (int i=0; i<options.length; ++i) {
            path.append(options[i]);
            dfs(digits, index+1, path, res);
            path.deleteCharAt(path.length()-1);
        }
    }
    public static List<String> letterCombinations(String digits) {
        StringBuilder path = new StringBuilder();
        LinkedList<String> res = new LinkedList<String>();
        dfs(digits, 0, path, res);
        return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{-2,0,0,2,2};
		System.out.println(letterCombinations("23"));
	}

}
