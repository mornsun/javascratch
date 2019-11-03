/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.List;

/**
 * 	力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
 *
 * U: 向y轴正方向移动一格
 * R: 向x轴正方向移动一格。
 * 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
 *
 * 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
 *
 * 示例 1：
 *
 * 输入：command = "URR", obstacles = [], x = 3, y = 2
 * 输出：true
 * 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
 * 示例 2：
 *
 * 输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2
 * 输出：false
 * 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
 * 示例 3：
 *
 * 输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2
 * 输出：true
 * 解释：到达终点后，再碰到障碍物也不影响返回结果。
 *  
 *
 * 限制：
 *
 * 2 <= command的长度 <= 1000
 * command由U，R构成，且至少有一个U，至少有一个R
 * 0 <= x <= 1e9, 0 <= y <= 1e9
 * 0 <= obstacles的长度 <= 1000
 * obstacles[i]不为原点或者终点
 *
 * Backtracking, Bit Manipulation
 *
 * @author Chauncey
 * 执行用时 : 5 ms, 在所有 java 提交中击败了62.26%的用户
 * 内存消耗 : 37.7 MB, 在所有 java 提交中击败了100.00%的用户
 */
public class LCP_3_Robot
{
	public boolean robot(String command, int[][] obstacles, int x, int y) {
		if (command ==null || command.length()==0)
			return false;
		boolean[][] route = new boolean[1001][1001];
		int x0 = 0, y0 = 0;
		route[0][0] = true;
		for (char ch : command.toCharArray()) {
			if (ch=='U') {
				++y0;
			} else if (ch == 'R') {
				++x0;
			}
			route[y0][x0] = true;
		}
		int div = Math.min(y/y0, x/x0);
		int ny = y-y0*div, nx = x-x0*div;
		if (ny>=route.length || nx>=route[0].length || !route[ny][nx]) return false;
		for (int[] obstacle : obstacles==null ? new int[][]{} : obstacles) {
			int odiv = Math.min(obstacle[1]/y0, obstacle[0]/x0);
			if (odiv>div || odiv==div && (obstacle[0]>x || obstacle[1]>y)) continue;
			ny = obstacle[1]-y0*odiv;
			nx = obstacle[0]-x0*odiv;
			if (ny<route.length && nx<route[0].length && route[ny][nx]) return false;
		}
		return true;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LCP_3_Robot solution = new LCP_3_Robot();
        System.out.println(solution.robot("URR", new int[][]{}, 3, 2)); //true
		System.out.println(solution.robot("URR", new int[][]{{2, 2}}, 3, 2)); //false
		System.out.println(solution.robot("URR", new int[][]{{4, 2}}, 3, 2)); //true
		System.out.println(solution.robot("RRU", new int[][]{{6,2},{1,4},{8,0},{5,3},{6,3},{1,2},{2,2},{4,0},{9,5}},410, 6491));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
