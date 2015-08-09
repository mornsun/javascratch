package topcoder;

import java.util.*;

/**
 * @author Chauncey
 *
 */
public class ValidNumber {

	public static boolean isNumber1(String s) {
		if (s == null) return false;
		s = s.trim();
		int slen = s.length();
		if (slen == 0) return false;
		int idxE = -1;
		int[] idxP = new int[2];
		idxP[0] = -1;
		idxP[1] = -1;
		int part = 0;
		int i=0;
		if (s.charAt(i) == '-') ++i;
		for (; i<slen; ++i) {
			char ch = s.charAt(i);
			if (!isDigit(ch) && ch != '.' && ch != 'e') return false;
			if (ch == '.') {
				if (idxP[part] == -1)
					idxP[part] = i;
				else
					return false;
			} else if (ch == 'e') {
				if (idxE == -1) {
					idxE = i;
					part = 1;
					if (i+1<slen) {
						if (s.charAt(i+1) == '-') ++i;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		System.out.println(idxE+":"+idxP[0]+":"+idxP[1]);
		if (idxE != -1) {
			return isSubNumber(s, 0, idxE, idxP[0]) && isSubNumber(s, idxE+1, slen, idxP[1]);
		} else {
			return isSubNumber(s, 0, slen, idxP[0]);
		}
    }
	
	private final static boolean isSubNumber(String s, int start, int end, int idxP) {
		if (start >= end) return false;
		return true;
	}
	
	private final static boolean isDigit(char ch) {
		return (ch>='0' && ch<='9');
	}
	public static boolean isNumber(String s) {
		if (s == null) return false;
		s = s.trim();
		int slen = s.length();
		if (slen == 0) return false;
		boolean has_point = false;
		boolean number_part = false;
		boolean has_e = false;
		int i=0;
		if (s.charAt(i) == '-' || s.charAt(i) == '+') ++i;
		for (; i<slen; ++i) {
			char ch = s.charAt(i);
			if (!isDigit(ch)) {
				if (ch == '.') {
					if (has_point) return false;
					if (has_e) return false;
					has_point = true;
					if (!(i>0 && isDigit(s.charAt(i-1))) && !(i+1<slen && isDigit(s.charAt(i+1)))) {
						return false;
					}
					number_part = true;
				} else if (ch == 'e') {
					if (has_e) return false;
					if (!number_part) return false;
					has_e = true;
					has_point = false;
					number_part = false;
					if (i+1<slen && (s.charAt(i+1) == '-' || s.charAt(i+1) == '+')) ++i;
				} else {
					return false;
				}
			} else {
				number_part = true;
			}
		}
		return number_part;
	}

    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isNumber("6e6.5"));
	}

}
