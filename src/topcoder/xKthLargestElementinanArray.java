/**
 * 
 */
package topcoder;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.

Hide Tags Divide and Conquer Heap

 * @author Chauncey
 * Runtime: 7 ms, faster than 28.07% of Java online submissions for Kth Largest Element in an Array.
 * Memory Usage: 39.7 MB, less than 22.44% of Java online submissions for Kth Largest Element in an Array.
 */
public class xKthLargestElementinanArray
{
    private int findKth(int[] nums, int k, int lo, int hi)
    {
        int v = nums[hi];
        int l = lo, h = hi-1;
        while (l<=h) {
            while (nums[l] > v)
                l++;
            while (h >= 1 && nums[h] <= v)
                h--;
            if (l>=h)
                break;
            swap(nums, l++, h--);
        }
        if (l+1==k)
            return nums[hi];
        swap(nums, l, hi);
        if (l+1<k) {
            return findKth(nums, k, l+1, hi);
        } else {
            return findKth(nums, k, lo, l-1);
        }
    }

    private int findKth3(int[] nums, int k, int lo, int hi)
    {
        int v = nums[hi];
        int i = lo;
        for (int j=lo; j<hi; ++j) {
            if (nums[j]>v) {
                swap(nums, i++, j);
            }
        }
        if (i+1==k)
            return nums[hi];
        swap(nums, i, hi);
        if (i+1<k) {
            return findKth(nums, k, i+1, hi);
        } else {
            return findKth(nums, k, lo, i-1);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        return findKth(nums, k, 0, nums.length-1);
    }

    /**
     * Use analog of quick sort to find the Kth largest element
     * @param lo, ri: inclusive
     */
    private int findKth1(int[] nums, int k, int lo, int hi)
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

    private static void swap(int[] nums, int i, int j) {
        if (i==j) return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    /**
     * Use analog of quick sort to find the Kth largest element
     * reference to "Introduction to Algorithms"
     * @param lo, ri: inclusive
     */
    private int findKth2(int[] nums, int k, int lo, int hi)
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
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        xKthLargestElementinanArray solution = new xKthLargestElementinanArray();
        System.out.println(solution.findKthLargest(new int[]{3,2,1,5,6,4}, 2)); //5
        System.out.println(solution.findKthLargest(new int[]{3,2,1,5,6,4}, 4)); //3
        System.out.println(solution.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4)); //4
        System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));

    }

}
