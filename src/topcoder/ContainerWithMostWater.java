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
public class ContainerWithMostWater
{
    public static int maxArea(int[] height) {
        int max = 0;
        int start = 0;
        int end = height.length -1;
        while (start < end) {
            int h = height[start] < height[end] ? height[start] : height[end];
            int area = (end - start) * h;
            if (area > max) max = area;
            if (height[start] > height[end]) {
                --end;
            } else {
                ++start;
            }
        }
        return max;
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
        System.out.println(maxArea(nums1));
        /*try {
            largetest("/home/work/testdata");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
