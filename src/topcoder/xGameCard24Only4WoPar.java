package topcoder;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Classic 24 game, which could to deal with any K cards with arbitrary number N[]
 * 
 * @author Chauncey
 *
 */
public class xGameCard24Only4WoPar
{
	public String card24(int[] cards) {
		boolean[] see = new boolean[4];
		int[] perm = new int[4];
		return dfs(cards, see, perm, 0);
	}

	char[] oprs = new char[]{'+','-','*','/'};
	String dfs(int[] cards, boolean[] see, int[] perm, int idx) {
		if (idx==4) {
			char[] ops = new char[3];
			for (int i=0; i<4; ++i) {
				ops[0] = oprs[i];
				for (int j=0; j<4; ++j) {
					ops[1] = oprs[j];
					for (int k=0; k<4; ++k) {
						ops[2] = oprs[k];
						if (testDoubleAns(24, eval(perm, ops)))
							return String.format("%d%c%d%c%d%c%d",perm[0],ops[0],perm[1],ops[1],perm[2],ops[2],perm[3]);
					}
				}
			}
			return null;
		}
		for (int i=0; i<4; ++i) {
			if (see[i]) continue;
			see[i] = true;
			perm[idx] = cards[i];
			String res = dfs(cards, see, perm, idx+1);
			if (res!=null)
				return res;
			see[i] = false;
		}
		return null;
	}

	double eval(int[] ns, char[] ops) {
		if (ns.length != ops.length+1) return -1;
		if (ops.length==0) return ns[0];
		LinkedList<Double> nstk = new LinkedList<>();
		LinkedList<Character> opstk = new LinkedList<>();
		nstk.push((double)ns[0]);
		for (int i=0; i<ops.length; ++i) {
			char op = ops[i];
			double r = ns[i + 1];
			if (op == '*')
				nstk.push(nstk.pop() * r);
			else if (op == '/')
				nstk.push(nstk.pop() / r);
			else if (op=='+' || op=='-') {
				nstk.push(r);
				opstk.push(op);
			}
		}
		double res = nstk.pollLast();
		while (!nstk.isEmpty()) {
			char op = opstk.pollLast();
			if (op=='+')
				res += nstk.pollLast();
			else
				res -= nstk.pollLast();
		}
		return res;
	}

	private boolean testDoubleAns(double target, double ans) {
		return (ans+0.000001 > target && ans-0.000001 < target);
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xGameCard24Only4WoPar solution = new xGameCard24Only4WoPar();

		System.out.println(solution.card24(new int[]{5,6,2,3}));
		System.out.println(solution.card24(new int[]{2,3,5,6}));
		System.out.println(solution.card24(new int[]{12,12,6,6}));
		System.out.println(solution.card24(new int[]{6,6,2,2}));
		System.out.println(solution.card24(new int[]{12,5,7,6}));
		//System.out.println(solution.eval(new int[]{2,3,5,6}, new char[]{'*','-','*'}));
		System.out.println(solution.card24(new int[]{11,4,6,6}));
	}

}
