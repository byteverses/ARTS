package concurrency;

import java.util.concurrent.Semaphore;

/**
 * https://leetcode.com/problems/print-in-order/
 * <p>
 * Suppose we have a class:
 * <p>
 * public class Foo {
 * public void first() { print("first"); }
 * public void second() { print("second"); }
 * public void third() { print("third"); }
 * }
 * <p>
 * The same instance of Foo will be passed to three different threads.
 * Thread A will call first(), thread B will call second(), and thread C will call third().
 * Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().
 */
public class PrintInOrder {
    
    class Foo {
        
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);
        
        public Foo() {
        
        }
        
        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            s2.release();
        }
        
        public void second(Runnable printSecond) throws InterruptedException {
            s2.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            s3.release();
        }
        
        public void third(Runnable printThird) throws InterruptedException {
            s3.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
