package practice1.tree_breath_first_search

import practice1.TreeNode
import java.util.*

class LevelOrderSuccessor {
    fun findSuccessor(root: TreeNode, key: Int): TreeNode? {
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        var found = false
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val node = queue.poll()
                if (found)
                    return node

                if (node.value == key)
                    found = true
                node.left?.let {
                    queue.add(it)
                }
                node.right?.let {
                    queue.add(it)
                }
            }
        }
        return null
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,2,3,4,5,-1,-1), intArrayOf(12,7,1,9,-1,10,5), intArrayOf(12,7,1,9,-1,10,5)
            )
            val keys = arrayOf(3, 9, 12)
            for (i in arrays.indices) {
                val tree = TreeNode.build(arrays[i], 0)
                tree?.print()
                val successor = LevelOrderSuccessor().findSuccessor(tree!!, keys[i])
                println("key: ${keys[i]}, successor: $successor")
            }
        }
    }
}