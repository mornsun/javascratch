package topcoder;
// Java program to implement solution of producer
// consumer problem.

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class xProducerConsumer {
    //private static final SynchronizedPC pc = new SynchronizedPC();
    private static final ConditionalPC pc = new ConditionalPC();

    public static void main(String[] args)
            throws InterruptedException {
        // Create producer thread
        Thread producer0 = new Thread(new Producer());
        Thread producer1 = new Thread(new Producer());
        Thread producer2 = new Thread(new Producer());
        Thread producer3 = new Thread(new Producer());
        Thread producer4 = new Thread(new Producer());
        Thread producer5 = new Thread(new Producer());

        // Create consumer thread
        Thread consumer0 = new Thread(new Consumer());
        Thread consumer1 = new Thread(new Consumer());
        Thread consumer2 = new Thread(new Consumer());
        Thread consumer3 = new Thread(new Consumer());
        Thread consumer4 = new Thread(new Consumer());
        Thread consumer5 = new Thread(new Consumer());

        // Start both threads
        producer0.start();
        producer1.start();
		producer2.start();
		/*producer3.start();
		producer4.start();
		producer5.start();*/
        consumer0.start();
        consumer1.start();
		consumer2.start();
		/*consumer3.start();
		consumer4.start();
		consumer5.start();*/

        // t1 finishes before t2
        //producer.join(100);
        //t2.start();
        //consumer.join();
    }

    public static class Producer implements Runnable {
        private static volatile int value = 0;

        @Override
        public void run() {
            try {
                while (true) {
                    pc.produce(value++);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                long startTime = System.currentTimeMillis();
                while (true) {
                    int val = pc.consume();
                    if (val >= 100000) {
                        System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
                        return;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //only support 1 producer and 1 consumer
    public static class SynchronizedPC {
        LinkedList<Integer> queue = new LinkedList<>();
        int capacity = 1;

        public void produce(int value) throws InterruptedException {
            synchronized (this) {
                while (queue.size() == capacity)
                    wait();
                queue.offer(value);
                notify();
            }
            System.out.println("Producer produced-"
                    + value);
        }

        public int consume() throws InterruptedException {
            int val;
            synchronized (this) {
                while (queue.size() == 0)
                    wait();
                val = queue.poll();
                notify();
            }
            System.out.println("Consumer consumed-"
                    + val);
            return val;
        }
    }

    //support n producer and n consumer
    public static class ConditionalPC {
        LinkedList<Integer> queue = new LinkedList<>();
        int capacity = 1;
        private final Lock aLock = new ReentrantLock();
        private final Condition bufferNotFull = aLock.newCondition();
        private final Condition bufferNotEmpty = aLock.newCondition();

        public void produce(int value) throws InterruptedException {
            aLock.lock();
            while (queue.size() == capacity)
                bufferNotFull.await();
            queue.offer(value);
            //bufferNotEmpty.signalAll();
            bufferNotFull.signal();//better perf
            aLock.unlock();
            //System.out.println("Producer produced-"
            //        + value);
        }

        public int consume() throws InterruptedException {
            int val;
            aLock.lock();
            while (queue.size() == 0)
                bufferNotEmpty.await();
            val = queue.poll();
            //bufferNotFull.signalAll();
            bufferNotFull.signal();//better perf
            aLock.unlock();
            //System.out.println("Consumer consumed-"
            //        + val);
            return val;
        }
    }
}