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
public class MedianofTwoSortedArraysIter
{
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //m1 + m2 == (nums1.length + nums2.length - 1)/2
        boolean need_avg = ((nums1.length + nums2.length)%2 ==0);
        if (nums1.length == 0) {
            int k = (nums2.length-1)/2;
            if (need_avg) return nums2[k]+(nums2[k+1]-nums2[k])/2.0;
            return nums2[k];
        }
        if (nums2.length == 0) {
            int k = (nums1.length-1)/2;
            if (need_avg) return nums1[k]+(nums1[k+1]-nums1[k])/2.0;
            return nums1[k];
        }
        if (nums1.length < nums2.length) { // ensure nums1 is larger
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        int total = (nums1.length + nums2.length)/2 + 1;
        int l = total-nums2.length;
        System.out.println(total);
        int r = total;
        while (l<r) {
            int m1 = l+(r-l)/2;   //(4 + 5) / 2 = 4
            int m2 = total - m1 - 1;
            System.out.println(l+":"+r+":"+m1+":"+m2);
            if (nums1[m1] == nums2[m2]) {
                return nums1[m1];
            } else if (nums1[m1] < nums2[m2]) {
                l = m1;
            } else {
                r = m1;
            }
        }
        int m1 = l;
        int m2 = total-l-1;
        System.out.println(l+":"+r+":"+m1+":"+m2);
        if (nums1[m1] < nums2[m2]) {
            if (need_avg) {
                if (m1+1 !=  nums1.length && nums1[m1+1]<nums2[m2]) {
                    return nums1[m1] + (nums1[m1+1] - nums1[m1])/2.0;
                }
                return nums1[m1] + (nums2[m2] - nums1[m1])/2.0;
            }
            return nums1[m1];
        } else {
            if (need_avg) {
                if (m2+1 != nums2.length && nums2[m2+1]<nums1[m1]) {
                    return nums2[m2] + (nums2[m2-1] - nums2[m2])/2.0;
                }
                return nums2[m2] + (nums1[m1] - nums2[m2])/2.0;
            }
            return nums2[m2];
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        int[] nums1 = new int[]{1,2};//{2,5,7,8,12};
        int[] nums2 = new int[]{3};//{8,9,10,13,15};
        //
        System.out.println(findMedianSortedArrays(nums1, nums2));
        /*try {
            largetest("/home/work/testdata");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
