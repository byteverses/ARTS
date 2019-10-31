package concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 *  https://leetcode.com/problems/print-zero-even-odd/
 *
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 *
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 *
 * Each of the threads is given a printNumber method to output an integer.
 * Modify the given program to output the series 010203040506... where the length of the series must be 2n.
 * */
public class PrintZeroEvenOdd {
    
    
    private int n;
    
    private Semaphore zeroSem       = new Semaphore(1);
    private Semaphore oddSem  = new Semaphore(0);
    private Semaphore evenSem = new Semaphore(0);
    
    public PrintZeroEvenOdd(int n) {
        this.n = n;
    }
    
    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zeroSem.acquire();
            printNumber.accept(0);
            ((i % 2 == 0) ? oddSem : evenSem).release();
        }
    }
    
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i=i+2) {
            evenSem.acquire();
            printNumber.accept(i);
            zeroSem.release();
        }
    }
    
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i=i+2) {
            oddSem.acquire();
            printNumber.accept(i);
            zeroSem.release();
        }
    }
}
