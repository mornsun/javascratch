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
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 You may assume nums1 and nums2 cannot be both empty.
 Example 1:
 nums1 = [1, 3]
 nums2 = [2]

 The median is 2.0

 Example 2:
 nums1 = [1, 2]
 nums2 = [3, 4]

 The median is (2 + 3)/2 = 2.5

 Hide Tags Divide and Conquer, Array, Binary Search

 * dp[i][j] denote s1[0,i] and s2[0,j] match s3[0, i+j].(i and j exclusive and begin with 1, i<=s1.length and j<=s2.length)
 * dp[i][j] = dp[i-1][j] && (s3[i+j-1]==s1[i-1]) || dp[i][j-1] && (s3[i+j-1]==s2[j-1])
 * 
 * @author Chauncey
 *
 */
public class MedianofTwoSortedArraysIter
{
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || (nums1.length==0 && nums2.length==0))
            return 0.0;
        if (nums1.length > nums2.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        if (nums1.length==0)
            return nums2.length%2==0 ? (nums2[nums2.length/2-1]+nums2[nums2.length/2])/2.0 : nums2[nums2.length/2];

        int iMin = 0;
        int iMax = nums1.length - 1;
        int halfLen = (nums1.length + nums2.length + 1) / 2;
        while (iMin < iMax) {
            int m1 = iMin + (iMax - iMin >> 1);
            int m2 = halfLen - m1 - 1;
            if (nums1[m1] < nums2[m2]) {
                iMin = m1 + 1;
            } else if (nums1[m1] > nums2[m2]) {
                iMax = m1 - 1;
            }
        }
        int left = iMin;
        int right = halfLen - iMin - 1;
        System.out.println(left+":"+right);
        if (left == 0) return nums1.length==nums2.length ? (nums1[0]+nums2[right])/2.0 : Math.min(nums1[left], nums2[right]);
        if (left == nums1.length-1) return nums1.length==nums2.length ? (nums1[nums1.length-1]+nums2[0])/2.0 : Math.min(nums1[left], nums2[right]);
        if ( (nums1.length+nums2.length) % 2 == 0) return (nums1[left]+nums2[right])/2.0;
        return Math.min(nums1[left], nums2[right]);
    }
    // 1, 3
    // 2, 4
    // 2[0,0] 3[0,1] 4[1,1] 5[1,2] 6[2,2] 7[2,3] 8[3,3]

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
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
        //
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3})); //2.0
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4})); //2.5
        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2})); //2.0
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1})); //1.0
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{2,3})); //2.5
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{-1,3})); //2.5
        /*try {
            largetest("/home/work/testdata");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
