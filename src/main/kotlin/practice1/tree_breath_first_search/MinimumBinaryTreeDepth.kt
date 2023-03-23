package practice1.tree_breath_first_search

import practice1.TreeNode
import java.util.*

class MinimumBinaryTreeDepth {
    fun findDepth(root: TreeNode): Int {
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        var depth = 0
        while (queue.isNotEmpty()) {
            depth++
            val size = queue.size
            for (i in 0 until size) {
                val node = queue.poll()
                if (node.left == null && node.right == null)
                    return depth
                node.left?.let {
                    queue.add(it)
                }
                node.right?.let {
                    queue.add(it)
                }
            }
        }
        return depth
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,2,3,4,5), intArrayOf(12,7,1,9,2,10,5))
            for (array in arrays) {
                val tree = TreeNode.build(array, 0)
                tree!!.print()
                val depth = MinimumBinaryTreeDepth().findDepth(tree)
                println("minimum depth: $depth")
            }
        }
    }
}