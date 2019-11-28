/**
 * 
 */
package topcoder;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5

Hide Tags Divide and Conquer Array Binary Search

 Runtime: 2 ms, faster than 99.97% of Java online submissions for Median of Two Sorted Arrays.
 Memory Usage: 47.3 MB, less than 84.03% of Java online submissions for Median of Two Sorted Arrays.
 * @author Chauncey
 *
 */
public class xMedianofTwoSortedArrays
{
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if (nums1.length>nums2.length) {
			int[] tmp = nums2;
			nums2 = nums1;
			nums1 = tmp;
		}
		int m = nums1.length, n = nums2.length;
		if (n==0) return -1;
		int imin = 0, imax = m, half = (m+n)/2;
		while (imin<=imax) {
			int i = (imin+imax)/2;
			int j = half-i;
			if (i>0 && nums1[i-1]>nums2[j])
				imax = i-1;
			else if (i<m && nums2[j-1]>nums1[i])
				imin = i+1;
			else {
				int rmin;
				if (i==m) rmin = nums2[j];
				else if (j==n) rmin = nums1[i];
				else rmin = Math.min(nums1[i], nums2[j]);
				if ((m+n)%2!=0)
					return rmin;

				int lmax;
				if (i==0) lmax = nums2[j-1];
				else if (j==0) lmax = nums1[i-1];
				else lmax = Math.max(nums1[i-1], nums2[j-1]);
				return (lmax+rmin)/2.0;
			}
		}
		return -1;
	}

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
    	int total = nums1.length + nums2.length;
    	if ((total & 0x1) == 0) {
    		return (findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, total/2) + findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, total/2+1)) / 2.0;
    	} else {
    		return findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, total/2 + 1);
    	}
    }
    public static double findKth(int[] nums1, int l1, int n1, int[] nums2, int l2, int n2, int k) {
    	//for terminating
    	if (n1 > n2) return findKth(nums2, l2, n2, nums1, l1, n1, k);
    	if (n1 == 0) return nums2[l2+k-1];
    	if (k == 1) return nums1[l1] < nums2[l2] ? nums1[l1] : nums2[l2];

    	int m1 = k/2 < n1 ? k/2 : n1, m2 = k-m1;
    	//System.out.println(m1+" "+m2);
    	if (nums1[l1+m1-1] == nums2[l2+m2-1]) {
    		return nums1[l1+m1-1];
    	} else if (nums1[l1+m1-1] < nums2[l2+m2-1]) {
    		return findKth(nums1, l1+m1, n1-m1, nums2, l2, n2, k-m1);
    	} else {
    		return findKth(nums1, l1, n1, nums2, l2+m2, n2-m2, k-m2);
    	}
    
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println(findMedianSortedArrays(new int[]{2}, new int[]{1}));//1.5
		System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2})); //2
		System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4})); //2.5
		System.out.println(findMedianSortedArrays(new int[]{2}, new int[]{})); //2
	}

}
