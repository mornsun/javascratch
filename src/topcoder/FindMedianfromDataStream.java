package topcoder;

import java.util.*;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2
Credits:
Special thanks to @Louis1992 for adding this problem and creating all test cases.

Hide Tags Heap Design

 * @author Chauncey
 *
 */
public class FindMedianfromDataStream
{
	private class ReverseComparator implements Comparator<Integer> {
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	}
	private final ReverseComparator comparator = new ReverseComparator();
	private final PriorityQueue<Integer> _heap1 = new PriorityQueue<Integer>(1024,comparator);
	private final PriorityQueue<Integer> _heap2 = new PriorityQueue<Integer>();
	private int _size = 0;
    // Adds a number into the data structure.
    public void addNum(int num) {
        ++_size;
        _heap2.offer(num);
        if (_heap1.size() < _heap2.size()) {
        	_heap1.offer(_heap2.poll());
        }
        if (_size>1 && _heap1.peek() > _heap2.peek()) {
        	_heap1.offer(_heap2.poll());
        	_heap2.offer(_heap1.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
    	if (_size == 0)
    		return 0;
    	int ret = _heap1.peek();
    	if ((_size&1) == 1)
    		return ret;
    	else
    		return ret + (_heap2.peek() - ret) / 2.0;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FindMedianfromDataStream solution = new FindMedianfromDataStream();
		solution.addNum(6);
		System.out.println(solution.findMedian());
		solution.addNum(5);
		System.out.println(solution.findMedian());
		solution.addNum(4);
		System.out.println(solution.findMedian());
		solution.addNum(3);
		System.out.println(solution.findMedian());
		solution.addNum(2);
		System.out.println(solution.findMedian());
		solution.addNum(1);
		System.out.println(solution.findMedian());
	}
}
