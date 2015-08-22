package topcoder;

import java.util.*;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
Credits:
Special thanks to @stellari for adding this problem, creating these two awesome images and all test cases.

Hide Tags Divide and Conquer Heap

 * @author Chauncey
 *
 */
public class TheSkylineProblem
{
	private Comparator<int[]> maxComp =  new Comparator<int[]>(){  
        public int compare(int[] o1, int[] o2) {
            if(o1[0] < o2[0]) {
                return 1;
            } else if(o1[0] > o2[0]) {  
                return -1;
            } else {
            	if (o1[1] < o2[1]) {
            		return 1;
            	} else if (o1[1] > o2[1]) {
            		return -1;
            	}
                return 0;
            }
        }
    };
    public List<int[]> getSkyline(int[][] buildings) {
    	LinkedList<int[]> res = new LinkedList<int[]>();
        if (buildings == null || buildings.length == 0) return res;
        PriorityQueue<int[]> q_height =  new PriorityQueue<int[]>((buildings.length>>1)+1, maxComp);
        int[] prev_height = null;
        //L,R,H
        for (int i=0; i<buildings.length; ) {//{0,2,3},{2,5,3}
        	int[] building = buildings[i];
        	int[] cur_height = q_height.peek(); //H,R
        	//System.out.println("cur:"+building[0]+":"+(cur_height==null?"null":cur_height[1]+":"+cur_height[0]));
        	//System.out.println("pre:"+building[0]+":"+(prev_height==null?"null":prev_height[1]+":"+prev_height[0]));
        	if (cur_height == null) {
        		if (!res.isEmpty() && building[0] == res.getLast()[0]) {
        			res.removeLast();
        		}
    			res.add(new int[]{building[0], building[2]});
        		//System.out.println(building[0] +":a:"+ building[2]);
        		q_height.add(new int[]{building[2], building[1]});
    			prev_height = null;
        		++i;
        	} else if (building[0] > cur_height[1]) { //L>R', poll height heap
        		if (prev_height == null) {
        			prev_height = cur_height;
        		}
        		q_height.poll();
        		cur_height = q_height.peek();
        		if (cur_height == null) {
        			res.add(new int[]{prev_height[1], 0});
            		//System.out.println(building[0] +":c1:" + prev_height[1] + ":0");
        			prev_height = null;
        		} else if (cur_height[1] > prev_height[1]) { //R'>R''
        			res.add(new int[]{prev_height[1], cur_height[0]});
            		//System.out.println(building[0] +":c2:"+ prev_height[1]+":"+ cur_height[0]+":"+cur_height[1]);
            		prev_height = cur_height;
        		}
        	} else if (building[1] >= cur_height[1]) { // R>=R'
        		if (building[2] > cur_height[0]) { // H>H'
            		q_height.poll();
            		prev_height = new int[]{building[2], building[1]};
            		//System.out.println("null1:"+building[1] +":"+ cur_height[1]);
        		} else { //H<=H'
        			q_height.add(new int[]{building[2], building[1]});
            		//System.out.println("null2:"+building[1] +":"+ cur_height[1]);
            		prev_height = null;
        			++i;
        		}
        	} else { //R<R'
        		if (building[2] > cur_height[0]) { // H>H'
        			q_height.add(new int[]{building[2], building[1]});
        			res.add(new int[]{building[0], building[2]});
            		//System.out.println(building[0] +":b:"+ building[2] +":"+ cur_height[0]);
        			prev_height = null;
        		}
    			++i;
        	}
        }
        while (!q_height.isEmpty()) {
        	int[] cur_height = q_height.peek(); //H,R
    		if (prev_height == null) {
    			prev_height = cur_height;
    		}
    		q_height.poll();
    		cur_height = q_height.peek();
    		if (cur_height == null) {
    			res.add(new int[]{prev_height[1], 0});
            	//System.out.println(prev_height[1] +":d:"+ 0);
    			prev_height = null;
    		} else if (cur_height[1] > prev_height[1]) { //R'>R''
    			//if (cur_height[0] != prev_height[0])
            	//System.out.println(cur_height[1] +":d:"+ prev_height[1]);
    			res.add(new int[]{prev_height[1], cur_height[0]});
        		prev_height = cur_height;
    		}
        }
        return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		TheSkylineProblem solution = new TheSkylineProblem();
		/*int[][] nums = new int[][]{{12201,180636,768079},{17026,833587,302899},{18677,413984,15176},{27370,135545,463369},
			{42943,768584,703338},{51976,936558,66041},{54606,127067,669012},{91091,258297,345480},{120084,551243,591722},
			{125332,482162,229054},{132267,178740,267569},{138894,237225,932827},{140440,580487,826146},{155313,225996,145444},
			{180691,915808,647004},{192902,938643,883952},{203216,332302,741361},{207950,456999,515711},{216187,667027,135252},
			{261232,981655,39884},{285814,905808,834322},{298641,425210,117058},{304154,968625,523103},{322773,930787,873059},
			{327500,676571,38708},{338631,494968,692391},{338997,473330,476269},{369949,516273,169265},{410278,954566,63172},
			{483053,574785,32225},{490123,712845,242431},{532359,861610,112707},{542839,818345,404991},{557965,724092,42073},
			{567055,714994,926566},{567082,630925,935263},{617444,764701,810427},{637484,931752,188344},{649558,938988,587319},
			{692137,713637,903511},{694808,974482,967343},{697940,699208,67540},{745045,978819,317587},{760350,846025,696863},
			{761974,869856,640266},{809879,910736,632624},{884041,994482,828751},{902556,964455,713111},{910850,938456,335481}};*/
		//int[][] nums = new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
		//int[][] nums = new int[][]{{1,2,1},{1,2,2},{1,2,3}};
		int[][] nums = new int[][]{{0,3,3},{1,5,3},{2,4,3},{3,7,3}};
		List<int[]> list = solution.getSkyline(nums);
		for (int[] item : list) {
			System.out.println(item[0]+","+item[1]);
		}
		
	}

}
