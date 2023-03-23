package patterns_for_coding_questions._8_tree_depth_first_search

import patterns_for_coding_questions.TreeNode

class CountPathsForASum {

    // from educative
    // This problem follows the Binary Tree Path Sum pattern. We can follow the same DFS approach. But there will be
    // four differences:
    // 1. We will keep track of the current path in a list which will be passed to every recursive call.
    // 2. Whenever we traverse a node we will do two things:
    //    * Add the current node to the current path.
    //    * As we added a new node to the current path, we should find the sums of all sub-paths ending at the current
    //      node. If the sum of any sub-path is equal to ‘S’ we will increment our path count.
    // 3. We will traverse all paths and will not stop processing after finding the first path.
    // 4. Remove the current node from the current path before returning from the function. This is needed to Backtrack
    //    while we are going up the recursive call stack to process other paths.
    fun countPaths(root: TreeNode, sum: Int): Int {
        return educative(root, sum, mutableListOf())
    }

    private fun educative(node: TreeNode?, sum: Int, path: MutableList<Int>): Int {
        if (node == null) {
            return 0
        }

        // add the current node to the path
        path.add(node.value)

        var pathCount = 0
        var pathSum = 0
        // calculate the sum from current node to root. this avoids calculating all sums from root to current node
        for (i in path.lastIndex downTo 0) {
            pathSum += path[i]
            // if a sum matches the target, increment count
            if (pathSum == sum) {
                pathCount++
            }
        }

        // traverse the left and the right sub-trees
        pathCount += educative(node.left, sum, path)
        pathCount += educative(node.right, sum, path)

        // remove the current node from the path to backtrack
        path.removeLast()

        return pathCount
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1), intArrayOf(7, 9), intArrayOf(6, 5, 2, 3)),
                arrayOf(intArrayOf(12), intArrayOf(7, 1), intArrayOf(4, -1, 10, 5)),
            )
            val sums = arrayOf(12, 11)
            for (i in arrays.indices) {
                val root = TreeNode.build(arrays[i])
                val count = CountPathsForASum().countPaths(root, sums[i])
                for (array in arrays[i]) {
                    print("${array.contentToString()}, ")
                }
                println("sum ${sums[i]}, count: $count")
            }
        }
    }
}