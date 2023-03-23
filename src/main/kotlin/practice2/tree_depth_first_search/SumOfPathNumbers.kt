package practice2.tree_depth_first_search

import practice2.TreeNode

class SumOfPathNumbers {
    fun findSumOfPathNumbers(root: TreeNode): Int {
        val paths = mutableListOf<Int>()
        dfs(root, mutableListOf(), paths)
        return paths.sum()
    }

    private fun dfs(node: TreeNode?, list: MutableList<Int>, sums: MutableList<Int>) {
        if (node == null)
            return

        list.add(node.value)
        if (node.left == null && node.right == null) {
            var sum = 0
            for (digit in list)
                sum = sum*10 + digit
            sums.add(sum)
        } else {
            dfs(node.left, list, sums)
            dfs(node.right, list, sums)
        }
        list.remove(node.value)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,7,9,-1,-1,2,9), intArrayOf(1,0,1,1,-1,6,5)
            )
            for (i in arrays.indices) {
                val root = TreeNode.build(arrays[i])!!
                val sum = SumOfPathNumbers().findSumOfPathNumbers(root)
                println("sum of all paths: $sum")
            }
        }
    }
}