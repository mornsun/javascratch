package SRM667;
/**
// BEGIN CUT HERE
// PROBLEM STATEMENT
// 
You are given two distinct points A and B in the two-dimensional plane.
Your task is to find any point C with the following properties:

C is different from A and B.
Each coordinate of C is an integer between -100 and 100, inclusive.
The distance between A and C is strictly greater than the distance between B and C.




You are given four ints: x1, y1, x2, and y2.
Point A has coordinates (x1,y1) and point B has coordinates (x2,y2).
Find the coordinates (x3,y3) of one possible point C with the above properties.
Return these coordinates as a int[] with two elements: element 0 is x3 and element 1 is y3.
In other words, return the int[] {x3,y3}.



For the constraints given below it is guaranteed that a valid point C always exists.
If there are multiple solutions, return any of them.


DEFINITION
Class:PointDistance
Method:findPoint
Parameters:int, int, int, int
Returns:int[]
Method signature:int[] findPoint(int x1, int y1, int x2, int y2)


NOTES
-In this problem we consider the standard Euclidean distance. Formally, the distance between points (xi,yi) and (xj,yj) is defined as sqrt( (xi-xj)^2 + (yi-yj)^2 ).


CONSTRAINTS
-x1,y1,x2,y2 will be between -50 and 50, inclusive.
-(x1,y1) will be different from (x2,y2).


EXAMPLES

0)
-1
0
1
0

Returns: {8, 48 }

In this example, point A is at (-1,0) and point B is at (1,0).
Almost any point with a positive x-coordinate will be a valid answer.
For example, your program can also return {100,100}, {2,0}, or {9,-100}.
Note that you cannot return {1,0} because point C must not be the same as point B.


1)
1
1
-1
-1

Returns: {25, -63 }

(x1,y1) is (1,1) and (x2,y2) is (-1,-1).

2)
0
1
2
3

Returns: {41, 65 }



3)
5
-4
-2
5

Returns: {68, 70 }



4)
-50
-50
50
-50

Returns: {67, 4 }



5)
-50
50
-49
49

Returns: {73, -25 }



// END CUT HERE
**/
import java.util.*;
public class PointDistance {
	private final int square (int x) {
		return x*x;
	}
    public int[] findPoint(int x1, int y1, int x2, int y2) {
        int x = x2-1;
        int y = y2;
        if (square(x-x1) + square(y-y1) > 1) return new int[]{x,y};
        x = x2+1;
        y = y2;
        if (square(x-x1) + square(y-y1) > 1) return new int[]{x,y};
        x = x2;
        y = y2-1;
        if (square(x-x1) + square(y-y1) > 1) return new int[]{x,y};
        x = x2;
        y = y2+1;
        if (square(x-x1) + square(y-y1) > 1) return new int[]{x,y};
        return new int[]{x,y};
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new PointDistance()).findPoint(-1, 0, 1, 0),new int[] {8, 48 });
            eq(1,(new PointDistance()).findPoint(1, 1, -1, -1),new int[] {25, -63 });
            eq(2,(new PointDistance()).findPoint(0, 1, 2, 3),new int[] {41, 65 });
            eq(3,(new PointDistance()).findPoint(5, -4, -2, 5),new int[] {68, 70 });
            eq(4,(new PointDistance()).findPoint(-50, -50, 50, -50),new int[] {67, 4 });
            eq(5,(new PointDistance()).findPoint(-50, 50, -49, 49),new int[] {73, -25 });
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
