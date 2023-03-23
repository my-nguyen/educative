package practice2.tree_breath_first_search

import practice2.TreeNode
import java.util.*

class ZigzagTraversal {
    fun traverse(root: TreeNode): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        var count = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            val list = mutableListOf<Int>()
            result.add(list)
            for (i in 0 until size) {
                val node = queue.poll()
                if (count % 2 == 0)
                    list.add(node.value)
                else
                    list.add(0, node.value)

                if (node.left != null)
                    queue.add(node.left!!)
                if (node.right != null)
                    queue.add(node.right!!)
            }
            count++
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
                val list = ZigzagTraversal().traverse(head!!)
                for (sublist in list) {
                    print("$sublist, ")
                }
                println()
            }
        }
    }
}