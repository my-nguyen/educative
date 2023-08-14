package patterns_for_coding_questions._8_tree_depth_first_search

import patterns_for_coding_questions.TreeNode

class SumOfPathNumbers {
    fun findSumOfPathNumbers(root: TreeNode): Int {
        // return mine(root)
        return educative(root, 0)
    }

    private fun educative(node: TreeNode?, sum: Int): Int {
        if (node == null) {
            return 0
        }

        // calculate the path number of the current node
        val newSum = sum*10 + node.value

        // if the current node is a leaf, return the current path sum
        if (node.left == null && node.right == null) {
            return newSum
        }

        // traverse the left and the right sub-tree
        return educative(node.left, newSum) + educative(node.right, newSum)
    }

    var sum = 0
    private fun mine(root: TreeNode): Int {
        val currentPath = mutableListOf<Int>()
        recursive(root, currentPath)
        return sum
    }

    private fun recursive(currentNode: TreeNode?, currentPath: MutableList<Int>) {
        if (currentNode == null) {
            return
        }

        currentPath.add(currentNode.value)
        if (currentNode.left == null && currentNode.right == null) {
            val number = getNumber(currentPath)
            sum += number
        } else {
            recursive(currentNode.left, currentPath)
            recursive(currentNode.right, currentPath)
        }

        currentPath.removeLast()
    }

    private fun getNumber(path: List<Int>): Int {
        var sum = 0
        for (number in path) {
            sum = sum * 10 + number
        }
        return sum
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(7,9), intArrayOf(-1,-1,2,9)),
                arrayOf(intArrayOf(1), intArrayOf(0,1), intArrayOf(1,-1,6,5)),
            )
            for (i in arrays.indices) {
                val root = TreeNode.build(arrays[i])
                val sum = SumOfPathNumbers().findSumOfPathNumbers(root)
                println("sum of all paths: $sum")
            }
        }
    }
}