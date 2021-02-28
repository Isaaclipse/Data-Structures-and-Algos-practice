// 814. Binary Tree Pruning
// Medium

// We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.

// Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

// (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)

// Example 1:
// Input: [1,null,0,0,1]
// Output: [1,null,0,null,1]
 
// Explanation: 
// Only the red nodes satisfy the property "every subtree not containing a 1".
// The diagram on the right represents the answer.


// Example 2:
// Input: [1,0,1,0,0,0,1]
// Output: [1,null,1,null,1]



// Example 3:
// Input: [1,1,0,1,1,0,1,0]
// Output: [1,1,0,1,1,null,1]

// Note:

//     The binary tree will have at most 200 nodes.
//     The value of each node will only be 0 or 1.

// Approach #1: Recursion [Accepted]

// Intuition

// Prune children of the tree recursively. The only decisions at each node are whether to prune the left child or the right child.

// Algorithm

// We'll use a function containsOne(node) that does two things: it tells us whether the subtree at this node contains a 1, and it also prunes all subtrees not containing 1.

// If for example, node.left does not contain a one, then we should prune it via node.left = null.

// Also, the parent needs to be checked. If for example the tree is a single node 0, the answer is an empty tree.

// Complexity Analysis

//     Time Complexity: O(N)O(N)O(N), where NNN is the number of nodes in the tree. We process each node once.

//     Space Complexity: O(H)O(H)O(H), where HHH is the height of the tree. This represents the size of the implicit call stack in our recursion.

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        return containsOne(root) ? root : null;
    }

    public boolean containsOne(TreeNode node) {
        if (node == null) return false;
        boolean a1 = containsOne(node.left);
        boolean a2 = containsOne(node.right);
        if (!a1) node.left = null;
        if (!a2) node.right = null;
        return node.val == 1 || a1 || a2;
    }
}