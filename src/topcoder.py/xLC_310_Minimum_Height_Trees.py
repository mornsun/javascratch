#!/usr/bin/env python
#coding=utf8

'''
For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Hint:

How many MHTs can a graph have at most?
Note:

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Subscribe to see which companies asked this question

Hide Tags Breadth-first Search Graph
Hide Similar Problems (M) Course Schedule (M) Course Schedule II

Beat 63.34%
@author: Chauncey
'''
class Solution(object):
    def findMinHeightTrees(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: List[int]
        """
        if n <= 1 or edges == None:
            return [0]

        graph = [set() for _ in xrange(n)]
        for i, j in edges:
            graph[i].add(j)
            graph[j].add(i)

        leaves = [i for i in xrange(n) if len(graph[i]) == 1]

        while n > 2:
            new_leaves = []
            n -= len(leaves)
            for i in leaves:
                j = graph[i].pop()
                graph[j].remove(i)
                #print '--', j, i, len(graph[j])
                if len(graph[j]) == 1:
                    new_leaves.append(j)
            leaves = new_leaves

        #print '-', n
        return leaves
        

        
if __name__ == '__main__':
    solution = Solution()
    print solution.findMinHeightTrees(n = 4, edges = [[1, 0], [1, 2], [1, 3]]) #[1]
    print solution.findMinHeightTrees(n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]) #[3, 4]
    #transactions = [buy, sell, cooldown, buy, sell]