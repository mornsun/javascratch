/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 	In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 *
 * In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
 *
 * Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.
 *
 * Example 1:
 *
 * Input: label = 14
 * Output: [1,3,4,14]
 * Example 2:
 *
 * Input: label = 26
 * Output: [1,2,6,10,26]
 *
 * Constraints:
 * 1 <= label <= 10^6
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Path In Zigzag Labelled Binary Tree.
 * Memory Usage: 33.6 MB, less than 100.00% of Java online submissions for Path In Zigzag Labelled Binary Tree.
 */
public class LC_1104_Path_In_Zigzag_Labelled_Binary_Tree
{
	public List<Integer> pathInZigZagTree(int label) {
		LinkedList<Integer> res = new LinkedList<>();
		if (label<=0) return res;
		int n=label;
		int level = 0;
		while(n>0) {
			n>>=1;
			level++;
		}
		n = label;
		if ((level&1)==0) {
			n = (1<<level)- 1 - n + (1<<(level-1));
		}
		while(n>0) {
			label = n;
			if ((level&1)==0) {
				label = (1<<level)- 1 - label + (1<<(level-1));
			}
			res.addFirst(label);
			level--;
			n = n>>1;
		}
		return res;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1104_Path_In_Zigzag_Labelled_Binary_Tree solution = new LC_1104_Path_In_Zigzag_Labelled_Binary_Tree();
        System.out.println(solution.pathInZigZagTree(14)); //
		System.out.println(solution.pathInZigZagTree(26)); //
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
