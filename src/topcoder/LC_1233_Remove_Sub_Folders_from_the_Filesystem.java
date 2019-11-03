/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 	Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.
 *
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 *
 * Example 1:
 *
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 * Example 2:
 *
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * Explanation: Folders "/a/b/c" and "/a/b/d/" will be removed because they are subfolders of "/a".
 * Example 3:
 *
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 *
 * Constraints:
 *
 * 1 <= folder.length <= 4 * 10^4
 * 2 <= folder[i].length <= 100
 * folder[i] contains only lowercase letters and '/'
 * folder[i] always starts with character '/'
 * Each folder name is unique.
 *
 * Array, String
 *
 * @author Chauncey
 * Runtime: 39 ms, faster than 94.21% of Java online submissions for Remove Sub-Folders from the Filesystem.
 * Memory Usage: 53.7 MB, less than 100.00% of Java online submissions for Remove Sub-Folders from the Filesystem.
 */
public class LC_1233_Remove_Sub_Folders_from_the_Filesystem
{
	public List<String> removeSubfolders(String[] folder) {
		ArrayList<String> res = new ArrayList<>();
		if (folder==null || folder.length==0) return res;
		Arrays.sort(folder);
		res.add(folder[0]);
		String temp = folder[0] + '/';
		for (int i=1; i<folder.length; ++i) {
			String f = folder[i];
			if (f.startsWith(temp)) continue;
			res.add(f);
			temp = f + '/';
		}

		return res;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1233_Remove_Sub_Folders_from_the_Filesystem solution = new LC_1233_Remove_Sub_Folders_from_the_Filesystem();
        System.out.println(solution.removeSubfolders(new String[]{"/a","/a/b","/c/d","/c/d/e","/c/f"})); //["/a","/c/d","/c/f"]
		System.out.println(solution.removeSubfolders(new String[]{"/a","/a/b/c","/a/b/d"})); //["/a"]
		System.out.println(solution.removeSubfolders(new String[]{"/a/b/c","/a/b/ca","/a/b/d"})); //["/a/b/c","/a/b/ca","/a/b/d"]
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
