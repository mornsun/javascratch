/**
 * 
 */
package topcoder;

/**
 * 	You are given two strings a and b of the same length. Choose an index and split both strings at the same index, splitting a into two strings: aprefix and asuffix where a = aprefix + asuffix, and splitting b into two strings: bprefix and bsuffix where b = bprefix + bsuffix. Check if aprefix + bsuffix or bprefix + asuffix forms a palindrome.
 *
 * When you split a string s into sprefix and ssuffix, either ssuffix or sprefix is allowed to be empty. For example, if s = "abc", then "" + "abc", "a" + "bc", "ab" + "c" , and "abc" + "" are valid splits.
 *
 * Return true if it is possible to form a palindrome string, otherwise return false.
 *
 * Notice that x + y denotes the concatenation of strings x and y.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "x", b = "y"
 * Output: true
 * Explaination: If either a or b are palindromes the answer is true since you can split in the following way:
 * aprefix = "", asuffix = "x"
 * bprefix = "", bsuffix = "y"
 * Then, aprefix + bsuffix = "" + "y" = "y", which is a palindrome.
 *
 * Example 2:
 *
 * Input: a = "abdef", b = "fecab"
 * Output: true
 *
 * Example 3:
 *
 * Input: a = "ulacfd", b = "jizalu"
 * Output: true
 * Explaination: Split them at index 3:
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * Then, aprefix + bsuffix = "ula" + "alu" = "ulaalu", which is a palindrome.
 *
 * Example 4:
 *
 * Input: a = "xbdef", b = "xecab"
 * Output: false
 *
 * Constraints:
 *
 *     1 <= a.length, b.length <= 105
 *     a.length == b.length
 *     a and b consist of lowercase English letters
 *
 * Hint 1: Try finding the largest prefix form a that matches a suffix in b
 * Hint 2: Try string matching
 *
 * Two Pointer, String, Greedy
 *
 * @author ChaunceyDetails
 * Runtime: 4 ms, faster than 80.35% of Java online submissions for Split Two Strings to Make Palindrome.
 * Memory Usage: 40.4 MB, less than 6.42% of Java online submissions for Split Two Strings to Make Palindrome.
 */
public class LC_1616_Split_Two_Strings_to_Make_Palindrome
{
    public boolean isPa(String s, int i, int j) {
        for (; i < j; ++i, --j)
            if (s.charAt(i) != s.charAt(j))
                return false;
        return true;
    }

    public boolean check(String a, String b) {
        for (int i = 0, j = a.length() - 1; i < j; ++i, --j)
            if (a.charAt(i) != b.charAt(j))
                return isPa(a, i, j) || isPa(b, i, j);
        return true;
    }

    public boolean checkPalindromeFormation(String a, String b) {
        return check(a, b) || check(b, a);
    }

    public boolean checkPalindromeFormation1(String a, String b) {
        if (a==null || b==null)
            return false;

        int i, n=a.length();
        // a is Palindrome
        for (i=0; i<n/2; ++i) {
            if (a.charAt(i) != a.charAt(n-1-i))
                break;
        }
        if (i == n/2)
            return true;

        // b is Palindrome
        for (i=0; i<n/2; ++i) {
            if (b.charAt(i) != b.charAt(n-1-i))
                break;
        }
        if (i == n/2)
            return true;

        String pref, suff;
        if (a.charAt(0) == b.charAt(n-1)) {
            // a + b split is Palindrome
            for (i=1, pref=a, suff=b; i<n/2; ++i) {
                if (pref.charAt(i) != suff.charAt(n-1-i))
                    break;
            }

            int split = i;
            for (i=split, pref=a, suff=a; i<n/2; ++i) {
                if (pref.charAt(i) != suff.charAt(n-1-i))
                    break;
            }
            if (i == n/2)
                return true;

            for (i=split, pref=b, suff=b; i<n/2; ++i) {
                if (pref.charAt(i) != suff.charAt(n-1-i))
                    break;
            }
            if (i == n/2)
                return true;
        }

        if (b.charAt(0) == a.charAt(n-1)) {
            // b + a split is Palindrome
            for (i=1, pref=b, suff=a; i<n/2; ++i) {
                if (pref.charAt(i) != suff.charAt(n-1-i))
                    break;
            }

            int split = i;
            for (i=split, pref=a, suff=a; i<n/2; ++i) {
                if (pref.charAt(i) != suff.charAt(n-1-i))
                    break;
            }
            if (i == n/2)
                return true;

            for (i=split, pref=b, suff=b; i<n/2; ++i) {
                if (pref.charAt(i) != suff.charAt(n-1-i))
                    break;
            }
            if (i == n/2)
                return true;
        }

        return false;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1616_Split_Two_Strings_to_Make_Palindrome solution = new LC_1616_Split_Two_Strings_to_Make_Palindrome();
        System.out.println(solution.checkPalindromeFormation("x", "y")); //0
        System.out.println(solution.checkPalindromeFormation("abdef", "fecab")); //0
        System.out.println(solution.checkPalindromeFormation("ulacfd", "jizalu")); //0
        System.out.println(solution.checkPalindromeFormation("xbdef", "xecab")); //0
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
