package lintcode;

import java.util.*;

/**
 * Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). Originally, the 2D matrix is all 0 which means there is only sea in the matrix. The list pair has k operator and each operator has two integer A[i].x, A[i].y means that you can change the grid matrix[A[i].x][A[i].y] from sea to island. Return how many island are there in the matrix after each operator.

Have you met this question in a real interview? Yes
Example
Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].

return [1,1,2,2].

Note
0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Tags Expand 
Union Find


Related Problems Expand 
Easy Number of Islands 19 %
Medium Find the Weak Connected Component in the Directed Graph 17 %

 *
 * @author Chauncey
 *
 */
public class xNumberofIslandsII
{/**
	 * Definition for a point.
	 */
	 private static class Point {
	     int x;
	     int y;
	     Point() { x = 0; y = 0; }
	     Point(int a, int b) { x = a; y = b; }
	 }
    /**
     * @param m an integer
     * @param n an integer
     * @param operators an array of point
     * @return an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
    	if (m==0 || n==0 || operators==null || operators.length==0) return new ArrayList<Integer>();
    	List<Integer> res = new ArrayList<Integer>(operators.length);
    	initset(m*n);
    	for (Point operator : operators) {
    		int idx = n*operator.y + operator.x;
    		if (size(idx) != 0) {
    			System.out.println(idx+":"+size(idx));
        		res.add(count());
    			continue;
    		}
			makeset(idx);
    		if (operator.x>0) { //left side
    			int side = idx-1;
    			if (size(side) != 0)
    				union(idx,side);
    		}
    		if (operator.x<n-1) { //right side
    			int side = idx+1;
    			if (size(side) != 0)
    				union(idx,side);
    		}
    		if (operator.y>0) { //up side
    			int side = idx-n;
    			if (size(side) != 0)
    				union(idx,side);
    		}
    		if (operator.y<m-1) { //up side
    			int side = idx+n;
    			if (size(side) != 0)
    				union(idx,side);
    		}
    		res.add(count());
    	}
    	return res;
    }
    
    private int _count;
    private int[] _id;
    private int[] _size;

    public void initset(int n) {
        this._count = 0;
        this._id = new int[n];
        this._size = new int[n];

        for (int i = 0; i < n; i++) {
            _id[i] = i;
            _size[i] = 0;
        }
    }
    
    public void makeset(int idx) {
    	if (size(idx) != 0) return;
        _size[idx] = 1;
        ++_count;
    }

    private int find(int idx) {
        while (_id[idx] != _id[_id[idx]]) {
            _id[idx] = _id[_id[idx]];
        }
        return _id[idx];
    }

    public void union(int idx1, int idx2) {
        int root1 = this.find(idx1);
        int root2 = this.find(idx2);

        if (root1 == root2) {
            return;
        }
        if (_size[root1] >= _size[root2]) {
            _id[root2] = root1;
            _size[root1] += _size[root2];
        } else {
            _id[root1] = root2;
            _size[root2] += _size[root1];
        }
        _count--;
    }

    public int count() {
        return this._count;
    }
    public int size(int idx) {
        return this._size[find(idx)];
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xNumberofIslandsII solution = new xNumberofIslandsII();
		System.out.println(solution.numIslands2(3, 3, new Point[]{new Point(0,0),new Point(0,1),new Point(2,2),new Point(2,1)}));
		System.out.println(solution.numIslands2(4, 5, new Point[]{new Point(1,1),new Point(0,1),new Point(3,3),new Point(3,4)}));
	}

}
