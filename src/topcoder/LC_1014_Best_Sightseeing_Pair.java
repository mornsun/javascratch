/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 	Given an array A of positive integers, A[i] represents the value of the i-th sightseeing spot, and two sightseeing spots i and j have distance j - i between them.
 *
 * The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) : the sum of the values of the sightseeing spots, minus the distance between them.
 *
 * Return the maximum score of a pair of sightseeing spots.
 *
 * Example 1:
 *
 * Input: [8,1,5,2,6]
 * Output: 11
 * Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 *
 * Note:
 *
 * 2 <= A.length <= 50000
 * 1 <= A[i] <= 1000
 *
 * Array

 * @author Chauncey
 * Runtime: 3 ms, faster than 96.21%
 */
public class LC_1014_Best_Sightseeing_Pair
{
    public int maxScoreSightseeingPair(int[] A) {
        if (A==null || A.length<2)
            return 0;

        int max = 0;
        int mm_i = 0;
        int mm = A[0];
        for (int i=1; i<A.length; ++i) {
            int score = A[i] + A[mm_i] + mm_i - i;
            if (score > max) max = score;
            if (A[i]+i>mm) {
                mm = A[i]+i;
                mm_i = i;
            }
        }

        return max;

    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1014_Best_Sightseeing_Pair solution = new LC_1014_Best_Sightseeing_Pair();
		System.out.println(solution.maxScoreSightseeingPair(new int[]{8,1,5,2,6})); //11
		System.out.println(solution.maxScoreSightseeingPair(new int[]{1,3,5})); //7
		System.out.println(solution.maxScoreSightseeingPair(new int[]{7,2,6,6,9,4,3})); //14
        System.out.println(solution.maxScoreSightseeingPair(new int[]{6,3,7,4,7,6,6,4,9})); //13

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
/*
public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] A = stringToIntegerArray(line);

            int ret = new Solution().maxScoreSightseeingPair(A);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}*/
