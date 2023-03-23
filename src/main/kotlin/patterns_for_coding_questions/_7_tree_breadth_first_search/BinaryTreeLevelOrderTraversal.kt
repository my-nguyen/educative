package patterns_for_coding_questions._7_tree_breadth_first_search

import patterns_for_coding_questions.TreeNode
import java.util.*

class BinaryTreeLevelOrderTraversal {
    fun traverse(root: TreeNode): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val list = mutableListOf<Int>()
            var size = queue.size
            for (i in 1..size) {
                val current = queue.pop()
                list.add(current.value)
                if (current.left != null) {
                    queue.add(current.left!!)
                }
                if (current.right != null) {
                    queue.add(current.right!!)
                }
            }
            result.add(list)
        }
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(2,3), intArrayOf(4,5,6,7)),
                arrayOf(intArrayOf(12), intArrayOf(7,1), intArrayOf(9,-1,10,5)),
            )
            for (array in arrays) {
                val head = TreeNode.build(array)
                val list = BinaryTreeLevelOrderTraversal().traverse(head)
                for (sublist in list) {
                    print("$sublist, ")
                }
                println()
            }
        }
    }
}