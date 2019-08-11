/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 * Example 1:
 *
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 *
 * Constraints:
 *
 * 1 <= length <= 50000
 * At most 50000 calls will be made to set, snap, and get.
 * 0 <= index < length
 * 0 <= snap_id < (the total number of times we call snap())
 * 0 <= val <= 10^9
 *
 * Related Topic:
 * Array

 * @author Chauncey
 * Runtime: 93 ms, faster than 83.31% of Java online submissions for Snapshot Array.
 * Memory Usage: 94.8 MB, less than 100.00% of Java online submissions for Snapshot Array.
 */
public class LC_1146_Snapshot_Array
{
    private ArrayList<ArrayList<int[]>> _arr;
    int _snap_id = 0;

    public LC_1146_Snapshot_Array(int length) {
        _arr = new ArrayList<ArrayList<int[]>>(length);
        for (int i=0; i<length; ++i) {
            ArrayList<int[]> li = new ArrayList<int[]>();
            li.add(new int[]{0,0});
            _arr.add(li);
        }
    }

    public void set(int index, int val) {
        ArrayList<int[]> ele = _arr.get(index);
        int[] cur = ele.get(ele.size()-1);
        if (cur[1] == val)
            return;
        ele.add(new int[]{_snap_id, val});
    }

    public int snap() {
        return _snap_id++;
    }

    public int get(int index, int snap_id) {
        ArrayList<int[]> ele = _arr.get(index);
        int lo=0;
        int hi=ele.size()-1;
        while (lo<hi) {
            int m = lo + (hi-lo>>1) + 1;
            int id = ele.get(m)[0];
            if (snap_id < id)
                hi = m - 1;
            else
                lo = m;
        }
        return ele.get(lo)[1];
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1146_Snapshot_Array solution = new LC_1146_Snapshot_Array(3);
        solution.set(0,5);
        System.out.println(solution.snap());
        solution.set(0,6);
        System.out.println(solution.get(0,0));
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
