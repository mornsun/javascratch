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
public class LongestCommonPrefix
{
    private static boolean foo1(int i) {
        System.out.println("foo1"+i);
        return true;
    }
    private static boolean foo2(int i) {
        System.out.println("foo2"+i);
        return true;
    }
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i=1; i<strs.length; ++i) {
            String str = strs[i];
            int len = str.length() < prefix.length() ? str.length() : prefix.length();
            int j = 0;
            while (j<len && str.charAt(j) == prefix.charAt(j)) {
                j++;
            }
            if (prefix.length() != j)
                prefix = prefix.substring(0,j);
        }
        return prefix;
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
        String[] strs = new String[]{"abbcc", "abbca", "abdbbbaccc"};
        String res = longestCommonPrefix(strs);
        System.out.println(res);
        /*try {
            largetest("/home/work/testdata");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
