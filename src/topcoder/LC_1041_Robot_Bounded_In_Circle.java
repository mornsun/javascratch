/**
 * 
 */
package topcoder;

/**
 * 	On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:
 *
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degress to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 *
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 *
 * Example 1:
 *
 * Input: "GGLLGG"
 * Output: true
 * Explanation:
 * The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
 * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
 * Example 2:
 *
 * Input: "GG"
 * Output: false
 * Explanation:
 * The robot moves north indefinitely.
 * Example 3:
 *
 * Input: "GL"
 * Output: true
 * Explanation:
 * The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 *
 * Note:
 *
 * 1 <= instructions.length <= 100
 * instructions[i] is in {'G', 'L', 'R'}
 *
 * Hint 1
 * Calculate the final vector of how the robot travels after executing all instructions once - it consists of a change in position plus a change in direction.
 * Hint 2
 *  The robot stays in the circle iff (looking at the final vector) it changes direction (ie. doesn't stay pointing north), or it moves 0.
 *
 *  Math
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Robot Bounded In Circle.
 * Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Robot Bounded In Circle.
 */
public class LC_1041_Robot_Bounded_In_Circle
{
	private int[][] step = new int[][]{{0,1},{-1,0},{0,-1},{1,0}};
	public boolean isRobotBounded(String instructions) {

		if (instructions==null || instructions.length()==0) return true;
		int d = 0, x = 0, y = 0;
		for (char ch : instructions.toCharArray()) {
			switch(ch) {
				case 'L': d++; break;
				case 'R': d--; break;
				default: d%=4; if (d<0) d+=4; x+=step[d][0]; y+=step[d][1]; break;
			}
		}
		return d%4!=0 || x==0 && y==0;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1041_Robot_Bounded_In_Circle solution = new LC_1041_Robot_Bounded_In_Circle();
        System.out.println(solution.isRobotBounded("GGLLGG")); //true
		System.out.println(solution.isRobotBounded("GG")); //false
		System.out.println(solution.isRobotBounded("GL")); //true
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
