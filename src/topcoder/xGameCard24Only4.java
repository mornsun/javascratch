package topcoder;

import javafx.util.Pair;

import java.util.*;

/**
 * Classic 24 game, which could to deal with any K cards with arbitrary number N[]
 * 
 * @author Chauncey
 *
 */
public class xGameCard24Only4
{
	public String card24(int[] cards) {
		ArrayList<Pair<Integer, HashMap<Double, String>>> l1 = new ArrayList<>();
		for (int i=0; i<4; ++i) {
			HashMap<Double, String> m = new HashMap<>();
			m.put((double)cards[i], Integer.toString(cards[i]));
			l1.add(new Pair<>(1<<(i+1), m));
		}

		ArrayList<Pair<Integer, HashMap<Double, String>>> l2 = new ArrayList<>();
		for (int i=0; i<l1.size(); ++i)
			for (int j=i+1; j<l1.size(); ++j) {
				l2.add(new Pair<>(l1.get(i).getKey() | l1.get(j).getKey(), addOprs(l1.get(i).getValue(), l1.get(j).getValue())));
			}

		ArrayList<Pair<Integer, HashMap<Double, String>>> l3 = new ArrayList<>();
		for (int i=0; i<l1.size(); ++i)
			for (int j=0; j<l2.size(); ++j) {
				if ((l1.get(i).getKey() & l2.get(j).getKey()) != 0) continue;
				l3.add(new Pair<>(l1.get(i).getKey() | l2.get(j).getKey(), addOprs(l1.get(i).getValue(), l2.get(j).getValue())));
			}

		for (int i=0; i<l2.size(); ++i)
			for (int j=i+1; j<l2.size(); ++j) {
				if ((l2.get(i).getKey() & l2.get(j).getKey()) != 0) continue;
				String res = tryOprs(24, l2.get(i).getValue(), l2.get(j).getValue());
				if (res != null)
					return res;
			}

		for (int i=0; i<l1.size(); ++i)
			for (int j=0; j<l3.size(); ++j) {
				if ((l1.get(i).getKey() & l3.get(j).getKey()) != 0) continue;
				String res = tryOprs(24, l1.get(i).getValue(), l3.get(j).getValue());
				if (res != null)
					return res;
			}

		return null;
	}

	private HashMap<Double, String> addOprs(HashMap<Double, String> m1, HashMap<Double, String> m2) {
		HashMap<Double, String> map = new HashMap<>();
		for (Map.Entry<Double, String> e1 : m1.entrySet())
			for (Map.Entry<Double, String> e2 : m2.entrySet()) {
				double n1 = e1.getKey(), n2 = e2.getKey();
				String s1 = e1.getValue(), s2 = e2.getValue();
				if (!map.containsKey(n1+n2))
					map.put(n1+n2, "(" + s1 + "+" + s2 + ")");
				if (n1>n2) {
					if (!map.containsKey(n1 - n2))
						map.put(n1 - n2, "(" + s1 + "-" + s2 + ")");
				} else {
					if (!map.containsKey(n2 - n1))
						map.put(n2 - n1, "(" + s2 + "-" + s1 + ")");
				}
				if (n1!=0) {
					if (!map.containsKey(n2 / n1))
						map.put(n2 / n1, "(" + s2 + "/" + s1 + ")");
				}
				if (n2!=0) {
					if (!map.containsKey(n1 / n2))
						map.put(n1 / n2, "(" + s1 + "/" + s2 + ")");
				}
			}

		return map;
	}

	private String tryOprs(double target, HashMap<Double, String> m1, HashMap<Double, String> m2) {
		for (Map.Entry<Double, String> e1 : m1.entrySet())
			for (Map.Entry<Double, String> e2 : m2.entrySet()) {
				double n1 = e1.getKey(), n2 = e2.getKey();
				String s1 = e1.getValue(), s2 = e2.getValue();
				if (testDoubleAns(target, n1 + n2))
					return "(" + s1 + "+" + s2 + ")";
				if (n1 > n2)
					if (testDoubleAns(target, n1 - n2))
						return "(" + s1 + "-" + s2 + ")";
					else if (testDoubleAns(target, n2 - n1))
						return "(" + s2 + "-" + s1 + ")";
				if (n1 != 0)
					if (testDoubleAns(target, n2 / n1))
						return "(" + s2 + "/" + s1 + ")";
				if (n2 != 0)
					if (testDoubleAns(target, n1 / n2))
						return "(" + s1 + "/" + s2 + ")";
			}

		return null;
	}

	private boolean testDoubleAns(double target, double ans) {
		return (ans+0.000001 > target && ans-0.000001 < target);
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xGameCard24Only4 solution = new xGameCard24Only4();
		
		System.out.println(solution.card24(new int[]{12,5,7,6}));
		System.out.println(solution.card24(new int[]{11,4,6,6}));
	}

}
