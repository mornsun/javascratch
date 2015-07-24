/**
 * 
 */
package topcoder;

import java.util.*;

import test.Tango.TreeNode;


/**
 * @author cherry
 *
 */
public class TangoMaxWidthofBTree
{

	 public static class TreeNode {
	      int val; //depth
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	 }
	 
    public static int getWidth(TreeNode root) {
        if (null == root) return 0;
        int max_wid = 0;
        int cur_wid = 1;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        TreeNode rnode = root;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (null != node.left)
                queue.offer(node.left);
            if (null != node.right)
                queue.offer(node.right);
            ++cur_wid;
            if (node == rnode) {
                rnode = queue.peekLast();
                if (cur_wid > max_wid) {
                    max_wid = cur_wid;
                }
                cur_wid = 0;
            }
        }
        return max_wid;
    }
    

    public static int sumApproximate(int[] nums) {
        if (null != nums && nums.length != 0) return 0;
        int l = 0;
        int r = nums.length-1;
        int sumL = 0, sumR = 0;
        while (l < r) {
            sumL += nums[l];
            
        }
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right= new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.left= new TreeNode(7);
        root.right.right= new TreeNode(1);
        root.right.right.right= new TreeNode(12);
        System.out.println(getWidth(root));
	}

}
