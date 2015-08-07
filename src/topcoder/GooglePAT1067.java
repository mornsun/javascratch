/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class GooglePAT1067
{
	public static int give(int x, int[] a, boolean[] mark)
	{
		int r = 0;
		boolean have = false;
		for (; !mark[x]; ++r) {
			if (x == 0) {
				have = true;
			}
			mark[x] = true;
			x = a[x];
		}
		return have ? (r-1) : ((r<=1) ? 0 : (r+1));
	}
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] a = new int[]{10, 3, 5, 7, 2, 6, 4, 9, 0, 8, 1};
		boolean[] mark = new boolean[a.length];
		int answer = 0;
		for (int i=0; i<a.length; ++i) {
			answer += give(a[i], a, mark);
		}
		System.out.println(answer);
	}

	static int findNotOK(int[] arr,int begin,int end)   //从begin开始往后寻找未到位的数  
	{  
	    for(int i=begin;i<end;i++)  
	    {  
	        if(arr[i]!=i)return i;  
	    }  
	    return 0;  
	}  
	public static void main1(String[] args)
	{
		int[] arr = new int[]{10, 3, 5, 7, 2, 6, 4, 9, 0, 8, 1};
	    int tmp = 0;  
	    int count=0;  
	    int firstPos = 1;  
	    firstPos = findNotOK(arr,firstPos,arr.length);  
	  
	    while(firstPos!=0)     //还有未到位的数字  
	    {  
	        if(arr[0]==0)       //如果0到位了，则与未到位的firstPos交换  
	        {  
	            arr[0] = arr[firstPos];  
	            arr[firstPos] = 0;  
	            count++;  
	        }  
	  
	        while(arr[0]!=0)    //如果0不到位，则循环与自己所指向的值交换  
	        {  
	            tmp = arr[0];  
	            arr[0] = arr[tmp];  
	            arr[tmp] = tmp;  
	            count++;  
	        }  
	        firstPos = findNotOK(arr,firstPos,arr.length);       //此时0归位了，找到下一个未到位的数字  
	    }  
	    System.out.println(count);  
	  
	}  
}
