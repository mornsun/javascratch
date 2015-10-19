/**
// BEGIN CUT HERE
// PROBLEM STATEMENT
// You are going to send a message to your friend.
The message is given as the String message.
To confuse potential eavesdroppers, you are going to scramble the message.


Scrambling of a message is performed using the int[] key.
If a letter is at the (0-based) position i in the original message, it will appear at the position key[i] in the scrambled message.
(The constraints given below guarantee that this process will produce a valid scrambled message.)


To make the encryption even more confusing, you are going to repeat the above process K times in a row.
Given message, key, and the int K, find and return the final encrypted message.

DEFINITION
Class:VerySecureEncryption
Method:encrypt
Parameters:String, int[], int
Returns:String
Method signature:String encrypt(String message, int[] key, int K)


CONSTRAINTS
-N will be between 1 and 10, inclusive.
-message will contain N characters.
-Each character of message will be a lowercase English letter.
-key will contain N elements.
-Each element of key will be between 0 and N-1, inclusive.
-The elements of key will be distinct.
-K will be between 1 and 50, inclusive.


EXAMPLES

0)
"abc"
{1,2,0}
1

Returns: "cab"


The character 'a' will go from position 0 to position key[0]=1.
The character 'b' will go from position 1 to position key[1]=2.
The character 'c' will go from position 2 to position key[2]=0.


1)
"abcde"
{4, 3, 2, 1, 0}
1

Returns: "edcba"



2)
"abcde"
{4, 3, 2, 1, 0}
2

Returns: "abcde"

This is the same message and the same key as in example 1, but now K=2, so we scramble the message twice.
For this particular key we see that each scrambling reverses the order of letters, which means that the final message is the same as the original we started with.

3)
"uogcodlk"
{4, 3, 6, 2, 5, 1, 0, 7}
44

Returns: "goodluck"



// END CUT HERE
**/
import java.util.*;
public class VerySecureEncryption {
    public String encrypt(String message, int[] key, int K) {
    	int sz = message.length();
		char[] res = message.toCharArray();
    	while (K-- != 0) {
    		char[] ori = res.clone();
    		for (int i=0; i<sz; ++i) {
    			res[key[i]] = ori[i];
    		}
    	}
        return String.valueOf(res);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new VerySecureEncryption()).encrypt("abc", new int[] {1,2,0}, 1),"cab");
            eq(1,(new VerySecureEncryption()).encrypt("abcde", new int[] {4, 3, 2, 1, 0}, 1),"edcba");
            eq(2,(new VerySecureEncryption()).encrypt("abcde", new int[] {4, 3, 2, 1, 0}, 2),"abcde");
            eq(3,(new VerySecureEncryption()).encrypt("uogcodlk", new int[] {4, 3, 6, 2, 5, 1, 0, 7}, 44),"goodluck");
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
