'''

@author: Chauncey
'''

# Definition for singly-linked list with a random pointer.
class RandomListNode:
    def __init__(self, x):
        self.label = x
        self.next = None
        self.random = None

class Solution:
    # @param head, a RandomListNode
    # @return a RandomListNode
    def copyRandomList(self, head):
        if head is None: return None
        cur = head
        while cur is not None:
            node = RandomListNode(cur.label)
            node.next = cur.next
            cur.next = node
            cur = node.next
        cur = head
        while cur is not None:
            if cur.random is not None:
                cur.next.random = cur.random.next
            cur = cur.next.next
        #separate two links
        head1 = head.next
        cur = head
        while cur is not None:
            node = cur.next
            cur.next = node.next
            cur = cur.next
            node.next = None if cur is None else cur.next
        return head1
                
                    
if __name__ == '__main__':
    solution = Solution();
    node1 = RandomListNode(-1)
    node1.random = node1
    print node1
    print solution.copyRandomList(node1)
    
    
    