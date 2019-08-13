package topcoder;

import java.util.*;

//Runtime: 0 ms, faster than 100.00% of Java online submissions for Decrease Elements To Make Array Zigzag.
//Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for Decrease Elements To Make Array Zigzag.

class LC_1144_Decrease_Elements_To_Make_Array_Zigzag {
    public int movesToMakeZigzag(int[] nums) {
        
if (nums==null || nums.length<2)
  return 0;

int res1 = 0;
for (int i=0; i<nums.length; i+=2) {
  int sub = 0;
  if (i+1<nums.length && nums[i] >= nums[i+1])
    sub = nums[i]-nums[i+1]+1;
  if (i>0 && nums[i]-sub >= nums[i-1])
    sub += nums[i]-sub-nums[i-1]+1;
  res1 += sub;
}

int res2 = 0;
for (int i=1; i<nums.length; i+=2) {
  int sub = 0;
  if (i+1<nums.length && nums[i] >= nums[i+1])
    sub = nums[i]-nums[i+1]+1;
  if (nums[i]-sub >= nums[i-1])
    sub += nums[i]-sub-nums[i-1]+1;
  res2 += sub;
}

return res1 < res2 ? res1 : res2;
    }
}