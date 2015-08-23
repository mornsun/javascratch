'''
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
Hide Tags Tree Depth-first Search
Hide Similar Problems (H) Populating Next Right Pointers in Each Node II (M) Binary Tree Right Side View

@author: Chauncey
'''
# Definition for binary tree with next pointer.
class TreeLinkNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
        self.next = None

class Solution:
    # @param root, a tree link node
    # @return nothing
    def connect(self, root):
        if root is None: return
        cur = root
        while cur is not None:
            #print cur.val
            if cur.left is not None:
                parent = cur
                cur = cur.left
                prev = cur
            elif cur.right is not None:
                parent = cur
                cur = cur.right
                prev = cur
            else:
                cur = cur.next
                continue
            while parent is not None:
                #print parent.val, prev.val
                if prev is parent.left:
                    if parent.right is not None:
                        prev.next = parent.right
                        prev = prev.next
                        continue
                elif prev is not parent.right:
                    if parent.left is not None:
                        prev.next = parent.left
                        prev = prev.next
                        continue
                    elif parent.right is not None:
                        prev.next = parent.right
                        prev = prev.next
                        continue
                parent = parent.next
                    
if __name__ == '__main__':
    solution = Solution();
    root = TreeLinkNode(5)
    root.left = TreeLinkNode(2)
    root.left.left = TreeLinkNode(1)
    root.left.right = TreeLinkNode(4)
    root.right = TreeLinkNode(7)
    #root.right.left = TreeLinkNode(0)
    root.right.right = TreeLinkNode(3)
    print solution.connect(root)
    print root.next
    print root.left.next, root.right.next
    print root.left.left.next, root.left.right.next, root.right.left.next, root.right.right.next
    
    
    