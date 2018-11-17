/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * The i-th person has weight people[i], and each boat can carry a maximum weight of limit.
 Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.
 Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)

 Example 1:
 Input: people = [1,2], limit = 3
 Output: 1
 Explanation: 1 boat (1, 2)
 Example 2:
 Input: people = [3,2,2,1], limit = 3
 Output: 3
 Explanation: 3 boats (1, 2), (2) and (3)
 Example 3:
 Input: people = [3,5,3,4], limit = 5
 Output: 4
 Explanation: 4 boats (3), (3), (4), (5)
 Note:
 1 <= people.length <= 50000
 1 <= people[i] <= limit <= 30000

 Related Topic
 Two Pointers, Greedy

 * @author Chauncey
 * beat 96.12%
 */
public class LC_881_Boats_to_Save_People
{
    public int numRescueBoats(int[] people, int limit) {
        if (people==null || people.length==0 || limit<=0) {
            return -1;
        }
        Arrays.sort(people);

        if (people[people.length-1] > limit) {
            return -1;
        }

        int x=0, y=people.length-1, ans=0;
        while(x<y) {
            if (people[x] + people[y] <= limit) {
                ++x;
                --y;
            } else {
                --y;
            }
            ++ans;
        }
        if (x==y)
            ++ans;

        return ans;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_881_Boats_to_Save_People solution = new LC_881_Boats_to_Save_People();
		System.out.println(solution.numRescueBoats(new int[]{1,2}, 3)); //1
        System.out.println(solution.numRescueBoats(new int[]{3,2,2,1}, 3)); //3
        System.out.println(solution.numRescueBoats(new int[]{3,5,3,4}, 5)); //4
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
