/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * @author Chauncey
 *
 */
public class Test
{
    private int a;
    private int b;
    private int c;
    
    public Test(int i, int j, int k)
    {
        a = i;
        b = j;
        c = k;
    }
    
    
	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + a;
        result = prime * result + b;
        result = prime * result + c;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Test other = (Test) obj;
        if (a != other.a)
            return false;
        if (b != other.b)
            return false;
        if (c != other.c)
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "[" + a + "," + b + "," + c + "]";
    }


    /**
	 * 
	 */
	public static Test solve(int a[], int target)
	{
		// TODO Auto-generated constructor stub
        if (a.length<3) return null;
        HashSet<Test> result = new HashSet<Test>(a.length);
        Arrays.sort(a);
        a.hashCode();
        //System.out.println(intArr2String(a));
        int min = Integer.MAX_VALUE;
        Test minTest = null;
        
        int last = a.length-1;
        for (int i = 0; i<last-1; ++i) {
            //System.out.println("curr:"+a[i]);
            int j = i+1;
            int k = last;
            while (j<k) {
                int sum = a[i] + a[j] + a[k];
                int delta = sum - target;
                delta = delta<0 ? -delta : delta; //abs
                //System.out.println(a[i] + ":" + a[j] + ":" + a[k]);
                if (delta < min) {
                    min = delta;
                    minTest = new Test(a[i], a[j], a[k]);
                }
                if (sum == target) {
                    return minTest;
                } else if (sum < target) {
                    ++j;
                } else if (sum > target) {
                    --k;
                }
            }
        }

        return minTest;
	}

    public static void decode(String paramString, byte[] paramArrayOfByte)
    {
    try
    {
        // Create an array to hold the key
      byte[] encryptKey = paramString.getBytes();

// Create a DESede key spec from the key
      DESKeySpec spec = new DESKeySpec(encryptKey);

// Get the secret key factor for generating DESede keys
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

// Generate a DESede SecretKey object
      SecretKey theKey = keyFactory.generateSecret(spec);

// Create a DESede Cipher
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

// Create the initialization vector required for CBC mode
      IvParameterSpec ivParameters = new IvParameterSpec( "12345678".getBytes());

// Initialize the cipher and put it in decrypt mode
      cipher.init(Cipher.DECRYPT_MODE, theKey, ivParameters);

      byte[] plaintext = cipher.doFinal(paramArrayOfByte);

      String plaintextStr = new String(plaintext);

      System.out.println("The plaintext is:");
      System.out.println(plaintextStr);
    }
    catch (Exception exc)
    {
      exc.printStackTrace();
    }
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	    //int a[] = {0,2,1,0,1,4,2,0,1,3,1,4,1,0};
	    //int res = Test.trapRainingWater(a);
	    //System.out.println(res);
	    try {
	       byte[] arrayDecoded = Base64.decode("8Wr/3r+L27YCkxcb4rLcNGKcIGoLylXnX0+sSFAthetepiVfSvVwEA==");
	        decode("43248742", arrayDecoded);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    /*CLinkedList head = new CLinkedList(1);
        CLinkedList p = head;
	    for (int i=2; i<=5; ++i) {
	        CLinkedList link = new CLinkedList(i);
            p.next = link;
            p = link;
	    }
        p = head;
        while (p != null) {
            System.out.print(p.num+",");
            p = p.next;
        }
        System.out.println();
        head = reverse_linkedlist(head, 0, 5);
	    p = head;
	    int i=0;
	    while (p != null && i<10) {
	        System.out.print(p.num+",");
	        p = p.next;
	        ++i;
	    }
        System.out.println();*/
	}
	
	public static String intArr2String(int[] a)
	{
	    if (null == a) {
	        return null;
	    }
	    StringBuilder sb = new StringBuilder("[");
	    if (a.length>0) {
            sb.append(a[0]);
    	    for (int i=0; i<a.length; ++i) {
    	        sb.append("," + a[i]);
    	    }
	    }
	    sb.append(']');
	    return sb.toString();
	}

}
