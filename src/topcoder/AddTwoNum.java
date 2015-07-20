/**
 * 
 */
package topcoder;

/**
 * @author cherry
 *
 */
public class AddTwoNum
{

	 public static class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
	 }
	 
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	ListNode res = null;
    	ListNode cur = null;
    	int carrybit = 0;
    	while (l1!=null || l2!= null) {
    		int num1 = 0;
    		if (l1 != null) {
    			num1 = l1.val;
    			l1 = l1.next;
    		}
    		int num2 = 0;
    		if (l2 != null) {
    			num2 = l2.val;
    			l2 = l2.next;
    		}
    		int sum = num1 + num2 + carrybit;
    		//System.out.println(num1 + " "+num2+" "+sum);
    		if (sum > 9) {
    			sum -= 10;
    			carrybit = 1;
    		} else {
    			carrybit = 0;
    		}
    		if (res == null) {
    			cur = new ListNode(sum);
    			res = cur;
    		} else {
    			cur.next = new ListNode(sum);
    			cur = cur.next;
    		}
    	}
    	if (carrybit != 0)
    		cur.next = new ListNode(1);
    	return res;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);

		ListNode l = addTwoNumbers(l1,l2);
		while (l != null) {
			System.out.print(l.val + "->");
			l = l.next;
		}
	}

}
