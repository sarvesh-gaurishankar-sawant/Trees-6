/*
314. Binary Tree Vertical Order Traversal
Ran on leetcode: Yes
TC: O(n) SC: O(n)
Process all the nodes level wise from left to right using BFS's natural order, use two queue to maintain one to one relationship betweeen the node and its column, on each level group the nodes on same level using a map and then create a result list using the map from min to max col number
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
    public List<List<Integer>> verticalOrder(TreeNode root) {

        Queue<TreeNode> nodeQueue = new LinkedList<>(); // Track nodes in BFS
        Queue<Integer> colQueue = new LinkedList<>(); // Track col number in BFS parallely
        Map<Integer, List<Integer>> colToVal = new HashMap<>(); // Maintain nodes, column wise
        List<List<Integer>> result = new ArrayList<>();

        if(root == null) return result; // empty root edge case

        // Add root to both the queues
        nodeQueue.add(root);
        colQueue.add(0);

        // min and max col
        int min = 0;
        int max = 0;

        while(!nodeQueue.isEmpty()){
            int size = nodeQueue.size();
            for(int i = 0; i < size; i++){ // Process level wise
                TreeNode node = nodeQueue.poll();
                int colWidth = colQueue.poll();

                // Add to the map
                colToVal.putIfAbsent(colWidth, new ArrayList<>()); // Create the List for the columnWidth key
                colToVal.get(colWidth).add(node.val); // Add the val according to the column it is in

                // Process left child
                if(node.left != null){
                    // Add both to the node and col queue parallely to maintain one to one relationship
                    nodeQueue.offer(node.left);
                    colQueue.offer(colWidth - 1);
                    // Check if it is the new min value for col
                    min = Math.min(min, colWidth - 1);
                }

                // Process right child
                if(node.right != null){
                    // Add both to the node and col queue parallely to maintain one to one relationship
                    nodeQueue.offer(node.right);
                    colQueue.offer(colWidth + 1);
                    // Check if it is the new max value for col
                    max = Math.max(max, colWidth + 1);
                }
            }
        }

        for(int i = min; i <= max; i++){ // Build the result from map, go from min to max value of col
            result.add(colToVal.get(i));
        }

        return result;

    }
}