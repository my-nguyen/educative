package practice2.tree_depth_first_search

import practice2.TreeNode

class PathWithGivenSequence {
    fun findPath(root: TreeNode, sequence: IntArray): Boolean {
        return dfs(root, sequence, 0, mutableListOf())
    }

    private fun dfs(node: TreeNode?, sequence: IntArray, index: Int, list: MutableList<Int>): Boolean {
        /*if (node == null) // 1,7,9,-1,-1,2,9 * 199
            return false
        if (node.value != sequence[index]) // 2 != 9
            return false

        if (node.left == null && node.right == null) {
            return index == sequence.lastIndex
        } else {
            list.add(node.value) // [1,9]
            if (dfs(node.left, sequence, index+1, list)) // 2
                return true
            val tmp = dfs(node.right, sequence, index+1, list) // right=9, index=2
            list.remove(node.value)
            return tmp
        }*/
        return when {
            node == null -> false
            node.value != sequence[index] -> false
            node.left == null && node.right == null -> index == sequence.lastIndex
            else -> {
                list.add(node.value)
                if (dfs(node.left, sequence, index+1, list))
                    true
                else {
                    val result = dfs(node.right, sequence, index+1, list) // right=9, index=2
                    list.remove(node.value)
                    result
                }
            }
        }
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