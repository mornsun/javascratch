/**
// BEGIN CUT HERE
// PROBLEM STATEMENT
// 
Devu loves to play games.
This problem is about a game he recently played.
In the game there are n locations, numbered 0 through n-1.
Each location has one entrance and one exit.
You are given a int[] nextLevel with n elements.
For each i, nextLevel[i] describes the exit from location i.
If nextLevel[i] is a number between 0 and n-1, inclusive, it means that the exit from location i leads to the entrance of location nextLevel[i].
Otherwise, nextLevel[i] will be -1 and it means that if the player reaches this exit, they win the game.





Devu started the game by entering location 0.
Return "Win" (quotes for clarity) if he can win the game.
Otherwise, return "Lose".
Note that the return value is case-sensitive.


DEFINITION
Class:DevuAndGame
Method:canWin
Parameters:int[]
Returns:String
Method signature:String canWin(int[] nextLevel)


CONSTRAINTS
-nextLevel will have between 1 and 50 elements, inclusive.
-Each element in nextLevel will be either -1 or will be between 0 and n - 1, inclusive.


EXAMPLES

0)
{1, -1}

Returns: "Win"

Devu will start in location 0. The exit from this location will bring him to location 1, and when he reaches the exit from location 1 he wins the game.

1)
{1, 0, -1}

Returns: "Lose"

Devu will go back and forth between locations 0 and 1. He is unable to reach the exit from location 2.

2)
{0, 1, 2}

Returns: "Lose"

The exit from location 0 leads back to location 0. Devu is unable to reach the other locations.

3)
{29,33,28,16,-1,11,10,14,6,31,7,35,34,8,15,17,26,12,13,22,1,20,2,21,-1,5,19,9,18,4,25,32,3,30,23,10,27}

Returns: "Win"

There can be multiple x such that nextLevel[x] is -1.
In order to win the game, Devu has to reach any single location with this property.

4)
{17,43,20,41,42,15,18,35,-1,31,7,33,23,33,-1,-1,0,33,19,12,42,-1,-1,9,9,-1,39,-1,31,46,-1,20,44,41,-1,-1,12,-1,36,-1,-1,6,47,10,2,4,1,29}

Returns: "Win"



5)
{3, 1, 1, 2, -1, 4}

Returns: "Lose"

In this game, Devu will go from location 0 to location 3, from there to location 2, and from there to location 1. There he will get stuck, as the exit from location 1 leads back to location 1.

// END CUT HERE
**/

package SRM666;
import java.util.*;
public class DevuAndGame {
    public String canWin(int[] nextLevel) {
    	if (null == nextLevel || nextLevel.length==0) return "Win";
    	boolean[] visited = new boolean[nextLevel.length];
    	int i=0;
    	while (nextLevel[i] != -1) {
    		if (visited[i] == true) {
    			return "Lose";
    		}
    		visited[i] = true;
    		i = nextLevel[i];
    	}
        return "Win";
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new DevuAndGame()).canWin(new int[] {1, -1}),"Win");
            eq(1,(new DevuAndGame()).canWin(new int[] {1, 0, -1}),"Lose");
            eq(2,(new DevuAndGame()).canWin(new int[] {0, 1, 2}),"Lose");
            eq(3,(new DevuAndGame()).canWin(new int[] {29,33,28,16,-1,11,10,14,6,31,7,35,34,8,15,17,26,12,13,22,1,20,2,21,-1,5,19,9,18,4,25,32,3,30,23,10,27}),"Win");
            eq(4,(new DevuAndGame()).canWin(new int[] {17,43,20,41,42,15,18,35,-1,31,7,33,23,33,-1,-1,0,33,19,12,42,-1,-1,9,9,-1,39,-1,31,46,-1,20,44,41,-1,-1,12,-1,36,-1,-1,6,47,10,2,4,1,29}),"Win");
            eq(5,(new DevuAndGame()).canWin(new int[] {3, 1, 1, 2, -1, 4}),"Lose");
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
