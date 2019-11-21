package math;

public class PowerOf {
    
    
    /**
     * https://leetcode.com/problems/power-of-two/
     */
    class PowerOfTwo {
        public boolean isPowerOfTwo(int n) {
            return n > 0 && ((n & (n-1)) == 0);
        }
    }
    
    
    /**
     * https://leetcode.com/problems/power-of-three/
     */
    class PowerOfThree {
        final int max3pow = (int)Math.pow(3, (int)(Math.log(Integer.MAX_VALUE)/Math.log(3)));
        
        public boolean isPowerOfThree(int n) {
            
            return n > 0 && max3pow % n == 0;
        }
    }
    
    /**
     * https://leetcode.com/problems/power-of-four/
     */
    class PowerOfFour {
        public boolean isPowerOfFour(int num) {
            return (num > 0 && ((num & (num-1)) == 0) && ((num-1) % 3 == 0));
        }
    }
}
