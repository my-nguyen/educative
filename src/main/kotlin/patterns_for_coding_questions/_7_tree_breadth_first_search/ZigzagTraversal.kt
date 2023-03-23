package patterns_for_coding_questions._7_tree_breadth_first_search

import patterns_for_coding_questions.TreeNode
import java.util.*

class ZigzagTraversal {
    fun traverse(root: TreeNode): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        var i = 0
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            val list = mutableListOf<Int>()
            result.add(list)
            for (j in 1..size) {
                val current = queue.remove()
                if (i % 2 == 0) {
                    list.add(current.value)
                } else {
                    list.add(0, current.value)
                }
                if (current.left != null) {
                    queue.add(current.left!!)
                }
                if (current.right != null) {
                    queue.add(current.right!!)
                }
            }
            i++
        }
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(2,3), intArrayOf(4,5,6,7)),
                arrayOf(intArrayOf(12), intArrayOf(7,1), intArrayOf(9,-1,10,5), intArrayOf(-1,-1,-1,-1,20,17,-1,-1)),
            )
            for (array in arrays) {
                val head = TreeNode.build(array)
                val list = ZigzagTraversal().traverse(head)
                for (sublist in list) {
                    print("$sublist, ")
                }
                println()
            }
        }
    }
}