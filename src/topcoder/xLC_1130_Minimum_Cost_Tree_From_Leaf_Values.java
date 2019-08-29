/**
 * 
 */
package topcoder;

import java.util.LinkedList;

/**
 * 	Given an array arr of positive integers, consider all binary trees such that:
 *
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
 * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.
 *
 * Example 1:
 *
 * Input: arr = [6,2,4]
 * Output: 32
 * Explanation:
 * There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
 *
 *     24            24
 *    /  \          /  \
 *   12   4        6    8
 *  /  \               / \
 * 6    2             2   4
 *
 * Constraints:
 *
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 *
 * Related Topic:
 * DP, Stack, Tree

 * @author Chauncey
 * Runtime: 1 ms, faster than 96.99% of Java online submissions for Minimum Cost Tree From Leaf Values.
 * Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Minimum Cost Tree From Leaf Values.
 */
public class xLC_1130_Minimum_Cost_Tree_From_Leaf_Values
{
    public int mctFromLeafValues(int[] arr) {
        if (arr==null || arr.length==0)
            return 0;

        LinkedList<Integer> stack  = new LinkedList<>();
        int sum = 0;
        for (int i=0; i<arr.length; ++i) {
            int n = arr[i];
            if (stack.size()>=2) {
                int n1 = stack.pop();
                int n2 = stack.pop();
                if (n >= n2) {
                    stack.push(Math.max(n1, n2));
                    sum += n1 * n2;
                    --i;
                } else {
                    stack.push(n2);
                    stack.push(n1);
                    stack.push(n);
                }
            } else {
                stack.push(n);
            }
        }
        while (stack.size()>1) {
            int n1 = stack.pop();
            int n2 = stack.pop();
            stack.push(Math.max(n1, n2));
            sum += n1 * n2;
        }
        return sum;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_1130_Minimum_Cost_Tree_From_Leaf_Values solution = new xLC_1130_Minimum_Cost_Tree_From_Leaf_Values();
        System.out.println(solution.mctFromLeafValues(new int[]{6,2,4}));
        System.out.println(solution.mctFromLeafValues(new int[]{15,13,5,3,15}));
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
