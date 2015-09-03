package topcoder;

import java.util.*;

/**
 * Implement an iterator to flatten a 2d vector.

For example,

Given 2d vector =
[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

 * @author Chauncey
 *
 */
public class Flatten2DVector
{
	private List<List<Integer>> _vec2d;

    public Flatten2DVector(List<List<Integer>> vec2d) {
    	_vec2d = vec2d;
    }
    public int next() {
    	List<Integer> vec = _vec2d.get(0);
    	int res = vec.get(0);
    	vec.remove(0);
    	return res;
    }
    public boolean hasNext() {
    	if (_vec2d.isEmpty()) return false;
    	List<Integer> vec = _vec2d.get(0);
    	while (vec == null || vec.isEmpty()) {
    		_vec2d.remove(0);
        	if (_vec2d.isEmpty()) return false;
    		vec = _vec2d.get(0);
    	}
    	return true;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		List<List<Integer>> vec2d = new LinkedList<List<Integer>>();
		List<Integer> vec = new LinkedList<Integer>(Arrays.asList(new Integer[]{1,2}));
		vec2d.add(vec);
		vec = new LinkedList<Integer>(Arrays.asList(new Integer[]{3}));
		vec2d.add(vec);
		vec = new LinkedList<Integer>(Arrays.asList(new Integer[]{4,5,6}));
		vec2d.add(vec);
		Flatten2DVector solution = new Flatten2DVector(vec2d);
		
		while(solution.hasNext())
			System.out.println(solution.next());
	}

}
