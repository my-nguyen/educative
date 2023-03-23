package patterns_for_coding_questions._13_top_k_elements

import java.util.*

class KthSmallestNumber {
    fun findKthSmallestNumber(array: IntArray, bottom: Int): Int {
        // return mine(array, bottom)
        return educative(array, bottom)
    }

    // from educative
    // This problem follows the Top ‘K’ Numbers pattern but has two differences:
    // 1. Here we need to find the Kth smallest number, whereas in Top ‘K’ Numbers we were dealing with ‘K’ largest numbers.
    // 2. In this problem, we need to find only one number (Kth smallest) compared to finding all ‘K’ largest numbers.
    // We can follow the same approach as discussed in the ‘Top K Elements’ problem. To handle the first difference
    // mentioned above, we can use a max-heap instead of a min-heap. As we know, the root is the biggest element in
    // the max heap. So, since we want to keep track of the ‘K’ smallest numbers, we can compare every number with
    // the root while iterating through all numbers, and if it is smaller than the root, we’ll take the root out and
    // insert the smaller number.
    private fun educative(nums: IntArray, k: Int): Int {
        val maxHeap = PriorityQueue<Int> { n1, n2 -> n2 - n1 }
        // put first k numbers in the max heap
        for (i in 0 until k) {
            maxHeap.add(nums[i])
        }

        // go through the remaining numbers of the array, if the number from the array is smaller than the top (biggest)
        // number of the heap, remove the top number from heap and add the number from array
        for (i in k until nums.size) {
            if (nums[i] < maxHeap.peek()) {
                maxHeap.poll()
                maxHeap.add(nums[i])
            }
        }

        // the root of the heap has the Kth smallest number
        return maxHeap.peek()
    }

    private fun mine(array: IntArray, bottom: Int): Int {
        val minHeap = PriorityQueue<Int>()
        for (number in array) {
            minHeap.offer(number)
        }

        for (i in 1 until bottom) {
            minHeap.poll()
        }
        return minHeap.peek()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1, 5, 12, 2, 11, 5), intArrayOf(1, 5, 12, 2, 11, 5), intArrayOf(5, 12, 11, -1, 12)
            )
            val bottoms = arrayOf(3, 4, 3)
            for (i in arrays.indices) {
                val smallest = KthSmallestNumber().findKthSmallestNumber(arrays[i], bottoms[i])
                println("array: ${arrays[i].contentToString()}, bottom: ${bottoms[i]}, smallest: $smallest")
            }
        }
    }
}