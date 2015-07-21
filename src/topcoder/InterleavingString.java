/**
 * 
 */
package topcoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * dp[i][j] denote s1[0,i] and s2[0,j] match s3[0, i+j].(i and j exclusive and begin with 1, i<=s1.length and j<=s2.length)
 * dp[i][j] = dp[i-1][j] && (s3[i+j-1]==s1[i-1]) || dp[i][j-1] && (s3[i+j-1]==s2[j-1])
 * 
 * @author Chauncey
 *
 */
public class InterleavingString
{
    public static boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length();
        if ( s3.length() != n1+n2) return false;
        boolean[][] dp = new boolean[n1+1][n2+1];
        dp[0][0] = true;
        for (int i=0; i<n1; ++i) {
            if (s1.charAt(i) == s3.charAt(i)) {
                dp[i+1][0] = true;
            } else {
                break;
            }
        }
        for (int j=0; j<n2; ++j) {
            if (s2.charAt(j) == s3.charAt(j)) {
                dp[0][j+1] = true;
            } else {
                break;
            }
        }
        for (int i=1; i<=n1; ++i) {
            for (int j=1; j<=n2; ++j) {
                if ( (dp[i-1][j] && (s1.charAt(i-1) == s3.charAt(i+j-1))) || 
                        dp[i][j-1] && (s2.charAt(j-1)==s3.charAt(i+j-1))) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[n1][n2];
    }
    
    public static void datacheck(int[] nums)
    {
        int k = 10000;
        for (int i=0; i<nums.length; ++i) {
            if (k != nums[i]) {
                if (i != 0) {
                    System.out.println("prev:"+(i-1)+":"+nums[i-1]);
                }
                System.out.println(i+":"+nums[i]);
            }
            k--;
        }
    }
    
    
    public static void largetest(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            String line = br.readLine();
            line = line.substring(1);
            line = line.substring(0, line.length()-1);
            String[] strs = line.split(",");
            int[] nums = new int[strs.length];
            for (int i=0; i<nums.length; ++i) {
                nums[i] = Integer.parseInt(strs[i]);
            }
            //int res = maxProfit(nums);
            //System.out.println(res);
            //datacheck(nums);
        } finally {
            br.close();
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        int[] prices = new int[]{9,8,7,6,5,4,3,2,1,0,0,0,0,0,0,0};
        //
        //s1 = "aabcc",
                //s2 = "dbbca",
                //When s3 = "aadbbcbcac", return true.
                //When s3 = "aadbbbaccc", return false.
        boolean res = isInterleave("aabcc", "dbbca", "aadbbbaccc");
        System.out.println(res);
        try {
            largetest("/home/work/testdata");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
