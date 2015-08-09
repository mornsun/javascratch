/**
 * 
 */
package topcoder;

/**
 * @author Chauncey
 *
 */
public class MedianofTwoSortedArrays
{

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	int total = nums1.length + nums2.length;
    	if ((total & 0x1) == 0) {
    		return (findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, total/2) + findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, total/2+1)) / 2.0;
    	} else {
    		return findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, total/2 + 1);
    	}
    }
    public static double findKth(int[] nums1, int l1, int n1, int[] nums2, int l2, int n2, int k) {
    	//System.out.println(l1+":"+n1+" "+l2+":"+n2+" "+k);
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
		int[] nums1 = new int[]{2};
		int[] nums2 = new int[]{1};
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}

}
