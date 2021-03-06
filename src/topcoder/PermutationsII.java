/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].

Hide Tags Backtracking
Hide Similar Problems (M) Next Permutation (M) Permutations

 * @author Chauncey
 *
 */
public class PermutationsII
{
	public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
	}
	private final static void dfs(ListNode head, int k, LinkedList<Integer> path, List<List<Integer>> res)
	{
		ListNode p = head;
		ListNode r = null;
		while (p != null) {
			if (r==null || p.val != r.val) {
				if (k==1) {
					path.addLast(p.val);
					LinkedList<Integer> new_res = new LinkedList<Integer>(path);
					res.add(new_res);
					path.pollLast();
				} else if (k>1) {
					path.addLast(p.val);
					if (r == null) {
						r = head;
						head = p.next;
						dfs(head, k-1, path, res);
						head = r;
						path.pollLast();
					} else {
						r.next = p.next;
						dfs(head, k-1, path, res);
						r.next = p;
						path.pollLast();
					}
				}
			}
			r = p;
			p = p.next;
		}
	}

    public static List<List<Integer>> permuteUnique(int[] nums) {
    	if (null == nums || nums.length == 0) return null;
    	List<List<Integer>> res = new LinkedList<List<Integer>>();
    	LinkedList<Integer> path = new LinkedList<Integer>();
    	Arrays.sort(nums);
    	ListNode head = null;
    	if (nums.length > 0) {
    		head = new ListNode(nums[0]);
    		ListNode p = head;
    		for (int i=1; i<nums.length; ++i) {
    			ListNode q = new ListNode(nums[i]);
    			p.next = q;
    			p = q;
    		}
    	}
    	dfs(head, nums.length, path, res);
    	return res;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{1,2,1,1,1};
		System.out.println(permuteUnique(nums));
	}

}
