'''
Question:
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to determine if a number is strobogrammatic. The number is represented as a string.
For example, the numbers "69", "88", and "818" are all strobogrammatic.

Hints:
two pointers

@author: Chauncey
'''

class Solution:
    # @param {string} num
    # @return {boolean}
    def isStrobogrammatic(self, num):
        dic = {'9':'6', '6':'9', '1':'1', '8':'8', '0':'0'}
        
        l, r = 0, len(num) - 1
        while l <= r:
            if num[l] not in dic or dic[num[l]] != num[r]:
                return False
            l += 1
            r -= 1
        return True
    
    def detect(self, mistake, prev, cur):
        if prev is not None and prev.val > cur.val:
            if len(mistake) == 0:
                mistake.append(prev)
                mistake.append(cur)
            else:
                mistake[1] = cur
                    
if __name__ == '__main__':
    solution = Solution();
    root = TreeNode(5)
    root.left = TreeNode(2)
    root.left.left = TreeNode(1)
    root.left.left.left = TreeNode(0)
    root.left.left.right = TreeNode(3)
    root.left.right = TreeNode(4)
    root.right = TreeNode(7)
    print solution.recoverTree(root)
'''
Leetcode: Strobogrammatic Number II
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.
For example,
Given n = 2, return ["11","69","88","96"].

Understand the problem:
The problem is an extension of the last problem. It could be solved by using recursion. 
There are two things need to be careful:
In each recursion, it should recurse with n - 2 not n / 2
Secondly, noticed that the strobogrammatic number should not contain leading "0"s. 

Code (Java):
public class Solution {
    private List<String> result = new ArrayList<String>();
    private Map<String, String> hashMap = new HashMap<String, String>();
    
    public List<String> findStrobogrammatic(int n) {
        result.clear();
        hashMap.clear();
        fillHashMap(hashMap);
        
        findStrobogrammaticHelper(n);
        
        return result;
    }
    
    private void findStrobogrammaticHelper(int n) {
        if (n <= 0) {
            return;
        }
        List<String> currResult = new ArrayList<String>();
        int size = 0;
        if (n == 1) {
            if (!result.isEmpty()) {
                size = result.get(0).length();
                int mid = size / 2;
        
                for (String str : result) {
                    StringBuffer sb = new StringBuffer(str);
                    sb.insert(mid, '0');
                    currResult.add(sb.toString());
                    
                    sb.setCharAt(mid, '1');
                    currResult.add(sb.toString());
                    
                    sb.setCharAt(mid, '8');
                    currResult.add(sb.toString());
                }
                result.clear();
                result.addAll(new ArrayList(currResult));
            } else {
                result.add("0");
                result.add("1");
                result.add("8");
            }
        }
        
        if (n > 1) {
            if (result.isEmpty()) {
                Iterator it = hashMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    String elem = pair.getKey() + "" + pair.getValue();
                    if (!elem.equals("00")) {
                        result.add(elem);
                    }
                }
            } else {
                size = result.get(0).length();
                int mid = size / 2;
                for (String str : result) {
                    Iterator it = hashMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        StringBuffer sb = new StringBuffer(str);
                        String elem = pair.getKey() + "" + pair.getValue();
                        sb.insert(mid, elem);
                        currResult.add(sb.toString());
                    }
                }
                result.clear();
                result.addAll(new ArrayList(currResult));
            }
        }
        
        findStrobogrammaticHelper(n - 2);
    }
    
    private void fillHashMap(Map<String, String> hashMap) {
        hashMap.put("0", "0");
        hashMap.put("1", "1");
        hashMap.put("8", "8");
        hashMap.put("6", "9");
        hashMap.put("9", "6");
    }
}

LeetCode: Strobogrammatic Number III
AUG 9 2015

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,

Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:

Because the range might be a large number, the low and high numbers are represented as string.

public class Solution {
    private char[] validNumbers = new char[]{'0', '1', '6', '8', '9'};
    private char[] singleable = new char[]{'0', '1', '8'};

    public int strobogrammaticInRange(String low, String high) {
        assert low != null && high != null;

        int ll = low.length();
        int hl = high.length();
        int result = 0;
        
        if(ll > hl || (ll == hl&&low.compareTo(high) > 0)) {
            return 0;
        }

        List<String> list = findStrobogrammatic(ll);

        if (ll == hl) {
            for (String s : list) {
                if (s.compareTo(low) >= 0 && s.compareTo(high) <= 0) {
                    result++;
                }
                if (s.compareTo(high) > 0) {
                    break;
                }
            }
        } else {
            for (int i = list.size() - 1; i >= 0; i--) {
                String s = list.get(i);
                if (s.compareTo(low) >= 0) {
                    result++;
                }
                if (s.compareTo(low) < 0) {
                    break;
                }
            }
            list = findStrobogrammatic(hl);
            for (String s : list) {
                if (s.compareTo(high) <= 0) {
                    result++;
                }
                if (s.compareTo(high) > 0) {
                    break;
                }
            }

            for (int i = ll + 1; i < hl; i++) {
                result += findStrobogrammatic(i).size();
            }
        }
        return result;
    }

    public List<String> findStrobogrammatic(int n) {
        assert n > 0;
        List<String> result = new ArrayList<>();

        if (n == 1) {
            for (char c : singleable) {
                result.add(String.valueOf(c));
            }
            return result;
        }

        if (n % 2 == 0) {
            helper(n, new StringBuilder(), result);
        } else {
            helper(n - 1, new StringBuilder(), result);
            List<String> tmp = new ArrayList<>();
            for (String s : result) {
                for (char c : singleable) {
                    tmp.add(new StringBuilder(s).insert(s.length() / 2, c).toString());
                }
            }
            result = tmp;
        }
        return result;
    }

    private void helper(int n, StringBuilder sb, List<String> result) {
        if (sb.length() > n) return;

        if (sb.length() == n) {
            if (sb.length() > 0 && sb.charAt(0) != '0') {
                result.add(sb.toString());
            }
            return;
        }

        for (char c : validNumbers) {
            StringBuilder tmp = new StringBuilder(sb);
            String s = "" + c + findMatch(c);
            tmp.insert(tmp.length() / 2, s);
            helper(n, tmp, result);
        }
    }

    private char findMatch(char c) {
        switch (c) {
            case '1':
                return '1';
            case '6':
                return '9';
            case '9':
                return '6';
            case '8':
                return '8';
            case '0':
                return '0';
            default:
                return 0;
        }
    }
}'''
    