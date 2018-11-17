/**
 * 
 */
package topcoder;

import java.util.Arrays;

/**
 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.
 Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
 Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
 Return the minimum integer K such that she can eat all the bananas within H hours.


 Example 1:
 Input: piles = [3,6,7,11], H = 8
 Output: 4
 Example 2:
 Input: piles = [30,11,23,4,20], H = 5
 Output: 30
 Example 3:
 Input: piles = [30,11,23,4,20], H = 6
 Output: 23

 Note:
 1 <= piles.length <= 10^4
 piles.length <= H <= 10^9
 1 <= piles[i] <= 10^9

 Related Topics
 Binary Search

 * @author Chauncey
 * beat 52.87%
 */
public class LC_875_Koko_Eating_Bananas
{
    public int minEatingSpeed(int[] piles, int H) {
        if (piles == null || piles.length == 0 || H<piles.length)
            return -1;

        Arrays.sort(piles);
        if (H == piles.length)
            return piles[piles.length-1];

        long sum = 0;
        for (long n : piles) {
            sum += n;
        }

        int lo = (int)(sum/H);
        if (lo == 0)
            return 1;
        int hi = piles[piles.length-1];

        while (lo<hi) {
            int m = lo + (hi-lo>>1);
            int h = hourCost(m, piles);
            //System.out.println(m+":"+h);
            if (h<=H) {
                hi = m;
            } else {
                lo = m+1;
            }
        }
        return (int)lo;
    }

    private static int hourCost(int k, int[] piles) {
        int h = 0;
        for (int pile : piles) {
            h += (pile+k-1)/k;
        }
        return h;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_875_Koko_Eating_Bananas solution = new LC_875_Koko_Eating_Bananas();
		System.out.println(solution.minEatingSpeed(new int[]{3,6,7,11}, 8)); //4
        System.out.println(solution.minEatingSpeed(new int[]{30,11,23,4,20}, 5)); //30
        System.out.println(solution.minEatingSpeed(new int[]{30,11,23,4,20}, 6)); //23
        System.out.println(solution.minEatingSpeed(new int[]{312884470}, 968709470)); //1
        System.out.println(solution.minEatingSpeed(new int[]{980628391, 681530205, 734313996, 168632541},819870953)); //


		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
