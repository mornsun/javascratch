class Solution {
public int numRollsToTarget(int d, int f, int target)
{
int[] dp = new int[target+1];
for (int i=1; i<=f; ++i) {
  dp[i] = 1;
}
int lo = 1;
int hi = f;
for (int i=1; i<d; ++i) {
  for (int j=Math.min(hi, target); j>=lo; --j) {
    for (int k=Math.min(f, target-j); k>=1; --k) {
      dp[j+k] += dp[j] + 1;
    }
  }
  hi += f;
  lo++;
}
return dp[target];
}


public int numRollsToTarget1(int d, int f, int target)
{
    if (target<1 || target>f*d)
      return 0;
    if (d==1)
        return 1;

    int res = combination(target-1, d-1) - combination(target-1-f, d-1);
    if (res<0) return res + 1000000007;
    else return res;
}

private int combination(int sub, int supe)
{
    int[] bf = new int[supe];
    for (int i=sub; i>sub-supe; --i) {
      bf[sub-i] = i;
    }
    for (int i=supe; i>0; --i) {
      for (int j=0; j<supe; ++j) {
        if (bf[j]%i==0) bf[j] /= i;
      }
    }
    int res = 1;
    for (int i=0; i<supe; ++i) {
      res = (res * bf[i]) % 1000000007;
    }

    System.out.println(res);
    return res;
}
}

11/4=2...3
14/4=3...2

42/4=10...2
154/4=38...2

//1000000007
