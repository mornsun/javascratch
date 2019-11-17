/**
 * 
 */
package topcoder;

import java.util.Arrays;

/**
 * Also know as: Longest Subarray With 2 Elements
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 *
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 *
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 *
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 *
 * Note:
 *
 * 1 <= tree.length <= 40000
 * 0 <= tree[i] < tree.length
 *
 * Two Pointer
 *
 * @author Chauncey
 * Runtime: 5 ms, faster than 98.90% of Java online submissions for Fruit Into Baskets.
 * Memory Usage: 48.9 MB, less than 56.60% of Java online submissions for Fruit Into Baskets.
 */
public class xLC_904_Fruit_Into_Baskets
{
    public int totalFruit(int[] tree) {
        int res = 0, cur = 0, count_b = 0, a = 0, b = 0;
        for (int c :  tree) {
            cur = c == a || c == b ? cur + 1 : count_b + 1;
            count_b = c == b ? count_b + 1 : 1;
            if (b != c) {a = b; b = c;}
            res = Math.max(res, cur);
        }
        return res;
    }

    public int totalFruit1(int[] tree) {
        if (tree==null || tree.length==0) return 0;
        int n1=0, max=0, i, n2;
        int a=tree[0], b;
        for (i=0; i<tree.length; ++i) {
            if (tree[i]!=a) break;
        }
        if (i==tree.length)
            return i;
        n2 = i;
        b = tree[n2];
        for (++i; i<tree.length; ++i) {
            if (tree[i] != tree[i-1]) {
                if (tree[i]==a || tree[i]==b)
                    n2 = i;
                else {
                    max = Math.max(max, i-n1);
                    n1=n2;
                    a=tree[n1];
                    n2=i;
                    b=tree[n2];
                }
            }
        }
        return Math.max(max, i-n1);
    }

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_904_Fruit_Into_Baskets solution = new xLC_904_Fruit_Into_Baskets();
		System.out.println(solution.totalFruit(new int[]{1,2,1})); //3
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
