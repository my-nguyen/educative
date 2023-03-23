package practice1.top_k_elements

import java.util.*

class KthLargestNumberInStream {
    lateinit var minHeap: PriorityQueue<Int>
    var top = 0

    fun KthLargestNumberInStream(array: IntArray, k: Int) {
        minHeap = PriorityQueue<Int> { a, b -> a - b }
        for (i in 0 until k)
            minHeap.add(array[i])
        for (i in k until array.size)
            add(array[i])
        top = k
    }

    fun add(number: Int): Int {
        if (number > minHeap.peek()) {
            minHeap.poll()
            minHeap.add(number)
        }
        return minHeap.peek()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(3,1,5,12,2,11), intArrayOf(3,5,7,9,11,13))
            val tops = arrayOf(4,4)
            val numbers = arrayOf(intArrayOf(6,6,6), intArrayOf(6,10,10,10))
            val stream = KthLargestNumberInStream()
            for (i in arrays.indices) {
                stream.KthLargestNumberInStream(arrays[i], tops[i])
                println("array: ${arrays[i].contentToString()}, top: ${tops[i]}")
                for (number in numbers[i])
                    println("added ${number}: ${stream.add(number)}")
            }
        }
    }
}