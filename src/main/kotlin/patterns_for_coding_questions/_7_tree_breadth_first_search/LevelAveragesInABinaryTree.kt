package patterns_for_coding_questions._7_tree_breadth_first_search

import patterns_for_coding_questions.TreeNode
import java.util.*

class LevelAveragesInABinaryTree {
    fun findLevelAverages(root: TreeNode): List<Double> {
        val averages = mutableListOf<Double>()
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            var total = 0
            for (i in 1..size) {
                val current = queue.remove()
                total += current.value
                if (current.left != null) {
                    queue.add(current.left!!)
                }
                if (current.right != null) {
                    queue.add(current.right!!)
                }
            }
            val average = total / size.toDouble()
            averages.add(average)
        }
        return averages
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(2,3), intArrayOf(4,5,6,7)),
                arrayOf(intArrayOf(12), intArrayOf(7,1), intArrayOf(9,2,10,5))
            )
            for (array in arrays) {
                val head = TreeNode.build(array)
                val averages = LevelAveragesInABinaryTree().findLevelAverages(head)
                for (average in averages) {
                    print("$average, ")
                }
                println()
            }
        }
    }
}