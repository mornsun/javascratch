/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

 Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:

 Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 Every worker in the paid group must be paid at least their minimum wage expectation.
 Return the least amount of money needed to form a paid group satisfying the above conditions.

 Example 1:

 Input: quality = [10,20,5], wage = [70,50,30], K = 2
 Output: 105.00000
 Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 Example 2:

 Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 Output: 30.66667
 Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.

 Note:

 1 <= K <= N <= 10000, where N = quality.length = wage.length
 1 <= quality[i] <= 10000
 1 <= wage[i] <= 10000
 Answers within 10^-5 of the correct answer will be considered correct.

 Related Topic
 Heap

 * @author Chauncey
 *
 */
public class LC_857_Minimum_Cost_to_Hire_K_Workers
{
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        if (quality == null || quality.length == 0 || wage == null || wage.length != quality.length || K < 1) {
            return 0.0;
        }

        int n = quality.length;
        double[][] workers = new double[n][];
        for (int i=0; i<n; ++i) {
            workers[i] = new double[]{(double)wage[i] / quality[i], (double)wage[i], (double)quality[i]};
        }
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));

        PriorityQueue<Double> heap = new PriorityQueue<Double>();
        double sum_heap = 0.0;
        double ans = Double.MAX_VALUE;
        for (double[] worker : workers) {
            heap.offer(-worker[2]);
            sum_heap += worker[2];
            if (heap.size() > K) {
                sum_heap += heap.poll();
            }
            if (heap.size() == K) {
                ans = Double.min(ans, sum_heap * worker[0]);
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

		LC_857_Minimum_Cost_to_Hire_K_Workers solution = new LC_857_Minimum_Cost_to_Hire_K_Workers();
		System.out.println(solution.mincostToHireWorkers(new int[]{10,20,5}, new int[]{70,50,30}, 2));
        System.out.println(solution.mincostToHireWorkers(new int[]{3,1,10,10,1}, new int[]{4,8,2,2,7}, 3));
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
