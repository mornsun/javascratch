package topcoder;

import java.util.*;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Hint:

Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)
Hide Tags Math String
Hide Similar Problems (M) Integer to Roman

 * @author Chauncey
 *
 */
public class IntegertoEnglishWords
{
	private static final String[] digit_word = new String[]{null, "One", "Two", "Three", "Four", "Five",
			"Six", "Seven", "Eight", "Nine"};
	private static final String[] teens = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
		"Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private static final String[] tys = new String[]{null, null, "Twenty", "Thirty", "Forty", "Fifty",
		"Sixty", "Seventy", "Eighty", "Ninety"};
	private static final String[] chunks = new String[]{null, "Thousand", "Million", "Billion"};
	
	private final String chunkToWords(char[] digits, int start, int end) {
		StringBuilder res = new StringBuilder();
		if (end-start==2) { //3 characters
			if (digits[start] != 0) {
				res.append(digit_word[digits[start]]);
				res.append(' ');
				res.append("Hundred ");
			}
			++start;
		}
		if (end-start==1) { //2 characters
			if (digits[start] == 1) {
				++start;
				res.append(teens[digits[start]]);
				res.append(' ');
			} else if (digits[start] > 1) {
				res.append(tys[digits[start]]);
				res.append(' ');
			}
			++start;
		}
		if (end-start==0) { //1 character
			if (digits[start] != 0) {
				res.append(digit_word[digits[start]]);
				res.append(' ');
			}
		}
		return res.length()>0 ? res.toString() : null;
	}
    public String numberToWords(int num) {
    	char[] digits = String.valueOf(num).toCharArray();
    	if (digits.length==1 && digits[0]=='0') return "Zero";
    	for (int i=0; i<digits.length; ++i) {
    		digits[i] -= '0';
    	}
    	int start = 0;
    	int end = digits.length%3-1;
    	if (end<0) end += 3;
    	StringBuilder res = new StringBuilder();
    	while (end < digits.length) {
    		String str = chunkToWords(digits, start, end);
    		if (str != null) {
    			res.append(str);
    			int ichunk = (digits.length-1-end)/3;
    			if (chunks[ichunk] != null) {
    				res.append(chunks[ichunk]);
    				res.append(' ');
    			}
    		}
    		start = end + 1;
    		end += 3;
    	}
    	if (res.length()>0) res.delete(res.length()-1, res.length()); //trim tail space
    	return res.toString();
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		IntegertoEnglishWords solution = new IntegertoEnglishWords();
		System.out.println(solution.numberToWords(1000000301));
	}

}
