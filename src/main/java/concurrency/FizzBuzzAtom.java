package concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;


/**
 *
 *  https://leetcode.com/problems/fizz-buzz-multithreaded/
 * */
class FizzBuzzAtom {
    private int n;
    private AtomicInteger num = new AtomicInteger(1);

    public FizzBuzzAtom(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        int currNum;
        while((currNum = num.get()) <= n) {
            if(currNum % 3 == 0 && currNum % 5 != 0) {
                printFizz.run();
                num.addAndGet(1);
            }
        };
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        int currNum;
        while((currNum = num.get()) <= n) {
            if(currNum % 3 != 0 && currNum % 5 == 0) {
                printBuzz.run();
                num.addAndGet(1);
            }
        };
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        int currNum;
        while((currNum = num.get()) <= n) {
            if(currNum % 3 == 0 && currNum % 5 == 0) {
                printFizzBuzz.run();
                num.addAndGet(1);
            }
        };
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        int currNum;
        while((currNum = num.get()) <= n) {
            if(currNum % 3 != 0 && currNum % 5 != 0) {
                printNumber.accept(currNum);
                num.addAndGet(1);
            }
        };
    
    }
}