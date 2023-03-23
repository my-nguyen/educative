package patterns_for_coding_questions._8_tree_depth_first_search

import patterns_for_coding_questions.TreeNode

class PathWithGivenSequence {
    fun findPath(root: TreeNode?, sequence: IntArray): Boolean {
        // return mine(root, sequence)
        return educative(root, sequence)
    }

    private fun educative(root: TreeNode?, sequence: IntArray): Boolean {
        return if (root == null) {
            sequence.isEmpty()
        } else {
            educative(root, sequence, 0)
        }
    }

    private fun educative(node: TreeNode?, sequence: IntArray, index: Int): Boolean {
        return if (node == null) {
            false
        } else if (index >= sequence.size || node.value != sequence[index]) {
            false
        } else if (node.left == null && node.right == null && index == sequence.size-1) {
            true
        } else {
            educative(node.left, sequence, index+1) || educative(node.right, sequence, index+1)
        }
    }

    private fun mine(root: TreeNode?, sequence: IntArray): Boolean {
        return if (root == null) {
            false
        } else if (sequence.isEmpty() || root.value != sequence.first()) {
            false
        } else if (root.left == null && root.right == null && sequence.size == 1) {
            true
        } else {
            val subarray = sequence.copyOfRange(1, sequence.size)
            findPath(root.left, subarray) || findPath(root.right, subarray)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(7,9), intArrayOf(-1,-1,2,9)),
                arrayOf(intArrayOf(1), intArrayOf(0,1), intArrayOf(1,-1,6,5)),
                arrayOf(intArrayOf(1), intArrayOf(0,1), intArrayOf(1,-1,6,5)),
            )
            val sequences = arrayOf(
                intArrayOf(1,9,9), intArrayOf(1,0,7), intArrayOf(1,1,6)
            )
            for (i in arrays.indices) {
                val root = TreeNode.build(arrays[i])
                val isPath = PathWithGivenSequence().findPath(root, sequences[i])
                println("${sequences[i].contentToString()} is path? $isPath")
            }
        }
    }
}