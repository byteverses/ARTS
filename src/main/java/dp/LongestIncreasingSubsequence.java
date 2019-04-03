package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {
    static class Solution {
        public int lengthOfLIS(int[] nums) {
            if(nums.length == 0) {
                return 0;
            }
            int[] max = new int[nums.length];
            int maxLength = 1;
            max[0] = 1;
            
            for(int i = 1; i < nums.length; i++) {
                max[i] = 1;
                for(int curr = 0; curr <= i; curr++) {
                    if(nums[i] > nums[curr]) {
                        max[i] = Math.max(max[i], max[curr] + 1);
                    }
                }
                maxLength = Math.max(maxLength, max[i]);
            }
            
            return maxLength;
        }
    }
    
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if(input.length() == 0) {
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
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
        
            int ret = new Solution().lengthOfLIS(nums);
        
            String out = String.valueOf(ret);
        
            System.out.print(out);
        }
    }
}