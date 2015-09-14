package lintcode;

import java.util.*;

/**
 * Given a set of n nuts of different sizes and n bolts of different sizes. There is a one-one mapping between nuts and bolts. Comparison of a nut to another nut or a bolt to another bolt is not allowed. It means nut can only be compared with bolt and bolt can only be compared with nut to see which one is bigger/smaller.

We will give you a compare function to compare nut with bolt.

Have you met this question in a real interview? Yes
Example
Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].

Your code should find the matching bolts and nuts.

one of the possible return:

nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].

we will tell you the match compare function. If we give you another compare function.

the possible return is the following:

nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].

So you must use the compare function that we give to do the sorting.

The order of the nuts or bolts does not matter. You just need to find the matching bolt for each nut.

Tags Expand 
Quick Sort Sort


Related Problems Expand 
Medium First Bad Version 34 %

 * @author Chauncey
 *
 */
public class NutsBoltsProblem
{
	/**
	 * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
	 * if "a" is bigger than "b", it will return 1, else if they are equal,
	 * it will return 0, else if "a" is smaller than "b", it will return -1.
	 * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
	*/
	public static class NBComparator {
		public int cmp(String a, String b) {
			return a.compareToIgnoreCase(b);
		}
	}
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
    	if (null == nuts || nuts.length == 0 ||
    			null == bolts || bolts.length != nuts.length ||
    			null == compare) return;
    	sortNutsAndBolts(nuts, bolts, 0, bolts.length-1, compare);
    }
    public void sortNutsAndBolts(String[] nuts, String[] bolts, int lo, int hi, NBComparator compare) {
    	if (lo >= hi) return;
    	//pivot is nuts[lo]
    	int prev = lo-1;
		for (int curr = lo; curr <= hi; ++curr) {
			int cmp_ret = compare.cmp(bolts[curr], nuts[lo]);
			//System.out.println(bolts[curr]+":"+nuts[lo]+":"+cmp_ret);
			if (cmp_ret < 0) {
				String tmp = bolts[curr];
				bolts[curr] = bolts[++prev];
				bolts[prev] = tmp; //swap [curr] and [prev] to put less bolt in left half
			} else if (cmp_ret == 0) {
				String tmp = bolts[curr];
				bolts[curr] = bolts[++prev];
				bolts[prev] = tmp; //swap [curr] and [prev] to put less bolt in left half
				tmp = bolts[prev];
				bolts[prev] = bolts[lo];
				bolts[lo] = tmp; //swap [prev] and [pivot] to put mapping bolt at [pivot]
			}
		}
    	prev = lo;
		for (int curr = lo+1; curr <= hi; ++curr) {
			int cmp_ret = compare.cmp(nuts[curr], bolts[lo]);
			//System.out.println(bolts[curr]+":"+nuts[lo]+":"+cmp_ret);
			if (cmp_ret < 0) {
				String tmp = nuts[curr];
				nuts[curr] = nuts[++prev];
				nuts[prev] = tmp; //swap [curr] and [prev] to put less bolt in left half
			}
		}
    	sortNutsAndBolts(nuts, bolts, lo+1, prev, compare);
    	sortNutsAndBolts(nuts, bolts, prev+1, hi, compare);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		NutsBoltsProblem solution = new NutsBoltsProblem();

		//
		String[] nuts = new String[]{"ff","gf","ab","bc","dd","gg","ac"};
		String[] bolts = new String[]{"BC","AB","DD","GG","GF","FF","AC"};
		nuts = new String[]{"ij","hi","ab","bc","dd","gg","cd","de","ef","fg","gh","jk"};
		bolts = new String[]{"GH","FG","EF","IJ","DE","JK","CD","HI","AB","GG","DD","BC"};
		solution.sortNutsAndBolts(nuts, bolts, new NBComparator());
		for (String nut : nuts)
			System.out.print(nut+",");
		System.out.print("==>");
		for (String bolt : bolts)
			System.out.print(bolt+",");
		System.out.println();
	}

}
