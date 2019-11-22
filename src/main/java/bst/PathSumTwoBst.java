package bst;/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/path-sum-ii/
 */
public class PathSumTwoBst {
    
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;
    
        TreeNode(int x) { val = x; }
    }
    static class Solution {
        
        List<List<Integer>> allResults = new ArrayList<>();
        
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            
            if(root == null) {
                return new ArrayList<>();
            }
            
            dfsTraversal(root, sum, new ArrayList<>());
            
            return allResults;
        }
        
        private void dfsTraversal(TreeNode root, int sum, List<Integer> path) {
            if(root == null) {
                return;
            }
            
            path.add(root.val);
            // leaf node
            if(root.left == null && root.right == null) {
                if(root.val == sum) {
                    allResults.add(new ArrayList<>(path));
                }
            }
            
            if(root.left != null) {
                dfsTraversal(root.left, sum - root.val, path);
                path.remove(path.size()-1);
            }
            if(root.right != null) {
                dfsTraversal(root.right, sum - root.val, path);
                path.remove(path.size()-1);
            }
        }
    }
    
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }
    
        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
    
        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
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
            TreeNode root = stringToTreeNode(line);
            line = in.readLine();
            int sum = Integer.parseInt(line);
            
            List<List<Integer>> ret = new Solution().pathSum(root, sum);
            
            String out = int2dListToString(ret);
            
            System.out.print(out);
        }
    }
}