/**
 * 
 */
package test;

/**
 * @author Chauncey
 *
 */
class HelloA {
public HelloA() {
System.out.println("HelloA");
}

{ System.out.println("Class A"); }

static { System.out.println("Static A"); }
}
 
public class HelloB extends HelloA {
public HelloB() {
System.out.println("HelloB");
}

{ System.out.println("Class B"); }

static { System.out.println("Static B"); }
   
public static void main(String[] args) {
new HelloB();
}
}

/*public class Test
{
    public static void main(String [] args) {

    try {
        badMethod();
        System.out.println("1");
    } catch (RuntimeException e) {
        System.out.println("2");
    } finally {
        System.out.println("3");
}
System.out.println("4");
}
public static void badMethod() {
    throw new NullPointerException();
}

}
*/