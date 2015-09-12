package lintcode;

import java.util.*;

/**
 * Given an integer array, heapify it into a min-heap array.

For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
Have you met this question in a real interview? Yes
Example
Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.

Challenge
O(n) time complexity

Clarification
What is heap?

Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.

What is heapify?
Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].

What if there is a lot of solutions?
Return any of them.
Tags Expand 
LintCode Copyright Heap

 * @author Chauncey
 *
 */
public class Heapify
{
    /**
     * @param A: Given an integer array
     * @return: void
     */
    public void heapify(int[] A) {
    	if (A==null || A.length==0) return;
    	int first_leaf = A.length/2;
    	for (int i=first_leaf-1; i>=0; --i) {
    		max_heapify(A, i);
    	}
    }
    
    private final void max_heapify(int[] A, int i) {
    	int left = (i<<1)+1;
    	int right = (i<<1)+2;
    	int least = i;
    	if (left < A.length && A[left] < A[least]) {
    		least = left;
    	}
    	if (right < A.length && A[right] < A[least]) {
    		least = right;
    	}
    	if (least != i) { //swap
    		int tmp = A[least];
    		A[least] = A[i];
    		A[i] = tmp;
        	max_heapify(A, least);
    	}
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Heapify solution = new Heapify();
		int[] nums = new int[]{2, 5, 6, 0, 3, 1};
		solution.heapify(nums);
		for (int num : nums) {
			System.out.print(num+",");
		}
		System.out.println();
	}

}
