/*
297. Serialize and Deserialize Binary Tree
TC: O(n) SC: O(n)
Serialize - Use BFS to build the string, if the node is not null add its left and right child to the queue and add the its value to the string and if the node is null add # value to the string but dont add to the queue. Deserialize - In BFS manner unravel the string by first creating root node and then left and right child if the value on the next index is not #. Left child is +1 from the root and right child is +2 from the root.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if(root == null) return "";

        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder();

        queue.offer(root);
        
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node != null){ // if the node is not null
                result.append(node.val); // add it val to the result string
                //add left and right child to the queue
                queue.offer(node.left);
                queue.offer(node.right);
            } else { // If it is null we will put # in its place
                result.append("#");
            }
            result.append(",");
        }

        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if(data.length() == 0) return null;

        String[] dataArr = data.split(",");
        int idx = 0;
        
        // Queue to unravel in BFS manner add the first element which is root
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(dataArr[0]));
        queue.offer(root);
        idx++;

        // Start of BFS
        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();

            if(!dataArr[idx].equals("#")){ // If the left child is not null add it to the queue
                curr.left = new TreeNode(Integer.valueOf(dataArr[idx]));
                queue.offer(curr.left);
            }

            idx++;

            if(!dataArr[idx].equals("#")){ // If the right child is not null add it to the queue
                curr.right = new TreeNode(Integer.valueOf(dataArr[idx]));
                queue.offer(curr.right);
            }

            idx++;

        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));