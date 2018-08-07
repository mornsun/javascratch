package lintcode;

import java.util.*;

/**
 * On one line there are n houses. Give you an array of integer means the the position of each house. Now you need to pick k position to build k post office, so that the sum distance of each house to the nearest post office is the smallest. Return the least possible sum of all distances between each village and its nearest post office.

Have you met this question in a real interview? Yes
Example
Given array a = [1,2,3,4,5], k = 2. return 3.

Challenge
Could you solve this problem in O(n^2) time ?

 * @author Chauncey
 *
 */
public class xPostOfficeProblem
{
    /**
     * @param A an integer array
     * @param k an integer
     * @return an integer
     */
    public int postOffice(int[] A, int k) {
        if (null == A || A.length==0 || k==0) return 0;
        if (k>=A.length) return 0;
        Arrays.sort(A);
        int[][] between = new int[A.length][A.length]; //between house i and j (inclusive), build 1 post office
        for (int i=0; i<A.length; ++i) {
            int lsum = A[i];
            int office = i; //the offset of post office, proved to be the middle element
            int rsum = 0;
            for (int j=i+1; j<A.length; ++j) {
                rsum+=A[j];
                if (office < i+(j-i>>1)) {
                    ++office;
                    lsum+=A[office];
                    rsum-=A[office];
                }
                between[i][j] = (A[office]*(office-i+1) - lsum) + (rsum-A[office]*(j-office));
            }
        }
        int[] fprev = new int[A.length];
        int[] fcurr = new int[A.length];
        for (int j=0; j<A.length; ++j) {
            fprev[j] = between[0][j]; //before house j (inclusive), build 1 post office
        }
        for (int i=1; i<k; ++i) {
            fcurr[i] = 0;
            int left = i;
            for (int j=i+1; j<A.length; ++j) {
                int min = Integer.MAX_VALUE;
                for (int s=left; s<j; ++s) {
                    int next = fprev[s-1] + between[s][j];
                    if (next < min) {
                        min = next;
                        left = s;
                    }
                    //System.out.print(next+",");
                }
                //System.out.println("min:"+min);
                fcurr[j] = min;
            }
            int[] tmp = fcurr;
            fcurr = fprev;
            fprev = tmp;
            //System.out.println();
        }
        return fprev[A.length-1];
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        xPostOfficeProblem solution = new xPostOfficeProblem();

        //3
        System.out.println(solution.postOffice(new int[]{1,2,3,4,5}, 2));
        //673
        System.out.println(solution.postOffice(new int[]{112,122,360,311,85,225,405,53,405,43,342,13,588,424,299,37,104,289,404,414}, 3));
    }

}
