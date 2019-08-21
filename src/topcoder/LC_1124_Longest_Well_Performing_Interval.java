
package topcoder;

import java.util.Arrays;
/*
Runtime: 4 ms, faster than 99.28% of Java online submissions for Longest Well-Performing Interval.
Memory Usage: 39.2 MB, less than 100.00% of Java online submissions for Longest Well-Performing Interval.

[6,6,9] //1
[9,9,6,0,6,6,9] //3
[6,9,9] //3
[6,9,6] //1
[8,12,7,6,10,10,9,11,12,6] //10
[11,2,4,14,2,15,7,10,1,16,9,0,2,8,4,14,6,12,2,8,6,4,14,13,7,16,14,2,3,2,8,3,12,3,3,9,14,1,5,3,12,0,15,5,0,2,3,16,7,2,1,1,4,9,0,11,9,16,15,7,0,5,6,4,12,1,1,2,13,8,3,9,12,9,3,11,4,14,7,5,16,0,11,8,8,14,1,5,0,6,5,8,10,15,9,14,16,11,1,13] //29
[6,6,6] //0
*/

class LC_1124_Longest_Well_Performing_Interval {
    public int longestWPI(int[] hours) {
        
if (hours==null || hours.length==0)
  return 0;
int cnt = 0, n = hours.length, max = 0;
int[] seen = new int[n+2];
Arrays.fill(seen, -1);
for (int i=0; i<n; ++i) {
  cnt += hours[i]>8 ? 1 : -1;
  if (cnt>0) {
    max = i+1;
  } else {
    if (seen[-cnt] == -1)
      seen[-cnt] = i;
    if (seen[-cnt+1] != -1)
      max = Math.max(max, i-seen[-cnt+1]);
  }
}
return max;

    }
}