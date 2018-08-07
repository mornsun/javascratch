/**
 * 
 */
package lintcode;

import java.util.*;

/**
 * 
See wiki: http://www.algorithmist.com/index.php/Coin_Change

 * @author Chauncey
 *
 */
public class CoinChange
{
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: Cosine similarity.
     */
    public List<List<Integer>> coinChange(int[] S, int n) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (null == S || S.length == 0) return res;
        LinkedList<Integer> path = new LinkedList<Integer>();
        dfs(res, path, S, n, S.length-1);
        return res;
    }
    private final void dfs(ArrayList<List<Integer>> res, LinkedList<Integer> path, int[] S, int n, int m) {
        if (n < 0 || m < 0) return;
        if (n == 0) {
            res.add(new LinkedList<Integer>(path));
            return;
        }
        dfs(res, path, S, n, m-1);
        path.addLast(S[m]);
        dfs(res, path, S, n-S[m], m);
        path.removeLast();
    }
    public int countCoinChange(int[] S, int n) {
        if (null == S || S.length == 0) return 0;
        return recursive_count(S, n, S.length-1);
    }
    private final int recursive_count(int[] S, int n, int m) {
        if (n < 0 || m < 0)
            return 0;
        if (n == 0)
            return 1;
        return recursive_count(S, n, m - 1) + recursive_count(S, n - S[m], m);
    }
    // f[m][n] = f[m-1][n] + f[m][n-S[m]];
    public int countCoinChangeDP(int[] S, int n) {
        if (null == S || S.length == 0) return 0;
        int[][] f = new int[S.length][n+1];
        for (int j=0; j<=n; j+=S[0]) {
            f[0][j] = 1;
        }
        for (int i=1; i<S.length; ++i) {
            f[i][0] = 1;
            for (int j=1; j<S[i]; ++j) {
                f[i][j] = f[i-1][j];
            }
            for (int j=S[i]; j<=n; ++j) {
                f[i][j] = f[i-1][j] + f[i][j-S[i]];
            }
        }
        return f[S.length-1][n];
    }
    //from wiki
    public int countCoinChangeDP1(int[] S, int n) {
        if (null == S || S.length == 0) return 0;
        int[][] f = new int[n+1][S.length];
        
        for (int i=0; i<=n; ++i)
            for (int j=0; j<=S.length-1; ++j) {
                if (i == 0)
                    f[i][j] = 1;
                else if (j == 0)
                    f[i][j] = i%S[j]==0 ? 1 : 0;
                else if (S[j]>i)
                    f[i][j] = f[i][j - 1];
                else
                    f[i][j] = f[i - S[j]][j] + f[i][j-1];
                }
        return f[n][S.length-1];
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        CoinChange solution = new CoinChange();
        System.out.println(solution.coinChange(new int[]{2,5}, 10));
        System.out.println(solution.coinChange(new int[]{2,5}, 10).size());
        System.out.println(solution.countCoinChange(new int[]{2,5}, 10));
        System.out.println(solution.countCoinChangeDP(new int[]{2,5}, 10));
        System.out.println(solution.countCoinChangeDP1(new int[]{2,5}, 10));
    }

}
