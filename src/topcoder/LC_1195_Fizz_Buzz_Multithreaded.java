/**
 * 
 */
package topcoder;

import java.util.function.IntConsumer;

/**
 * 	Write a program that outputs the string representation of numbers from 1 to n, however:
 *
 * If the number is divisible by 3, output "fizz".
 * If the number is divisible by 5, output "buzz".
 * If the number is divisible by both 3 and 5, output "fizzbuzz".
 * For example, for n = 15, we output: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
 *
 * Suppose you are given the following code:
 *
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }               // constructor
 *   public void fizz(printFizz) { ... }          // only output "fizz"
 *   public void buzz(printBuzz) { ... }          // only output "buzz"
 *   public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 *   public void number(printNumber) { ... }      // only output the numbers
 * }
 * Implement a multithreaded version of FizzBuzz with four threads. The same instance of FizzBuzz will be passed to four different threads:
 *
 * Thread A will call fizz() to check for divisibility of 3 and outputs fizz.
 * Thread B will call buzz() to check for divisibility of 5 and outputs buzz.
 * Thread C will call fizzbuzz() to check for divisibility of 3 and 5 and outputs fizzbuzz.
 * Thread D will call number() which should only output the numbers.
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Ugly Number III.
 * Memory Usage: 33.2 MB, less than 100.00% of Java online submissions for Ugly Number III.
 */
public class LC_1195_Fizz_Buzz_Multithreaded
{
	private int n;
	private int currentNumber = 1;

	public LC_1195_Fizz_Buzz_Multithreaded(int n) {
		this.n = n;
	}

	// printFizz.run() outputs "fizz".
	public void fizz(Runnable printFizz) throws InterruptedException {
		while (currentNumber <= n) {
			if (currentNumber % 3 != 0 || currentNumber % 5 == 0) {
				wait();
				continue;
			}
			printFizz.run();
			currentNumber += 1;
			notifyAll();
		}
	}

	// printBuzz.run() outputs "buzz".
	public void buzz(Runnable printBuzz) throws InterruptedException {
		while (currentNumber <= n) {
			if (currentNumber % 5 != 0 || currentNumber % 3 == 0) {
				wait();
				continue;
			}
			printBuzz.run();
			currentNumber += 1;
			notifyAll();
		}
	}

	// printFizzBuzz.run() outputs "fizzbuzz".
	public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
		while (currentNumber <= n) {
			if (currentNumber % 5 == 0 || currentNumber % 3 == 0) {
				wait();
				continue;
			}
			printFizzBuzz.run();
			currentNumber += 1;
			notifyAll();
		}
	}

	// printNumber.accept(x) outputs "x", where x is an integer.
	public void number(IntConsumer printNumber) throws InterruptedException {
		while (currentNumber <= n) {
			if (currentNumber % 5 != 0 || currentNumber % 3 == 0) {
				wait();
				continue;
			}
			printNumber.accept(currentNumber);
			currentNumber += 1;
			notifyAll();
		}
	}

		/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1195_Fizz_Buzz_Multithreaded solution = new LC_1195_Fizz_Buzz_Multithreaded(15);
		IntConsumer display = a -> System.out.println(a);
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
