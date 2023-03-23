package practice1

import java.util.*

data class TreeNode(val value: Int, var left: TreeNode? = null, var right: TreeNode? = null) {
    override fun toString() = value.toString()

    fun print() {
        var current = this
        val queue = LinkedList<TreeNode>()
        queue.add(current)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val node = queue.poll()
                print("$node, ")
                if (node.left != null)
                    queue.add(node.left!!)
                if (node.right != null)
                    queue.add(node.right!!)
            }
            print("** ")
        }
        println()
    }

    companion object {
        fun build(array: IntArray, i: Int = 0): TreeNode? {
            return if (i < array.size && array[i] != -1) {
                val root = TreeNode(array[i])
                root.left = build(array, 2*i + 1)
                root.right = build(array, 2*i + 2)
                root
            } else {
                null
            }
        }
    }
}
