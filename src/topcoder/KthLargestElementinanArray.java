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
 * @author Chauncey
 *
 */
public class KthLargestElementinanArray
{
    /**
     * Use analog of quick sort to find the Kth largest element
     * @param lo, ri: inclusive
     */
    public static int findKth1(int[] nums, int k, int lo, int hi)
    {
        int v = nums[hi];
        int l = lo, r=hi;

        while (true) {
            while (nums[l] > v && l<r) {
                l++;
            }
            while (nums[r] < v && l<r) {
                r--;
            }
            if (l==r)
                break;
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
        }
        
        if (k == l+1 ) {
            return v;
        } else if (k < l+1) {
            return findKth(nums, k, lo, l-1);
        } else {
            return findKth(nums, k, l+1, hi);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    /**
     * Use analog of quick sort to find the Kth largest element
     * @param lo, ri: inclusive
     */
    public static int findKth(int[] nums, int k, int lo, int hi)
    {
        int v = nums[hi];
        int i = lo-1;
        for (int j=lo; j<hi; ++j) {
            if (nums[j] >= v) {
                ++i;
                swap(nums, i, j);
            }
        }
        swap(nums, i+1, hi);
        
        if (k == i+2 ) {
            return v;
        } else if (k < i+2) {
            return findKth(nums, k, lo, i);
        } else {
            return findKth(nums, k, i+2, hi);
        }
    }
    
    public static int findKthLargest(int[] nums, int k) {
        return findKth(nums, k, 0, nums.length-1);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        int[] nums = new int[]{3,2,1,5,6,4};
        int res = findKthLargest(nums, 4);
        for (int num : nums) {
            System.out.print(num+",");
        }
        System.out.println();
        System.out.println(res);
    }

}
