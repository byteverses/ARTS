package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node
 * to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *      / \
 *    15   7
 *
 * Output: 42
 *
 */
public class BinaryTreeMaximumPathSum {
    
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
        
        int maxVal = Integer.MIN_VALUE;
        
        public int maxPathSum(TreeNode root) {
            if(root == null) {
                return Integer.MIN_VALUE;
            }
            int rootMax = this.maxDFSSum(root);
            
            int leftMax = this.maxPathSum(root.left);
            int rightMax = this.maxPathSum(root.right);
            
            maxVal = Math.max(rootMax, Math.max(leftMax, rightMax));
            
            return maxVal;
        }
        
        private int maxDFSSum(TreeNode root) {
            if(root == null) {
                return Integer.MIN_VALUE;
            }
            
            int leftMax = this.maxDFSSum(root.left);
            int rightMax = this.maxDFSSum(root.right);
            
            return root.val + Math.max(leftMax, 0) + Math.max(rightMax, 0);
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
            
            int ret = new Solution().maxPathSum(root);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}