package practice1.tree_depth_first_search

import practice1.TreeNode

class CountAllPathSum {
    fun countPaths(root: TreeNode, target: Int): Int {
        return countPaths(root, target, mutableListOf())
    }

    fun countPaths(node: TreeNode?, target: Int, path: MutableList<Int>): Int {
        if (node == null)
            return 0
        path.add(node.value)
        var sum = 0
        var count = 0
        for (i in path.indices.reversed()) {
            sum += path[i]
            if (sum == target)
                count++
        }

        count += countPaths(node.left, target, path)
        count += countPaths(node.right, target, path)
        path.remove(node.value)
        return count
    }
    fun countPaths1(root: TreeNode, sum: Int): Int {
        return countPaths1(root, sum, mutableListOf())
    }

    fun countPaths1(node: TreeNode?, target: Int, path: MutableList<Int>): Int {
        if (node == null)
            return 0

        path.add(node.value)
        var count = 0
        var sum = 0
        for (i in path.indices.reversed()) {
            sum += path[i]
            if (sum == target)
                count++
        }

        count += countPaths1(node.left, target, path)
        count += countPaths1(node.right, target, path)

        path.remove(node.value)
        return count
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