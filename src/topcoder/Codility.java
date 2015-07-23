/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class Codility
{
	public static int countPath(int[][] check)
	{
	  if (null == check || check.length == 0 || check[check.length-1][check.length-1] == 1) return 0;
	  
	  int[] dp = new int[check.length];
	  for(int i=0; i<check.length; ++i) {
	    if (check[0][i] == 1) break;
	    else dp[i] = 1;
	  }
      for (int i=1; i<check.length; ++i) {
        if (check[i][0] == 1) dp[0] = 0;
        else dp[0] = 1;
        for (int j=1; j<check.length; ++j) {
          if (check[i][j] == 1) dp[j] = 0;
          else dp[j] += dp[j-1];
        }
      }
	  return dp[check.length-1];
	}
	
	
    public static int solution1(int[] A) {
    	if (null == A || A.length < 2) return 0;
    	Arrays.sort(A);
    	int max_dist = 0;
    	for (int i=1; i<A.length; ++i) {
    		int dist = A[i] - A[i-1];
    		if (dist > max_dist) {
    			max_dist = dist;
    		}
    	}
    	return max_dist/2;
    }
    public static int abs(int x) {
    	return x<0? -x:x;
    }
    public static int square(int x) {
    	return x*x;
    }
    public static int solution2(int X, int Y) {
    	//System.out.println(X+":"+Y);
    	int len = abs(X)>abs(Y)? abs(X):abs(Y);
    	if (len == 0) return 0;
    	int in_num = square((len-1)*2+1);
    	if (Y == len && X>=-len+1 && X<=len) {
    		return in_num + X + len -1;
    	} else if (Y == -len &&X>=-len && X<=len-1) {
    		return in_num + len*2*2 - X + len -1;
    	} else if (X == len && Y>=-len && Y<=len-1) {
    		return in_num + len*2 + len - Y -1;
    	} else {
    		return in_num + len*2*3 + Y + len -1;
    	}
    }
    
    public static int solution(int[] A) {
    	if (null == A || A.length<5)
    		return 0;
    	int[] sum = new int[A.length];
    	sum[0] = A[0];
    	for (int i=1; i<A.length; ++i) {
    		sum[i] = sum[i-1] + A[i];
    	}
    	int x=1, y=A.length-2;
    	int total = sum[A.length-1];
    	//
    	while (x<y) {
    		int slice1 = sum[x-1];
    		int slice2 = sum[y-1]-sum[x];
    		int slice3 = total-sum[y];
    		if (slice1 > slice2 || slice3>slice2) {
    			break;
    		}
    		if (slice1 == slice3 && slice3 == slice2) {
    			return 1;
    		} else if (slice1<slice3) {
    			x++;
    		} else {
    			y--;
    		}
    	}
    	return 0;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//int[] nums = new int[]{4,5,1,1,1,1,4,3,1};
		//System.out.println(solution(nums));
		//1,2,6,20,70
		int[][] check = new int[5][5];
		//2^(n-1)+2*(n-2)
		//check[1][1] = 1;
		System.out.println(countPath(check));
		//
		/*System.out.println(solution(0,0));
		System.out.println(solution(0,1));
		System.out.println(solution(1,1));
		System.out.println(solution(1,0));
		System.out.println(solution(1,-1));
		System.out.println(solution(0,-1));
		System.out.println(solution(-1,-1));
		System.out.println(solution(-1,0));
		System.out.println(solution(-1,1));
		System.out.println(solution(-1,2));
		//
		System.out.println(solution(-1,2));
		System.out.println(solution(0,2));
		System.out.println(solution(1,2));
		System.out.println(solution(2,2));
		System.out.println(solution(2,1));
		System.out.println(solution(2,0));
		System.out.println(solution(2,-1));
		System.out.println(solution(2,-2));
		System.out.println(solution(1,-2));
		System.out.println(solution(0,-2));
		System.out.println(solution(-1,-2));
		System.out.println(solution(-2,-2));
		System.out.println(solution(-2,-1));
		System.out.println(solution(-2,0));
		System.out.println(solution(-2,1));
		System.out.println(solution(-2,2));
		System.out.println("start3");
		System.out.println(solution(-2,3));
		System.out.println(solution(-1,3));
		System.out.println(solution(0,3));
		System.out.println(solution(3,3));
		System.out.println(solution(3,0));
		System.out.println(solution(3,-3));
		System.out.println(solution(0,-3));
		System.out.println(solution(-3,-3));
		System.out.println(solution(-3,0));
		System.out.println(solution(-3,3));
		System.out.println(solution(-3,4));*/
		
		/*List<List<Integer>> list = threeSum(nums);
		for (List<Integer> l : list) {
			System.out.println(l);
		}*/
	}

}
