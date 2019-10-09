/**
 * 
 */
package topcoder;

/**
 * 	Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.
 *
 * It is guaranteed that the answer is unique.
 *
 * Example 1:
 *
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 *
 * Example 2:
 *
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 *
 * Example 3:
 *
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s only contains lower case English letters.
 *
 * @author Chauncey
 * Runtime: 6 ms, faster than 89.20% of Java online submissions for Remove All Adjacent Duplicates in String II.
 * Memory Usage: 38.7 MB, less than 100.00% of Java online submissions for Remove All Adjacent Duplicates in String II.
 */
public class LC_1209_Remove_All_Adjacent_Duplicates_in_String_II
{
	public String removeDuplicates(String s, int k) {
		if (s==null || s.length()==0)
			return "";

		int n=s.length();
		char[] res = new char[n+1];
		int[] cnts = new int[n];
		int t = 0;
		for (int i=0; i<n; ++i) {
			char ch = s.charAt(i);
			if (t==0 || ch != res[t-1]) {
				res[t] = ch;
				cnts[t++] = 1;
				continue;
			}
			cnts[t] = cnts[t-1]+1;
			if (cnts[t] < k) {
				res[t++] = ch;
				continue;
			}
			t -= k-1;
		}

		return new String(res).substring(0, t);
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1209_Remove_All_Adjacent_Duplicates_in_String_II solution = new LC_1209_Remove_All_Adjacent_Duplicates_in_String_II();
        System.out.println(solution.removeDuplicates("abcd", 2)); //abcd
		System.out.println(solution.removeDuplicates("deeedbbcccbdaa", 3)); //aa
		System.out.println(solution.removeDuplicates("pbbcggttciiippooaais", 2)); //ps
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
