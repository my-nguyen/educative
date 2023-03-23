package patterns_for_coding_questions._7_tree_breadth_first_search

import patterns_for_coding_questions.TreeNode
import java.util.*

class ReverseLevelOrderTraversal {
    fun traverse(root: TreeNode): List<List<Int>> {
        // return mine(root)
        return educative(root)
    }

    private fun educative(root: TreeNode): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) {
            return result
        }

        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val list = mutableListOf<Int>()
            val size = queue.size
            for (i in 1..size) {
                val current = queue.poll()
                list.add(current.value)
                if (current.left != null) {
                    queue.add(current.left!!)
                }
                if (current.right != null) {
                    queue.add(current.right!!)
                }
            }
            // append the current level at the beginning
            result.add(0, list)
        }
        return result
    }

    private fun mine(root: TreeNode): List<List<Int>> {
        val queue = LinkedList<TreeNode>()
        val stack = Stack<List<Int>>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val list = mutableListOf<Int>()
            val size = queue.size
            for (i in 1..size) {
                val current = queue.remove()
                list.add(current.value)
                if (current.left != null) {
                    queue.add(current.left!!)
                }
                if (current.right != null) {
                    queue.add(current.right!!)
                }
            }
            stack.push(list)
        }

        val result = mutableListOf<List<Int>>()
        while (stack.isNotEmpty()) {
            val list = stack.pop()
            result.add(list)
        }
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(2,3), intArrayOf(4,5,6,7)),
                arrayOf(intArrayOf(12), intArrayOf(7,1), intArrayOf(9,-1,10,5))
            )
            for (array in arrays) {
                val head = TreeNode.build(array)
                val list = ReverseLevelOrderTraversal().traverse(head)
                for (sublist in list) {
                    print("$sublist, ")
                }
                println()
            }
        }
    }
}