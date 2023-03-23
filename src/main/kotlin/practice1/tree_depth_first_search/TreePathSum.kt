package practice1.tree_depth_first_search

import practice1.TreeNode

class TreePathSum {
    fun hasPath(root: TreeNode?, sum: Int): Boolean {
        return if (root == null)
            false
        else if (root.left == null && root.right == null)
            root.value == sum
        else
            hasPath(root.left, sum-root.value) || hasPath(root.right, sum-root.value)
    }

    fun hasPath1(root: TreeNode?, sum: Int): Boolean {
        return when {
            root == null -> false
            root.value == sum && root.left == null && root.right == null -> true
            else -> hasPath(root.left, sum-root.value) || hasPath(root.right, sum-root.value)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,2,3,4,5,6,7),
                intArrayOf(12,7,1,9,-1,10,5),
                intArrayOf(12,7,1,9,-1,10,5),
            )
            val sums = arrayOf(10, 23, 16)
            for (i in arrays.indices) {
                val tree = TreeNode.build(arrays[i])
                tree?.print()
                val hasPath = TreePathSum().hasPath(tree, sums[i])
                println("target: ${sums[i]}, has path? $hasPath")
            }
        }
    }
}