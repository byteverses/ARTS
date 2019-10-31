package concurrency;

import java.util.concurrent.Semaphore;

/**
 * https://leetcode.com/problems/print-foobar-alternately/
 * <p>
 * The same instance of FooBar will be passed to two different threads.
 * Thread A will call foo() while thread B will call bar().
 * Modify the given program to output "foobar" n times.
 */
public class PrintFooBarAlternately {
    
    class FooBar {
        private int n;
        
        private Semaphore foo = new Semaphore(0);
        private Semaphore bar = new Semaphore(1);
        
        public FooBar(int n) {
            this.n = n;
        }
        
        public void foo(Runnable printFoo) throws InterruptedException {
            
            for(int i = 0; i < n; i++) {
                foo.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                bar.release();
            }
        }
        
        public void bar(Runnable printBar) throws InterruptedException {
            
            for(int i = 0; i < n; i++) {
                bar.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                foo.release();
            }
        }
    }
    
    
    
}
