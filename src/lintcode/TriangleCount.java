package lintcode;

import java.util.*;

/**
 * Given an array of integers, how many three numbers can be found in the array, so that we can build an triangle whose three edges length is the three numbers that we find?

Have you met this question in a real interview? Yes
Example
Given array S = [3,4,6,7], return 3. They are:

[3,4,6]
[3,6,7]
[4,6,7]
Given array S = [4,4,4,4], return 4. They are:

[4(1),4(2),4(3)]
[4(1),4(2),4(4)]
[4(1),4(3),4(4)]
[4(2),4(3),4(4)]
Tags Expand 
Two Pointers LintCode Copyright


Related Problems Expand 
Medium 3 Sum 19 %
Medium 2 Sum 27 %

 * @author Chauncey
 *
 */
public class TriangleCount
{
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int S[]) {
        if (S==null || S.length==0) return 0;
        int cnt = 0;
        Arrays.sort(S);
        for (int i=S.length-1; i>1; --i) {
            int x=0;
            int y=i-1;
            while (x<y) {
                if (S[x]+S[y] > S[i]) {
                    cnt += y-x;
                    --y;
                } else {
                    ++x;
                }
            }
        }
        return cnt;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        TriangleCount solution = new TriangleCount();

        //3
        System.out.println(solution.triangleCount(new int[]{3,4,6,7}));
        //4
        System.out.println(solution.triangleCount(new int[]{4,4,4,4}));
    }

}
