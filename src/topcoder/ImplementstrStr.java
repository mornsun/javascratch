/**
 * 
 */
package topcoder;

/**
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
