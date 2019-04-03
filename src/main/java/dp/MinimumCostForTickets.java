package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-cost-for-tickets/
 */
public class MinimumCostForTickets {
    
    static class Solution {
        public int mincostTickets(int[] days, int[] costs) {
            Set<Integer> daySet = new HashSet<>(days.length);
            for(int day : days) {
                daySet.add(day);
            }
            
            int lastDay = days[days.length-1];
            int[] dayCost = new int[lastDay+1];
            dayCost[0] = 0;
            
            for(int i = 1; i <= lastDay; i++) {
                if(daySet.contains(i)) {
                    dayCost[i] = Math.min(Math.min(dayCost[i-1]+costs[0],
                                                   ((i<7)?0:dayCost[i-7])+costs[1]),
                                          ((i<30)?0:dayCost[i-30])+costs[2]);
                }
                else  {
                    dayCost[i] = dayCost[i-1];
                }
            }
            
            return dayCost[lastDay];
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
            int[] days = stringToIntegerArray(line);
            line = in.readLine();
            int[] costs = stringToIntegerArray(line);
            
            int ret = new Solution().mincostTickets(days, costs);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}