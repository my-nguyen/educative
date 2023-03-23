package patterns_for_coding_questions._13_top_k_elements

import java.util.*

class MaximumDistinctElements {
    // i don't understand this solution
    // from educative
    // This problem follows the Top ‘K’ Numbers pattern, and shares similarities with Top ‘K’ Frequent Numbers.
    // We can following a similar approach as discussed in Top ‘K’ Frequent Numbers problem:
    // 1. First, we will find the frequencies of all the numbers.
    // 2. Then, push all numbers that are not distinct (i.e., have a frequency higher than one) in a Min Heap based on
    //    their frequencies. At the same time, we will keep a running count of all the distinct numbers.
    // 3. Following a greedy approach, in a stepwise fashion, we will remove the least frequent number from the heap
    //    (i.e., the top element of the min-heap), and try to make it distinct. We will see if we can remove all
    //    occurrences of a number except one. If we can, we will increment our running count of distinct numbers.
    //    We have to also keep a count of how many removals we have done.
    // 4. If after removing elements from the heap, we are still left with some deletions, we have to remove some
    //    distinct elements
    fun findMaximumDistinctElements(numbers: IntArray, k: Int): Int {
        var distinctElementsCount = 0
        if (numbers.size <= k) {
            return distinctElementsCount
        }

        // find the frequency of each number
        val numFrequencyMap = mutableMapOf<Int, Int>()
        for (i in numbers) {
            numFrequencyMap[i] = numFrequencyMap.getOrDefault(i, 0) + 1
        }

        val minHeap = PriorityQueue<Map.Entry<Int, Int>> { (_, v1), (_, v2) -> v1 - v2 }

        // insert all numbers with frequency greater than '1' into the min-heap
        for (entry in numFrequencyMap.entries) {
            if (entry.value == 1) {
                distinctElementsCount++
            } else {
                minHeap.add(entry)
            }
        }

        // following a greedy approach, try removing the least frequent numbers first from the min-heap
        var k = k
        while (k > 0 && !minHeap.isEmpty()) {
            val (_, value) = minHeap.poll()
            // to make an element distinct, we need to remove all of its occurrences except one
            k -= value - 1
            if (k >= 0) {
                distinctElementsCount++
            }
        }

        // if k > 0, this means we have to remove some distinct numbers
        if (k > 0) {
            distinctElementsCount -= k
        }

        return distinctElementsCount
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(7, 3, 5, 8, 5, 3, 3), intArrayOf(3, 5, 12, 11, 12), intArrayOf(1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5)
            )
            val Ks = arrayOf(2, 3, 2)
            for (i in arrays.indices) {
                val distinct = MaximumDistinctElements().findMaximumDistinctElements(arrays[i], Ks[i])
                println("array: ${arrays[i].contentToString()}, k: ${Ks[i]}, distinct: $distinct")
            }
        }
    }
}