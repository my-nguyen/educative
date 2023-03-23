package patterns_for_coding_questions._8_tree_depth_first_search

import patterns_for_coding_questions.TreeNode

class AllPathsForASum2 {
    fun findPaths(root: TreeNode): List<List<Int>> {
        val allPaths = mutableListOf<List<Int>>()
        val currentPath = mutableListOf<Int>()
        recursive(root, currentPath, allPaths)
        return allPaths
    }

    private fun recursive(currentNode: TreeNode?, currentPath: MutableList<Int>, allPaths: MutableList<List<Int>>) {
        if (currentNode == null) {
            return
        }

        // add the current node to the path
        currentPath.add(currentNode.value)

        // if the current node is a leaf and its value is equal to sum, save the current path
        if (currentNode.left == null && currentNode.right == null) {
            allPaths.add(currentPath.toList())
        } else {
            recursive(currentNode.left, currentPath, allPaths)
            recursive(currentNode.right, currentPath, allPaths)
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
            for (i in arrays.indices) {
                val root = TreeNode.build(arrays[i])
                val paths = AllPathsForASum2().findPaths(root)
                for (path in paths) {
                    print("$path, ")
                }
                println()
            }
        }
    }
}