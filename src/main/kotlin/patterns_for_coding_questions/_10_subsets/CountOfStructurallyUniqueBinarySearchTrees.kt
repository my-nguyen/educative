package patterns_for_coding_questions._10_subsets

class CountOfStructurallyUniqueBinarySearchTrees {
    fun countTrees(n: Int): Int {
        // return count(n)
        return memoization(n)
    }

    private fun count(n: Int): Int {
        if (n <= 1) {
            return 1
        }

        var count = 0
        for (i in 1..n) {
            // making 'i' root of the tree
            val left = countTrees(i - 1)
            val right = countTrees(n - i)
            count += left * right
        }
        return count
    }

    // Our algorithm has overlapping subproblems as our recursive call will be evaluating the same sub-expression
    // multiple times. To resolve this, we can use memoization and store the intermediate results in a HashMap. In each
    // function call, we can check our map to see if we have already evaluated this sub-expression before.
    val map = mutableMapOf<Int, Int>()
    fun memoization(n: Int): Int {
        if (map.containsKey(n)) {
            return map[n]!!
        }

        if (n <= 1) {
            return 1
        }

        var count = 0
        for (i in 1..n) {
            // making 'i' root of the tree
            val left = countTrees(i - 1)
            val right = countTrees(n - i)
            count += left * right
        }

        map[n] = count
        return count
    }

    companion object {
        @JvmStatic
        fun main() {
            val counts = arrayOf(2, 3)
            for (n in counts) {
                val count = CountOfStructurallyUniqueBinarySearchTrees().countTrees(n)
                println("n: $n, count: $count")
            }
        }
    }
}