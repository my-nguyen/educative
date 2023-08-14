package patterns_for_coding_questions._7_tree_breadth_first_search

import patterns_for_coding_questions.TreeNode
import java.util.*

class LevelOrderSuccessor {
    fun findSuccessor(root: TreeNode, key: Int): TreeNode? {
        // return mine(root, key)
        return educative(root, key)
    }

    private fun educative(root: TreeNode, key: Int): TreeNode? {
        if (root == null) {
            return null
        }

        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (current.left != null) {
                queue.add(current.left!!)
            }
            if (current.right != null) {
                queue.add(current.right!!)
            }

            if (current.value == key) {
                break
            }
        }

        return queue.peek()
    }

    private fun mine(root: TreeNode, key: Int): TreeNode? {
        var found = false
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 1..size) {
                val current = queue.poll()
                if (found) {
                    return current
                } else if (current.value == key) {
                    found = true
                }

                if (current.left != null) {
                    queue.add(current.left!!)
                }
                if (current.right != null) {
                    queue.add(current.right!!)
                }
            }
        }
        return null
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(2, 3), intArrayOf(4, 5, -1, -1)),
                arrayOf(intArrayOf(12), intArrayOf(7,1), intArrayOf(9,-1,10,5)),
                arrayOf(intArrayOf(12), intArrayOf(7,1), intArrayOf(9,-1,10,5)),
            )
            val keys = arrayOf(3, 9, 12)
            for (i in arrays.indices) {
                val head = TreeNode.build(arrays[i])
                val successor = LevelOrderSuccessor().findSuccessor(head, keys[i])
                println("key: ${keys[i]}, successor: $successor")
            }
        }
    }
}