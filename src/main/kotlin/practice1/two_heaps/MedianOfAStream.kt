package practice1.two_heaps

import java.util.*

class MedianOfAStream {
    val minHeap = PriorityQueue<Int>()
    val maxHeap = PriorityQueue<Int>() { a, b -> b - a }

    fun insertNum(number: Int) {
        if (maxHeap.isEmpty() || number <= maxHeap.peek())
            maxHeap.add(number)
        else
            minHeap.add(number)

        if (maxHeap.size > minHeap.size + 1)
            minHeap.add(maxHeap.poll())
        else if (maxHeap.size < minHeap.size)
            maxHeap.add(minHeap.poll())
    }

    fun findMedian(): Double {
        return if (maxHeap.size == minHeap.size)
            (maxHeap.peek() + minHeap.peek()) / 2.0
        else
            maxHeap.peek().toDouble()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val stream = MedianOfAStream()
            stream.insertNum(3)
            stream.insertNum(1)
            println("median: " + stream.findMedian())
            stream.insertNum(5)
            println("median: " + stream.findMedian())
            stream.insertNum(4)
            println("median: " + stream.findMedian())
        }
    }
}