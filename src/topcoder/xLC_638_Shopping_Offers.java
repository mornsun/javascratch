/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	In LeetCode Store, there are some kinds of items to sell. Each item has a price.
 *
 * However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.
 *
 * You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.
 *
 * Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.
 *
 * You could use any of special offers as many times as you want.
 *
 * Example 1:
 * Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
 * Output: 14
 * Explanation:
 * There are two kinds of items, A and B. Their prices are $2 and $5 respectively.
 * In special offer 1, you can pay $5 for 3A and 0B
 * In special offer 2, you can pay $10 for 1A and 2B.
 * You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
 * Example 2:
 * Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
 * Output: 11
 * Explanation:
 * The price of A is $2, and $3 for B, $4 for C.
 * You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C.
 * You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C.
 * You cannot add more items, though only $9 for 2A ,2B and 1C.
 * Note:
 * There are at most 6 kinds of items, 100 special offers.
 * For each item, you need to buy at most 6 of them.
 * You are not allowed to buy more items than you want, even if that would lower the overall price.
 *
 * DP, DFS
 *
 * @author Chauncey
 * Runtime: 769 ms, faster than 5.15% of Java online submissions for Shopping Offers.
 * Memory Usage: 59.3 MB, less than 30.77% of Java online submissions for Shopping Offers.
 *
 * Map
 * Runtime: 7 ms, faster than 62.09% of Java online submissions for Shopping Offers.
 * Memory Usage: 42.7 MB, less than 46.15% of Java online submissions for Shopping Offers.
 *
 * Runtime: 2 ms, faster than 99.74% of Java online submissions for Shopping Offers.
 * Memory Usage: 40.5 MB, less than 92.31% of Java online submissions for Shopping Offers.
 */
public class xLC_638_Shopping_Offers
{
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		//HashMap<List<Integer>, Integer> map = new HashMap<>();
		return dfs(needs, price, special, 0);
	}
	private int dfs(List<Integer> curr, List<Integer> price, List<List<Integer>> special, int pos) {
		int n = price.size();
		int money = 0;
		for (int k=0; k<n; ++k) {
			money += price.get(k) * curr.get(k);
		}
		for (int i=pos; i<special.size(); ++i) {
			List<Integer> offer = special.get(i);
			int k;
			for (k=0; k<n; ++k) {
				if (curr.get(k)-offer.get(k)<0) break;
			}
			if (k<n) continue;
			ArrayList<Integer> next = new ArrayList<>();
			for (k=0; k<n; ++k) {
				next.add(curr.get(k)-offer.get(k));
			}
			int m = offer.get(n) + dfs(next, price, special, i);
			if (m < money) money = m;
		}
		return money;
	}

	public int shoppingOffers1(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {

		int n = price.size();
		int[] cnts = new int[n];

		HashMap<Integer, Integer> map = new HashMap<>();
		HashSet<Integer> curr = new HashSet<>();
		map.put(0, 0);
		curr.add(0);
		int tsig = signature(n, null, needs);
		int min = Integer.MAX_VALUE;

		while (!curr.isEmpty()) {
			HashSet<Integer> prev = curr;
			curr = new HashSet<>();
			for (int sig : prev) {
				int pm = map.getOrDefault(sig, 0);
				if (sig == tsig && pm < min) min = pm;
				for (int k = 0; k < n; ++k) {
					cnts[k] = (sig >> (3 * k) & 7);
				}
				for (List<Integer> offer : special) {
					int k = 0;
					for (; k < n; ++k) {
						if (cnts[k] + offer.get(k) > needs.get(k)) break;
					}
					if (k < n) continue;
					int money = offer.get(n);
					sig = signature(n, cnts, offer);
					int m = map.getOrDefault(sig, Integer.MAX_VALUE);
					if (pm + money < m) {
						map.put(sig, pm + money);
						curr.add(sig);
					}
				}
				for (int k = 0; k < n; ++k) {
					if (cnts[k] == needs.get(k)) continue;
					int money = price.get(k);
					cnts[k]++;
					sig = signature(n, cnts, null);
					cnts[k]--;
					int m = map.getOrDefault(sig, Integer.MAX_VALUE);
					if (pm + money < m) {
						map.put(sig, pm + money);
						curr.add(sig);
					}
				}
			}
		}
		return min;
	}

	private int signature(int n, int[] cnts, List<Integer> offer)
	{
		int sig = 0;
		for (int k=n-1; k>=0; --k) {
			sig <<= 3;
			int next = (cnts==null ? 0 : cnts[k]) + (offer==null ? 0 : offer.get(k));
			sig |= next;
			//System.out.print(next+",");
		}
		//System.out.println();
		return sig;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_638_Shopping_Offers solution = new xLC_638_Shopping_Offers();
		ArrayList<List<Integer>> offers = new ArrayList<>();
		offers.add(Arrays.asList(new Integer[]{3,0,5}));
		offers.add(Arrays.asList(new Integer[]{1,2,10}));
		System.out.println(solution.shoppingOffers(Arrays.asList(new Integer[]{2,5}), offers, Arrays.asList(new Integer[]{3,2}))); //14
		offers.clear();
		offers.add(Arrays.asList(new Integer[]{1,1,0,4}));
		offers.add(Arrays.asList(new Integer[]{2,2,1,9}));
		System.out.println(solution.shoppingOffers(Arrays.asList(new Integer[]{2,3,4}), offers, Arrays.asList(new Integer[]{1,2,1}))); //11
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
