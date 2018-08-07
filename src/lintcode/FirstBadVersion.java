package lintcode;

import java.util.*;

/**
 * The code base version is an integer start from 1 to n. One day, someone committed a bad version in the code case, so it caused this version and the following versions are all failed in the unit tests. Find the first bad version.

You can call isBadVersion to help you determine which version is the first bad one. The details interface can be found in the code's annotation part.

Have you met this question in a real interview? Yes
Example
Given n = 5:

isBadVersion(3) -> false
isBadVersion(5) -> true
isBadVersion(4) -> true
Here we are 100% sure that the 4th version is the first bad version.

Note
Please read the annotation in code area to get the correct way to call isBadVersion in different language. For example, Java is SVNRepo.isBadVersion(v)

Challenge
You should call isBadVersion as few as possible.

Tags Expand 
Binary Search LintCode Copyright


Related Problems Expand 
Medium Nuts & Bolts Problem 12 %

 * @author Chauncey
 *
 */
public class FirstBadVersion
{

    /**
     * you can use SVNRepo.isBadVersion(k) to judge whether 
     * the kth code version is bad or not.
     */
     public static class SVNRepo {
         public static boolean isBadVersion(int k) {return false;}
     }
     
    /**
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        if (n < 1) return 0;
        if (!SVNRepo.isBadVersion(n)) return n + 1;
        int lo = 1;
        int hi = n;
        while (lo < hi) {
            int m = lo + (hi - lo >> 1);
            if (SVNRepo.isBadVersion(m)) {
                hi = m;
            } else {
                lo = m + 1;
            }
        }
        return lo;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        FirstBadVersion solution = new FirstBadVersion();

        //10
        System.out.println(solution.findFirstBadVersion(12));
    }

}
