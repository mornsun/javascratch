#!/usr/bin/env python
#coding=utf8

'''
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.

@author: Chauncey
beat 83.65%
'''

import heapq
import datetime
import time
import sys

class LinkNode:
    def __init__(self, key, x):
        self.key = key
        self.val = x
        self.prev = None
        self.next = None

class AllOne(object):

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.dict = {}
        self.head = LinkNode("", 0)
        self.tail = LinkNode("", sys.maxint)
        self.head.next = self.tail
        self.tail.prev = self.head
        

    def inc(self, key):
        """
        Inserts a new key <Key> with value 1. Or increments an existing key by 1.
        :type key: str
        :rtype: void
        """
        if key in self.dict:
            item = self.dict[key]
            item.val += 1
            curr = item.next
            while item.val > curr.val:
                curr = curr.next
            if curr is not item:
                item.prev.next = item.next
                item.next.prev = item.prev
                item.next = curr
                item.prev = curr.prev
                curr.prev.next = item
                curr.prev = item
        else:
            item = LinkNode(key, 1)
            self.dict[key] = item
            item.next = self.head.next
            item.prev = self.head
            self.head.next.prev = item
            self.head.next = item
        

    def dec(self, key):
        """
        Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
        :type key: str
        :rtype: void
        """
        if key in self.dict:
            item  = self.dict[key]
            if item.val == 1:
                item.prev.next = item.next
                item.next.prev = item.prev
                del self.dict[key]
            else:
                item.val -= 1
                curr = item.prev
                while item.val < curr.val:
                    curr = curr.prev
                if curr is not item:
                    item.prev.next = item.next
                    item.next.prev = item.prev
                    item.next = curr.next
                    item.prev = curr
                    curr.next.prev = item
                    curr.next = item
        

    def getMaxKey(self):
        """
        Returns one of the keys with maximal value.
        :rtype: str
        """
        return self.tail.prev.key
        

    def getMinKey(self):
        """
        Returns one of the keys with Minimal value.
        :rtype: str
        """
        return self.head.next.key
        


if __name__ == '__main__':
    start_time = datetime.datetime.now()

    # Your AllOne object will be instantiated and called as such:
    obj = AllOne()
    obj.inc("hello")
    obj.inc("world")
    obj.inc("leet")
    obj.inc("code")
    obj.inc("DS")
    obj.inc("leet")
    print obj.getMaxKey()
    obj.inc("DS")
    obj.dec("leet")
    print obj.getMaxKey()
    obj.dec("DS")
    obj.inc("hello")
    print obj.getMaxKey()
    obj.inc("hello")
    obj.inc("hello")
    obj.dec("world")
    obj.dec("leet")
    obj.dec("code")
    obj.dec("DS")
    print obj.getMaxKey()
    obj.inc("new")
    obj.inc("new")
    obj.inc("new")
    obj.inc("new")
    obj.inc("new")
    obj.inc("new")
    print obj.getMaxKey()
    print obj.getMinKey()

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]