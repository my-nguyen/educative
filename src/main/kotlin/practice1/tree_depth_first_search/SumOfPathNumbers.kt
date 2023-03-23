package practice1.tree_depth_first_search

import practice1.TreeNode

class SumOfPathNumbers {
    var grandTotal = 0

    fun findSumOfPathNumbers(root: TreeNode): Int {
        findSum(root, 0)
        return grandTotal
    }

    fun findSum(node: TreeNode?, sum: Int) {
        if (node == null)
            return

        val current = sum*10 + node.value
        if (node.left == null && node.right == null)
            grandTotal += current
        else {
            findSum(node.left, current)
            findSum(node.right, current)
        }
    }

    fun findSumOfPathNumbers1(root: TreeNode): Int {
        findSum1(root, 0)
        return grandTotal
    }

    fun findSum1(node: TreeNode?, sum: Int) {
        if (node == null)
            return

        val current = sum*10 + node.value
        if (node.left == null && node.right == null) {
            grandTotal += current
        } else {
            findSum(node.left, current)
            findSum(node.right, current)
        }
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