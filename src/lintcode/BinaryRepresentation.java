package lintcode;

import java.util.*;

/**
 * Given a (decimal - e.g. 3.72) number that is passed in as a string, return the binary representation that is passed in as a string. If the fractional part of the number can not be represented accurately in binary with at most 32 characters, return ERROR.

Have you met this question in a real interview? Yes
Example
For n = "3.72", return "ERROR".

For n = "3.5", return "11.1".

Tags Expand 
String Cracking The Coding Interview Bit Manipulation


Related Problems Expand 
Medium Update Bits 19 %

 * @author Chauncey
 *
 */
public class BinaryRepresentation
{
    /**
     *@param n: Given a decimal number that is passed in as a string
     *@return: A string
     */
    public String binaryRepresentation(String n) {
    	if (n==null || n.length()==0) return n;
    	int point = n.indexOf('.');
    	String fraction = null;
    	String number = null;
    	if (point == -1) {
    		number = n;
    	} else {
    		number = n.substring(0, point);
    		fraction = "0."+n.substring(point+1);
    	}
    	StringBuilder sb_fraction = new StringBuilder(32);
    	if (fraction != null) {
    		double num = Double.parseDouble(fraction);
    		double divisor = 0.5;
    		boolean allzero = true;
    		for (int i=0; i<32 && num!=0; ++i,divisor /= 2) {
    			int bit = (int)(num/divisor);
        		if (bit == 1) allzero = false;
    			sb_fraction.append(bit);
    			num -= bit*divisor;
    		}
    		if (num != 0) {
    			return "ERROR";
    		}
    		if (allzero) sb_fraction = null;
    	}
    	StringBuilder sb_number = new StringBuilder(32);
		int num = Integer.parseInt(number);
		if (num == 0) {
			sb_number.append(0);
		} else {
    		int bitchecker = 1<<30;
    		for (; bitchecker!=0 && (bitchecker&num) == 0; bitchecker >>= 1);
    		for (; bitchecker!=0; bitchecker >>= 1) {
    			if ((bitchecker&num) != 0) sb_number.append(1);
    			else sb_number.append(0);
    		}
    	}
		return sb_number+(sb_fraction==null ? "" : "."+sb_fraction);
    }
    
    public String binaryRepresentation1(String n) {
    	if (n==null || n.length()==0) return n;
    	int point = n.indexOf('.');
    	String fraction = null;
    	String number = null;
    	if (point == -1) {
    		number = n;
    	} else {
    		number = n.substring(0, point);
    		fraction = n.substring(point+1);
    	}
    	StringBuilder sb_fraction = new StringBuilder(32);
    	if (fraction != null) {
    		int flen = fraction.length();
    		if (flen>32) flen = 32;
    		long num = 0;
    		long divisor = 5;
    		boolean allzero = true;
    		for (int i=0; i<flen; ++i,divisor *= 5) {
    			num = num*10 + (fraction.charAt(i)-'0');
    			int bit = (int)(num/divisor);
        		//System.out.println(sb_fraction+":"+num+":"+bit+":"+divisor);
        		if (bit == 1) allzero = false;
    			sb_fraction.append(bit);
    			num -= bit*divisor;
    		}
    		if (num != 0) {
    			return "ERROR";
    		}
    		if (allzero) sb_fraction = null;
    	}
    	StringBuilder sb_number = new StringBuilder(32);
		int num = Integer.parseInt(number);
		if (num == 0) {
			sb_number.append(0);
		} else {
    		int bitchecker = 1<<30;
    		for (; bitchecker!=0 && (bitchecker&num) == 0; bitchecker >>= 1);
    		for (; bitchecker!=0; bitchecker >>= 1) {
    			if ((bitchecker&num) != 0) sb_number.append(1);
    			else sb_number.append(0);
    		}
    	}
		return sb_number+(sb_fraction==null ? "" : "."+sb_fraction);
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BinaryRepresentation solution = new BinaryRepresentation();

		//ERROR
		System.out.println(solution.binaryRepresentation("3.72"));
		//11.1
		System.out.println(solution.binaryRepresentation("3.5"));
		System.out.println(solution.binaryRepresentation("3.23283064365386962890625"));
		System.out.println(solution.binaryRepresentation("1.0"));
	}

}
