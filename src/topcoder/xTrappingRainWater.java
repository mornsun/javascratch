/**
 * 
 */
package topcoder;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Hide Tags Array Stack Two Pointers
Hide Similar Problems (M) Container With Most Water (M) Product of Array Except Self

 * @author Chauncey
 * Runtime: 1 ms, faster than 95.77% of Java online submissions for Trapping Rain Water.
 * Memory Usage: 37.2 MB, less than 98.63% of Java online submissions for Trapping Rain Water.
 */
public class xTrappingRainWater
{
    public static int trap(int[] height) {
        if (height==null || height.length==0) return 0;
        int lo=0, hi=height.length-1, maxl=0, maxr=0, res=0;
        while(lo<=hi) {
            if (height[lo]<=height[hi]) {
                if (height[lo]>maxl) maxl = height[lo];
                else res+=maxl-height[lo];
                lo++;
            } else {
                if (height[hi]>maxr) maxr = height[hi];
                else res+=maxr-height[hi];
                hi--;
            }
        }
        return res;
    }

    public static int trap1(int[] height) {
        if (null == height || height.length == 0) return 0;
        int[] l2r = new int[height.length];
        int[] r2l = new int[height.length];
        // left to right scan
        l2r[0] = height[0];
        for (int i=1; i<height.length; ++i) {
            if (height[i] > l2r[i-1]) {
                l2r[i] = height[i];
            } else {
                l2r[i] = l2r[i-1];
            }
        }
        // right to left scan
        r2l[height.length-1] = height[height.length-1];
        for (int i=height.length-2; i>=0; --i) {
            if (height[i] > r2l[i+1]) {
                r2l[i] = height[i];
            } else {
                r2l[i] = r2l[i+1];
            }
        }
        // trap raining water
        int res = 0;
        for (int i=0; i<height.length; ++i) {
            int min = r2l[i] < l2r[i] ? r2l[i] : l2r[i];
            res += min-height[i];
        }
        return res;
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        int[] nums1 = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};//{2,5,7,8,12};
        int[] nums2 = new int[]{3};//{8,9,10,13,15};
        //
        System.out.println(trap(nums1));
        /*try {
            largetest("/home/work/testdata");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
