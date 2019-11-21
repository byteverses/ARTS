package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/
 */
public class CombinationSum3 {
    static class Solution {
        public List<List<Integer>> combinationSum3(int k, int n) {
        
            List<List<Integer>> results = new ArrayList<>();
        
            this.backTracking(results, new LinkedList<>(), k, n, 1);
        
            return results;
        }
    
        private void backTracking(List<List<Integer>> results,
                                  LinkedList<Integer> tmpResults,
                                  int count,
                                  int target,
                                  int startIdx) {
            if(target == 0 && count == 0) {
                results.add(new ArrayList<>(tmpResults));
            }
        
            for(int i = startIdx; i < 10; i++) {
                if(target < i || count <= 0) {
                    continue;
                }
                tmpResults.add(i);
                this.backTracking(results,
                                  tmpResults,
                                  count-1,
                                  target - i,
                                  i+1);
            
                tmpResults.removeLast();
            
            }
        }
    }
    
    
    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }
    
        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }
    
    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }
    
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int k = Integer.parseInt(line);
            line = in.readLine();
            int n = Integer.parseInt(line);
            
            List<List<Integer>> ret = new Solution().combinationSum3(k, n);
            
            String out = int2dListToString(ret);
            
            System.out.print(out);
        }
    }
}