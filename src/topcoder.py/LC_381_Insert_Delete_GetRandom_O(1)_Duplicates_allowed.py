#!/usr/bin/env python
#coding=utf8

'''
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();


Related Topics 
Array, Hash Table, Design 
Similar Questions 
Insert Delete GetRandom O(1)

@author: Chauncey
beat 46.39%
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
            self.dict[val].add(len(self.memo))
            self.memo.append(val)
            return False
        else:
            self.dict[val] = set([len(self.memo)])
            self.memo.append(val)
            return True
        

    def remove(self, val):
        if val not in self.dict:
            return False
        idx, last = self.dict[val].pop(), self.memo[-1]
        #print idx,last
        if idx == len(self.memo)-1:
            self.memo.pop()
        else:
            self.memo[idx] = last
            self.dict[last].remove(len(self.memo)-1)
            self.dict[last].add(idx)
            self.memo.pop()

        if len(self.dict[val]) == 0:
            self.dict.pop(val, 0)
        return True
        
        
    def getRandom(self):
        """
        Get a random element from the set.
        :rtype: int
        """
        #print self.memo
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
    print

    randomSet = RandomizedSet()
    # Inserts 1 to the set. Returns true as 1 was inserted successfully.
    print randomSet.insert(1) #true
    print randomSet.insert(1) #false
    print randomSet.insert(2) #true
    print randomSet.getRandom() #[1,1,2]
    print randomSet.remove(1) #true
    print randomSet.getRandom() #[1,2]
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
