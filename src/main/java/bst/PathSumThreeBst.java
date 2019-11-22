package bst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/path-sum-iii/
 */
public class PathSumThreeBst {
    
    
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
        
        public int pathSum(TreeNode root, int sum) {
            if(root == null) {
                return 0;
            }
            
            //
            int rootCount = this.dfsPathSum(root, sum);
            
            int leftCount = this.pathSum(root.left, sum);
            int rightCount = this.pathSum(root.right, sum);
            
            return rootCount + leftCount + rightCount;
        }
        
        public int dfsPathSum(TreeNode root, int sum) {
            if(root == null) {
                return 0;
            }
            
            return ((root.val == sum) ? 1 : 0) +
                   (dfsPathSum(root.left, sum - root.val) + dfsPathSum(root.right, sum - root.val));
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
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            line = in.readLine();
            int sum = Integer.parseInt(line);
            
            int ret = new Solution().pathSum(root, sum);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}