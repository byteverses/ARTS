package backtracking;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.json.JSONArray;

/**
 *
 * https://leetcode.com/problems/path-with-maximum-gold/
 *
 * 1219. Path with Maximum Gold
 * In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 *
 * Return the maximum amount of gold you can collect under the conditions:
 *
 * Every time you are located in a cell you will collect all the gold in that cell.
 * From your position you can walk one step to the left, right, up or down.
 * You can't visit the same cell more than once.
 * Never visit a cell with 0 gold.
 * You can start and stop collecting gold from any position in the grid that has some gold.
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 * Example 2:
 *
 * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * Output: 28
 * Explanation:
 * [[1,0,7],
 *  [2,0,6],
 *  [3,4,5],
 *  [0,3,0],
 *  [9,0,20]]
 * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 *
 */
public class PathWithMaximumGold {

    static class Solution {
        public int getMaximumGold(int[][] grid) {

            int maxGold = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (!this.isPath(grid, i, j)) {
                        maxGold = Math.max(maxGold, this.findMax(grid, i, j));
                    }
                }
            }

            return maxGold;
        }

        private int findMax(int[][] grid, int currRow, int currCol) {
            if (this.getGold(grid, currRow, currCol) <= 0) {
                return 0;
            }
            int currGold = grid[currRow][currCol];
            int max = 0;
            // set curr 0
            grid[currRow][currCol] = 0;

            // move next
            max = Math.max(max, this.findMax(grid, currRow - 1, currCol));
            max = Math.max(max, this.findMax(grid, currRow + 1, currCol));
            max = Math.max(max, this.findMax(grid, currRow, currCol - 1));
            max = Math.max(max, this.findMax(grid, currRow, currCol + 1));

            //reset curr
            grid[currRow][currCol] = currGold;

            return currGold + max;
        }

        private boolean isPath(int[][] grid, int row, int col) {
            int goldCount = 0;
            if (this.getGold(grid, row - 1, col) > 0 && this.getGold(grid, row + 1, col) > 0) {
                return true;
            }
            if (this.getGold(grid, row, col - 1) > 0 && this.getGold(grid, row, col + 1) > 0) {
                return true;
            }

            return false;
        }

        private int getGold(int[][] grid, int row, int col) {
            if ((row < 0 || row >= grid.length)
                || (col < 0 || col >= grid[0].length)) {
                return -1;
            }
            return grid[row][col];
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
    
    public static int[][] stringToInt2dArray(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        if (jsonArray.size() == 0) {
            return new int[0][0];
        }

        int[][] arr = new int[jsonArray.size()][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stringToIntegerArray(jsonArray.get(i).toString());
        }
        return arr;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[][] grid = stringToInt2dArray(line);
            
            int ret = new Solution().getMaximumGold(grid);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}