/**
 * 
 */
package topcoder;

import java.util.*;

/**
 *
 *
 * @author Chauncey
 */
public class AM_Assessment
{

	private final static int[][] steps  = new int[][]{{-1,0}, {0,-1}, {0,1}, {1,0}};

	int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot)
	{
		if (lot == null || lot.size()==0 || lot.get(0)==null || lot.get(0).size()==0)
			return -1;
		int m=lot.size(), n=lot.get(0).size();
		boolean[][] visited = new boolean[m][n];
		LinkedList<int[]> curr = new LinkedList<>();
		curr.offer(new int[]{0,0});
		visited[0][0] = true;
		int dist = 0;
		while (!curr.isEmpty()) {
			LinkedList<int[]> prev = curr;
			curr = new LinkedList<>();
			++dist;
			while (!prev.isEmpty()) {
				int[] pos = prev.poll();
				for (int[] step : steps) {
					int y = pos[0] + step[0], x = pos[1] + step[1];
					if (y<0 || y>=m || x<0 || x>=n || visited[y][x]) continue;
					switch (lot.get(y).get(x)) {
						case 1:
							curr.offer(new int[]{y,x});
							visited[y][x] = true;
							break;
						case 9:
							return dist;
						default:
							break;
					}
				}
			}
		}
		return -1;
	}

	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED [0,0,0] ,0

	ArrayList<Integer> IDsOfPackages(int truckSpace,
									 ArrayList<Integer> packagesSpace)
	{
		if (truckSpace <30 || packagesSpace==null || packagesSpace.size()<2)
			return null;
		ArrayList<Integer> res = new ArrayList<>();
		int n = packagesSpace.size();
		int totalSpace = truckSpace-30;
		HashMap<Integer, Integer> packs = new HashMap<>();
		int max = Integer.MIN_VALUE;
		for (int i=0; i<n; ++i) {
			Integer space = packagesSpace.get(i);
			Integer other = packs.get(totalSpace - space);
			packs.put(space, i);
			if (other == null)
				continue;
			int larger = Math.max(space, totalSpace-space);
			if (larger>max) {
				max = larger;
				res.clear();
				res.add(other);
				res.add(i);
			}
		}
		return res;
	}
	/*ArrayList<Integer> IDsOfPackages(int truckSpace,
									 ArrayList<Integer> packagesSpace)
	{
		if (truckSpace <30 || packagesSpace==null || packagesSpace.size()==0) return null;
		ArrayList<Integer> res = new ArrayList<>();
		int n = packagesSpace.size();
		int[][] packs = new int[n][];
		for (int i=0; i<n; ++i) {
			packs[i] = new int[]{packagesSpace.get(i), i};
		}
		Arrays.sort(packs, (o1, o2) -> o1[0]-o2[0]);
		int lo = 0, hi = n-1;
		while (lo<hi) {
			if (packs[lo][0] + packs[hi][0])
		}
	}*/

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		AM_Assessment solution = new AM_Assessment();
		ArrayList<Integer> test = new ArrayList<>();
		test.add(0);
		test.add(0);
		test.add(0);
        System.out.println(solution.IDsOfPackages(20, test)); //0 1 0 0 1 0 1 0
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
