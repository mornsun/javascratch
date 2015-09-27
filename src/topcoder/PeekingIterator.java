package topcoder;

import java.util.*;

/**
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

Call next() gets you 1, the first element in the list.

Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

Hint:

Think of "looking ahead". You want to cache the next element.
Is one variable sufficient? Why or why not?
Test your design with call order of peek() before next() vs next() before peek().
For a clean implementation, check out Google's guava library source code.[https://github.com/google/guava/blob/703ef758b8621cfbab16814f01ddcc5324bdea33/guava-gwt/src-super/com/google/common/collect/super/com/google/common/collect/Iterators.java#L1125]
Follow up: How would you extend your design to be generic and work with all types, not just integer?

Credits:
Special thanks to @porker2008 for adding this problem and creating all test cases.

Hide Tags Design
Hide Similar Problems (M) Binary Search Tree Iterator (M) Flatten 2D Vector (M) Zigzag Iterator

 * @author Chauncey
 *
 */
public class PeekingIterator implements Iterator<Integer> {
	private final Iterator<Integer> _iterator;
	private Integer _topelement = null;

	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
		_iterator = iterator;
		if (_iterator.hasNext())
			_topelement = _iterator.next();
		else
			_topelement = null;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		return _topelement;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		if (!hasNext()) {
			//throw new NoSuchElementException();
			return null;
		}
		Integer ret = _topelement;
		if (_iterator.hasNext()) {
			_topelement = _iterator.next();
		} else {
			_topelement = null;
		}
	    return ret;
	}

	@Override
	public boolean hasNext() {
	    return _topelement != null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
		PeekingIterator solution = new PeekingIterator(list.iterator());

		while (solution.hasNext()) {
			System.out.println(solution.peek());
			System.out.println(solution.next());
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
