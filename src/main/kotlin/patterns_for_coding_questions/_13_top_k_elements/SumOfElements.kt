package patterns_for_coding_questions._13_top_k_elements

import java.util.*

class SumOfElements {
    fun findSumOfElements(numbers: IntArray, k1: Int, k2: Int): Int {
        // return mine(numbers, k1, k2)
        // return useMinHeap(numbers, k1, k2)
        return useMaxHeap(numbers, k1, k2)
    }

    // from educative
    // This problem follows the Top ‘K’ Numbers pattern, and shares similarities with Kth Smallest Number.
    // We can find the sum of all numbers coming between the K1’th and K2’th smallest numbers in the following steps:
    // 1. First, insert all numbers in a min-heap.
    // 2. Remove the first K1 smallest numbers from the min-heap.
    // 3. Now take the next K2-K1-1 numbers out of the heap and add them. This sum will be our required output.
    private fun useMinHeap(numbers: IntArray, k1: Int, k2: Int): Int {
        val minHeap = PriorityQueue { n1: Int, n2: Int -> n1 - n2 }
        // insert all numbers to the min heap
        for (i in 0 until numbers.size) minHeap.add(numbers.get(i))

        // remove k1 small numbers from the min heap
        for (i in 0 until k1) minHeap.poll()

        var elementSum = 0
        // sum next k2-k1-1 numbers
        for (i in 0 until k2 - k1 - 1) elementSum += minHeap.poll()

        return elementSum
    }

    // from educative
    // We can iterate the array and use a max-heap to keep track of the top K2 numbers. We can, then, add the top
    // K2-K1-1 numbers in the max-heap to find the sum of all numbers coming between the K1’th and K2’th smallest numbers.
    private fun useMaxHeap(numbers: IntArray, k1: Int, k2: Int): Int {
        val maxHeap = PriorityQueue { n1: Int, n2: Int -> n2 - n1 }
        // keep smallest k2 numbers in the max heap
        for (i in numbers.indices) {
            if (i < k2 - 1) {
                maxHeap.add(numbers[i])
            } else if (numbers[i] < maxHeap.peek()) {
                maxHeap.poll() // as we are interested only in the smallest k2 numbers
                maxHeap.add(numbers[i])
            }
        }

        // get the sum of numbers between k1 and k2 indices these numbers will be at the top of the max heap
        var elementSum = 0
        for (i in 0 until k2 - k1 - 1) elementSum += maxHeap.poll()

        return elementSum
    }

    private fun mine(numbers: IntArray, k1: Int, k2: Int): Int {
        val minHeap = PriorityQueue<Int>()
        val size = k2 - k1
        for (number in numbers) {
            minHeap.offer(number)
            if (minHeap.size > size) {
                minHeap.poll()
            }
        }
        var sum = 0
        for (i in 1 until size) {
            sum += minHeap.poll()
        }
        return sum
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1, 3, 12, 5, 15, 11), intArrayOf(3, 5, 8, 7)
            )
            val K1s = arrayOf(3, 1)
            val K2s = arrayOf(6, 4)
            for (i in arrays.indices) {
                val sum = SumOfElements().findSumOfElements(arrays[i], K1s[i], K2s[i])
                println("array: ${arrays[i].contentToString()}, k1: ${K1s[i]}, k2: ${K2s[i]}, sum: $sum")
            }
        }
    }
}