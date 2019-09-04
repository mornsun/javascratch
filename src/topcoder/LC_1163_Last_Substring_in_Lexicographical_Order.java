package topcoder;


/**
 * Given a string s, return the last substring of s in lexicographical order.
 *
 * Example 1:
 *
 * Input: "abab"
 * Output: "bab"
 * Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
 * Example 2:
 *
 * Input: "leetcode"
 * Output: "tcode"
 *
 * Note:
 *
 * 1 <= s.length <= 4 * 10^5
 * s contains only lowercase English letters.
 *
 * String, Suffix Array
 * Runtime: 49 ms, faster than 46.45% of Java online submissions for Last Substring in Lexicographical Order.
 * Memory Usage: 49 MB, less than 100.00% of Java online submissions for Last Substring in Lexicographical Order.
 */
class LC_1163_Last_Substring_in_Lexicographical_Order {
    class LinkNode {
        int val;
        LinkNode next;
        public LinkNode(int v) {
            val = v;
        }
    }

    public String lastSubstring(String s) {
        if (s==null || s.length()==0)
            return "";

        int n=s.length();
        char max = 0;
        for (int i=0; i<n; ++i) {
            char ch = s.charAt(i);
            if (ch > max) {
                max = ch;
            }
        }

        LinkNode dummy = new LinkNode(0);
        LinkNode curr = null;
        for (int i=n-1; i>=0; --i) {
            char ch = s.charAt(i);
            if (ch == max) {
                LinkNode prev = new LinkNode(i);
                prev.next = curr;
                curr = prev;
            }
        }
        dummy.next = curr;

        int ofs = 1;
        while (dummy.next.next != null) {
            curr = dummy.next;
            LinkNode prev = dummy;
            max = s.charAt(curr.val+ofs);
            while (curr != null) {
                int pos = curr.val+ofs;
                char ch = pos >= n ? 0 : s.charAt(pos);
                if (curr.next!=null) {
                    if (pos >= curr.next.val) {
                        curr.next = curr.next.next;
                    }
                }
                if (ch>max) {
                    max = ch;
                    dummy.next = curr;
                    prev = curr;
                } else if (ch<max) {
                    prev.next = curr.next;
                } else {
                    prev = curr;
                }
                curr = curr.next;
            }
            ofs++;
        }
        return s.substring(dummy.next.val);
    }
    
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

        LC_1163_Last_Substring_in_Lexicographical_Order solution = new LC_1163_Last_Substring_in_Lexicographical_Order();
        System.out.println(solution.lastSubstring("abab")); //"bab"
        System.out.println(solution.lastSubstring("leetcode")); //"tcode"
        System.out.println(solution.lastSubstring("jlidhumidloagrlvvxdqscegbeaybfhhyaeilzxdpyvvxixrjytdalknkospradmumysbkizziltzjwsxkteykblcvkfivzmtvnsyrfgvojhyzkouscymixrdfmehiwijntzqptqaxgalygtzvwxnsgcnygbjzeqmbactgmckvssvkentpxcnznrbbnkttnzpvlzfmdvvsozaiycumzlizbfxvyucyagclrifczcvzvrkiqiajindjjyxgxflnjcgckruujsbppxtwgwvrrxgniqplynvboqyvrsxnmbjhgoybqophbxmjhhrznezstujjuucvrrvofktxldxfaioyijoayggmvjmgzjflzxmkwxmxnyizampdcfntdfkxxprgfxjduiwrmgdfuprpljgnbzbedqbzhqsbmohbhlszvdzcgbimfurmkwqaignxbeevevonmdgupugcjxvqglqkwqzrlqequliwmfrvidtpprodcbhgkt")); //"zziltzjwsxkteykblcvkfivzmtvnsyrfgvojhyzkouscymixrdfmehiwijntzqptqaxgalygtzvwxnsgcnygbjzeqmbactgmckvssvkentpxcnznrbbnkttnzpvlzfmdvvsozaiycumzlizbfxvyucyagclrifczcvzvrkiqiajindjjyxgxflnjcgckruujsbppxtwgwvrrxgniqplynvboqyvrsxnmbjhgoybqophbxmjhhrznezstujjuucvrrvofktxldxfaioyijoayggmvjmgzjflzxmkwxmxnyizampdcfntdfkxxprgfxjduiwrmgdfuprpljgnbzbedqbzhqsbmohbhlszvdzcgbimfurmkwqaignxbeevevonmdgupugcjxvqglqkwqzrlqequliwmfrvidtpprodcbhgkt"

        System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}
}