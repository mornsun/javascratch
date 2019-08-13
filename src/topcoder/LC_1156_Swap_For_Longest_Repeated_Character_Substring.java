package topcoder;

import java.util.*;

//Runtime: 4 ms, faster than 81.67% of Java online submissions for Swap For Longest Repeated Character Substring.
//Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Swap For Longest Repeated Character Substring.

class LC_1156_Swap_For_Longest_Repeated_Character_Substring {
    public int maxRepOpt1(String text) {

if (text==null || text.length()==0)
  return 0;

int n = text.length();

int lo = 0;
int ex_id = -1;

LinkedList<int[]> maxlist = new LinkedList<int[]>();
maxlist.addLast(new int[]{1, 0});

for (int i=1; i<n; ++i) {
  char c = text.charAt(i);
  if (c != text.charAt(lo)) {
    if (ex_id >= 0) {
      int sz = i-1-lo;
      int[] max = maxlist.getLast();
      if (sz > max[0]) {
        maxlist.clear();
        maxlist.add(new int[]{sz, lo});
      }
      if (i-ex_id==1) {
        if (c==text.charAt(ex_id)) {
          lo = ex_id;
          ex_id = -1;
        } else {
          lo = ex_id;
          ex_id = i;
        }
      } else if (i-ex_id==2) {
        if (c==text.charAt(ex_id)) {
          lo = ex_id;
          ex_id++;
        } else {
          lo = i-1;
          ex_id = i;
        }
      } else {
        lo = ex_id+1;
        ex_id = i;
      }
    } else {
      ex_id = i;
    }
  }
}
if (n-1 != lo && text.charAt(n-1) == text.charAt(lo)) {
  if (ex_id >= 0) {
    int sz = n-1-lo;
    int[] max = maxlist.getLast();
    if (sz>max[0]) {
      maxlist.clear();
      maxlist.add(new int[]{sz, lo});
    } else if (sz==max[0]) {
      maxlist.add(new int[]{sz, lo});
    }
  } else {
    int sz = n-lo;
    int[] max = maxlist.getLast();
    if (sz>max[0]) {
      maxlist.clear();
      maxlist.add(new int[]{sz, lo});
    } else if (sz==max[0]) {
      maxlist.add(new int[]{sz, lo});
    }
  }
}

for (int[] max : maxlist) {
  char ch = text.charAt(max[1]);
  for (int i=0; i<max[1]; ++i) {
    if (ch == text.charAt(i)) {
      return max[0]+1;
    }
  }
  for (int i=max[1]+max[0]+1; i<n; ++i) {
    if (ch == text.charAt(i)) {
      return max[0]+1;
    }
  }
}

return maxlist.getLast()[0];
        
    }
}
