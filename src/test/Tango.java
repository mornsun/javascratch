/**
 * 
 */
package test;

import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * @author Chauncey
 *
 */
 
public class Tango {
    
    /**
     * Time complexity O(n)
     * Space complexity O(n) (a hashset and a result array)
     */
    static int[] removeDuplicated(int[] nums)
    {
        if (null == nums || nums.length < 2) return nums;
        LinkedHashSet<Integer> hset = new LinkedHashSet<Integer>((int)(nums.length*1.6));
        for (int num : nums) {
            hset.add(num);
        }
        int[] res = new int[hset.size()];
        int i=0;
        for (int num : hset) {
            res[i++] = num;
        }
        return res;
    }
    
    /**
     * Use analog of quick sort to find the Kth largest element
     * @param lo, ri: inclusive
     */
    public static int findKth(int[] nums, int k, int lo, int hi)
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
    
    /**
     * Return k largest elements of the input array nums
     * Time complexity O(n)
     * Space complexity O(k) (result array)
     * @param k = 100 for this specific problem
     */
    public static int[] kLargestElements(int[] nums, int k)
    {
        int kth = findKth(nums, k, 0, nums.length-1);
        int[] res = new int[k];
        int i = 0;
        for (int num : nums) {
            if (num >= kth) res[i++] = num;
            if (i >= res.length) break; // in case of duplicated elements exist in the input array
        }
        return res;
    }

    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int x){val = x;}
    }
    public static int xSum(TreeNode root)
    {
        if (root == null)
        return 0;
        return root.val + xSum(root.right) - xSum(root.left);
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 4, 2, 7, 3, 6, 8};
        int[]  list = kLargestElements(nums, 3);
        for (int num : nums) {
            System.out.print(num+",");
        }
        System.out.println();
        for (int num : list) {
            System.out.print(num+",");
        }
        
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right= new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.left= new TreeNode(7);
        root.right.right= new TreeNode(1);
        root.right.right.right= new TreeNode(12);
        System.out.println(xSum(root));
        
        
        /*int[] list = new int[]{1, 5, 4, 2, 7, 2, 6, 5};
        list = removeDuplicated(list);
        for (int num : list) {
            System.out.print(num+",");
        }*/
    }
}

/*public class Test
{
    public static void main(String [] args) {

    try {
        badMethod();
        System.out.println("1");
    } catch (RuntimeException e) {
        System.out.println("2");
    } finally {
        System.out.println("3");
}
System.out.println("4");
}
public static void badMethod() {
    throw new NullPointerException();
}

}
*/