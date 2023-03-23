package patterns_for_coding_questions._7_tree_breadth_first_search

import patterns_for_coding_questions.TreeNode
import java.util.*

class MinimumDepthOfABinaryTree {
    fun findDepth(root: TreeNode): Int {
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        var depth = 1
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 1..size) {
                val current = queue.poll()
                if (current.left == null && current.right == null) {
                    return depth
                } else {
                    if (current.left != null) {
                        queue.offer(current.left!!)
                    }
                    if (current.right != null) {
                        queue.offer(current.right!!)
                    }
                }
            }
            depth++
        }
        return depth
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(2,3), intArrayOf(4,5,-1,-1)),
                arrayOf(intArrayOf(12), intArrayOf(7,1), intArrayOf(-1,-1,10,5)),
                arrayOf(intArrayOf(12), intArrayOf(7,1), intArrayOf(9,-1,10,5), intArrayOf(-1,-1,-1,-1,11,-1,-1,-1)),
            )
            for (array in arrays) {
                val head = TreeNode.build(array)
                val depth = MinimumDepthOfABinaryTree().findDepth(head)
                println("minimum depth: $depth")
            }
        }
    }
}