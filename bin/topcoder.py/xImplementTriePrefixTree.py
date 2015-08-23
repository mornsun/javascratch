'''
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.

Hide Tags Data Structure Trie
Hide Similar Problems (M) Add and Search Word - Data structure design

@author: Chauncey
'''


class TrieNode:
    # Initialize your data structure here.
    def __init__(self, ch):
        self.ch = ch
        self.next = {}
        self.end = False
        
class Trie:

    def __init__(self):
        self.root = TrieNode('#')

    # @param {string} word
    # @return {void}
    # Inserts a word into the trie.
    def insert(self, word):
        if word is None or len(word)==0: return
        node = self.root
        for ch in word:
            next_node = node.next.get(ch)
            if next_node is None:
                next_node = TrieNode(ch)
                node.next[ch] = next_node
            node = next_node
        node.end = True
    
    # @param {string} word
    # @return {boolean}
    # Returns if the word is in the trie.
    def search(self, word):
        if word is None or len(word)==0: return False
        node = self.root
        for ch in word:
            next_node = node.next.get(ch)
            if next_node is None:
                return False
            node = next_node
        return node.end is True
        
    # @param {string} prefix
    # @return {boolean}
    # Returns if there is any word in the trie
    # that starts with the given prefix.
    def startsWith(self, prefix):
        if prefix is None or len(prefix)==0: return False
        node = self.root
        for ch in prefix:
            next_node = node.next.get(ch)
            if next_node is None:
                return False
            node = next_node
        return True
        
    def output(self):
        node = self.root
        queue = deque()
        queue.append(node)
        while len(queue) != 0:
            node = queue.popleft()
            print '['+node.ch+':',
            for k,v in node.next.items():
                queue.append(v)
                print k+',',
            if node.end: print '$',
            print ']'
                    
if __name__ == '__main__':
    trie = Trie()
    trie.insert("somestring")
    trie.insert("key")
    print trie.search("key")
    print trie.search("some")
    print trie.startsWith("some")
    
    
    