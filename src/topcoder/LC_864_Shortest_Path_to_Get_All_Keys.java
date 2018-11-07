/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

 We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

 For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

 Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.

 Example 1:

 Input: ["@.a.#","###.#","b.A.B"]
 Output: 8
 Example 2:

 Input: ["@..aA","..B#.","....b"]
 Output: 6

 Note:

 1 <= grid.length <= 30
 1 <= grid[0].length <= 30
 grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
 The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.

 Related Topic
 Heap, Breadth-first Search

 * @author Chauncey
 * beat 18.35%
 */
public class LC_864_Shortest_Path_to_Get_All_Keys
{
    public int shortestPathAllKeys(String[] grid) {
        if (grid == null || grid.length == 0 || grid[0].length() == 0){
            return -1;
        }
        int height = grid.length;
        int width = grid[0].length();
        int[][] keyPos = new int[6][]; //abcdef
        int keys = 0;

        int startY=height, startX=width;
        for (int y=0; y<height; ++y) {
            for (int x=0; x<width; ++x) {
                char label = grid[y].charAt(x);
                if (label == '@') {
                    startY = y;
                    startX = x;
                }
                if (label >= 'a' && label <='f') {
                    int idx = label-'a';
                    keyPos[idx] = new int[]{y, x};
                    keys |= 1 << idx;
                }
            }
        }
        if (keys == 0) { // no any keys
            return 0;
        }
        if (startY==height || startX==width) {
            return -1;
        }
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> (a.dist-b.dist)*1000+(b.cnt-a.cnt));
        heap.add(new Node(0, keys, startY, startX));

        /*for (String s : grid) {
            System.out.println(s);
        }*/
        while (!heap.isEmpty()) {
            Node curr = heap.poll();

            //check if get all keys at first
            if (curr.keys == 0) {
                return curr.dist; // get all keys and return distance
            }
            // search next keys
            //System.out.println(curr.dist+" "+curr.y+":"+curr.x + " " + getKeyCode(curr.keys, keyPos));
            for (int i=0; i<keyPos.length && keyPos[i] != null; ++i) {
                if ((curr.keys & (1<<i)) == 0) //already get
                    continue;
                Node next = getRoute(grid, curr.keys, curr.y, curr.x, keyPos[i][0], keyPos[i][1]);

                if (next==null)
                    continue;
                next.dist += curr.dist;
                //System.out.println(next.dist+" "+next.y+":"+next.x + " " + getKeyCode(next.keys, keyPos));
                heap.offer(next);
                //return -1;
            }
        }

        return -1;
    }

    public class Node {
        public int dist;
        public final int keys;
        public final int cnt;
        public final int y;
        public final int x;
        public Node(int d, int k, int Y, int X){dist = d; keys = k; cnt = popCount(k); y = Y; x = X;}
    }

    private static final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int popCount(int x)
    {
        int cnt = 0;
        for (int popcount=x; popcount!=0; ++cnt) {
            popcount &= (popcount-1);
        }
        return cnt;
    }

    private Node getRoute(String[] grid, int keys, int Y, int X, int endY, int endX)
    {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b)->((a[0]-b[0])*1000+(b[1]-a[1])));
        boolean[][] visited = new boolean[grid.length][grid[0].length()];

        heap.offer(new int[]{Math.abs(endY-Y)+Math.abs(endX-X), popCount(keys), Y, X, keys, 0});
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            Y = curr[2]; //curr.Y
            X = curr[3]; //curr.X
            int dist = curr[5]; //curr.key
            //System.out.println(dist+" "+endY+"="+Y+":"+endX+"="+X + " " + getKeyCode(curr[4], 4) + " " + curr[0] + ":from");
            if (Y==endY && X==endX) {
                return new Node(curr[0], curr[4], Y, X);
            }
            visited[Y][X] = true;

            for (int i=0; i<directions.length; ++i) {
                int y = Y+directions[i][0];
                if (y<0 || y>=visited.length)
                    continue;
                int x = X+directions[i][1];
                if (x<0 || x>=visited[0].length)
                    continue;
                if (visited[y][x])
                    continue;
                char label = grid[y].charAt(x);
                if (label == '#')
                    continue;
                int k = curr[4]; //curr.key
                if (label >= 'A' && label <= 'F' && (k & (1<<label-'A'))!=0) { //do not have the key
                    continue;
                }
                //System.out.println(y+":"+x);
                if (label >= 'a' && label <= 'f') {
                    k &= ~(1<<label-'a');
                }
                int[] next = new int[]{dist+1+Math.abs(endY-y)+Math.abs(endX-x), popCount(k), y, x, k, dist+1};
                heap.offer(next);
                //System.out.println(next[5]+" "+endY+"="+next[2]+":"+endX+"="+next[3] + " " + getKeyCode(next[4], 4) + " " + next[0] + ":to");
            }
        }

        return null;
    }

    private String getKeyCode(int key, int[][] keyPos) {
        StringBuilder s = new StringBuilder();
        for (char ch = 'a'; ch <= 'f' && keyPos[ch-'a'] != null; ++ch) {
            if (((1<<ch-'a') & key) == 0) {
                s.append(ch);
            }
        }
        return s.toString();
    }

    private String getKeyCode(int key, int num) {
        StringBuilder s = new StringBuilder();
        for (char ch = 'a'; ch <= 'f' && ch-'a' < num; ++ch) {
            if (((1<<ch-'a') & key) == 0) {
                s.append(ch);
            }
        }
        return s.toString();
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_864_Shortest_Path_to_Get_All_Keys solution = new LC_864_Shortest_Path_to_Get_All_Keys();
		System.out.println(solution.shortestPathAllKeys(new String[]{"@.a.#","###.#","b.A.B"})); //8
        System.out.println(solution.shortestPathAllKeys(new String[]{"@..aA","..B#.","....b"})); //6
        System.out.println(solution.shortestPathAllKeys(new String[]{".@aA"})); //1
        System.out.println(solution.shortestPathAllKeys(new String[]{"@Aa"})); //-1
        System.out.println(solution.shortestPathAllKeys(new String[]{"@...a",".###A","b.BCc"})); //10
        System.out.println(solution.shortestPathAllKeys(new String[]{".#.b.","A.#aB","#d...","@.cC.","D...#"})); //8
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
