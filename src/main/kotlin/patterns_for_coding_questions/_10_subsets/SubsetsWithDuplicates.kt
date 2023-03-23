package patterns_for_coding_questions._10_subsets

class SubsetsWithDuplicates {
    // from educative
    // This problem follows the Subsets pattern and we can follow a similar Breadth First Search (BFS) approach. The only
    // additional thing we need to do is handle duplicates. Since the given set can have duplicate numbers, if we follow
    // the same approach discussed in Subsets, we will end up with duplicate subsets, which is not acceptable. To handle
    // this, we will do two extra things:
    // 1. Sort all numbers of the given set. This will ensure that all duplicate numbers are next to each other.
    // 2. Follow the same BFS approach but whenever we are about to process a duplicate (i.e., when the current and
    // the previous numbers are same), instead of adding the current number (which is a duplicate) to all the existing
    // subsets, only add it to the subsets which were created in the previous step.
    // Let’s take Example-2 mentioned above to go through each step of our algorithm:
    // Given set: [1, 5, 3, 3]
    // Sorted set: [1, 3, 3, 5]
    // 1. Start with an empty set: [[]]
    // 2. Add the first number (1) to all the existing subsets to create new subsets: [[], [1]];
    // 3. Add the second number (3) to all the existing subsets: [[], [1], [3], [1,3]].
    // 4. The next number (3) is a duplicate. If we add it to all existing subsets we will get:
    //    [[], [1], [3], [1,3], [3], [1,3], [3,3], [1,3,3]]
    //    We got two duplicate subsets: [3], [3], and [1,3], [1,3]
    //    Whereas we only needed the new subsets: [3,3], [1,3,3]
    //    To handle this instead of adding (3) to all the existing subsets ([[], [1], [3], [1,3]]), we only add it to
    //    the new subsets which were created in the previous (3rd) step, which is only [[3], [1,3]]:
    //    [[], [1], [3], [1,3], [3,3], [1,3,3]]
    // 5. Finally, add the forth number (5) to all the existing subsets: [[], [1], [3], [1,3], [3,3], [1,3,3], [5], [1,5], [3,5], [1,3,5], [3,3,5], [1,3,3,5]]
    fun findSubsets(array: IntArray): List<List<Int>> {
        // sort the numbers to handle duplicates
        array.sort()

        val subsets = mutableListOf<List<Int>>()
        val emptySet = listOf<Int>()
        subsets.add(emptySet)
        var start = 0
        var end = 0
        for (i in array.indices) {
            start = 0
            // if current and the previous elements are the same, create new subsets only from the subsets added in
            // the previous step
            if (i > 0 && array[i] == array[i-1]) {
                start = end + 1
            }
            end = subsets.size - 1

            for (j in start..end) {
                // create a new subset from the existing subset and add the current element to it
                val subset = subsets[j].toMutableList()
                subset.add(array[i])
                subsets.add(subset)
            }
        }
        return subsets
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,3,3), intArrayOf(1,5,3,3))
            for (array in arrays) {
                val subsets = SubsetsWithDuplicates().findSubsets(array)
                for (subset in subsets) {
                    print("$subset, ")
                }
                println()
            }
        }
    }
}