package patterns_for_coding_questions._13_top_k_elements

import java.util.*

class TopKFrequentNumbers {
    fun findTopKFrequentNumbers(array: IntArray, top: Int): List<Int> {
        val map = mutableMapOf<Int, Int>()
        // obtain the frequency of each number in array
        for (number in array) {
            val count = map.getOrDefault(number, 0)
            map[number] = count + 1
        }

        val minHeap = PriorityQueue<Int>() { a, b -> map[a]!! - map[b]!! }
        var i = 0
        // go through all numbers of the map and push them in the minHeap, which will have top k frequent numbers.
        // If the heap size is more than k, we remove the smallest (top) number
        for ((k, v) in map) {
            /*if (i >= top) {
                if (v > map[minHeap.peek()]!!) {
                    minHeap.poll()
                    minHeap.add(k)
                }
            } else {
                minHeap.offer(k)
            }
            i++*/
            minHeap.offer(k)
            if (minHeap.size > top) {
                minHeap.poll()
            }
        }

        return minHeap.toList()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1, 3, 5, 12, 11, 12, 11), intArrayOf(5, 12, 11, 3, 11))
            val tops = arrayOf(2, 2)
            for (i in arrays.indices) {
                val numbers = TopKFrequentNumbers().findTopKFrequentNumbers(arrays[i], tops[i])
                println("array: ${arrays[i].contentToString()}, top: ${tops[i]}, frequent numbers: $numbers")
            }
        }
    }
}