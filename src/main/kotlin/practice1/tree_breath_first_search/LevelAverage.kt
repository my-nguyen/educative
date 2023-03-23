package practice1.tree_breath_first_search

import practice1.TreeNode
import java.util.*

class LevelAverage {
    fun findLevelAverages(root: TreeNode): List<Double> {
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        val averages = mutableListOf<Double>()
        while (queue.isNotEmpty()) {
            val size = queue.size
            var sum = 0
            for (i in 0 until size) {
                val node = queue.poll()
                sum += node.value
                node.left?.let {
                    queue.add(it)
                }
                node.right?.let {
                    queue.add(it)
                }
            }
            val average = sum / size.toDouble()
            averages.add(average)
        }
        return averages
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,2,3,4,5,6,7),
                intArrayOf(12,7,1,9,2,10,5)
            )
            for (array in arrays) {
                val head = TreeNode.build(array, 0)
                head?.print()
                val list = LevelAverage().findLevelAverages(head!!)
                for (sublist in list) {
                    print("$sublist, ")
                }
                println()
            }
        }
    }
}