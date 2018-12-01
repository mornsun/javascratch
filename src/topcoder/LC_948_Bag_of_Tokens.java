/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 	You have an initial power P, an initial score of 0 points, and a bag of tokens.
 Each token can be used at most once, has a value token[i], and has potentially two ways to use it.
 If we have at least token[i] power, we may play the token face up, losing token[i] power, and gaining 1 point.
 If we have at least 1 point, we may play the token face down, gaining token[i] power, and losing 1 point.
 Return the largest number of points we can have after playing any number of tokens.

 Example 1:
 Input: tokens = [100], P = 50
 Output: 0

 Example 2:
 Input: tokens = [100,200], P = 150
 Output: 1

 Example 3:
 Input: tokens = [100,200,300,400], P = 200
 Output: 2

 Note:
 tokens.length <= 1000
 0 <= tokens[i] < 10000
 0 <= P < 10000

 Related Topics:
 Greedy

 * @author Chauncey
 * beat 70.60%
 */
public class LC_948_Bag_of_Tokens
{
	public int bagOfTokensScore(int[] tokens, int P) {
		if (tokens==null || tokens.length==0 || P<=0)
			return 0;

		Arrays.sort(tokens);
		int lo = 0;
		int hi = tokens.length-1;
		int points = 0;
		int ans = 0;
		while(lo<=hi && (P>=tokens[lo] || points>0)) {
			while (lo<=hi && P>=tokens[lo]) {
				P -= tokens[lo++];
				++points;
			}
			ans = Math.max(ans, points);
			if (lo<=hi && points>0) {
				--points;
				P += tokens[hi--];
			}
		}
		return ans;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_948_Bag_of_Tokens solution = new LC_948_Bag_of_Tokens();
		System.out.println(solution.bagOfTokensScore(new int[]{100}, 50)); //0
		System.out.println(solution.bagOfTokensScore(new int[]{100,200}, 150)); //1
		System.out.println(solution.bagOfTokensScore(new int[]{100,200,300,400}, 200)); //2
		System.out.println(solution.bagOfTokensScore(new int[]{33,4,28,24,96}, 35)); //3

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
