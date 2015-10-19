/**
// BEGIN CUT HERE
// PROBLEM STATEMENT
// It's a bird! It's a plane! No, it's a square in a plane! Wait, is it really a square?


There are four distinct points in the plane.
You are given their coordinates in the int[]s x and y:
for each i between 0 and 3, inclusive, there is a point at (x[i], y[i]).


Return "It's a square" (quotes for clarity) if the four points are the vertices of a square.
Otherwise, return "Not a square".

DEFINITION
Class:IsItASquare
Method:isSquare
Parameters:int[], int[]
Returns:String
Method signature:String isSquare(int[] x, int[] y)


CONSTRAINTS
-x will contain 4 elements.
-y will contain 4 elements.
-Each element of x will be between 0 and 10,000, inclusive.
-Each element of y will be between 0 and 10,000, inclusive.
-The four points described by x and y will be distinct.


EXAMPLES

0)
{0, 0, 2, 2}
{0, 2, 0, 2}

Returns: "It's a square"



1)
{0, 1, 5, 6}
{1, 6, 0, 5}

Returns: "It's a square"

Note that the sides of the square do not have to be parallel to the coordinate axes.
Also note that the order in which the points are given does not have to be the same as the order in which you would encounter them when following the boundary of the square.

2)
{0, 0, 7, 7}
{0, 3, 0, 3}

Returns: "Not a square"



3)
{0, 5000, 5000, 10000}
{5000, 0, 10000, 5000}

Returns: "It's a square"



4)
{1, 2, 3, 4}
{4, 3, 2, 1}

Returns: "Not a square"



5)
{0, 5, 3, 8}
{0, 0, 4, 4}

Returns: "Not a square"



// END CUT HERE
**/
import java.util.*;
public class IsItASquare {
	private static final String yes = "It's a square";
	private static final String no = "Not a square";
    public String isSquare(int[] x, int[] y) {
    	ArrayList<Integer> dists = new ArrayList<Integer>();
    	for (int i=0; i<4; ++i) {
    		for (int j=i+1; j<4; ++j) {
    			dists.add(square(x[i]-x[j]) + square(y[i]-y[j]));
    		}
    	}
    	Collections.sort(dists);
    	if (!dists.get(0).equals(dists.get(1)) || !dists.get(0).equals(dists.get(2)) ||
    			!dists.get(0).equals(dists.get(3)))
    		return no;
    	if (!dists.get(4).equals(dists.get(5)))
    		return no;
    	if (!dists.get(4).equals(dists.get(0)*2))
    		return no;
        return yes;
    }
    private final int square(int x) {
    	return (x*x);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new IsItASquare()).isSquare(new int[] {0, 0, 2, 2}, new int[] {0, 2, 0, 2}),"It's a square");
            eq(1,(new IsItASquare()).isSquare(new int[] {0, 1, 5, 6}, new int[] {1, 6, 0, 5}),"It's a square");
            eq(2,(new IsItASquare()).isSquare(new int[] {0, 0, 7, 7}, new int[] {0, 3, 0, 3}),"Not a square");
            eq(3,(new IsItASquare()).isSquare(new int[] {0, 5000, 5000, 10000}, new int[] {5000, 0, 10000, 5000}),"It's a square");
            eq(4,(new IsItASquare()).isSquare(new int[] {1, 2, 3, 4}, new int[] {4, 3, 2, 1}),"Not a square");
            eq(5,(new IsItASquare()).isSquare(new int[] {0, 5, 3, 8}, new int[] {0, 0, 4, 4}),"Not a square");
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
