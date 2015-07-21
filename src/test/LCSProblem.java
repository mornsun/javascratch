package test;

import java.util.ArrayList;

public class LCSProblem   
{  
    public static void main(String[] args)
    {
        String[] x = { "A", "B", "C", "D", "E"};
        String[] y = {  "A", "A", "B", "C", "D", "E", "F"};
//        String[] y = {"B", "D", "C", "A", "B", "A"};

        ArrayList<Integer> stack = new  ArrayList<Integer>();
        int[] idx = getLCS(x, y);
        for (int i=0; i<idx.length; ++i) {
        	System.out.print(idx[i]+" ");
        	stack.add(idx[i]);
        }
        System.out.println();
        stack.set(0, null);
        stack.remove(0);
        System.out.println(stack);
    }

    public static int[] getLCS(String[] x, String[] y)  
    {  
        int i, j;
        int[][] memo = new int[x.length+1][y.length+1];
        // DP
        for(i=0; i<x.length; i++) {
            for(j=0; j<y.length; j++) {
                if( x[i] == y[j]) {
                    memo[i+1][j+1] = memo[i][j] + 1;
                } else if(memo[i][j+1] >= memo[i+1][j]) {
                    memo[i+1][j+1] = memo[i][j+1];
                } else {
                    memo[i+1][j+1] = memo[i+1][j];
                }
            }
        }
        // Get result: record indexes of the common elements [memo1 in x, memo1 in y, memo2 in x, memo2 in y, ...]
        int lenLCS = memo[x.length][y.length];
        int idx[] = new int[lenLCS<<1];
        int k = (lenLCS<<1)-1;
        i = x.length;
        j = y.length;
        while (i != 0 && j != 0) {
        	if (memo[i][j] == memo[i-1][j]) {
        		--i;
        	} else if (memo[i][j] == memo[i][j-1]) {
        		--j;
        	} else {
        		idx[k--] = j-1;
        		idx[k--] = i-1;
        		--i;
        		--j;
        	}
        }
        //Test
        /*for (i=0; i<memo.length; ++i) {
            for (j=0; j<memo[i].length; ++j) {
                System.out.print(memo[i][j] + ":" + (b[i][j]+1) + " \t");
            }
            System.out.println();
        }
        for (i=0; i<idx.length; ++i) {
        	System.out.print(idx[i]+" ");
        }
        System.out.println();*/
        return idx;
    }
      
    public static void Display(int[][] c, String[] x, int i, int j)
    {
    	ArrayList<String> stack = new  ArrayList<String>(i);
        while (i != 0 && j != 0) {
        	if (c[i][j] == c[i-1][j]) {
        		--i;
        	} else if (c[i][j] == c[i][j-1]) {
        		--j;
        	} else {
        		stack.add(x[i-1]);
        		--i;
        		--j;
        	}
        }
        int len = stack.size();
        for (i = 0; i < len/2; ++i) {
        	String stmp = stack.get(i);
        	stack.set(i, stack.get(len-1-i));
        	stack.set(len-1-i, stmp);
        }
        System.out.print(stack + " ");
    }
}  