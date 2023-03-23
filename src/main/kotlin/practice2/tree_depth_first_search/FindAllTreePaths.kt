package practice2.tree_depth_first_search

import practice2.TreeNode

class FindAllTreePaths {
    fun findPaths(root: TreeNode, sum: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        dfs(root, sum, mutableListOf(), result)
        return result
    }

    private fun dfs(node: TreeNode?, sum: Int, path: MutableList<Int>, paths: MutableList<List<Int>>) {
        if (node == null)
            return

        path.add(node.value)
        if (node.left == null && node.right == null) {
            if (node.value == sum)
                paths.add(path.toList())
        } else {
            dfs(node.left, sum-node.value, path, paths)
            dfs(node.right, sum-node.value, path, paths)
        }
        path.remove(node.value)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,7,9,4,5,2,7), intArrayOf(12,7,1,4,1,10,5))
            val sums = arrayOf(12, 23)
            for (i in arrays.indices) {
                val tree = TreeNode.build(arrays[i])!!
                tree.print()
                val paths = FindAllTreePaths().findPaths(tree, sums[i])
                print("sum: ${sums[i]}, paths: ")
                for (path in paths)
                    print("$path, ")
                println()
            }
        }
    }
}