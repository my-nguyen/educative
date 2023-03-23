package patterns_for_coding_questions._8_tree_depth_first_search

import patterns_for_coding_questions.TreeNode

class AllPathsForASum3 {
    var maxPath = listOf<Int>()
    var maxSum = 0

    fun findPath(root: TreeNode): List<Int> {
        val currentPath = mutableListOf<Int>()
        recursive(root, 0, currentPath)
        return maxPath
    }

    private fun recursive(currentNode: TreeNode?, sum: Int, currentPath: MutableList<Int>) {
        if (currentNode == null) {
            return
        }

        // add the current node to the path
        currentPath.add(currentNode.value)

        // if the current node is a leaf and its value is equal to sum, save the current path
        if (currentNode.left == null && currentNode.right == null) {
            if (sum+currentNode.value > maxSum) {
                maxSum = sum + currentNode.value
                maxPath = currentPath.toList()
            }
        } else {
            recursive(currentNode.left, sum+currentNode.value, currentPath)
            recursive(currentNode.right, sum+currentNode.value, currentPath)
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
                arrayOf(intArrayOf(1), intArrayOf(2,1), intArrayOf(3,4,6,5)),
            )
            val sums = arrayOf(12, 23)
            for (i in arrays.indices) {
                val root = TreeNode.build(arrays[i])
                val path = AllPathsForASum3().findPath(root)
                println("path with max sum: $path")
            }
        }
    }
}