package patterns_for_coding_questions._7_tree_breadth_first_search

import patterns_for_coding_questions.TreeNode
import java.util.*

class RightViewOfABinaryTree {
    fun traverse(root: TreeNode): List<TreeNode> {
        // return mine(root)
        return educative(root)
    }

    private fun educative(root: TreeNode): List<TreeNode> {
        val result = mutableListOf<TreeNode>()
        if (root == null) {
            return result
        }

        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 1..size) {
                val current = queue.poll()
                // if it is the last node of this level, add it to the result
                if (i == size) {
                    result.add(current)
                }

                if (current.left != null) {
                    queue.add(current.left!!)
                }
                if (current.right != null) {
                    queue.add(current.right!!)
                }
            }
        }
        return result
    }

    private fun mine(root: TreeNode): List<TreeNode> {
        val rightMost = mutableListOf<TreeNode>()
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        rightMost.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            var right: TreeNode? = null
            for (i in 1..size) {
                val current = queue.poll()
                if (current.left != null) {
                    queue.offer(current.left!!)
                    right = current.left
                }
                if (current.right != null) {
                    queue.offer(current.right!!)
                    right = current.right
                }
            }
            if (right != null) {
                rightMost.add(right!!)
            }
        }
        return rightMost
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(2,3), intArrayOf(4,5,6,7)),
                arrayOf(intArrayOf(12), intArrayOf(7,1), intArrayOf(9,-1,10,5), intArrayOf(3,-1,-1,-1,-1,-1,-1,-1)),
            )
            for (array in arrays) {
                val head = TreeNode.build(array)
                val list = RightViewOfABinaryTree().traverse(head)
                for (node in list) {
                    print("${node.value} ")
                }
                println()
            }
        }
    }
}