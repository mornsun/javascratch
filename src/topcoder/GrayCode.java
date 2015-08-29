package topcoder;

import java.util.*;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.

Hide Tags Backtracking

自然二进制码转换为格雷码：g0=b0; gi=bi^b(i-1)
保留自然二进制码的最高位作为格雷码的最高位，格雷码次高位为二进制码的高位与次高位异
或，其余各位与次高位的求法类似。例如，将自然二进制码1001，转换为格雷码的过程是：保留最
高位；然后将第1 位的1 和第2 位的0 异或，得到1，作为格雷码的第2 位；将第2 位的0 和第3 位
的0 异或，得到0，作为格雷码的第3 位；将第3 位的0 和第4 位的1 异或，得到1，作为格雷码的
第4 位，最终，格雷码为1101。
格雷码转换为自然二进制码：b0=g0; bi=gi^b(i-1)
保留格雷码的最高位作为自然二进制码的最高位，次高位为自然二进制高位与格雷码次高位异
或，其余各位与次高位的求法类似。例如，将格雷码1000 转换为自然二进制码的过程是：保留最高
位1，作为自然二进制码的最高位；然后将自然二进制码的第1 位1 和格雷码的第2 位0 异或，得
到1，作为自然二进制码的第2 位；将自然二进制码的第2 位1 和格雷码的第3 位0 异或，得到1，
作为自然二进制码的第3 位；将自然二进制码的第3 位1 和格雷码的第4 位0 异或，得到1，作为
自然二进制码的第4 位，最终，自然二进制码为1111。
格雷码有数学公式，整数n 的格雷码是n^(n/2)。

 * @author Chauncey
 *
 */
public class GrayCode
{
    public List<Integer> grayCode(int n) {
    	if (n<0) return new ArrayList<Integer>();
    	ArrayList<Integer> res = new ArrayList<Integer>(1<<n);
    	res.add(0);
    	for (int i=0; i<n; ++i) {
    		int hbit = 1<<i;
    		int j=res.size()-1;
    		for (; j>=0; --j) {
    			res.add(hbit | res.get(j));
    		}
    	}
    	return res;
    }
    public int getGrayCode(int n) {
    	if (n < 0) return -1;
    	int i = 0;
    	int res = 0;
    	while (n != 0) {
    		int bit = n%2;
    		n /= 2;
    		if ((n&1) == 1) bit = ~bit & 1;
    		res |= bit<<i++;
    		System.out.println(bit+","+n+","+res);
    	}
    	return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		GrayCode solution = new GrayCode();

		System.out.println(solution.grayCode(4));
		System.out.println(solution.getGrayCode(8));
	}

}
