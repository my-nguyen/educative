package patterns_for_coding_questions._13_top_k_elements

import java.lang.Math.abs
import java.util.*


class KClosestNumbers {
    fun findClosestElements(numbers: IntArray, K: Int, X: Int): List<Int> {
        // return mine(numbers, K, X)
        // return useMinHeap(numbers, K, X)
        return useTwoPointers(numbers, K, X)
    }

    // from educative
    // This problem follows the Top ‘K’ Numbers pattern. The biggest difference in this problem is that we need to find
    // the closest (to ‘X’) numbers compared to finding the overall largest numbers. Another difference is that the given
    // array is sorted.
    // Utilizing a similar approach, we can find the numbers closest to ‘X’ through the following algorithm:
    // 1. Since the array is sorted, we can first find the number closest to ‘X’ through Binary Search. Let’s say that
    //    number is ‘Y’.
    // 2. The ‘K’ closest numbers to ‘Y’ will be adjacent to ‘Y’ in the array. We can search in both directions of ‘Y’
    //    to find the closest numbers.
    // 3. We can use a heap to efficiently search for the closest numbers. We will take ‘K’ numbers in both directions
    //    of ‘Y’ and push them in a Min Heap sorted by their absolute difference from ‘X’. This will ensure that
    //    the numbers with the smallest difference from ‘X’ (i.e., closest to ‘X’) can be extracted easily from the Min Heap.
    // 4. Finally, we will extract the top ‘K’ numbers from the Min Heap to find the required numbers
    data class Entry(val key: Int, val value: Int)
    private fun useMinHeap(numbers: IntArray, K: Int, X: Int): List<Int> {
        val index = binarySearch(numbers, X)
        var low = index - K
        var high = index + K
        low = Math.max(low, 0) // 'low' should not be less than zero
        high = Math.min(high, numbers.lastIndex) // 'high' should not be greater the size of the array
        
        val minHeap = PriorityQueue<Entry>() { a, b -> a.key - b.key }
        // add all candidate elements to the min heap, sorted by their absolute difference from 'X'
        for (i in low..high) {
            minHeap.add(Entry(abs(numbers[i] - X), i))
        }

        // we need the top 'K' elements having smallest difference from 'X'
        val result = mutableListOf<Int>()
        for (i in 0 until K) {
            result.add(numbers[minHeap.poll().value])
        }

        return result.sorted()
    }

    // i don't understand this solution
    // from educative
    // After finding the number closest to ‘X’ through Binary Search, we can use the Two Pointers approach to find
    // the ‘K’ closest numbers. Let’s say the closest number is ‘Y’. We can have a left pointer to move back from ‘Y’
    // and a right pointer to move forward from ‘Y’. At any stage, whichever number pointed out by the left or the right
    // pointer gives the smaller difference from ‘X’ will be added to our result list.
    // To keep the resultant list sorted we can use a Queue. So whenever we take the number pointed out by the left
    // pointer, we will append it at the beginning of the list and whenever we take the number pointed out by the right
    // pointer we will append it at the end of the list.
    private fun useTwoPointers(numbers: IntArray, K: Int, X: Int): List<Int> {
        val result = mutableListOf<Int>()
        val index = binarySearch(numbers, X)
        var leftPointer = index
        var rightPointer = index + 1
        for (i in 0 until K) {
            if (leftPointer >= 0 && rightPointer < numbers.size) {
                val diff1 = abs(X - numbers[leftPointer])
                val diff2 = abs(X - numbers[rightPointer])
                if (diff1 <= diff2) {
                    // append in the beginning
                    result.add(0, numbers[leftPointer--])
                } else {
                    // append at the end
                    result.add(numbers[rightPointer++])
                }
            } else if (leftPointer >= 0) {
                result.add(0, numbers[leftPointer--])
            } else if (rightPointer < numbers.size) {
                result.add(numbers[rightPointer++])
            }
        }
        return result
    }

    private fun mine(numbers: IntArray, K: Int, X: Int): List<Int> {
        val maxHeap = PriorityQueue<Int>() { a, b -> abs(b-X) - abs(a-X) }
        for (number in numbers) {
            maxHeap.offer(number)
            if (maxHeap.size > K) {
                maxHeap.poll()
            }
        }
        return maxHeap.toList()
    }

    private fun binarySearch(arr: IntArray, target: Int): Int {
        var low = 0
        var high = arr.lastIndex
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] < target -> low = mid + 1
                arr[mid] > target -> high = mid - 1
                else -> return mid
            }
        }
        return if (low > 0) low - 1 else low
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(5, 6, 7, 8, 9), intArrayOf(2, 4, 5, 6, 9), intArrayOf(2, 4, 5, 6, 9)
            )
            val Ks = arrayOf(3, 3, 3)
            val Xs = arrayOf(7, 6, 10)
            for (i in arrays.indices) {
                val closest = KClosestNumbers().findClosestElements(arrays[i], Ks[i], Xs[i])
                println("array: ${arrays[i].contentToString()}, K: ${Ks[i]}, X: ${Xs[i]}, closest elements: $closest")
            }
        }
    }
}