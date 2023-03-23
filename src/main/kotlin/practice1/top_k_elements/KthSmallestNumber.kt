package practice1.top_k_elements

import java.util.*

class KthSmallestNumber {
    fun findKthSmallestNumber(array: IntArray, smallest: Int): Int {
        // return findKthSmallestNumber1(array, smallest)
        return findKthSmallestNumber2(array, smallest)
    }

    fun findKthSmallestNumber1(array: IntArray, smallest: Int): Int {
        val minHeap = PriorityQueue<Int>()
        for (number in array)
            minHeap.add(number)
        for (i in 1 until smallest)
            minHeap.poll()
        return minHeap.peek()
    }

    fun findKthSmallestNumber2(array: IntArray, smallest: Int): Int {
        val maxHeap = PriorityQueue<Int>() { a, b -> b - a }
        // val minHeap = PriorityQueue<Int>()
        for (i in 0 until smallest)
            maxHeap.add(array[i])
        for (i in smallest until array.size) {
            if (array[i] >= maxHeap.peek())
                continue
            else {
                maxHeap.poll()
                maxHeap.add(array[i])
            }
        }
        return maxHeap.peek()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = IntArray(15) { it }
            array.shuffle()
            val arrays = arrayOf(
                intArrayOf(1,5,12,2,11,5), intArrayOf(1,5,12,2,11,5), intArrayOf(5,12,11,-1,12), array, array, array
            )
            val bottoms = arrayOf(3, 4, 3, 5, 8, 10)
            for (i in arrays.indices) {
                val smallest = KthSmallestNumber().findKthSmallestNumber(arrays[i], bottoms[i])
                println("array: ${arrays[i].contentToString()}, bottom: ${bottoms[i]}, smallest: $smallest")
            }
        }
    }
}