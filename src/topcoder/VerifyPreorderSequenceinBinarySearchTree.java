package topcoder;

import java.util.*;

/**
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?

 * @author Chauncey
 *
 */
public class VerifyPreorderSequenceinBinarySearchTree
{
	//O(1) space complexity, O(n2) time complexity
    public boolean verifyPreorder(int[] preorder) {
    	if (preorder == null || preorder.length == 0) return false;
    	int stack_top = 0;
    	int stack_bottom = 0;
		int low = Integer.MIN_VALUE;
		for (int i=1; i<preorder.length; ) {
			int num = preorder[i];
			if (num < preorder[stack_top]) {
				stack_top = i;
				++i;
			} else {
				if (preorder[stack_top] < low) {
					return false;
				}
				low = preorder[stack_top];
				while (--stack_top >= stack_bottom && preorder[stack_top] < num) {
					if (preorder[stack_top] > low)
						low = preorder[stack_top];
				}
				if (stack_top < stack_bottom) {
					stack_bottom = i;
				}
				stack_top = i;
				++i;
			}
		}
		return preorder[stack_top] >= low;
	}
    public boolean verifyPreorder1(int[] preorder) {
    	if (preorder == null || preorder.length == 0) return false;
		LinkedList<Integer> stack = new LinkedList<Integer>();
		int low = Integer.MIN_VALUE;
		for (int i=0; i<preorder.length; ) {
			int num = preorder[i];
			if (stack.isEmpty()) {
				stack.push(num);
				++i;
			} else {
				if (num < stack.peek()) {
					stack.push(num);
					++i;
				} else {
					int k = stack.pop();
					if (k < low)
						return false;
					low = k;
				}
			}
		}
		return !stack.isEmpty() && stack.peek() >= low;
	}
    public boolean verifyPreorder2(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> path = new Stack<Integer>();
        for (int p : preorder) {
            if (p < low)
                return false;
            while (!path.empty() && p > path.peek())
                low = path.pop();
            path.push(p);
        }
        return true;
    }
    //O(1) space complexity, same as above, but abusing the given array for the stack.
    public boolean verifyPreorder3(int[] preorder) {
        int low = Integer.MIN_VALUE, i = -1;
        for (int p : preorder) {
            if (p < low)
                return false;
            while (i >= 0 && p > preorder[i])
                low = preorder[i--];
            preorder[++i] = p;
        }
        return true;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		VerifyPreorderSequenceinBinarySearchTree solution = new VerifyPreorderSequenceinBinarySearchTree();

		System.out.println(solution.verifyPreorder1(new int[]{5,3,1,0,2,4,7,6,9,8,10}));
		System.out.println(solution.verifyPreorder(new int[]{5,3,1,0,2,4,7,6,9,8,10}));
		System.out.println(solution.verifyPreorder1(new int[]{5,3,1,0,2,4,7,6,9,8,6}));
		System.out.println(solution.verifyPreorder(new int[]{5,3,1,0,2,4,7,6,9,8,6}));
	}

}
