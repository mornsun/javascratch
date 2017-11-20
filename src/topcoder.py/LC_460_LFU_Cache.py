#!/usr/bin/env python
#coding=utf8

'''
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

Related Topics 
Design 
Similar Questions 
LRU Cache Design In-Memory File System

@author: Chauncey
beat 83.65%
'''

import heapq
import datetime
import time
import sys

class LinkNode:
    def __init__(self, key, cnt, value):
        self.key = key
        self.cnt = cnt
        self.value = value
        self.prev = None
        self.next = None

class LFUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.dict = {}
        self.head = LinkNode("", 0, -1)
        self.tail = LinkNode("", sys.maxint, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if len(self.dict) != 0 and key in self.dict:
            item = self.dict[key]
            item.cnt += 1
            curr = item.next
            while item.cnt >= curr.cnt:
                curr = curr.next
            if curr is not item:
                item.prev.next = item.next
                item.next.prev = item.prev
                item.next = curr
                item.prev = curr.prev
                curr.prev.next = item
                curr.prev = item

            return item.value
        else:
            return -1
        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: void
        """
        if key in self.dict:
            item = self.dict[key]
            item.value = value
            item.cnt += 1
            curr = item.next
            while item.cnt >= curr.cnt:
                curr = curr.next
            if curr is not item:
                item.prev.next = item.next
                item.next.prev = item.prev
                item.next = curr
                item.prev = curr.prev
                curr.prev.next = item
                curr.prev = item
        else:
            if self.capacity == 0:
                return
            if len(self.dict) == self.capacity:
                self.dict.pop(self.head.next.key)
                self.head.next = self.head.next.next
                self.head.next.prev = self.head
            item = LinkNode(key, 1, value)
            self.dict[key] = item
            item.next = self.head.next
            item.prev = self.head
            self.head.next.prev = item
            self.head.next = item
            curr = item.next
            while item.cnt >= curr.cnt:
                curr = curr.next
            if curr is not item:
                item.prev.next = item.next
                item.next.prev = item.prev
                item.next = curr
                item.prev = curr.prev
                curr.prev.next = item
                curr.prev = item


if __name__ == '__main__':
    start_time = datetime.datetime.now()

    # Your AllOne object will be instantiated and called as such:
    cache = LFUCache( 2 );

    cache.put(1, 1);
    cache.put(2, 2);
    print cache.get(1);       # returns 1
    cache.put(3, 3);    # evicts key 2
    print cache.get(2);       # returns -1 (not found)
    print cache.get(3);       # returns 3.
    cache.put(4, 4);    # evicts key 1.
    print cache.get(1);       # returns -1 (not found)
    print cache.get(3);       # returns 3
    print cache.get(4);       # returns 4

    cache = LFUCache(0)
    cache.put(0,0)
    print cache.get(0)

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]