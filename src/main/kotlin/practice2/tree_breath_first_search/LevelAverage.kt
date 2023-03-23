package practice2.tree_breath_first_search

import practice2.TreeNode
import java.util.*

class LevelAverage {
    fun findLevelAverages(root: TreeNode): List<Double> {
        val averages = mutableListOf<Double>()
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            var sum = 0
            for (i in 0 until size) {
                val node = queue.poll()
                sum += node.value
                if (node.left != null)
                    queue.add(node.left!!)
                if (node.right != null)
                    queue.add(node.right!!)
            }
            averages.add(sum / size.toDouble())
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