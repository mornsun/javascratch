/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.
 *
 * Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.
 *
 * Example:
 *
 * Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * Output: 3
 * Explanation:
 * There're totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 *
 * Note:
 *
 * The integer 1 <= d, t, n <= 10,000.
 * You can't take two courses simultaneously.
 *
 * @author Chauncey
 * Runtime: 68 ms, faster than 72.78% of Java online submissions for Course Schedule III.
 * Memory Usage: 59.5 MB, less than 28.57% of Java online submissions for Course Schedule III.
 */
public class xLC_630_Course_Schedule_III
{
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (o1, o2) -> o1[1]-o2[1]);
        int time = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int[] c : courses) {
            time += c[0];
            pq.offer(c[0]);
            if (time>c[1]) {
                time -= pq.poll();
            }
        }
        return pq.size();

    }
        
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        
        xLC_630_Course_Schedule_III solution = new xLC_630_Course_Schedule_III();
        System.out.println(solution.scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}})); //3

        System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
    }

}
