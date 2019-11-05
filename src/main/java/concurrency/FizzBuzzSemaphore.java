package concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 *
 * https://leetcode.com/problems/fizz-buzz-multithreaded/
 * */
class FizzBuzzSemaphore {
    private int n;
    
    private Semaphore numSemaphore  = new Semaphore(1);
    private Semaphore fizzSemaphore = new Semaphore(0);
    private Semaphore buzzSemaphore = new Semaphore(0);
    private Semaphore fizzbuzzSema = new  Semaphore(0);
    
    public FizzBuzzSemaphore(int n) {
        this.n = n;
    }
    
    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i = 3; i <= n; i += 3) {
            fizzSemaphore.acquire();
            printFizz.run();
            if((i+3) % 15 == 0) {
                i += 3;
            }
            numSemaphore.release();
        }
    }
    
    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i = 5; i <= n; i += 5) {
            buzzSemaphore.acquire();
            printBuzz.run();
            if((i+5) % 15 == 0) {
                i += 5;
            }
            numSemaphore.release();
        }
    }
    
    // printFizzBuzz.run(fadsfasdfsd) outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i = 15; i <= n; i += 15) {
            fizzbuzzSema.acquire();
            printFizzBuzz.run();
            numSemaphore.release();
        }
    }
    
    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            numSemaphore.acquire();
            if(i % 3 == 0 && i % 5 == 0) {
                fizzbuzzSema.release();
            }
            else if(i % 3 == 0) {
                fizzSemaphore.release();
            }
            else if(i % 5 == 0) {
                buzzSemaphore.release();
            }
            else {
                printNumber.accept(i);
                
                numSemaphore.release();
            }
        }
    }
}