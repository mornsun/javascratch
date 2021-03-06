package topcoder;

import javafx.util.Pair;

import java.util.*;

/**
 * Classic 24 game, which could to deal with any K cards with arbitrary number N[]
 * 
 * @author Chauncey
 *
 */
public class xGameCard24
{
	private final class SubEvaluation {
		public char operator;
		public String expression;
		public SubEvaluation(char op, String exp) {operator=op; expression=exp;}
	}

	public List<String> card24(int[] cards) { //Wrong
		ArrayList<String> res = new ArrayList<>();
		if (cards==null || cards.length==0) return res;
		HashMap<Float, SubEvaluation> all = compute(cards, 0, cards.length);
		for (Map.Entry<Float, SubEvaluation> entry : all.entrySet()) {
			float ans = entry.getKey();
			if (ans+0.000001 < 24 || ans-0.000001 > 24) continue;
			res.add(entry.getValue().expression);
		}
		return res;
	}

	private HashMap<Float, SubEvaluation> compute(int[] cards, int lo, int hi) {
		HashMap<Float, SubEvaluation> map = new HashMap<>();
		if (lo==hi-1) {
			map.put((float)cards[lo], new SubEvaluation('#', String.valueOf(cards[lo])));
			return map;
		}
		//TODO:only a permutation
		for (int i=lo+1; i<hi; ++i) {
			map.putAll(compute(compute(cards, lo, i), compute(cards, i, hi)));
		}
		return map;
	}

	private final HashMap<Float, SubEvaluation> compute(HashMap<Float, SubEvaluation> leftSet, HashMap<Float, SubEvaluation> rightSet) {
		HashMap<Float, SubEvaluation> curset = new HashMap<>();
		for (Map.Entry<Float, SubEvaluation> entryleft: leftSet.entrySet()) {
			for (Map.Entry<Float, SubEvaluation> entryright: rightSet.entrySet()) {
				//avoid redundant brackets
				float l = entryleft.getKey();
				float r = entryright.getKey();
				float ans = l + r;
				if (curset.get(ans) == null) {
					curset.put(ans, new SubEvaluation('+', entryleft.getValue().expression+'+'+entryright.getValue().expression));
				}
				ans = l * r;
				if (curset.get(ans) == null) {
					char lop = entryleft.getValue().operator;
					String lexp = (lop=='+' || lop=='-') ? '('+entryleft.getValue().expression+')' : entryleft.getValue().expression;
					char rop = entryright.getValue().operator;
					String rexp = (rop!='*' && rop!='#') ? '('+entryright.getValue().expression+')' : entryright.getValue().expression;
					curset.put(ans, new SubEvaluation('*', lexp+'*'+rexp));
				}
				ans = l - r;
				if (curset.get(ans) == null) {
					char rop = entryright.getValue().operator;
					String rexp = (rop=='+' || rop=='-') ? '('+entryright.getValue().expression+')' : entryright.getValue().expression;
					curset.put(ans, new SubEvaluation('-', entryleft.getValue().expression+'-'+rexp));
				}
				ans = r - l;
				if (curset.get(ans) == null) {
					char rop = entryleft.getValue().operator;
					String rexp = (rop=='+' || rop=='-') ? '('+entryleft.getValue().expression+')' : entryleft.getValue().expression;
					curset.put(ans, new SubEvaluation('-', entryright.getValue().expression+'-'+rexp));
				}
				if (r != 0) {
					ans = l / r;
					if (curset.get(ans) == null) {
						char lop = entryleft.getValue().operator;
						String lexp = (lop=='+' || lop=='-') ? '('+entryleft.getValue().expression+')' : entryleft.getValue().expression;
						char rop = entryright.getValue().operator;
						String rexp = (rop!='#') ? '('+entryright.getValue().expression+')' : entryright.getValue().expression;
						curset.put(ans, new SubEvaluation('/', lexp+'/'+rexp));
					}
				}
				if (l != 0) {
					ans = r / l;
					if (curset.get(ans) == null) {
						char lop = entryright.getValue().operator;
						String lexp = (lop=='+' || lop=='-') ? '('+entryright.getValue().expression+')' : entryright.getValue().expression;
						char rop = entryleft.getValue().operator;
						String rexp = (rop!='#') ? '('+entryleft.getValue().expression+')' : entryleft.getValue().expression;
						curset.put(ans, new SubEvaluation('/', lexp+'/'+rexp));
					}
				}
			}
		}
		return curset;
	}


	public static final int MAX_LENGTH = 32;
    public List<String> card24_old(int[] cards) {
    	if (cards == null || cards.length==0 || cards.length>MAX_LENGTH) return null;
    	List<String> res = new ArrayList<String>();
    	int set_number = 1 << cards.length;
    	ArrayList<HashMap<Float, SubEvaluation>> sets = new ArrayList<HashMap<Float, SubEvaluation>>(set_number);
    	for (int i=0; i<set_number; ++i) {
    		sets.add(new HashMap<Float, SubEvaluation>());
    	}
    	for (int i=0; i<cards.length; ++i) {
    		sets.get(1<<i).put((float)cards[i], new SubEvaluation('#', String.valueOf(cards[i])));
    	}
    	for (int i=1; i<set_number; ++i) {
    		evaluate(sets, i);
    	}
    	for (Map.Entry<Float, SubEvaluation> entry: sets.get(set_number-1).entrySet()) {
    		float ans = entry.getKey();
    		if (ans+0.000001 < 24 || ans-0.000001 > 24) continue;
    		res.add(entry.getValue().expression);
    	}
    	return res;
    }

    private final void evaluate(ArrayList<HashMap<Float, SubEvaluation>> sets, int index) {
    	int cnt = 0;
    	int[] pos = new int[MAX_LENGTH];
    	for (int popcount=index; popcount!=0; ++cnt) {
    		int prev = popcount;
    		popcount &= (popcount-1);
    		pos[cnt] = prev ^ popcount;
    	}
    	for (int i=(1<<(cnt-1))-1; i>0; --i) {
    		int left = 0;
    		int bittest = i;
    		for (int j=0; j<cnt; ++j, bittest>>=1) {
    			if ( (bittest&1) != 0) left |= pos[j];
    		}
    		int right = index ^ left;
    		evaluate(sets, index, left, right);
    	}
    }
    
    private final void evaluate(ArrayList<HashMap<Float, SubEvaluation>> sets, int index, int left, int right) {
    	HashMap<Float, SubEvaluation> curset = sets.get(index);
    	for (Map.Entry<Float, SubEvaluation> entryleft: sets.get(left).entrySet()) {
    		for (Map.Entry<Float, SubEvaluation> entryright: sets.get(right).entrySet()) {
    			//avoid redundant brackets 
    			float l = entryleft.getKey();
    			float r = entryright.getKey();
    			float ans = l + r;
    			if (curset.get(ans) == null) {
    				curset.put(ans, new SubEvaluation('+', entryleft.getValue().expression+'+'+entryright.getValue().expression));
    			}
    			ans = l * r;
    			if (curset.get(ans) == null) {
    				char lop = entryleft.getValue().operator;
    				String lexp = (lop=='+' || lop=='-') ? '('+entryleft.getValue().expression+')' : entryleft.getValue().expression;
    				char rop = entryright.getValue().operator;
    				String rexp = (rop!='*' && rop!='#') ? '('+entryright.getValue().expression+')' : entryright.getValue().expression;
    				curset.put(ans, new SubEvaluation('*', lexp+'*'+rexp));
    			}
    			ans = l - r;
    			if (curset.get(ans) == null) {
    				char rop = entryright.getValue().operator;
    				String rexp = (rop=='+' || rop=='-') ? '('+entryright.getValue().expression+')' : entryright.getValue().expression;
    				curset.put(ans, new SubEvaluation('-', entryleft.getValue().expression+'-'+rexp));
    			}
    			ans = r - l;
    			if (curset.get(ans) == null) {
    				char rop = entryleft.getValue().operator;
    				String rexp = (rop=='+' || rop=='-') ? '('+entryleft.getValue().expression+')' : entryleft.getValue().expression;
    				curset.put(ans, new SubEvaluation('-', entryright.getValue().expression+'-'+rexp));
    			}
    			if (r != 0) {
	    			ans = l / r;
	    			if (curset.get(ans) == null) {
	    				char lop = entryleft.getValue().operator;
	    				String lexp = (lop=='+' || lop=='-') ? '('+entryleft.getValue().expression+')' : entryleft.getValue().expression;
	    				char rop = entryright.getValue().operator;
	    				String rexp = (rop!='#') ? '('+entryright.getValue().expression+')' : entryright.getValue().expression;
	    				curset.put(ans, new SubEvaluation('/', lexp+'/'+rexp));
	    			}
    			}
    			if (l != 0) {
	    			ans = r / l;
	    			if (curset.get(ans) == null) {
	    				char lop = entryright.getValue().operator;
	    				String lexp = (lop=='+' || lop=='-') ? '('+entryright.getValue().expression+')' : entryright.getValue().expression;
	    				char rop = entryleft.getValue().operator;
	    				String rexp = (rop!='#') ? '('+entryleft.getValue().expression+')' : entryleft.getValue().expression;
	    				curset.put(ans, new SubEvaluation('/', lexp+'/'+rexp));
	    			}
    			}
    		}
    	}
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xGameCard24 solution = new xGameCard24();

		System.out.println(solution.card24(new int[]{12,5,7,6}));
		System.out.println(solution.card24(new int[]{1,10,6,3}));
		System.out.println(solution.card24_old(new int[]{1,10,6,3}));
		System.out.println(solution.card24(new int[]{12,5,7,6,11}));
		System.out.println(solution.card24(new int[]{11,4,6,6}));
	}

}
