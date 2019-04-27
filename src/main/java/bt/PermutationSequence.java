package bt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PermutationSequence {
    static class Solution {
    
    
        public String getPermutation(int n, int k) {
            StringBuilder sb = new StringBuilder(n);
    
            
            
            int[] factorial = new int[n + 1];
            factorial[0] = 1;
            for(int i = 1; i <= n; i++) {
                factorial[i] = factorial[i-1] * i;
            }
    
            System.out.println("factorial = " + Arrays.toString(factorial));
    
            for(int i = 1; i < n; i++) {
                int fact = k / factorial[n-i];
                
                sb.append(fact+1);
                k = k % factorial[n-i];
            }
            
//            AtomicInteger kth = new AtomicInteger(k);
//            this.backTracking(sb, new LinkedList<>(), kth, n);
            
            return sb.toString();
            
        }
        
        private void backTracking(StringBuilder sb,
                                  LinkedList<Integer> tmpResults,
                                  AtomicInteger kth,
                                  int n) {
            if(tmpResults.size() == n) {
                int count = kth.decrementAndGet();
                
                if(count == 0) {
                    for(Integer num : tmpResults) {
                        sb.append(num);
                    }
                }
                return;
            }
            if(kth.get()==0) {
                return;
            }
            
            for(int i = 1; i <= n; i++) {
                if(tmpResults.contains(i)) {
                    continue;
                }
                tmpResults.add(i);
                this.backTracking(sb, tmpResults, kth, n);
                tmpResults.removeLast();
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        //        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //        String line;
        //        while ((line = in.readLine()) != null) {
        //            int n = Integer.parseInt(line);
        //            line = in.readLine();
        //            int k = Integer.parseInt(line);
        //
        //            String ret = new Solution().getPermutation(n, k);
        //
        //            String out = (ret);
        //
        //            System.out.print(out);
        //        }
        
        String ret = new Solution().getPermutation(3, 1);
        System.out.print(ret);
    }
}