/*
938. Range Sum of BST
Ran on leetcode: Yes
TC: O(n) SC: O(n) - for skewed tree
In DFS manner visit the node and if Lies within the range, accumulate in sum, or else visit other children. If already current is already low dont go to left, cauz left would be lower or if current is already high dont go to right, cauz right would be higher
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    
    // Variable to accumulate sum
    private int sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        dfs(root, low, high);
        return sum;
    }

    private void dfs(TreeNode root, int low, int high){
        // base case
        if(root == null){
            return;
        }
        // logic
        if(root.val >= low && root.val <= high){ // Lies within the range, accumulate in sum
            sum += root.val;
        }
        if(!(root.val < low)){ // If it is already low dont go to left, cauz left would be lower
            dfs(root.left, low, high);
        }
        
        if(!(root.val > high)){ // If it is already high dont go to right, cauz right would be higher
            dfs(root.right, low, high);
        }
    }
}