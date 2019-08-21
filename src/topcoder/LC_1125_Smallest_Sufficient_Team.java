/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * In a project, you have a list of required skills req_skills, and a list of people.  The i-th person people[i] contains a list of skills that person has.
 *
 * Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill.  We can represent these teams by the index of each person: for example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
 *
 * Return any sufficient team of the smallest possible size, represented by the index of each person.
 *
 * You may return the answer in any order.  It is guaranteed an answer exists.
 *
 * Example 1:
 *
 * Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
 * Output: [0,2]
 * Example 2:
 *
 * Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 * Output: [1,2]
 *
 * Constraints:
 *
 * 1 <= req_skills.length <= 16
 * 1 <= people.length <= 60
 * 1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
 * Elements of req_skills and people[i] are (respectively) distinct.
 * req_skills[i][j], people[i][j][k] are lowercase English letters.
 * Every skill in people[i] is a skill in req_skills.
 * It is guaranteed a sufficient team exists.
 *
 * Dynamic Programming, Bit Manipulation

 * @author Chauncey
 * Runtime: 74 ms, faster than 45.70% of Java online submissions for Smallest Sufficient Team.
 * Memory Usage: 44.1 MB, less than 100.00% of Java online submissions for Smallest Sufficient Team.
 */
public class LC_1125_Smallest_Sufficient_Team
{
	public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people)
	{
		if (req_skills==null || req_skills.length==0 || people==null || people.size()==0)
			return new int[0];

		int n = req_skills.length;
		HashMap<String, Integer> skills = new HashMap<>(n);
		int i = 0;
		for (; i<n; ++i) {
			skills.put(req_skills[i], 1<<i);
		}

		int m = people.size();
		int[] peoplebm = new int[m];
		HashMap<Integer, long[]> dp = new HashMap<>();
		i = 0;

		for (List<String> p : people) {
			for (String sk : p) {
				Integer bm = skills.get(sk);
				if (bm == null)
					continue;
				peoplebm[i] |= bm;
			}
			//dp.put(peoplebm[i], new int[]{1<<i, 1});
			++i;
		}

		int sz = m;
		for (i=0; i<m; ++i) {
			int pbm = peoplebm[i];
			HashMap<Integer, long[]> ndp = new HashMap<>();
			dp.put(pbm, new long[]{((long)1<<i), 1});
			for (Map.Entry<Integer, long[]> entry : dp.entrySet()) {
				int nbm = pbm | entry.getKey();
				long[] v = entry.getValue();
				if (this.BadItem(ndp, nbm, v[1]))
					continue;
				if (this.BadItem(dp, nbm, v[1]))
					continue;
				ndp.put(nbm, new long[]{v[0] | ((long)1<<i), v[1]+1});
			}
			for (Map.Entry<Integer, long[]> entry : ndp.entrySet()) {
				dp.put(entry.getKey(), entry.getValue());
				//System.out.println(entry.getKey() + "|" + entry.getValue()[0] + "|" + entry.getValue()[1]);
			}
			//System.out.println(i);
		}

		long[] min = dp.get((1<<n)-1);
		long min_who = min[0];
/*for (Map.Entry<Integer, int[]> entry : dp.entrySet()) {
  int[] v = entry.getValue();
  if (v[1] < min) {
    min = v[1];
    min_who = v[0];
  }
}
System.out.println(min[1]);
System.out.println(min_who);
System.out.println((1<<n)-1);*/
		int cnt = 0;
		long test = min_who;
		while (test != 0) {
			test &= test-1;
			cnt++;
		}
		//System.out.println(cnt+"|"+Long.toBinaryString(min_who));
		int[] res = new int[cnt];
		int idx = 0;
		for (i=0; i<m; ++i) {
			if ((min_who & ((long)1<<i)) != 0) {
				//System.out.println(i);
				res[idx++] = i;
			}
		}

		return res;
	}

	private boolean BadItem(HashMap<Integer, long[]> bp, int nbm, long ncnt) {
		long[] item = bp.get(nbm);
		if (item==null)
			return false;
		if (ncnt < item[1])
			return false;
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1125_Smallest_Sufficient_Team solution = new LC_1125_Smallest_Sufficient_Team();
		//System.out.println(solution.smallestSufficientTeam(new int[]{7,7,7,8,5,7,5,5,5,8,8}));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
		//["java","nodejs","reactjs"]
		//[["java"],["nodejs"],["nodejs","reactjs"]]
		//["mwobudvo","goczubcwnfze","yspbsez","pf","ey","hkq"]
		//[[],["mwobudvo"],["hkq"],["pf"],["pf"],["mwobudvo","pf"],[],["yspbsez"],[],["hkq"],[],[],["goczubcwnfze","pf","hkq"],["goczubcwnfze"],["hkq"],["mwobudvo"],[],["mwobudvo","pf"],["pf","ey"],["mwobudvo"],["hkq"],[],["pf"],["mwobudvo","yspbsez"],["mwobudvo","goczubcwnfze"],["goczubcwnfze","pf"],["goczubcwnfze"],["goczubcwnfze"],["mwobudvo"],["mwobudvo","goczubcwnfze"],[],["goczubcwnfze"],[],["goczubcwnfze"],["mwobudvo"],[],["hkq"],["yspbsez"],["mwobudvo"],["goczubcwnfze","ey"]]
	}

}
