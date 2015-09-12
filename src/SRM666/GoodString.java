/**
// BEGIN CUT HERE
// PROBLEM STATEMENT
// 
Chandan loves to play with strings.
He just learned a new operation: inserting one string X into another string Y.



When inserting X into Y, it is also allowed to place X at the beginning or at the end of Y.
For example, there are exactly five ways how to insert the string "ab" into the string "????":
you can produce one of the strings "ab????", "?ab???", "??ab??", "???ab?", and "????ab".



According to Chandan, a good string is a string that can be constructed in the following way:
Initially, he takes the empty string "".
Then, he performs a sequence of zero or more steps.
In each step he inserts the string "ab" anywhere into the current string.



According to the above definition, the strings "ab", "aabb", and "aababb" are good while the strings "aab", "ba", and "abbb" are not good.



Chandan's friend Ravi came up with a String s.
Ravi asked Chandan whether it is a good string.
Return "Good" (quotes for clarity) if the string is good, or "Bad" if the string is not good.


DEFINITION
Class:GoodString
Method:isGood
Parameters:String
Returns:String
Method signature:String isGood(String s)


CONSTRAINTS
-s will contain between 1 and 50 characters, inclusive.
-Each element of s will be either 'a' or 'b'.


EXAMPLES

0)
"ab"

Returns: "Good"

Chandan can start with an empty string and insert "ab".

1)
"aab"

Returns: "Bad"



2)
"aabb"

Returns: "Good"

Chandan can construct this string as follows: "" -> "ab" -> "aabb".

3)
"ababab"

Returns: "Good"

One way to construct this string is to append "ab" to the current string three times.

4)
"abaababababbaabbaaaabaababaabbabaaabbbbbbbb"

Returns: "Bad"



5)
"aaaaaaaabbbaaabaaabbabababababaabbbbaabbabbbbbbabb"

Returns: "Good"



// END CUT HERE
**/

package SRM666;
import java.util.*;
public class GoodString {
    public String isGood(String s) {
    	if (null == s || s.length()==0) return "Good";
    	int sz = s.length();
    	int stack = 0;
    	for (int i=0; i<sz; ++i) {
    		if (s.charAt(i)=='a') {
    			++stack;
    		} else {
    			--stack;
    		}
    		if (stack<0) return "Bad";
    	}
        return stack==0 ? "Good" : "Bad";
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new GoodString()).isGood("ab"),"Good");
            eq(1,(new GoodString()).isGood("aab"),"Bad");
            eq(2,(new GoodString()).isGood("aabb"),"Good");
            eq(3,(new GoodString()).isGood("ababab"),"Good");
            eq(4,(new GoodString()).isGood("abaababababbaabbaaaabaababaabbabaaabbbbbbbb"),"Bad");
            eq(5,(new GoodString()).isGood("aaaaaaaabbbaaabaaabbabababababaabbbbaabbabbbbbbabb"),"Good");
        } catch( Exception exx) {
            System.err.println(exx);
            exx.printStackTrace(System.err);
        }
    }
    private static void eq( int n, int a, int b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected "+b+", received "+a+".");
    }
    private static void eq( int n, char a, char b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected '"+b+"', received '"+a+"'.");
    }
    private static void eq( int n, long a, long b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected \""+b+"L, received "+a+"L.");
    }
    private static void eq( int n, boolean a, boolean b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected "+b+", received "+a+".");
    }
    private static void eq( int n, String a, String b ) {
        if ( a != null && a.equals(b) )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected \""+b+"\", received \""+a+"\".");
    }
    private static void eq( int n, int[] a, int[] b ) {
        if ( a.length != b.length ) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++)
            if ( a[i] != b[i] ) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void eq( int n, long[] a, long[] b ) {
        if ( a.length != b.length ) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++ )
            if ( a[i] != b[i] ) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void eq( int n, String[] a, String[] b ) {
        if ( a.length != b.length) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++ )
            if( !a[i].equals( b[i])) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void print( int a ) {
        System.err.print(a+" ");
    }
    private static void print( long a ) {
        System.err.print(a+"L ");
    }
    private static void print( String s ) {
        System.err.print("\""+s+"\" ");
    }
    private static void print( int[] rs ) {
        if ( rs == null) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print(rs[i]);
            if ( i != rs.length-1 )
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void print( long[] rs) {
        if ( rs == null ) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print(rs[i]);
            if ( i != rs.length-1 )
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void print( String[] rs ) {
        if ( rs == null ) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print( "\""+rs[i]+"\"" );
            if( i != rs.length-1)
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void nl() {
        System.err.println();
    }
// END CUT HERE
}
