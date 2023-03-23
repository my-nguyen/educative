package practice2.tree_depth_first_search

import practice2.TreeNode

class TreePathSum {
    fun hasPath(root: TreeNode?, sum: Int): Boolean {
        return dfs(root, sum) // 1,2,3,4,5,6,7; 10
    }

    fun dfs(node: TreeNode?, sum: Int): Boolean {
        return when {
            node == null -> false
            node.left == null && node.right == null -> node.value == sum
            dfs(node.left, sum - node.value) -> true
            else -> dfs(node.right, sum - node.value)
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