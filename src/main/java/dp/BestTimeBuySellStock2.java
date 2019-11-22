package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 *
 * Example 2:
 *
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 *
 * Example 3:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeBuySellStock2 {
    
    static class Solution {
        public int maxProfit(int[] prices) {
            if(prices.length==0) {
                return 0;
            }

            int maxProfit = 0;
            int[] maxProfits = new int[prices.length];
            maxProfits[0] = 0;

            for(int i = 1; i < prices.length; i++) {
                maxProfits[i]=0;
                for(int curr = 0; curr <= i; curr++) {
                    if(prices[i] > prices[curr]) {
                        maxProfits[i] = prices[i]-prices[curr];
                    }
                }
                System.out.println("maxProfits = " + Arrays.toString(maxProfits));
                maxProfit = Math.max(maxProfit, maxProfits[i]);
            }

            return maxProfit;
        }
    }
    
    // Approach 3: Simple One Pass
    static class Solution3 {
        public int maxProfit(int[] prices) {
            if(prices.length==0) {
                return 0;
            }
        
            int maxProfit = 0;
        
            for(int i = 1; i < prices.length; i++) {
                maxProfit += Math.max(prices[i] - prices[i - 1], 0);
            }
        
            return maxProfit;
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
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] prices = stringToIntegerArray(line);
            
            int ret = new Solution().maxProfit(prices);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}