package topcoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A transaction is possibly invalid if:
 *
 * the amount exceeds $1000, or;
 * if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
 * Each transaction string transactions[i] consists of comma separated values representing the name, time (in minutes), amount, and city of the transaction.
 *
 * Given a list of transactions, return a list of transactions that are possibly invalid.  You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
 * Output: ["alice,20,800,mtv","alice,50,100,beijing"]
 * Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
 *
 * Example 2:
 *
 * Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
 * Output: ["alice,50,1200,mtv"]
 *
 * Example 3:
 *
 * Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
 * Output: ["bob,50,1200,mtv"]
 *
 * Constraints:
 *
 * transactions.length <= 1000
 * Each transactions[i] takes the form "{name},{time},{amount},{city}"
 * Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
 * Each {time} consist of digits, and represent an integer between 0 and 1000.
 * Each {amount} consist of digits, and represent an integer between 0 and 2000.
 *
 * Array, String
 * Runtime: 12 ms, faster than 97.22% of Java online submissions for Invalid Transactions.
 * Memory Usage: 39.6 MB, less than 100.00% of Java online submissions for Invalid Transactions.
 */
class LC_1169_Invalid_Transactions {
    public List<String> invalidTransactions(String[] transactions) {

        List<String> res = new ArrayList<>();
        if (transactions == null || transactions.length == 0)
            return res;

        int n = transactions.length;
        HashMap<String, ArrayList<int[]>> transMap = new HashMap<>();
        int[] moneys = new int[n];
        String[] cities = new String[n];
        boolean[] invalid = new boolean[n];
        for (int i = 0; i < n; ++i) {
            String[] ss = transactions[i].split(",");
            int t = Integer.parseInt(ss[1]);
            int money = Integer.parseInt(ss[2]);
            String city = ss[3];
            moneys[i] = money;
            cities[i] = city;
            if (money > 1000)
                invalid[i] = true;
            ArrayList<int[]> trans = transMap.get(ss[0]);
            if (trans == null) {
                trans = new ArrayList<int[]>();
                trans.add(new int[]{t, i});
                transMap.put(ss[0], trans);
            } else {
                int lo = 0;
                int hi = trans.size() - 1;
                while (lo < hi) {
                    int m = lo + (hi - lo >> 1);
                    if (t <= trans.get(m)[0])
                        hi = m;
                    else
                        lo = m + 1;
                }
                if (trans.get(lo)[0]<t) lo++;
                trans.add(lo, new int[]{t, i});
                boolean inv = false;
                for (int j = lo - 1; j >= 0; --j) {
                    int[] item = trans.get(j);
                    int t0 = item[0];
                    int k = item[1];
                    if (t - t0 <= 60) {
                        //System.out.println(t+" " + t0);
                        if (!cities[k].equals(city)) {
                            invalid[k] = true;
                            inv = true;
                        }
                    } else
                        break;
                }
                for (int j = lo + 1; j < trans.size(); ++j) {
                    int[] item = trans.get(j);
                    int t0 = item[0];
                    int k = item[1];
                    if (t0 - t <= 60) {
                        //System.out.println(t+":"+lo+":" + t0);
                        if (!cities[k].equals(city)) {
                            invalid[k] = true;
                            inv = true;
                        }
                    } else
                        break;
                }
                if (inv)
                    invalid[i] = true;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (invalid[i])
                res.add(transactions[i]);
        }
        return res;
    }
    
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

        LC_1169_Invalid_Transactions solution = new LC_1169_Invalid_Transactions();
        System.out.println(solution.invalidTransactions(new String[]{"alice,20,800,mtv","alice,50,100,beijing"})); //["alice,20,800,mtv","alice,50,100,beijing"]
        System.out.println(solution.invalidTransactions(new String[]{"alice,20,800,mtv","alice,50,1200,mtv"})); //["alice,50,1200,mtv"]
        System.out.println(solution.invalidTransactions(new String[]{"alice,20,800,mtv","bob,50,1200,mtv"})); //["bob,50,1200,mtv"]
        System.out.println(solution.invalidTransactions(new String[]{"alex,676,260,bangkok","bob,656,1366,bangkok","alex,393,616,bangkok","bob,820,990,amsterdam","alex,596,1390,amsterdam"})); //["bob,656,1366,bangkok","alex,596,1390,amsterdam"]
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}
}