package patterns_for_coding_questions._9_two_heaps

import java.util.*

class SlidingWindowMedian {
    fun findSlidingWindowMedian(array: IntArray, window: Int): DoubleArray {
        // return mine(array, window)
        return educative(array, window)
    }

    // from educative
    // This problem follows the Two Heaps pattern and share similarities with Find the Median of a Number Stream. We can
    // follow a similar approach of maintaining a max-heap and a min-heap for the list of numbers to find their median.
    // The only difference is that we need to keep track of a sliding window of ‘k’ numbers. This means, in each iteration,
    // when we insert a new number in the heaps, we need to remove one number from the heaps which is going out of
    // the sliding window. After the removal, we need to rebalance the heaps in the same way that we did while inserting.
    private val minHeap = PriorityQueue<Int>()
    private val maxHeap = PriorityQueue<Int> { a, b -> b - a }

    private fun educative(array: IntArray, window: Int): DoubleArray {
        val count = array.size - window + 1
        val medians = DoubleArray(count)

        for (i in array.indices) {
            // always make maxHeap contain more elements than minHeap
            insert(array[i])
            rebalance()

            val index = i - window + 1
            if (index >= 0) {
                // there's at least 'window' elements in the sliding window: add the median to the the medians array
                if (maxHeap.size == minHeap.size) {
                    // even number of elements: take the average of middle two elements
                    medians[index] = (maxHeap.peek()+ minHeap.peek()) / 2.0
                } else {
                    // median is the top of maxHeap since it always has one more element than minHeap
                    medians[index] = maxHeap.peek().toDouble()
                }

                // remove the outgoing element from the sliding window
                delete(array[index])
                rebalance()
            }
        }
        return medians
    }

    private fun delete(number: Int) {
        if (number <= maxHeap.peek()) {
            maxHeap.remove(number)
        } else {
            minHeap.remove(number)
        }
    }

    private fun insert(number: Int) {
        if (maxHeap.isEmpty() || number <= maxHeap.peek()) {
            maxHeap.offer(number)
        } else {
            minHeap.offer(number)
        }
    }

    private fun rebalance() {
        if (maxHeap.size > minHeap.size + 1) {
            minHeap.offer(maxHeap.poll())
        } else if (maxHeap.size < minHeap.size) {
            maxHeap.offer(minHeap.poll())
        }
    }

    // incorrect solution with a sliding window median
    private fun mine(array: IntArray, window: Int): DoubleArray {
        var sum = 0
        for (i in 0 until window) {
            sum += array[i]
        }
        val count = array.size - window + 1
        val medians = DoubleArray(count)
        medians[0] = sum / window.toDouble()

        for (i in 1 until count) {
            sum = sum - array[i-1] + array[i+window-1]
            medians[i] = sum / window.toDouble()
        }
        return medians
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1, 2, -1, 3, 5), intArrayOf(1, 2, -1, 3, 5),
            )
            val windows = arrayOf(2, 3)
            for (i in arrays.indices) {
                val median = SlidingWindowMedian().findSlidingWindowMedian(arrays[i], windows[i])
                println("array: ${arrays[i].contentToString()}, window: ${windows[i]}, median: ${median.contentToString()}")
            }
        }
    }
}