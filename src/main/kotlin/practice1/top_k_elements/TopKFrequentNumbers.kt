package practice1.top_k_elements

import java.util.*

class TopKFrequentNumbers {
    fun findTopKFrequentNumbers(array: IntArray, top: Int): List<Int> {
        val map = mutableMapOf<Int, Int>()
        for (number in array) {
            val count = map.getOrDefault(number, 0)
            map[number] = count + 1
        }

        val maxHeap = PriorityQueue<Int> { a, b -> map[b]!! - map[a]!! }
        for ((k, v) in map) {
            maxHeap.add(k)
        }
        val numbers = mutableListOf<Int>()
        for (i in 0 until top)
            numbers.add(maxHeap.poll())
        return numbers
    }

    fun findTopKFrequentNumbers2(array: IntArray, top: Int): List<Int> {
        val map = mutableMapOf<Int, Int>()
        for (number in array) {
            val count = map.getOrDefault(number, 0)
            map[number] = count + 1
        }

        val minHeap = PriorityQueue<Int> { a, b -> map[a]!! - map[b]!! }
        var i = 0
        for (number in map.keys) {
            if (i < top)
                minHeap.add(number)
            else {
                if (map[number]!! <= map[minHeap.peek()]!!)
                    continue
                else {
                    minHeap.poll()
                    minHeap.add(number)
                }
            }
            i++
        }
        return minHeap.toList()
    }

    fun findTopKFrequentNumbers1(array: IntArray, top: Int): List<Int> {
        val map = mutableMapOf<Int, Int>()
        for (number in array) {
            val count = map.getOrDefault(number, 0)
            map[number] = count + 1
        }
        val minHeap = PriorityQueue<Int> { a, b -> map[a]!! - map[b]!! }
        var i = 0
        for ((k, v) in map) {
            if (i < top)
                minHeap.add(k)
            else {
                if (map[k]!! <= map[minHeap.peek()]!!)
                    continue
                else {
                    minHeap.poll()
                    minHeap.add(k)
                }
            }
            i++
        }

        return minHeap.toList()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = intArrayOf(1,2,2,3,3,3,4,4,4,4,5,5,5,5,5,6,6,6,6,6,6,7,7,7,7,7,7,7,8,8,8,8,8,8,8,8)
            val arrays = arrayOf(
                intArrayOf(1,3,5,12,11,12,11), intArrayOf(5,12,11,3,11),
                array, array, array, array, array, array
            )
            val tops = arrayOf(2, 2, 1, 2, 3, 4, 5, 6)
            for (i in arrays.indices) {
                val numbers = TopKFrequentNumbers().findTopKFrequentNumbers(arrays[i], tops[i])
                println("array: ${arrays[i].contentToString()}, top: ${tops[i]}, frequent numbers: $numbers")
            }
        }
    }
}