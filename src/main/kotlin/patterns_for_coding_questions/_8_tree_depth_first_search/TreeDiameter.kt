package patterns_for_coding_questions._8_tree_depth_first_search

import patterns_for_coding_questions.TreeNode

class TreeDiameter {
    // from educative
    // This problem follows the Binary Tree Path Sum pattern. We can follow the same DFS approach. There will be a few
    // differences:
    // 1. At every step, we need to find the height of both children of the current node. For this, we will make two
    //    recursive calls similar to DFS.
    // 2. The height of the current node will be equal to the maximum of the heights of its left or right children,
    //    plus ‘1’ for the current node.
    // 3. The tree diameter at the current node will be equal to the height of the left child plus the height of the
    //    right child plus ‘1’ for the current node: diameter = leftTreeHeight + rightTreeHeight + 1. To find the overall
    //    tree diameter, we will use a class level variable. This variable will store the maximum diameter of all the
    //    nodes visited so far, hence, eventually, it will have the final tree diameter.
    private var treeDiameter = 0
    fun findDiameter(root: TreeNode): Int {
        getHeight(root)
        return treeDiameter
    }

    private fun getHeight(node: TreeNode?): Int {
        if (node == null) {
            return 0
        }

        // first calculate the height of the left and right subtrees
        val leftHeight = getHeight(node.left)
        val rightHeight = getHeight(node.right)

        // only calculate diameter for a node that has both left and right subtrees
        if (leftHeight != 0 && rightHeight != 0) {
            // diameter is the height of left subtree + the height of right sub-tree + 1 for the current node
            val diameter = leftHeight + rightHeight + 1

            // update the global tree diameter
            treeDiameter = maxOf(treeDiameter, diameter)
        }

        // height is the maximum of the heights of left or right subtrees, plus 1 for the current node
        return maxOf(leftHeight, rightHeight) + 1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(2,3), intArrayOf(4,-1,5,6)),
                arrayOf(intArrayOf(1), intArrayOf(2,3), intArrayOf(-1,-1,5,6),
                    intArrayOf(-1,-1,-1,-1,7,8,9,-1), intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,11,-1,-1,-1)),
            )
            for (i in arrays.indices) {
                val root = TreeNode.build(arrays[i])
                val diameter = TreeDiameter().findDiameter(root)
                println("tree diameter: $diameter")
            }
        }
    }
}