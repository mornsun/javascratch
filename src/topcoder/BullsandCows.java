package topcoder;

import java.util.*;

/**
 * You are playing the following Bulls and Cows game with your friend: You write a 4-digit secret number and ask your friend to guess it, each time your friend guesses a number, you give a hint, the hint tells your friend how many digits are in the correct positions (called "bulls") and how many digits are in the wrong positions (called "cows"), your friend will use those hints to find out the secret number.

For example:

Secret number:  1807
Friend's guess: 7810
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
According to Wikipedia: "Bulls and Cows (also known as Cows and Bulls or Pigs and Bulls or Bulls and Cleots) is an old code-breaking mind or paper and pencil game for two or more players, predating the similar commercially marketed board game Mastermind. The numerical version of the game is usually played with 4 digits, but can also be played with 3 or any other number of digits."

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows, in the above example, your function should return 1A3B.

You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

Credits:
Special thanks to @jeantimex for adding this problem and creating all test cases.

Subscribe to see which companies asked this question

Hide Tags Hash Table

 * @author Chauncey
 *
 */
public class BullsandCows
{
    public String getHint(String secret, String guess) {
        if (null == secret || null == guess || secret.length()==0 || secret.length()!=guess.length())
        	return "0A0B";
        int sz = secret.length();
        int bulls = 0;
        int cows = 0;
        int[] cntmap = new int[10];
        Arrays.fill(cntmap, -1);
        for (int i=0; i<sz; ++i) {
        	int idx = secret.charAt(i)-'0';
        	if (cntmap[idx] == -1) cntmap[idx] = 1;
        	else ++cntmap[idx];
        }
        for (int i=0; i<sz; ++i) {
        	int idx = guess.charAt(i)-'0';
        	if (cntmap[idx] != -1) {
        		if (guess.charAt(i) == secret.charAt(i)) {
            		++bulls;
            		--cntmap[idx];
        		}
        	}
        }
        for (int i=0; i<sz; ++i) {
        	int idx = guess.charAt(i)-'0';
        	if (cntmap[idx] != -1) {
        		if (guess.charAt(i) != secret.charAt(i) && cntmap[idx]!=0) {
            		++cows;
            		--cntmap[idx];
        		}
        	}
        }
        return ""+bulls+"A"+cows+"B";
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BullsandCows solution = new BullsandCows();
		//0A0B
		System.out.println(solution.getHint("",""));
		//1A3B
		System.out.println(solution.getHint("1807","7810"));
		//0A0B
		System.out.println(solution.getHint("1","0"));
		//1A0B
		System.out.println(solution.getHint("11","10"));
		//0A1B
		System.out.println(solution.getHint("1234","0111"));
		//2A0B
		System.out.println(solution.getHint("11","11"));
		//0A4B
		System.out.println(solution.getHint("1122","2211"));
	}

}
