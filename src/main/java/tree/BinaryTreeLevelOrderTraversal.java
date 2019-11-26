package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {
    
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
    
        // Recursive
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            if(root == null) {
                return new ArrayList<>();
            }
            List<List<Integer>> levelOrders = new ArrayList<>();
        
            this.levelTraversal(root, 0, levelOrders);
        
            return levelOrders;
        }
    
        private void levelTraversal(TreeNode root, int level, List<List<Integer>> levelOrders) {
            if(levelOrders.size() == level) {
                levelOrders.add(new ArrayList<>());
            }
        
            levelOrders.get(level).add(root.val);
            if(root.left != null) {
                this.levelTraversal(root.left, level + 1, levelOrders);
            }
            if(root.right != null) {
                this.levelTraversal(root.right, level + 1, levelOrders);
            }
        }
    
        // Loop
        public List<List<Integer>> levelOrderLoopBottom(TreeNode root) {
            if(root == null) {
                return new ArrayList<>();
            }
            LinkedList<List<Integer>> levelOrderTraversal = new LinkedList<>();
        
            Queue<TreeNode> currQueue = new LinkedList<>();
            List<Integer> levelNodes = new ArrayList<>();
            Queue<TreeNode> nextLevelQueue = new LinkedList<>();
        
            currQueue.offer(root);
        
            while(!currQueue.isEmpty()) {
                TreeNode currNode = currQueue.poll();
                levelNodes.add(currNode.val);
                if(currNode.left != null) {
                    nextLevelQueue.offer(currNode.left);
                }
                if(currNode.right != null) {
                    nextLevelQueue.offer(currNode.right);
                }
                if(currQueue.isEmpty()) {
                    levelOrderTraversal.add(levelNodes);
                    levelNodes = new ArrayList<>();
                    currQueue = nextLevelQueue;
                    nextLevelQueue = new LinkedList<>();
                }
            }
        
            return levelOrderTraversal;
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
            
            List<List<Integer>> ret = new Solution().levelOrderBottom(root);
    
            String out = int2dListToString(ret);
    
            System.out.println("Recursive: "+out);
    
            System.out.println("Loop:"+int2dListToString(new Solution().levelOrderLoopBottom(root)));
        }
    }
}