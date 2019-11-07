/**
 * 
 */
package topcoder;

/**
 * 	Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.
 *
 * However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.
 *
 * Example:
 * Input: [1000,100,10,2]
 * Output: "1000/(100/10/2)"
 * Explanation:
 * 1000/(100/10/2) = 1000/((100/10)/2) = 200
 * However, the bold parenthesis in "1000/((100/10)/2)" are redundant,
 * since they don't influence the operation priority. So you should return "1000/(100/10/2)".
 *
 * Other cases:
 * 1000/(100/10)/2 = 50
 * 1000/(100/(10/2)) = 50
 * 1000/100/10/2 = 0.5
 * 1000/100/(10/2) = 2
 * Note:
 *
 * The length of the input array is [1, 10].
 * Elements in the given array will be in range [2, 1000].
 * There is only one optimal division for each test case.
 *
 * Math, String
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Optimal Division.
 * Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Optimal Division.
 */
public class LC_553_Optimal_Division
{
	public String optimalDivision(int[] nums) {

		if (nums==null || nums.length==0) return "";
		int n=nums.length;
		StringBuilder res = new StringBuilder();
		res.append(Integer.toString(nums[0]));
		if (n==1) return res.toString();
		res.append('/');
		if (n==2) return res.toString()+nums[1];
		res.append('(');
		for (int i=1; i<n; ++i) {
			res.append(Integer.toString(nums[i]));
			res.append('/');
		}
		res.deleteCharAt(res.length()-1);
		res.append(')');
		return res.toString();

	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_553_Optimal_Division solution = new LC_553_Optimal_Division();
		System.out.println(solution.optimalDivision(new int[]{1000})); //1000
		System.out.println(solution.optimalDivision(new int[]{1000,100})); //1000/100
        System.out.println(solution.optimalDivision(new int[]{1000,100,10,2})); //1000/(100/10/2)
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
