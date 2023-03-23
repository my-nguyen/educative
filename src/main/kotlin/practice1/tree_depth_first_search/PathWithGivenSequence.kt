package practice1.tree_depth_first_search

import practice1.TreeNode

class PathWithGivenSequence {
    fun findPath(root: TreeNode, sequence: IntArray): Boolean {
        return findPath(root, sequence, 0)
    }

    fun findPath(node: TreeNode?, sequence: IntArray, i: Int): Boolean {
        return if (node == null || node.value != sequence[i])
            false
        else if (node.left == null && node.right == null)
            i == sequence.lastIndex
        else {
            findPath(node.left, sequence, i+1) || findPath(node.right, sequence, i+1)
        }
    }

    fun findPath1(root: TreeNode, sequence: IntArray): Boolean {
        return findPath1(root, sequence, 0, mutableListOf())
    }

    fun findPath1(node: TreeNode?, sequence: IntArray, i: Int, path: MutableList<Int>): Boolean {
        if (node == null || node.value != sequence[i])
            return false

        if (node.left == null && node.right == null && i == sequence.lastIndex)
            return true

        path.add(node.value)
        val left = findPath1(node.left, sequence, i+1, path)
        val right = findPath1(node.right, sequence, i+1, path)
        path.remove(node.value)
        return left || right
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,7,9,-1,-1,2,9), intArrayOf(1,0,1,1,-1,6,5), intArrayOf(1,0,1,1,-1,6,5)
            )
            val sequences = arrayOf(
                intArrayOf(1,9,9), intArrayOf(1,0,7), intArrayOf(1,1,6)
            )
            for (i in arrays.indices) {
                val root = TreeNode.build(arrays[i])!!
                root.print()
                val isPath = PathWithGivenSequence().findPath(root, sequences[i])
                println("sequence: ${sequences[i].contentToString()} is path? $isPath")
            }
        }
    }
}