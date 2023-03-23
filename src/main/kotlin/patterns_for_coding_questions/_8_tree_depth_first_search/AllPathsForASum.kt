package patterns_for_coding_questions._8_tree_depth_first_search

import patterns_for_coding_questions.TreeNode

class AllPathsForASum {
    // from educative
    // This problem follows the Binary Tree Path Sum pattern. We can follow the same DFS approach. There will be two differences:
    // 1. Every time we find a root-to-leaf path, we will store it in a list.
    // 2. We will traverse all paths and will not stop processing after finding the first path.
    fun findPaths(root: TreeNode, sum: Int): List<List<Int>> {
        val allPaths = mutableListOf<List<Int>>()
        val currentPath = mutableListOf<Int>()
        educative(root, sum, currentPath, allPaths)
        return allPaths
    }

    private fun educative(currentNode: TreeNode?, sum: Int, currentPath: MutableList<Int>, allPaths: MutableList<List<Int>>) {
        if (currentNode == null) {
            return
        }

        // add the current node to the path
        currentPath.add(currentNode.value)

        // if the current node is a leaf and its value is equal to sum, save the current path
        if (currentNode.value == sum && currentNode.left == null && currentNode.right == null) {
            allPaths.add(currentPath.toList())
        } else {
            educative(currentNode.left, sum-currentNode.value, currentPath, allPaths)
            educative(currentNode.right, sum-currentNode.value, currentPath, allPaths)
        }

        // remove the current node from the path to backtrack
        currentPath.removeLast()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(7,9), intArrayOf(4,5,2,7)),
                arrayOf(intArrayOf(12), intArrayOf(7,1), intArrayOf(4,-1,10,5)),
            )
            val sums = arrayOf(12, 23)
            for (i in arrays.indices) {
                val root = TreeNode.build(arrays[i])
                val paths = AllPathsForASum().findPaths(root, sums[i])
                for (path in paths) {
                    print("$path, ")
                }
                println()
            }
        }
    }
}