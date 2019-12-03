package math;

import java.util.Arrays;

public class FibonacciNumber {
    
    /**
     * Solution 1: recursive solution
     * Time complexity: O(2^n)
     */
    public long recursivelyResolve(long n) {
        
        return (n < 2) ? n : (recursivelyResolve(n - 1) + recursivelyResolve(n - 2));
    }
    
    /**
     * Solution 1-2: cached recursive solution
     * Time complexity: O(n)
     */
    public long recursivelyCacheResolve(long n) {
        if(n < 2) {
            return n;
        }
        
        long[] cachedNum = new long[(int) n + 1];
        cachedNum[0] = 0;
        cachedNum[1] = 1;
    
        return this.rrCached(n, cachedNum);
    }
    
    private long rrCached(long n, long[] cachedNum) {
        
        if(n > 1 && cachedNum[(int) n] == 0) {
            cachedNum[(int) n] = this.rrCached(n - 1, cachedNum) + this.rrCached(n - 2, cachedNum);
        }
        
        return cachedNum[(int) n];
    }
    
    /**
     * Solution 2: iterative solution
     * Time complexity: o(n)
     */
    public long iterativelyResolve(long n) {
        if(n < 2) {
            return n;
        }
        long a1 = 0;
        long a2 = 1;
        for(long i = 1; i < n; i++) {
            long tmp = a1;
            a1 = a2;
            a2 += tmp;
        }
        return a2;
    }
    
    /**
     * Solution 3: formula solution
     * Time complexity: O(log(n))
     */
    public long formulaResolve(long n) {
        
        // Formula.
        double val = (Math.pow(((1 + Math.sqrt(5)) / 2), n) - Math.pow(((1 - Math.sqrt(5)) / 2), n)) / Math.sqrt(5);
        
        return (long) val;
    }

    /**
     * Solution 4: Matrix manipulation
     * Time complexity: O(log(n))
     */
    public long matrixFormulaResolve(long n) {
        
        long[][] result = {{1, 0}, {0, 1}};
        long[][] tmp = {{1, 1}, {1, 0}};
        
        while(n > 0) {
            if((n % 2) == 1) {
                result = this.matrixMultiply(result, tmp);
                n--;
            }
            tmp = this.matrixMultiply(tmp, tmp);
            n = n >> 1;
        }
        return result[0][1];
    }
    
    private long[][] matrixMultiply(long[][] m, long[][] n) {
        long[][] mn = new long[m.length][n[0].length];
        
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < n[0].length; j++) {
                for(int k = 0; k < m[i].length; k++) {
                    mn[i][j] += m[i][k] * n[k][j];
                }
            }
        }
        
        return mn;
    }
    
    public static void main(String[] args) {
        FibonacciNumber fibonacciNumber = new FibonacciNumber();
        
        int num = 10;
        System.out.println("fibonacciNumber.recursivelyResolve(num) = " + fibonacciNumber.recursivelyResolve(num));
        
        System.out.println("fibonacciNumber.iterativelyResolve(num) = " + fibonacciNumber.iterativelyResolve(num));
        
        System.out.println("fibonacciNumber.formulaResolve(num) = " + fibonacciNumber.formulaResolve(num));
        
        System.out.println("fibonacciNumber.matrixFormulaResolve(num) = " + fibonacciNumber.matrixFormulaResolve(num));
        
        System.out.println(
                "fibonacciNumber.recursivelyResolveCached(num) = " + fibonacciNumber.recursivelyCacheResolve(num));
    }
}
