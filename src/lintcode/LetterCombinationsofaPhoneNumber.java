package lintcode;

import java.util.*;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Cellphone

Have you met this question in a real interview? Yes
Example
Given "23"

Return ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]

Note
Although the above answer is in lexicographical order, your answer could be in any order you want.

Tags Expand 
String Backtracking Recursion

 * @author Chauncey
 *
 */
public class LetterCombinationsofaPhoneNumber
{
    private static final char[][] _letters = new char[][]{{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},
            {'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
    /**
     * @param digits A digital string
     * @return all posible letter combinations
     */
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        if (null == digits || digits.length() == 0) return res;
        StringBuffer path = new StringBuffer();
        dfs(res, digits, 0, path);
        return res;
    }
    private final void dfs(ArrayList<String> res, String digits, int idx, StringBuffer path) {
        if (idx == digits.length()) {
            res.add(path.toString());
            return;
        }
        int k = digits.charAt(idx) - '0';
        if (0 == _letters[k].length) {
            dfs(res, digits, idx+1, path);
        } else {
            for (int i=0; i<_letters[k].length; ++i) {
                path.append(_letters[k][i]);
                dfs(res, digits, idx+1, path);
                path.deleteCharAt(path.length()-1);
            }
        }
        return;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        LetterCombinationsofaPhoneNumber solution = new LetterCombinationsofaPhoneNumber();

        //10
        System.out.println(solution.letterCombinations("0123"));
        System.out.println(solution.letterCombinations(""));
    }

}
