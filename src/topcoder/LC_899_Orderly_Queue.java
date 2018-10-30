/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * A string S of lowercase letters is given.  Then, we may make any number of moves.

 In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.

 Return the lexicographically smallest string we could have after any number of moves.


 Example 1:

 Input: S = "cba", K = 1
 Output: "acb"
 Explanation:
 In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".
 In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".
 Example 2:

 Input: S = "baaca", K = 3
 Output: "aaabc"
 Explanation:
 In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".
 In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".


 Note:

 1 <= K <= S.length <= 1000
 S consists of lowercase letters only.

 Related Topic
 Math, String

 * @author Chauncey
 *
 */
public class LC_899_Orderly_Queue
{
    public String orderlyQueue(String S, int K) {
        if (S == null || S.length() == 0 || K <= 0) {
            return S;
        }
        if (K==1) {
            String res = S;
            for (int i=0; i<S.length(); ++i) {
                S = S.substring(1) + S.charAt(0);
                if (S.compareTo(res)<0) {
                    res = S;
                }
            }
            return res;
        } else {
            char[] chs = S.toCharArray();
            Arrays.sort(chs);
            return new String(chs);
        }
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_899_Orderly_Queue solution = new LC_899_Orderly_Queue();
		System.out.println(solution.orderlyQueue("baaca", 3));
        System.out.println(solution.orderlyQueue("cba", 1));
		
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
