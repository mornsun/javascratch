/**
 * 
 */
package topcoder;

import java.util.Arrays;

/**
 *
#NW  R1 | R2
  1  60 | 0
  2  60 | 90
  3  60 | 0
     -------
     60 | 30

 ? -> Rank1 or Rank2
 combined ranker : (60+90+60) = 70

 IF (#NW in [a,b])
   RankX
 ELSE
   RankY

 output a,b,x,y

 example: a=2,b=2,x=2,y=1

 Related Topics:
 Array

 * @author Chauncey
 * beat %
 */
public class xCombinedRanker
{
    public int[] combinedRanker(int[] scores1, int[] scores2) {
		if (scores1==null || scores1.length==0 || scores2==null || scores2.length!=scores1.length)
			return null;

		int[] main1 = combinedRankerRyAsMain(scores2, scores1);
		int[] main2 = combinedRankerRyAsMain(scores1, scores2);

		if (main1==null || main2==null || main1.length!=3 || main2.length!=3)
			return null;

		if (main1[0] > main2[0])
			return new int[]{main1[1], main1[2], 2, 1};
		else
			return new int[]{main2[1], main2[2], 1, 2};
    }


	public int[] combinedRankerRyAsMain(int[] scoresX, int[] scoresY) {
		if (scoresX==null || scoresX.length==0 || scoresY==null || scoresY.length!=scoresX.length)
			return null;

		int[] subs = new int[scoresX.length];
		for (int i=0; i<scoresX.length; ++i)
			subs[i] = scoresX[i] - scoresY[i];

		int sum = 0;
		int curr_a = -1;

		//Rank1 in [a,b]
		int max = 0;
		int max_a = -1;
		int max_b = -1;
		for (int i=0; i<subs.length; ++i) {
			sum += subs[i];
			if (sum < 0) {
				sum = 0;
				curr_a = -1;
				continue;
			}
			if (curr_a == -1)
				curr_a = i;
			if (sum > max) {
				max = sum;
				max_a = curr_a;
				max_b = i;
			}
		}

		sum = 0;
		if (max_a==-1) {
			for (int score : scoresY) {
				sum += score;
			}
		} else {
			int i = 0;
			for (; i<max_a; ++i) {
				sum += scoresY[i];
			}
			for (; i<=max_b; ++i) {
				sum += scoresX[i];
			}
			for (; i<scoresY.length; ++i) {
				sum += scoresY[i];
			}
		}
		return new int[]{sum, max_a+1, max_b+1};
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xCombinedRanker solution = new xCombinedRanker();
		System.out.println(Arrays.toString(solution.combinedRanker(new int[]{60, 60, 60}, new int[]{0,90,0}))); //[2, 2, 2, 1]
		System.out.println(Arrays.toString(solution.combinedRanker(new int[]{0,90,0}, new int[]{60, 60, 60}))); //[2, 2, 1, 2]

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
