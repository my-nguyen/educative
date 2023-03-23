package practice1.tree_depth_first_search

import practice1.TreeNode

class FindAllTreePaths {
    fun findPaths(root: TreeNode, sum: Int): List<List<Int>> {
        val allPaths = mutableListOf<List<Int>>()
        findPaths(root, sum, mutableListOf(), allPaths)
        return allPaths
    }

    fun findPaths(node: TreeNode?, sum: Int, path: MutableList<Int>, allPaths: MutableList<List<Int>>) {
        if (node == null)
            return

        path.add(node.value)
        if (node.left == null && node.right == null) {
            if (node.value == sum)
                allPaths.add(path.toList())
        } else {
            findPaths(node.left, sum, path, allPaths)
            findPaths(node.right, sum, path, allPaths)
        }
        path.remove(node.value)
    }

    fun findPaths5(root: TreeNode, sum: Int): List<List<Int>> {
        val path = mutableListOf<Int>()
        val allPaths = mutableListOf<List<Int>>()
        findPaths(root, sum, path, allPaths)
        return allPaths
    }

    fun findPaths5(node: TreeNode?, sum: Int, path: MutableList<Int>, allPaths: MutableList<List<Int>>) {
        if (node == null)
            return

        path.add(node.value)
        if (node.left == null && node.right == null) {
            if (node.value == sum)
                allPaths.add(path.toList())
        } else {
            findPaths(node.left, sum-node.value, path, allPaths)
            findPaths(node.right, sum-node.value, path, allPaths)
        }
        path.remove(node.value)
    }
    fun findPaths4(root: TreeNode, sum: Int): List<List<Int>> {
        val path = mutableListOf<Int>()
        val allPaths = mutableListOf<List<Int>>()
        findPaths(root, sum, path, allPaths)
        return allPaths
    }

    fun findPaths4(node: TreeNode?, sum: Int, path: MutableList<Int>, allPaths: MutableList<List<Int>>) {
        if (node == null)
            return

        path.add(node.value)
        if (node.left == null && node.right == null) {
            if (node.value == sum)
                allPaths.add(path.toList())
        } else {
            findPaths(node.left, sum-node.value, path, allPaths)
            findPaths(node.right, sum-node.value, path, allPaths)
        }
        path.remove(node.value)
    }

    fun findPaths3(root: TreeNode, sum: Int): List<List<Int>> {
        val path = mutableListOf<Int>()
        val allPaths = mutableListOf<List<Int>>()
        findPaths3(root, sum, path, allPaths)
        return allPaths
    }

    fun findPaths3(node: TreeNode?, sum: Int, path: MutableList<Int>, allPaths: MutableList<List<Int>>) {
        if (node == null)
            return

        path.add(node.value)
        if (node.left == null && node.right == null) {
            if (node.value == sum)
                allPaths.add(path.toList())
        } else {
            findPaths3(node.left, sum-node.value, path, allPaths)
            findPaths3(node.right, sum-node.value, path, allPaths)
        }
        path.remove(node.value)
    }

    fun findPaths2(root: TreeNode, sum: Int): List<List<Int>> {
        val path = mutableListOf<Int>()
        val allPaths = mutableListOf<List<Int>>()
        findPaths3(root, sum, path, allPaths)
        return allPaths
    }

    fun findPaths2(node: TreeNode?, sum: Int, path: MutableList<Int>, allPaths: MutableList<List<Int>>) {
        if (node == null)
            return

        path.add(node.value)
        if (node.left == null && node.right == null) {
            if (node.value == sum) {
                allPaths.add(path.toList())
            }
        } else {
            findPaths3(node.left, sum-node.value, path, allPaths)
            findPaths3(node.right, sum-node.value, path, allPaths)
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