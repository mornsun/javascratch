/**
// BEGIN CUT HERE
// PROBLEM STATEMENT
// 
Surya has a tree with n nodes, numbered 1 through n.
Each node contains some arbitrary nonnegative number of tokens.



Surya sometimes goes for a walk on the tree.
He has to start his walk in node 1, but he may terminate it in any node of the tree.
Surya gets tired easily: during the walk he is only able to traverse at most L edges.



Surya now wants to collect as many tokens as possible during a single walk.
He can collect tokens in all nodes he visits, including the nodes where he starts and ends his walk.
Obviously, the tokens in each node can only be collected once.



You are given the structure of the tree in int[]s A and B, each with n-1 elements.
For each valid i the tree contains an edge between the nodes A[i] and B[i].
You are also given the int[] tokens with n elements.
For each valid i, tokens[i] is the number of tokens in node i+1.
Finally, you are given the int L.



Return the maximum number of tokens Surya can collect.


DEFINITION
Class:CollectingTokens
Method:maxTokens
Parameters:int[], int[], int[], int
Returns:int
Method signature:int maxTokens(int[] A, int[] B, int[] tokens, int L)


CONSTRAINTS
-n will be between 1 and 50, inclusive.
-A and B will contain exactly n-1 elements each.
-Each element of A and B will be between 1 and n, inclusive.
-A and B will define a tree.
-tokens will contain exactly n elements.
-Each element of tokens will be between 1 and 100, inclusive.
-L will be between 1 and 100, inclusive.


EXAMPLES

0)
{1}
{2}
{7,1}
6

Returns: 8

This tree consists of two nodes and a single edge.
There are 7 tokens in node 1 and 1 token in node 2.
Surya can make at most six steps, which is more than enough to collect all 7+1 = 8 tokens.

1)
{3,1}
{2,2}
{2,3,9}
5

Returns: 14



2)
{1,2,5,3}
{4,4,1,4}
{6,1,6,4,4}
3

Returns: 16

This is a tree with five nodes.
One optimal walk for this tree is to start in node 1, go to node 4, then to node 3, and then back to node 4.
As L=3, this is the longest walk Surya may make.
During this walk he will collect 6 tokens in node 1, 4 tokens in node 4, 6 tokens in node 3, and then 0 tokens when revisiting node 4.
The total is 6+4+6+0 = 16 tokens.

Another optimal walk is to start in node 1, go to node 4, then to node 3, and to stop there.
Surya is not required to make all L steps.

3)
{9,1,7,10,5,8,3,4,2}
{6,6,9,6,6,1,1,6,6}
{4,2,1,6,3,7,8,5,2,9}
4

Returns: 26



4)
{25,22,35,42,40,9,32,12,37,44,23,1,24,28,20,4,26,33,11,48,34,6,16,50,46,17,8,43,18,30,31,36,39,13,
10,45,3,47,15,2,29,19,7,14,41,49,38,27,21}
{5,5,25,25,25,42,25,40,5,35,25,32,42,9,32,23,40,25,20,33,26,37,12,1,48,24,22,25,11,24,48,34,18,9,50,42,16,40,1,
10,47,22,48,44,48,1,4,46,47}
{6,9,4,9,5,8,6,4,4,1,4,8,3,4,5,8,5,6,4,9,7,9,7,9,5,2,7,2,7,7,5,9,5,8,5,7,1,9,3,9,3,6,4,5,5,4,7,9,2,2}
48

Returns: 194



// END CUT HERE
**/
package SRM666;
import java.util.*;
/**
 * 
题意，给出一个树，每个结点都有一定数量的金币，每个结点的金币只能一次全拿走，拿走就没了，从根结点1出发，最多走l条边，要求最终能得到的最大金币数。

不错的树形dp的题

dp[i][j][0]表示第i结点，走了j条边，最终回到i点的最大值。

dp[i][j][1]表示第i结点，走了j条边，最终不用回到i点的最大值。
则状态转移为 goal为s结点的子结点

dp[s][j][0] = max(dp[s][j][0],dp[s][k][0] + dp[goal][j - k - 2][0]); //表示，前面子结点，回到了s结点，当前goal结点也回到s结点
dp[s][j][1] = max(dp[s][j][1],dp[s][k][1] + dp[goal][j - k - 2][0]); //表示，前面子结点，没回到了s结点，当前goal结点回到s结点。所以要-2，由于可能回来了，也可能没回来，又因为比较小，放到没回来的组
dp[s][j][1] = max(dp[s][j][1],dp[s][k][0] + dp[goal][j - k - 1][1]); //表示，前面子结点，回到了s结点，当前goal结点没回到s结点
注意，要从大到小枚举，因为，这样可 以保证，小的没有被污染，仍是原值。

复杂度为o(n ^ 3)
 */
public class CollectingTokens {
	private int[][][] dp;
	private boolean[] visited;
	
	private final void dfs (ArrayList<ArrayList<Integer>> graph, int[] tokens, int l, int u, int prev) {
		if (l==0) return;
		for (int i=0; i<=l; ++i) dp[u][i][0] = dp[u][i][1] = tokens[u];
		
		for (int v : graph.get(u)) {
			if (v == prev) continue;
			if (visited[v]) continue;
			visited[v] = true;
			//System.out.println(u+":"+v+":"+l);
			dfs(graph, tokens, l-1, v, u);
			for (int j=l; j>=0; --j) {
				for (int k=0; k<j; ++k) {
					if (j-k>=2) {
						dp[u][j][0] = Math.max(dp[u][j][0], dp[u][k][0]+dp[v][j-k-2][0]);
						dp[u][j][1] = Math.max(dp[u][j][1], dp[u][k][1]+dp[v][j-k-2][0]);
						dp[u][j][1] = Math.max(dp[u][j][1], dp[u][k][0]+dp[v][j-k-1][1]);
					} else if (j-k>=1) {
						dp[u][j][1] = Math.max(dp[u][j][1], dp[u][k][0]+dp[v][j-k-1][1]);
					}
				}
				//System.out.println(dp[u][j][0]+":"+dp[u][j][1]+"("+u+":"+j+")");
			}
			/*for (int j=l-1; j>=0; --j) {
				for (int k=0; k<=j; ++k) {
					dp[u][j+2][0] = Math.max(dp[u][j+2][0], dp[u][j-k][0]+dp[v][k][0]);
					dp[u][j+2][1] = Math.max(dp[u][j+2][1], dp[u][j-k][1]+dp[v][k][0]);
					dp[u][j+1][1] = Math.max(dp[u][j+1][1], dp[u][j-k][0]+dp[v][k][1]);
				}
			}*/
		}
	}
    public int maxTokens(int[] A, int[] B, int[] tokens, int L) {
    	int n = tokens.length;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<=n; ++i)
        	graph.add(new ArrayList<Integer>());
        for (int i=0; i<A.length; ++i) {
        	graph.get(A[i]-1).add(B[i]-1);
        	graph.get(B[i]-1).add(A[i]-1);
        }
        dp = new int[A.length+2][L+2][2];
        visited = new boolean[tokens.length+1];
		visited[0] = true;
        
        dfs(graph, tokens, L, 0, -1);
        return Math.max(dp[0][L][1], dp[0][L][0]);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
        	System.out.println("Case 0:");
            eq(0,(new CollectingTokens()).maxTokens(new int[] {1}, new int[] {2}, new int[] {7,1}, 6),8);
            System.out.println("Case 1:");
            eq(1,(new CollectingTokens()).maxTokens(new int[] {3,1,3}, new int[] {2,2,1}, new int[] {2,3,9}, 5),14);
            System.out.println("Case 2:");
            eq(2,(new CollectingTokens()).maxTokens(new int[] {1,2,5,3}, new int[] {4,4,1,4}, new int[] {6,1,6,4,4}, 3),16);
            eq(3,(new CollectingTokens()).maxTokens(new int[] {9,1,7,10,5,8,3,4,2}, new int[] {6,6,9,6,6,1,1,6,6}, new int[] {4,2,1,6,3,7,8,5,2,9}, 4),26);
            eq(4,(new CollectingTokens()).maxTokens(new int[] {25,22,35,42,40,9,32,12,37,44,23,1,24,28,20,4,26,33,11,48,34,6,16,50,46,17,8,43,18,30,31,36,39,13,
               10,45,3,47,15,2,29,19,7,14,41,49,38,27,21}, new int[] {5,5,25,25,25,42,25,40,5,35,25,32,42,9,32,23,40,25,20,33,26,37,12,1,48,24,22,25,11,24,48,34,18,9,50,42,16,40,1,
               10,47,22,48,44,48,1,4,46,47}, new int[] {6,9,4,9,5,8,6,4,4,1,4,8,3,4,5,8,5,6,4,9,7,9,7,9,5,2,7,2,7,7,5,9,5,8,5,7,1,9,3,9,3,6,4,5,5,4,7,9,2,2}, 48),194);
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
