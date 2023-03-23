package practice1.tree_breath_first_search

import practice1.TreeNode
import java.util.*

class LevelOrderTraversal {
    fun traverse(root: TreeNode): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            val list = mutableListOf<Int>()
            result.add(list)
            for (i in 0 until size) {
                val node = queue.poll()
                list.add(node.value)
                node.left?.let {
                    queue.add(it)
                }
                node.right?.let {
                    queue.add(it)
                }
            }
        }
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,2,3,4,5,6,7),
                intArrayOf(12,7,1,9,10,5)
            )
            for (array in arrays) {
                val head = TreeNode.build(array, 0)
                head?.print()
                val list = LevelOrderTraversal().traverse(head!!)
                for (sublist in list) {
                    print("$sublist, ")
                }
                println()
            }
        }
    }
}