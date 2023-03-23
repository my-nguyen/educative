package practice2.tree_breath_first_search

import practice2.TreeNode
import java.util.*

class ConnectLevelOrderSiblings {
    fun connect(root: TreeNode) {
        return solution(root)
    }

    fun practice1(root: TreeNode) {
        var previous: TreeNode? = null
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val node = queue.poll()
                if (previous == null)
                    previous = node
                else {
                    previous.right = node
                    previous = node
                }
                if (node.left != null)
                    queue.add(node.left!!)
                if (node.right != null)
                    queue.add(node.right!!)
            }
        }
    }

    fun solution(root: TreeNode) {
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            var previous: TreeNode? = null
            for (i in 1..size) {
                val current = queue.poll()
                if (previous != null) {
                    previous.right = current
                }
                previous = current

                if (current.left != null) {
                    queue.add(current.left!!)
                }
                if (current.right != null) {
                    queue.add(current.right!!)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(2, 3), intArrayOf(4, 5, 6,7)),
                arrayOf(intArrayOf(12), intArrayOf(7, 1), intArrayOf(-1, 9, 10, 5)),
            )
            for (array in arrays) {
                /*val head = TreeNode.build(array)
                ConnectLevelOrderSiblings().connect(head)
                head.print()*/
            }
        }
    }
}