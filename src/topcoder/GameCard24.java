package topcoder;

import java.util.*;

/**
 * Classic 24 game, which could to deal with any K cards with arbitrary number N[]
 * 
 * @author Chauncey
 *
 */
public class GameCard24
{
	/*private final class SubEvaluation {
		public int answer;
		public String expression;
		public SubEvaluation(int a, String exp) {answer=a; expression=exp;}
	}*/
	public static final int MAX_LENGTH = 32;
    public List<String> card24(int[] cards) {
    	if (cards == null || cards.length==0 || cards.length>MAX_LENGTH) return null;
    	List<String> res = new ArrayList<String>();
    	int set_number = 1 << cards.length;
    	ArrayList<HashMap<Float, String>> sets = new ArrayList<HashMap<Float, String>>(set_number);
    	for (int i=0; i<set_number; ++i) {
    		sets.add(new HashMap<Float, String>());
    	}
    	for (int i=0; i<cards.length; ++i) {
    		sets.get(1<<i).put((float)cards[i], String.valueOf(cards[i]));
    	}
    	for (int i=1; i<set_number; ++i) {
    		evaluate(sets, i);
    	}
    	for (Map.Entry<Float, String> entry: sets.get(set_number-1).entrySet()) {
    		float ans = entry.getKey();
    		if (ans+0.000001 < 24 || ans-0.000001 > 24) continue;
    		res.add(entry.getValue());
    	}
    	return res;
    }
    
    private final void evaluate(ArrayList<HashMap<Float, String>> sets, int index) {
    	int cnt = 0;
    	int[] pos = new int[MAX_LENGTH];
    	for (int test=index; test!=0; ++cnt) {
    		int prev = test;
    		test &= (test-1);
    		pos[cnt] = prev ^ test;
    	}
    	int combination_num = 1<<(cnt-1);
    	for (int i=combination_num-1; i>0; --i) {
    		int left = 0;
    		int k = 0;
    		int test = i;
    		while (test != 0) {
    			if ((test&1) == 1) {
    				left |= pos[k];
    			}
    			test >>= 1;
    			++k;
    		}
    		int right = index ^ left;
    		evaluate(sets, index, left, right);
    	}
    }
    
    private final void evaluate(ArrayList<HashMap<Float, String>> sets, int index, int left, int right) {
    	HashMap<Float, String> curset = sets.get(index);
    	for (Map.Entry<Float, String> entryleft: sets.get(left).entrySet()) {
    		for (Map.Entry<Float, String> entryright: sets.get(right).entrySet()) {
    			float l = entryleft.getKey();
    			float r = entryright.getKey();
    			float ans = l + r;
    			if (curset.get(ans) == null)
    				curset.put(ans, '('+entryleft.getValue()+'+'+entryright.getValue()+')');
    			ans = l * r;
    			if (curset.get(ans) == null)
    				curset.put(ans, '('+entryleft.getValue()+'*'+entryright.getValue()+')');
    			ans = l - r;
    			if (curset.get(ans) == null)
    				curset.put(ans, '('+entryleft.getValue()+'-'+entryright.getValue()+')');
    			ans = r - l;
    			if (curset.get(ans) == null)
    				curset.put(ans, '('+entryright.getValue()+'-'+entryleft.getValue()+')');
    			if (r != 0) {
	    			ans = l / r;
	    			if (curset.get(ans) == null)
	    				curset.put(ans, '('+entryleft.getValue()+'/'+entryright.getValue()+')');
    			}
    			if (l != 0) {
	    			ans = r / l;
	    			if (curset.get(ans) == null)
	    				curset.put(ans, '('+entryright.getValue()+'/'+entryleft.getValue()+')');
    			}
    		}
    	}
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		GameCard24 solution = new GameCard24();
		
		System.out.println(solution.card24(new int[]{12,5,7,14}));
	}

}
