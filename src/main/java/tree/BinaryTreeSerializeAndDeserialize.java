package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that
 * it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class BinaryTreeSerializeAndDeserialize {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    static class Solution {
        
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null) {
                return "";
            }
            StringBuilder flatSb = new StringBuilder();
            
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if(node != null) {
                    flatSb.append(node.val).append(",");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
                else {
                    flatSb.append("null").append(",");
                }
            }
            //System.out.println(flatSb.deleteCharAt(flatSb.length()-1).toString());
            return flatSb.deleteCharAt(flatSb.length()-1).toString();
        }
        
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == null || data.length() == 0) {
                return null;
            }
            
            String[] nodesArray = data.split(",");
            
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            TreeNode rootNode = new TreeNode(Integer.parseInt(nodesArray[0]));
            nodeQueue.offer(rootNode);
            
            int idx = 1;
            while(!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.poll();
                
                //left node
                if(idx < nodesArray.length) {
                    String nodeStr = nodesArray[idx++];
                    if(!nodeStr.equals("null")) {
                        node.left = new TreeNode(Integer.parseInt(nodeStr));
                        nodeQueue.offer(node.left);
                    }
                }
                //right node
                if(idx < nodesArray.length) {
                    String nodeStr = nodesArray[idx++];
                    if(!nodeStr.equals("null")) {
                        node.right = new TreeNode(Integer.parseInt(nodeStr));
                        nodeQueue.offer(node.right);
                    }
                }
            }
            
            return rootNode;
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
            
            String ret = new Solution().serialize(root);
            
            String out = (ret);
            
            System.out.print(out);
        }
    }
}