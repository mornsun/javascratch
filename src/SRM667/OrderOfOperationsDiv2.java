package SRM667;
/**
// BEGIN CUT HERE
// PROBLEM STATEMENT
// 
Cat Noku has just finished writing his first computer program.
Noku's computer has m memory cells.
The cells have addresses 0 through m-1.
Noku's program consists of n instructions.
The instructions have mutually independent effects and therefore they may be executed in any order.
The instructions must be executed sequentially (i.e., one after another) and each instruction must be executed exactly once.



You are given a description of the n instructions as a String[] with n elements.
Each instruction is a String of m characters.
For each i, character i of an instruction is '1' if this instruction accesses memory cell i, or '0' if it does not.



Noku's computer uses caching, which influences the time needed to execute an instruction.
More precisely, executing an instruction takes k^2 units of time, where k is the number of new memory cells this instruction accesses.
(I.e., k is the number of memory cells that are accessed by this instruction but have not been accessed by any previously executed instruction.
Note that k may be zero, in which case the current instruction is indeed executed in 0 units of time.)



Noku's instructions can be executed in many different orders.
Clearly, different orders may lead to a different total time of execution.
Find and return the shortest amount of time in which it is possible to execute all instructions.


DEFINITION
Class:OrderOfOperationsDiv2
Method:minTime
Parameters:String[]
Returns:int
Method signature:int minTime(String[] s)


CONSTRAINTS
-n,m will be between 1 and 20, inclusive.
-s will have exactly n elements.
-Each element of s will have exactly m characters.
-Each character of s[i] will be either '0' or '1' for all valid i.


EXAMPLES

0)
{
 "111",
 "001",
 "010"
}

Returns: 3

Cat Noku has 3 instructions.
The first instruction ("111") accesses all three memory cells.
The second instruction ("001") accesses only memory cell 2.
The third instruction ("010") accesses only memory cell 1.
If Noku executes these three instructions in the given order, it will take 3^2 + 0^2 + 0^2 = 9 units of time.
However, if he executes them in the order "second, third, first", it will take only 1^2 + 1^2 + 1^2 = 3 units of time.
This is one optimal solution.
Another optimal solution is to execute the instructions in the order "third, second, first".

1)
{
 "11101",
 "00111",
 "10101",
 "00000",
 "11000"
}

Returns: 9



2)
{
  "11111111111111111111"
}

Returns: 400

A single instruction that accesses all 20 memory cells.

3)
{
  "1000",
  "1100",
  "1110"
}

Returns: 3



4)
{
  "111",
  "111",
  "110",
  "100"
}

Returns: 3



// END CUT HERE
**/
import java.util.*;
public class OrderOfOperationsDiv2 {
    public int minTime(String[] s) {
    	int n = s.length;
    	int m = s[0].length();
    	int[] t = new int[n]; //binary code of instructions
    	int ncombination = 1<<n;
    	int[] f = new int[ncombination];
    	for (int i=0; i<n; ++i) {
    		for (int j=0; j<m; ++j) {
    			if (s[i].charAt(j) == '1') t[i] |= 1<<j;
    		}
    	}
    	for (int i=0; i< ncombination; ++i) {
    		f[i] = Integer.MAX_VALUE;
    	}
    	f[0] = 0;
    	for (int i=0; i< ncombination; ++i) {
    		int mask = 0;
    		for (int j=0; j<n; ++j) {
    			if ((i & 1<<j) != 0) { //instructions have not been operated
    				mask |= t[j];
    			}
    		}
    		for (int j=0; j<n; ++j) {
    			if ((i & 1<<j) == 0) {
	    			int c = 0;
	    			int num = (t[j] & ~mask); //pop count
	    			while (num != 0) {
	    				num = (num-1) & num;
	    				++c;
	    			}
	    			f[i | 1<<j] = Math.min(f[i | 1<<j], f[i]+c*c);
    			}
    		}
    	}
    	return f[(1<<n)-1];
    }
    public int minTime1(String[] s) {
        boolean[] cache = new boolean[s[0].length()];
        boolean[] visited = new boolean[s.length];
    	int mink = 0;
        int sum = 0;
        while (mink != -1) {
        	int min = Integer.MAX_VALUE;
    		mink = -1;
	        for (int k=0; k<s.length; ++k) {
	        	if (visited[k]) continue;
	        	String str = s[k];
	        	int sz = str.length();
	        	int cnt = 0;
	        	for (int i=0; i<sz; ++i) {
	        		if (str.charAt(i) == '1' && !cache[i]) ++cnt;
	        	}
	        	if (cnt < min) {
	        		min = cnt;
	        		mink = k;
	        	}
	        }
	        if (mink != -1) {
	        	//System.out.println(mink+":"+min);
	        	sum += min*min;
	        	visited[mink] = true;
	        	String str = s[mink];
	        	int sz = str.length();
	        	for (int i=0; i<sz; ++i) {
	        		if (str.charAt(i) == '1' && !cache[i]) cache[i] = true;
	        	}
	        }
        }
        return sum;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new OrderOfOperationsDiv2()).minTime(new String[] {
                "111",
                "001",
                "010"
               }),3);
            eq(1,(new OrderOfOperationsDiv2()).minTime(new String[] {
                "11101",
                "00111",
                "10101",
                "00000",
                "11000"
               }),9);
            eq(2,(new OrderOfOperationsDiv2()).minTime(new String[] {
                 "11111111111111111111"
               }),400);
            eq(3,(new OrderOfOperationsDiv2()).minTime(new String[] {
                 "1000",
                 "1100",
                 "1110"
               }),3);
            eq(4,(new OrderOfOperationsDiv2()).minTime(new String[] {
                 "111",
                 "111",
                 "110",
                 "100"
               }),3);
            OrderOfOperationsDiv2 soltion = new OrderOfOperationsDiv2();
        	Random rand =new Random(25);
        	final int OPERATION_NUMBER = 5;
            for (int i=0; i<50; ++i) {
            	String[] s = new String[OPERATION_NUMBER];
                System.out.println("case "+(100+i));
            	for (int j=0; j<OPERATION_NUMBER; ++j) {
            		int num = rand.nextInt();
            		StringBuilder sb = new StringBuilder(10);
            		int checker = 1<<30;
            		for (int k=0; k<OPERATION_NUMBER; ++k,checker>>=1) {
            			sb.append(((checker&num)==0)?'0':'1');
            		}
            		s[j] = sb.toString();
            		System.out.println(s[j]);
            	}
                eq((100+i), soltion.minTime1(s), soltion.minTime(s));
            }
            /*eq(5,(new OrderOfOperationsDiv2()).minTime(new String[] {
                    "00000000000000000001",
                    "00000000000000000011",
                    "00000000000000000111",
                    "00000000000000001111",
                    "00000000000000011111",
                    "00000000000000111111",
                    "00000000000001111111",
                    "00000000000010000000",
                    "00000000000100000000",
                    "00000000001000000000",
                    "00000000010000000000",
                    "00000000100000000000",
                    "00000001000000000000",
                    "00000010000000000000",
                    "00000100000000000000",
                    "00001000000000000000",
                    "00010000000000000000",
                    "00100000000000000000",
                    "01000000000000000000",
                    "10000000000000000000"
                  }),20);*/
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
