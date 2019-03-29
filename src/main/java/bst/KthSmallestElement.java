package bst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class KthSmallestElement {
    
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    /**************** Solution *******************/
    static class Solution {
        public int kthSmallest(TreeNode root, int k) {
            // include currNode.
            int nodeCount = this.nodeCount(root.left) + 1;
            if (nodeCount > k) {
                return this.kthSmallest(root.left, k);
            } else if (nodeCount < k) {
                return this.kthSmallest(root.right, k - nodeCount);
            } else {
                return root.val;
            }
        }
        
        public int nodeCount(TreeNode currNode) {
            if (currNode == null) {
                return 0;
            }
            
            return (1 + nodeCount(currNode.left) + nodeCount(currNode.right));
        }
    }
    /**************** Solution *******************/
    
    
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
            int k = Integer.parseInt(line);
            
            int ret = new Solution().kthSmallest(root, k);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}
