package patterns_for_coding_questions._8_tree_depth_first_search

import patterns_for_coding_questions.TreeNode

class PathWithMaximumSum {
    // from educative
    // This problem follows the Binary Tree Path Sum pattern and shares the algorithmic logic with Tree Diameter. We can
    // follow the same DFS approach. The only difference will be to ignore the paths with negative sums. Since we need
    // to find the overall maximum sum, we should ignore any path which has an overall negative sum.
    private var maxSum = Integer.MIN_VALUE
    fun findMaximumPathSum(root: TreeNode): Int {
        findMaxSum(root)
        return maxSum
    }

    private fun findMaxSum(node: TreeNode?): Int {
        if (node == null) {
            return 0
        }

        var leftSum = findMaxSum(node.left)
        var rightSum = findMaxSum(node.right)

        // ignore paths with negative sums
        leftSum = maxOf(leftSum, 0)
        rightSum = maxOf(rightSum, 0)

        // maximum path sum is the sum from the left subtree + the sum from right subtree + value of current node
        val sum = leftSum + rightSum + node.value

        // update the global maximum sum
        maxSum = maxOf(maxSum, sum)

        // maximum sum is the maximum of the sums from left or right subtrees plus the value of the current node
        return maxOf(leftSum, rightSum) + node.value
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(2,3), intArrayOf(4,-1,5,6)),
                arrayOf(intArrayOf(1), intArrayOf(2,3), intArrayOf(1,3,5,6), intArrayOf(-1,-1,-1,-1,7,8,9,-1)),
                arrayOf(intArrayOf(1), intArrayOf(2,3)),
                arrayOf(intArrayOf(1), intArrayOf(2,3), intArrayOf(1,3,5,6), intArrayOf(-1,-1,-1,-1,7,8,9,-1)),
                arrayOf(intArrayOf(-1), intArrayOf(-3,3), intArrayOf(1,3,5,6), intArrayOf(-1,-1,-1,-1,7,8,9,-1)),
            )
            for (array in arrays) {
                val root = TreeNode.build(array)
                val sum = PathWithMaximumSum().findMaximumPathSum(root)
                println("sum $sum")
            }
        }
    }
}