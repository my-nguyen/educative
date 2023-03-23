package patterns_for_coding_questions._10_subsets

import patterns_for_coding_questions.TreeNode

class StructurallyUniqueBinarySearchTrees {
    // from educative
    // This problem follows the patterns_for_coding_questions.Subsets pattern and is quite similar to Evaluate Expression. Following a similar approach,
    // we can iterate from 1 to ‘n’ and consider each number as the root of a tree. All smaller numbers will make up
    // the left sub-tree and bigger numbers will make up the right sub-tree. We will make recursive calls for the left
    // and right sub-trees
    fun findUniqueTrees(number: Int): List<TreeNode?> {
        return if (number <= 0) {
            mutableListOf()
        } else {
            findRecursive(1, number)
        }
    }

    private fun findRecursive(start: Int, end: Int): List<TreeNode?> {
        val trees = mutableListOf<TreeNode?>()
        // base condition, return 'null' for an empty sub-tree
        // consider n=1, in this case we will have start=end=1, this means we should have only one tree
        // we will have two recursive calls, findUniqueTreesRecursive(1, 0) & (2, 1)
        // both of these should return 'null' for the left and the right child
        if (start > end) {
            trees.add(null)
            return trees
        }

        for (i in start..end) {
            // making 'i' root of the tree
            val left = findRecursive(start, i - 1)
            val right = findRecursive(i + 1, end)
            for (l in left) {
                for (r in right) {
                    val node = TreeNode(i, l, r)
                    trees.add(node)
                }
            }
        }
        return trees
    }


    companion object {
        @JvmStatic
        fun main() {
            val numbers = arrayOf(2, 3)
            for (number in numbers) {
                val trees = StructurallyUniqueBinarySearchTrees().findUniqueTrees(number)
                println("number: $number, trees: $trees")
            }
        }
    }
    // Since our algorithm has overlapping subproblems, can we use memoization to improve it? We could, but every time
    // we return the result of a subproblem from the cache, we have to clone the result list because these trees will be
    // used as the left or right child of a tree. This cloning is equivalent to reconstructing the trees, therefore,
    // the overall time complexity of the memoized algorithm will also be the same.
}