package practice2.two_heaps

import java.util.*

class MedianOfAStream {
    val minQueue = PriorityQueue<Int>()
    val maxQueue = PriorityQueue<Int> { a, b -> b - a }

    fun insertNum(number: Int) {
        // insert1(number)
        // insert2(number)
        insert3(number)
    }

    fun insert3(number: Int) {
        if (maxQueue.isEmpty() || number <= maxQueue.peek())
            maxQueue.add(number)
        else
            minQueue.add(number)
        if (maxQueue.size > minQueue.size + 1)
            minQueue.add(maxQueue.poll())
        else if (maxQueue.size < minQueue.size)
            maxQueue.add(minQueue.poll())
    }

    fun insert2(number: Int) {
        if (maxQueue.isEmpty() || number <= maxQueue.peek())
            maxQueue.add(number)
        else
            minQueue.add(number)

        if (maxQueue.size > minQueue.size + 1)
            minQueue.add(maxQueue.poll())
        else if (maxQueue.size < minQueue.size)
            maxQueue.add(minQueue.poll())
    }

    fun insert1(number: Int) {
        if (maxQueue.isEmpty() || number <= maxQueue.peek())
            maxQueue.add(number)
        else
            minQueue.add(number)

        if (maxQueue.size > minQueue.size + 1)
            minQueue.add(maxQueue.poll())
        else if (maxQueue.size < minQueue.size)
            maxQueue.add(minQueue.poll())
    }

    fun findMedian(): Double {
        return if (maxQueue.size == minQueue.size)
            (maxQueue.peek() + minQueue.peek()) / 2.0
        else
            maxQueue.peek().toDouble()
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