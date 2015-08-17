/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author Chauncey
 *
 */
public class LRUCache
{
	private class LRUCacheItem{
		public LRUCacheItem pre;
		public LRUCacheItem next;
		public int key;
		public int value;
		public LRUCacheItem(int k, int v) {key = k; value = v;}
	}
	private int _capacity;
	private HashMap<Integer, LRUCacheItem> _cache_map;
	private LRUCacheItem _head_queue;
	private LRUCacheItem _tail_queue;
	
	private void output_queue(String prefix) {
		System.out.print(prefix+":["+_cache_map.size()+"-"+_capacity+"]");
		LRUCacheItem p = _head_queue;
		int max = 0;
		while (p != null && max++!=20) {
			System.out.print(p.key+":"+p.value+", ");
			p = p.next;
		}
		System.out.println("max:"+max+",tail:"+(_tail_queue==null?null:_tail_queue.key+":"+_tail_queue.value));
	}
	/**
	 * @param item - is in cache queue, so must be not null
	 */
	private void refresh(LRUCacheItem item) {
		if (item.pre != null) { //move item to head of queue
			if (item == _tail_queue)
				_tail_queue = item.pre;
			else
				item.next.pre = item.pre;
			item.pre.next = item.next;
			_head_queue.pre = item;
			item.next = _head_queue;
			item.pre = null;
			_head_queue = item;
		} //else item is already at head, keep it
	}
	
	public LRUCache(int capacity) {
		_capacity = capacity;
		_head_queue = null;
		_tail_queue = null;
		_cache_map = new HashMap<Integer, LRUCacheItem>((int)(capacity/0.75+1));
	}
	
	private final void remove(LRUCacheItem item) {
		if (item.pre != null) {
			item.pre.next = item.next;
		}
		if (item.next != null) {
			item.next.pre = item.pre;
		}
		if (item == _tail_queue) {
			_tail_queue = item.pre;
		}
		if (item == _head_queue) {
			_head_queue = item.next;
		}
	}
	
	private final void add2head(LRUCacheItem item) {
		if (_head_queue == null) {
			_head_queue = _tail_queue = item;
		} else {
			item.next = _head_queue;
			item.pre = null;
			_head_queue.pre = item;
			_head_queue = item;
		}
	}
	
	public int get(int key) {
		LRUCacheItem item = _cache_map.get(key);
		if (item == null)
			return -1;
		//output_queue("get1:"+(item.pre==null?null:item.pre.key+":"+item.pre.value));
		remove(item);
		add2head(item);
		//output_queue("get2:"+(item.pre==null?null:item.pre.key+":"+item.pre.value));
		return item.value;
	}
	
	public void set(int key, int value) {
		LRUCacheItem item = _cache_map.get(key);
		//output_queue("set1");
		if (item == null) {//insert to head
			if (_cache_map.size() == _capacity) {
				_cache_map.remove(_tail_queue.key);
				remove(_tail_queue);
			}
			item = new LRUCacheItem(key, value);
			add2head(item);
			_cache_map.put(key, item);
		} else {//set
			item.value = value;
			remove(item);
			add2head(item);
		}
		//output_queue("set2");
	}
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*LRUCache cache = new LRUCache(2);
		cache.set(2,1);
		cache.set(2,2);
		System.out.println(cache.get(2));
		cache.set(1,1);
		cache.set(4,1);
		System.out.println(cache.get(2));
		
		cache = new LRUCache(1);
		cache.set(2,1);
		System.out.println(cache.get(2));
		cache.set(3,2);
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
		
		//2,[set(2,1),set(1,1),get(2),set(4,1),get(1),get(2)]
		cache = new LRUCache(2);
		cache.set(2,1);
		cache.set(1,1);
		System.out.println(cache.get(2));
		cache.set(4,1);
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));*/
		LRUCache cache = new LRUCache(10);
		cache.set(10,13);
		cache.set(3,17);
		cache.set(6,11);
		cache.set(10,5);
		cache.set(9,10);
		System.out.println(cache.get(13));
		cache.set(2,19);
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
		cache.set(5,25);
		System.out.println(cache.get(8));
		cache.set(9,22);
		cache.set(5,5);
		cache.set(1,30);
		System.out.println(cache.get(11));
		cache.set(9,12);
		System.out.println(cache.get(7));
		System.out.println(cache.get(5));
		System.out.println(cache.get(8));
		System.out.println(cache.get(9));
		cache.set(4,30);
		cache.set(9,3);
		System.out.println(cache.get(9));
		System.out.println(cache.get(10));
		System.out.println(cache.get(10));
		cache.set(6,14);
		cache.set(3,1);
		System.out.println(cache.get(3));
		cache.set(10,11);
		System.out.println(cache.get(8));
		cache.set(2,14);
		System.out.println(cache.get(1));
		System.out.println(cache.get(5));
		System.out.println(cache.get(4));
		cache.set(11,4);
		cache.set(12,24);
		cache.set(5,18);
		System.out.println(cache.get(13));
		cache.set(7,23);
		System.out.println(cache.get(8));
		System.out.println(cache.get(12));
		cache.set(3,27);
		cache.set(2,12);
		System.out.println(cache.get(5));
		cache.set(2,9);
		cache.set(13,4);
		cache.set(8,18);
		cache.set(1,7);
		System.out.println(cache.get(6));
		cache.set(9,29);
		cache.set(8,21);
		System.out.println(cache.get(5));
		cache.set(6,30);
		cache.set(1,12);
		System.out.println(cache.get(10));
		cache.set(4,15);
		cache.set(7,22);
		cache.set(11,26);
		cache.set(8,17);
		cache.set(9,29);
		System.out.println(cache.get(5));
		cache.set(3,4);
		cache.set(11,30);
		System.out.println(cache.get(12));
		cache.set(4,29);
		System.out.println(cache.get(3));
		System.out.println(cache.get(9));
		System.out.println(cache.get(6));
		cache.set(3,4);
		System.out.println(cache.get(1));
		System.out.println(cache.get(10));
		cache.set(3,29);
		cache.set(10,28);
		cache.set(1,20);
		cache.set(11,13);
		System.out.println(cache.get(3));
		cache.set(3,12);
		cache.set(3,8);
		cache.set(10,9);
		cache.set(3,26);
		System.out.println(cache.get(8));
		System.out.println(cache.get(7));
		System.out.println(cache.get(5));
		cache.set(13,17);
		cache.set(2,27);
		cache.set(11,15);
		System.out.println(cache.get(12));
		cache.set(9,19);
		cache.set(2,15);
		cache.set(3,16);
		System.out.println(cache.get(1));
		cache.set(12,17);
		cache.set(9,1);
		cache.set(6,19);
		System.out.println(cache.get(4));
		System.out.println(cache.get(5));
		System.out.println(cache.get(5));
		cache.set(8,1);
		cache.set(11,7);
		cache.set(5,2);
		cache.set(9,28);
		System.out.println(cache.get(1));
		cache.set(2,2);
		cache.set(7,4);
		cache.set(4,22);
		cache.set(7,24);
		cache.set(9,26);
		cache.set(13,28);
		cache.set(11,26);
		//[-1,19,17,-1,-1,-1,5,-1,12,3,5,5,1,-1,30,5,30,-1,-1,24,18,-1,18,-1,18,-1,4,29,30,12,-1,29,17,22,18,-1,20,-1,18,18,20]

	}

}
