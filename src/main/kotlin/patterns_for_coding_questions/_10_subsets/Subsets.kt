package patterns_for_coding_questions._10_subsets

class Subsets {
    // from educative
    // To generate all subsets of the given set, we can use the Breadth First Search (BFS) approach. We can start with
    // an empty set, iterate through all numbers one-by-one, and add them to existing sets to create new subsets.
    // Let’s take the example-2 mentioned above to go through each step of our algorithm:
    // Given set: [1, 5, 3]
    // 1. Start with an empty set: [[]]
    // 2. Add the first number (1) to all the existing subsets to create new subsets: [[], [1]];
    // 3. Add the second number (5) to all the existing subsets: [[], [1], [5], [1,5]];
    // 4. Add the third number (3) to all the existing subsets: [[], [1], [5], [1,5], [3], [1,3], [5,3], [1,5,3]].
    fun findSubsets(array: IntArray): List<List<Int>> {
        val subsets = mutableListOf<List<Int>>()
        val emptySet = listOf<Int>()
        // start by adding the empty subset
        subsets.add(emptySet)
        for (number in array) {
            val size = subsets.size
            println("number: $number, size: $size")
            for (i in 0 until size) {
                // take all existing subsets and insert the current number in them to create new subsets
                val subset = subsets[i].toMutableList()
                print("new subset, before: $subset")
                subset.add(number)
                println(", after: $subset")
                subsets.add(subset)
            }
        }
        return subsets
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(/*intArrayOf(1,3), intArrayOf(1,5,3), */intArrayOf(1,3,3))
            for (array in arrays) {
                val subsets = Subsets().findSubsets(array)
                for (subset in subsets) {
                    print("$subset, ")
                }
                println()
            }
        }
    }
}