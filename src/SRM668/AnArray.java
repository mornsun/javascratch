package SRM668;

/**
// BEGIN CUT HERE
// PROBLEM STATEMENT
// One day, Bob the Coder was wondering whether abstract programming problems can have applications in practice. The next day, he was selected to be on a quiz show. He will win one million dollars if he answers the following question:

Given a int[] A with N elements and an int K, count the number of tuples (p, q, r) such that 0 <= p < q < r < N and A[p] * A[q] * A[r] is divisible by K.

Please compute and return the answer to Bob's question.

DEFINITION
Class:AnArray
Method:solveProblem
Parameters:int[], int
Returns:int
Method signature:int solveProblem(int[] A, int K)


CONSTRAINTS
-A will contain between 3 and 2,000 elements, inclusive.
-K will be between 1 and 1,000,000, inclusive.
-Each element of A will be between 1 and 100,000,000, inclusive.


EXAMPLES

0)
{31, 1, 3, 7, 2, 5}
30

Returns: 1

The return value is 1 because there is exactly one valid tuple. The tuple is (2, 4, 5). It is valid because A[2] * A[4] * A[5] = 3 * 2 * 5 = 30.

1)
{4, 5, 2, 25}
100

Returns: 2



2)
{100000000, 100000000, 100000000}
1000000

Returns: 1

Note that the product A[p] * A[q] * A[r] doesn't have to fit into a 64-bit integer variable.

3)
{269, 154, 94, 221, 171, 154, 50, 210, 258, 358, 121, 159, 8, 47, 290, 125, 291, 293, 338, 248, 295, 160, 268, 227, 99, 4, 273}
360

Returns: 114



4)
{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
1

Returns: 220



// END CUT HERE
**/
import java.util.*;
public class AnArray {
    public int solveProblem(int[] A, int K) {
		int cnt = 0;
		HashMap<Long, Integer> memo = new HashMap<Long, Integer>(10240);
		for (int prev=0; prev<A.length-1; ++prev) {
			long j = 1;
			for (; j * j < A[prev]; ++j) {
				if (A[prev] % j == 0) {
					incr(memo, j);
					incr(memo, A[prev]/j);
				}
			}
			if (j*j == A[prev])
				incr(memo, j);
			int q = prev+1;
			for (int r=q+1; r<A.length; ++r) {
				long key = K / gcd((long)A[q] * (long)A[r], K);
				Integer c = memo.get(key);
				if (c != null)
					cnt += c;
			}
		}
		return cnt;
    }
	private final void incr(HashMap<Long, Integer> dp, long key) {
		Integer c = dp.get(key);
		if (c == null) {
			dp.put(key, 1);
		} else {
			dp.put(key, c+1);
		}
	}
	private final int gcd (long a, int b) {
		if (b == 0) return (int)a;
		return gcd(b, (int)(a%b));
	}

// BEGIN CUT HERE
	private final int[][] _memo = new int[2001][1001];
	private final int find (int ix, int K, int[] A) {
		if (_memo[ix][K] != -1) {
			return _memo[ix][K];
		}
		int cnt = 0;
		for (int i=ix; i<A.length; ++i) {
			if (A[i]%K==0) ++cnt;
		}
		_memo[ix][K] = cnt;
		return cnt;
	}
	public int solveProblem1(int[] A, int K) {
		int cnt = 0;
		Arrays.sort(A);
		for (int i=0; i<_memo.length; ++i) {
			for (int j=0; j<_memo[0].length; ++j) {
				_memo[i][j] = -1;
			}
		}
		for (int p=0; p<A.length-2; ++p) {
			for (int q=p+1; q<A.length-1; ++q) {
				long m = A[p] * A[q];
				int g = gcd(m, K);
				cnt += find(q+1, K/g, A);
			}
		}
		return cnt;
	}
    public static void main(String[] args) {
		for (int i=99998000; i<=99999999; ++i) {
			//System.out.print(i+","); //2198967
		}
        try {
            eq(0,(new AnArray()).solveProblem(new int[] {31, 1, 3, 7, 2, 5}, 30),1);
            eq(1,(new AnArray()).solveProblem(new int[] {4, 5, 2, 25}, 100),2);
            eq(2,(new AnArray()).solveProblem(new int[] {100000000, 100000000, 100000000}, 1000000),1);
            eq(3,(new AnArray()).solveProblem(new int[] {269, 154, 94, 221, 171, 154, 50, 210, 258, 358, 121, 159, 8, 47, 290, 125, 291, 293, 338, 248, 295, 160, 268, 227, 99, 4, 273}, 360),114);
            eq(4,(new AnArray()).solveProblem(new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 1),220);
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
