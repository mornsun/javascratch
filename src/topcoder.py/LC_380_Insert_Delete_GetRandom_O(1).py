#!/usr/bin/env python
#coding=utf8

'''
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();

Related Topics 
Array Hash Table Design 
Similar Questions 
Insert Delete GetRandom O(1) - Duplicates allowed 

@author: Chauncey
beat 87.71%
'''

import heapq
import datetime
import time
import sys
import collections
import random

class RandomizedSet(object):

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.memo = []
        self.dict = {}
        

    def insert(self, val):
        """
        Inserts a value to the set. Returns true if the set did not already contain the specified element.
        :type val: int
        :rtype: bool
        """
        if val in self.dict:
            return False
        self.dict[val] = len(self.memo)
        self.memo.append(val)
        return True
        

    def remove(self, val):
        if val not in self.dict:
            return False
        idx, last = self.dict[val], self.memo[-1]
        self.memo[idx], self.dict[last] = last, idx
        self.memo.pop()
        self.dict.pop(val, 0)
        return True

    def remove1(self, val):
        """
        Removes a value from the set. Returns true if the set contained the specified element.
        :type val: int
        :rtype: bool
        """
        if val not in self.dict:
            return False
        index = self.dict.pop(val)
        if index == len(self.memo) - 1:
            self.memo.pop()
        else:
            self.memo[index] = self.memo.pop()
            self.dict[self.memo[index]] = index
        return True
        

    def getRandom(self):
        """
        Get a random element from the set.
        :rtype: int
        """
        return random.choice(self.memo)
        #index = random.randint(0, len(self.memo)-1)
        #return self.memo[index]


if __name__ == '__main__':
    start_time = datetime.datetime.now()

    randomSet = RandomizedSet()
    print randomSet.remove(0) #false
    print randomSet.remove(0) #false
    print randomSet.insert(0) #true
    print randomSet.getRandom()
    print randomSet.remove(0) #true
    print randomSet.insert(0) #true

    randomSet = RandomizedSet()
    # Inserts 1 to the set. Returns true as 1 was inserted successfully.
    print randomSet.insert(1) #true
    # Returns false as 2 does not exist in the set.
    print randomSet.remove(2) #false
    # Inserts 2 to the set, returns true. Set now contains [1,2].
    print randomSet.insert(2) #true
    # getRandom should return either 1 or 2 randomly.
    print randomSet.getRandom()
    # Removes 1 from the set, returns true. Set now contains [2].
    print randomSet.remove(1) #true
    # 2 was already in the set, so return false.
    print randomSet.insert(2) #false
    # Since 2 is the only number in the set, getRandom always return 2.
    print randomSet.getRandom()
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
