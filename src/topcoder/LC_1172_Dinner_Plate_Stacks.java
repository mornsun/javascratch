/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	You have an infinite number of stacks arranged in a row and numbered (left to right) from 0, each of the stacks has the same maximum capacity.
 *
 * Implement the DinnerPlates class:
 *
 * DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks.
 * void push(int val) pushes the given positive integer val into the leftmost stack with size less than capacity.
 * int pop() returns the value at the top of the rightmost non-empty stack and removes it from that stack, and returns -1 if all stacks are empty.
 * int popAtStack(int index) returns the value at the top of the stack with the given index and removes it from that stack, and returns -1 if the stack with that given index is empty.
 * Example:
 *
 * Input:
 * ["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
 * [[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
 * Output:
 * [null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]
 *
 * Explanation:
 * DinnerPlates D = DinnerPlates(2);  // Initialize with capacity = 2
 * D.push(1);
 * D.push(2);
 * D.push(3);
 * D.push(4);
 * D.push(5);         // The stacks are now:  2  4
 *                                            1  3  5
 *                                            ﹈ ﹈ ﹈
 * D.popAtStack(0);   // Returns 2.  The stacks are now:     4
 *                                                        1  3  5
 *                                                        ﹈ ﹈ ﹈
 * D.push(20);        // The stacks are now: 20  4
 *                                            1  3  5
 *                                            ﹈ ﹈ ﹈
 * D.push(21);        // The stacks are now: 20  4 21
 *                                            1  3  5
 *                                            ﹈ ﹈ ﹈
 * D.popAtStack(0);   // Returns 20.  The stacks are now:     4 21
 *                                                         1  3  5
 *                                                         ﹈ ﹈ ﹈
 * D.popAtStack(2);   // Returns 21.  The stacks are now:     4
 *                                                         1  3  5
 *                                                         ﹈ ﹈ ﹈
 * D.pop()            // Returns 5.  The stacks are now:      4
 *                                                         1  3
 *                                                         ﹈ ﹈
 * D.pop()            // Returns 4.  The stacks are now:   1  3
 *                                                         ﹈ ﹈
 * D.pop()            // Returns 3.  The stacks are now:   1
 *                                                         ﹈
 * D.pop()            // Returns 1.  There are no stacks.
 * D.pop()            // Returns -1.  There are still no stacks.
 *
 * Constraints:
 *
 * 1 <= capacity <= 20000
 * 1 <= val <= 20000
 * 0 <= index <= 100000
 * At most 200000 calls will be made to push, pop, and popAtStack.
 *
 * Related Topic:
 * Array

 * @author Chauncey
 * Runtime: 117 ms, faster than 88.28% of Java online submissions for Dinner Plate Stacks.
 * Memory Usage: 140.8 MB, less than 100.00% of Java online submissions for Dinner Plate Stacks.
 */
public class LC_1172_Dinner_Plate_Stacks
{
    private ArrayList<LinkedList<Integer>> _stacks = new ArrayList<LinkedList<Integer>>();
    private int _capacity;
    private ArrayList<Integer> _waitingstacks = new ArrayList<Integer>();

    public LC_1172_Dinner_Plate_Stacks(int capacity) {
        _capacity = capacity;
        _stacks.add(new LinkedList<Integer>());
    }

    public void push(int val)
    {
        if (!_waitingstacks.isEmpty()) {
            int idx = _waitingstacks.get(0);
            if (idx < _stacks.size()) {
                LinkedList<Integer> st = _stacks.get(idx);
                st.push(val);
                if (st.size()>=_capacity) {
                    _waitingstacks.remove(0);
                }
                return;
            }
            _waitingstacks.clear();
        }
        int idx = _stacks.size()-1;
        LinkedList<Integer> curr = _stacks.get(idx);
        if (curr.size() >= _capacity) {
            curr = new LinkedList<Integer>();
            _stacks.add(curr);
        }
        curr.push(val);
    }

    public int pop()
    {
        int idx = _stacks.size() - 1;
        if (idx < 0) return -1;
        while (!_waitingstacks.isEmpty() && _waitingstacks.get(_waitingstacks.size() - 1) > idx)
            _waitingstacks.remove(_waitingstacks.size() - 1);
        LinkedList<Integer> curr = _stacks.get(idx);
        while (curr.isEmpty()) {
            if (idx <= 0) return -1;
            _stacks.remove(idx);
            idx--;
            curr = _stacks.get(idx);
        }
        return curr.pop();
    }

    public int popAtStack(int index)
    {
        int idx = _stacks.size() - 1;
        if (index > idx) return -1;
        LinkedList<Integer> curr = _stacks.get(index);
        if (curr.isEmpty())
            return -1;
        if (curr.size() == _capacity) {
            int lo = 0;
            int hi = _waitingstacks.size() - 1;
            while (lo < hi) {
                int m = lo + (hi - lo >> 1);
                if (index <= _waitingstacks.get(m))
                    hi = m;
                else
                    lo = m + 1;
            }
            if (hi<0 || _waitingstacks.get(lo) != index)
                _waitingstacks.add(lo, index);
        }
        return curr.pop();
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1172_Dinner_Plate_Stacks solution = new LC_1172_Dinner_Plate_Stacks(2);
        solution.push(1);
        solution.push(2);
        solution.push(3);
        solution.push(4);
        solution.push(5);
        System.out.println(solution.popAtStack(0));
        solution.push(20);
        solution.push(21);
        System.out.println(solution.popAtStack(0));
        System.out.println(solution.popAtStack(2));
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());

        solution = new LC_1172_Dinner_Plate_Stacks(2);
        solution.push(1);
        solution.push(2);
        solution.push(3);
        solution.push(4);
        solution.push(5);
        System.out.println(solution.popAtStack(0));
        solution.push(20);
        solution.push(21);
        System.out.println(solution.popAtStack(1));
        System.out.println(solution.popAtStack(1));
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
