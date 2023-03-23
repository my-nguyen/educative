package practice1.top_k_elements

import java.util.*

class SumOfElements {
    fun findSumOfElements(array: IntArray, start: Int, end: Int): Int {
        val maxHeap = PriorityQueue<Int> { a, b -> b - a }
        for (i in 0 until end)
            maxHeap.add(array[i])
        for (i in end until array.size) {
            if (array[i] < maxHeap.peek())
                maxHeap.add(array[i])
        }
        maxHeap.poll()
        val list = mutableListOf<Int>()
        for (i in 0 until end-start-1)
            list.add(maxHeap.poll())
        return list.sum()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,3,12,5,15,11), intArrayOf(3,5,8,7)
            )
            val starts = arrayOf(3, 1)
            val ends = arrayOf(6, 4)
            for (i in arrays.indices) {
                val sum = SumOfElements().findSumOfElements(arrays[i], starts[i], ends[i])
                println("array: ${arrays[i].contentToString()}, start: ${starts[i]}, end: ${ends[i]}, sum: $sum")
            }
        }
    }
}