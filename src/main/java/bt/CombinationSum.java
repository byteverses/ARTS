package bt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum {
    
    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            
            List<List<Integer>> results = new ArrayList<>();
            
            this.backTracking(results, new LinkedList<>(), target, candidates, 0);
            
            return results;
        }
        
        private void backTracking(List<List<Integer>> results,
                                  LinkedList<Integer> tmpResults,
                                  int target,
                                  int[] candidates,
                                  int startIdx) {
            if(target == 0) {
                results.add(new ArrayList<>(tmpResults));
            }
            
            for(int i = startIdx; i < candidates.length; i++) {
                if(target < candidates[i]) {
                    continue;
                }
                tmpResults.add(candidates[i]);
                // Here use i, not i+1, so we can use same item several times.
                this.backTracking(results,
                                  tmpResults,
                                  target - candidates[i],
                                  candidates,
                                  i);
                
                tmpResults.removeLast();
                
            }
        }
    }
    
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
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
            int[] candidates = stringToIntegerArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);
            
            List<List<Integer>> ret = new Solution().combinationSum(candidates, target);
            
            String out = int2dListToString(ret);
            
            System.out.print(out);
        }
    }
}