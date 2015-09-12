package lintcode;

import java.util.*;

/**
 * The size of the hash table is not determinate at the very beginning. If the total size of keys is too large (e.g. size >= capacity / 10), we should double the size of the hash table and rehash every keys. Say you have a hash table looks like below:

size=3, capacity=4

[null, 21, 14, null]
       ↓    ↓
       9   null
       ↓
      null
The hash function is:

int hashcode(int key, int capacity) {
    return key % capacity;
}
here we have three numbers, 9, 14 and 21, where 21 and 9 share the same position as they all have the same hashcode 1 (21 % 4 = 9 % 4 = 1). We store them in the hash table by linked list.

rehashing this hash table, double the capacity, you will get:

size=3, capacity=8

index:   0    1    2    3     4    5    6   7
hash : [null, 9, null, null, null, 21, 14, null]
Given the original hash table, return the new hash table after rehashing .

Have you met this question in a real interview? Yes
Example
Given [null, 21->9->null, 14->null, null],

return [null, 9->null, null, null, null, 21->null, 14->null, null]

Note
For negative integer in hash table, the position can be calculated as follow:

C++/Java: if you directly calculate -4 % 3 you will get -1. You can use function: a % b = (a % b + b) % b to make it is a non negative integer.
Python: you can directly use -1 % 3, you will get 2 automatically.
Tags Expand 
LintCode Copyright Hash Table

 * @author Chauncey
 *
 */
public class Rehashing
{
	/**
	 * Definition for ListNode
	 */
	public static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	        val = x;
	        next = null;
	    }
		@Override
		public String toString() {
			return "[" + val + "," + next + "]";
		}
	}
	
    /**
     * @param hashTable: A list of The first node of linked list
     * @return: A list of The first node of linked list which have twice size
     */
    public ListNode[] rehashing(ListNode[] hashTable) {
        if (hashTable==null || hashTable.length==0) return hashTable;
        ListNode[] res = new ListNode[hashTable.length<<1];
        for (ListNode e : hashTable) {
        	while (null != e) {
        		ListNode next = e.next;
        		int newidx = (e.val%res.length + res.length) % res.length;
        		if (null == res[newidx]) {
            		res[newidx] = e;
            		e.next = null;
        		} else {
        			ListNode node = res[newidx];
        			for (; node.next != null; node = node.next);
        			node.next = e;
        			e.next = null;
        		}
        		e = next;
        	}
        }
        return res;
    }
    public ListNode[] rehashing1(ListNode[] hashTable) {
        if (hashTable==null || hashTable.length==0) return hashTable;
        ListNode[] res = new ListNode[hashTable.length<<1];
        for (ListNode e : hashTable) {
        	while (null != e) {
        		ListNode next = e.next;
        		int newidx = (e.val%res.length + res.length) % res.length;
        		e.next = res[newidx];
        		res[newidx] = e;
        		e = next;
        	}
        }
        return res;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Rehashing solution = new Rehashing();
		ListNode[] hash_table = new ListNode[4];
		hash_table[1] = new ListNode(21);
		hash_table[1].next = new ListNode(9);
		hash_table[2] = new ListNode(14);
		hash_table = solution.rehashing(hash_table);
		for (ListNode num : hash_table) {
			System.out.print(num+",");
		}
		System.out.println();
	}

}
