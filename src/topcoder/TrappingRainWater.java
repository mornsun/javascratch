/**
 * 
 */
package topcoder;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * 
 * Note: You may not slant the container.
 * @author Chauncey
 *
 */
public class TrappingRainWater
{
    public static int trap(int[] height) {
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
