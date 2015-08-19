/**
 * 
 */
package topcoder;

/**
 * Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Update (2014-11-02):
The signature of the function had been updated to return the index instead of the pointer. If you still see your function signature returns a char * or String, please click the reload button  to reset your code definition.

Hide Tags Two Pointers String
Hide Similar Problems (H) Shortest Palindrome

 * @author Chauncey
 *
 */
public class ImplementstrStr
{
    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        return haystack.indexOf(needle);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println(strStr("hello world", "x"));
	}

}
