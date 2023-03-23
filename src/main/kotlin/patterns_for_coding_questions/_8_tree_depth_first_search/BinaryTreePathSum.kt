package patterns_for_coding_questions._8_tree_depth_first_search

import patterns_for_coding_questions.TreeNode
import java.util.*

class BinaryTreePathSum {
    fun hasPath(root: TreeNode, sum: Int): Boolean {
        return educative(root, sum)
    }

    // from educative
    // As we are trying to search for a root-to-leaf path, we can use the Depth First Search (DFS) technique to solve
    // this problem.
    // To recursively traverse a binary tree in a DFS fashion, we can start from the root and at every step, make
    // two recursive calls one for the left and one for the right child.
    // Here are the steps for our Binary Tree Path Sum problem:
    // 1. Start DFS with the root of the tree.
    // 2. If the current node is not a leaf node, do two things:
    //    * Subtract the value of the current node from the given number to get a new sum => S = S - node.value
    //    * Make two recursive calls for both the children of the current node with the new number calculated in the previous step.
    // 3. At every step, see if the current node being visited is a leaf node and if its value is equal to the given
    //    number ‘S’. If both these conditions are true, we have found the required root-to-leaf path, therefore return true.
    // 4. If the current node is a leaf but its value is not equal to the given number ‘S’, return false.
    private fun educative(root: TreeNode?, sum: Int): Boolean {
        if (root == null) {
            return false
        }

        // if the current node is a leaf and its value is equal to the sum, we've found a path
        if (root.value == sum && root.left == null && root.right == null) {
            return true
        }

        // recursively call to traverse the left and right sub-tree
        return educative(root.left, sum - root.value) || educative(root.right, sum - root.value)
    }

    // incorrect solution
    private fun mine(root: TreeNode, sum: Int): Boolean {
        var total = 0
        val set = mutableSetOf<TreeNode>()
        val stack = Stack<TreeNode>()
        stack.push(root)
        while (stack.isNotEmpty()) {
            /*set.add(current)
            total += current.value*/
        }
        return false
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(2, 3), intArrayOf(4, 5, 6,7)),
                arrayOf(intArrayOf(12), intArrayOf(7, 1), intArrayOf(9, -1, 10, 5)),
                arrayOf(intArrayOf(12), intArrayOf(7, 1), intArrayOf(9, -1, 10, 5)),
            )
            val sums = arrayOf(10, 23, 16)
            for (i in arrays.indices) {
                val root = TreeNode.build(arrays[i])
                val hasPath = BinaryTreePathSum().hasPath(root, sums[i])
                println("Tree has path: $hasPath")
            }
        }
    }
}