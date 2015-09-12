package SRM667;
/**
// BEGIN CUT HERE
// PROBLEM STATEMENT
// 
Carol is starting a new taco shop business.
She is going to open some taco shops in a block of buildings.
The blocks consists of n adjacent buildings in a row.
Each building has exactly m floors.
The buildings are numbered 0 through n-1 in order.



Carol can open between 0 and m taco shops in each building (as there can be at most one taco shop per floor in each building).
For each taco shop, the profit P[x][y] will depend on two factors:

the number x of the building that contains this taco shop
the total count y of taco shops in that particular building and in buildings adjacent to that building (including this particular taco store)




You are given the ints n and m.
You are also given the profits as defined above, encoded into a int[] c.
For each x between 0 and n-1, and for each y between 1 and 3m, the profit P[x][y] is given in c[x*3*m+y-1].



It is guaranteed that the profits don't increase as y increases. That is, for each valid x and y, P[x][y] will be greater than or equal to P[x][y+1].
Note that the profit is for a single store.
For example, if there are three taco stores in building 7 and no other stores in buildings 6 and 8, each of these three taco stores will bring the profit P[7][3].



Determine and return the maximum total profit that Carol can gain from opening the taco shops.


DEFINITION
Class:ShopPositions
Method:maxProfit
Parameters:int, int, int[]
Returns:int
Method signature:int maxProfit(int n, int m, int[] c)


CONSTRAINTS
-n will be between 1 and 30, inclusive.
-m will be between 1 and 30, inclusive.
-c will have exactly n*3*m elements.
-Each element of c will be between 1 and 1,000, inclusive.
-For each x between 0 and n-1, the sequence c[3*m*x], c[3*m*x + 1], ..., c[3*m*(x+1) - 1] will be sorted in nonincreasing order


EXAMPLES

0)
1
5
{100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 1, 1, 1, 1, 1}

Returns: 300


Carol has 1 building with 5 floors.



Building one shop will get her a profit of 100, while building two shops will get a profit of 90*2.
The optimal strategy in this case is to build 5 taco shops, for a profit of 60*5=300.




1)
1
5
{1000, 5, 4, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}

Returns: 1000



2)
3
1
{
  7,6,1,
  10,4,1,
  7,6,3
}

Returns: 14

The optimal strategy here is to open one taco store in building 0 and one taco store in building 2.

3)
2
2
{
 12,11,10,9,8,7,
 6,5,4,3,2,1
}

Returns: 24



4)
3
3
{
  30,28,25,15,14,10,5,4,2,
  50,40,30,28,17,13,8,6,3,
  45,26,14,14,13,13,2,1,1
}

Returns: 127



// END CUT HERE
**/
import java.util.*;
public class ShopPositions {
	private final int get_profit_idx(int[] c, int n, int m, int y) {
		if (n==0 || y==0) return 0;
		//System.out.println(n+":"+m+":"+y);
		return c[(n-1)*3*m+y-1];
	}
    public int maxProfit(int n, int m, int[] c) {
    	int[][][] f = new int[n+1][m+1][m+1];
    	int ans = 0;
    	f[0][0][0] = 0;
    	for (int i=1; i<=n; ++i) {
    		for (int j=0; j<=m; ++j) {
    			for (int k=0; k<=m; ++k) {
    				for (int p=0; p<=m; ++p) {
    					f[i][k][p] = Math.max(f[i][k][p], f[i-1][j][k] + get_profit_idx(c, i-1, m, j+k+p)*k);
    					if (i==n) ans = Math.max(ans, f[i][k][p] + get_profit_idx(c, i, m, k+p)*p);
    				}
    			}
    		}
    	}
    	/*for (int i=0; i<m; ++i) {
    		for (int j=0; j<m; ++j) {
    			ans = Math.max(ans, f[1][i][j]);
    		}
    	}*/
    	return ans;
    	
    	/*for (int i=30*30; i>0; --i) {
    		System.out.print(i+",");
    	}
    	for (int i=30*30; i>0; --i) {
    		System.out.print(i+",");
    	}
    	for (int i=30*30; i>0; --i) {
    		System.out.print(i+",");
    	}
    	System.out.println();*/
        //return 0;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new ShopPositions()).maxProfit(1, 5, new int[] {100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 1, 1, 1, 1, 1}),300);
            eq(1,(new ShopPositions()).maxProfit(1, 5, new int[] {1000, 5, 4, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}),1000);
            eq(2,(new ShopPositions()).maxProfit(3, 1, new int[] {
                 7,6,1,
                 10,4,1,
                 7,6,3
               }),14);
            eq(3,(new ShopPositions()).maxProfit(2, 2, new int[] {
                12,11,10,9,8,7,
                6,5,4,3,2,1
               }),24);
            eq(4,(new ShopPositions()).maxProfit(3, 3, new int[] {
                 30,28,25,15,14,10,5,4,2,
                 50,40,30,28,17,13,8,6,3,
                 45,26,14,14,13,13,2,1,1
               }),127);
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
