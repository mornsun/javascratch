package topcoder;

import java.util.*;

/**
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two
 * consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.

Google的“+-游戏”最优策略下的O(N^2) DP解法

 * @author Chauncey
 *
 */
public class FlipGameII
{
    public boolean canWin(String s) {
    	if (null == s || s.length()==0) return false;
    	int sz = s.length();
    	int cnt = 0;
    	int maxcnt = 2;
    	ArrayList<Integer> game = new ArrayList<Integer>();
    	for (int i=0; i<sz; ++i) {
    		if (s.charAt(i) == '+') {
    			++cnt;
    		} else {
    			if (cnt > 0) {
	    			if (cnt > 1) {
	    				game.add(cnt);
	    				if (cnt > maxcnt)
	    					maxcnt = cnt;
	    			}
	    			cnt = 0;
    			}
    		}
    	}
		if (cnt > 1) {
			game.add(cnt);
			if (cnt > maxcnt)
				maxcnt = cnt;
		}
    	//System.out.println(game);
    	int[] f = new int[maxcnt+1];
    	f[0] = 0; f[1] = 0;
    	for (int i=2; i<maxcnt; ++i) {
    		HashSet<Integer> gsub = new HashSet<Integer>();    // the S-G value of all subgame states
    		for (int j=0; j<i/2; ++j) {//0 2, 1 1, 
    			gsub.add(f[j] ^ f[i-2-j]);
    		}
    		f[i] = sprague_grundy(gsub);
    		//System.out.println(gsub+":"+f[i]);
    	}


        int ret = 0;
        for (int sub: game) ret ^= sub;
        return ret != 0;    // Theorem 1: First player must win iff g(current_state) != 0
    }
	private final int sprague_grundy(HashSet<Integer> set) {
		int sz = set.size();
		for (int i=0; i<sz; ++i) {
			if (!set.contains(i)) return i;
		}
		return sz;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FlipGameII solution = new FlipGameII();
		System.out.println(solution.canWin("++++++++++++++++++++"));
	}

}
