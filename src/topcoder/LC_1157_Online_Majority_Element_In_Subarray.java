//Runtime: 677 ms, faster than 69.28% of Java online submissions for Online Majority Element In Subarray.
//Memory Usage: 66.4 MB, less than 100.00% of Java online submissions for Online Majority Element In Subarray.
class MajorityChecker {

    private HashMap<Integer, ArrayList<Integer>> _a2i = new HashMap<Integer, ArrayList<Integer>>();
    private int[] _a;
    
    public MajorityChecker(int[] arr) {

        if (arr == null)
          return;

        _a = arr;

        for (int i=0; i<arr.length; ++i) {
          ArrayList<Integer> li = _a2i.get(arr[i]);
          if (li == null) {
            li = new ArrayList<Integer>();
            _a2i.put(arr[i], li);
          }
          li.add(i);
        }

    }
    
    public int query(int left, int right, int threshold) {

        HashSet<Integer> numset = new HashSet<Integer>();
        for (int i=left; i<=left+(right-left>>1) && numset.size()<=20; ++i) {
          if (numset.contains(_a[i]))
            continue;
          ArrayList<Integer> li = _a2i.get(_a[i]);
          int l = bisect(li, left, 0);
          int r = bisect(li, right+1, l+1);
        //System.out.println(left+":"+right+":"+threshold+":"+_a[i]+":"+l+":"+r);
          if (r-l >= threshold)
            return _a[i];
          numset.add(_a[i]);
        }
        return -1;

    }

    private int bisect(ArrayList<Integer> li, int val, int lo) {

        int hi = li.size()-1;
        if (val>li.get(hi))
            return hi+1;
        while (lo<hi) {
          int m = lo + (hi-lo>>1);
          if (val > li.get(m)) {
            lo = m + 1;
          } else {
            hi = m;
          }
        }
        return lo;

    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */