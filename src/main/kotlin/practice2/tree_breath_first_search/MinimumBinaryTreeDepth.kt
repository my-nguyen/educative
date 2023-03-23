package practice2.tree_breath_first_search

import practice2.TreeNode
import java.util.*

class MinimumBinaryTreeDepth {
    fun findDepth(root: TreeNode): Int {
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        var depth = 1
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val node = queue.poll()
                if (node.left == null && node.right == null) {
                    return depth
                } else {
                    if (node.left != null)
                        queue.add(node.left!!)
                    if (node.right != null)
                        queue.add(node.right!!)
                }
            }
            depth++
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