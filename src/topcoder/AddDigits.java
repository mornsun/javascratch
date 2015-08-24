package topcoder;

import java.util.*;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

Hint:

A naive implementation of the above process is trivial. Could you come up with other methods?
What are all the possible results?
How do they occur, periodically or randomly?
You may find this Wikipedia article useful.
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Math
Hide Similar Problems (E) Happy Number

 * @author Chauncey
 *
 */
public class AddDigits
{
    public int addDigits(int num) {
        if (num<10) return num;
        if (num%9 == 0) return 9;
        if ((num&1) == 0) return (addDigits(num>>1)*2)%9;
        else return (addDigits((num-1))+1)%9;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		AddDigits solution = new AddDigits();
		
		System.out.println(solution.addDigits(24));
	}

}
