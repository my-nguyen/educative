package practice2.tree_depth_first_search

import practice2.TreeNode

class CountAllPathSum {
    fun countPaths(root: TreeNode, target: Int): Int {
        // return dfs1(root, target, mutableListOf())
        return dfs2(root, target, mutableListOf())
    }

    private fun dfs2(node: TreeNode?, target: Int, path: MutableList<Int>): Int {
        if (node == null)
            return 0

        path.add(node.value)
        var count = if (match(target, path)) 1 else 0
        count += dfs2(node.left, target, path) + dfs2(node.right, target, path)
        path.remove(node.value)
        return count
    }

    private fun dfs1(node: TreeNode?, target: Int, path: MutableList<Int>): Int {
        if (node == null)
            return 0

        path.add(node.value)
        var count = if (match(target, path)) 1 else 0
        count += dfs1(node.left, target, path) + dfs1(node.right, target, path)
        path.remove(node.value)
        return count
    }

    private fun match(target: Int, path: List<Int>): Boolean {
        var sum = 0
        for (i in path.indices.reversed()) {
            sum += path[i]
            if (sum == target)
                return true
        }
        return false
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,7,9,6,5,2,3),
                intArrayOf(12,7,1,4,-1,10,5)
            )
            val sums = arrayOf(12, 11)
            for (i in arrays.indices) {
                val root = TreeNode.build(arrays[i])!!
                root.print()
                val count = CountAllPathSum().countPaths(root, sums[i])
                println("sum ${sums[i]}, count: $count")
            }
        }
    }
}