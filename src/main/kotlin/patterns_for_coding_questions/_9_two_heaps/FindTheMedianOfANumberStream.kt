package patterns_for_coding_questions._9_two_heaps

import java.util.*

class FindTheMedianOfANumberStream {
    class MedianOfAStream {
        private val mine = Mine()
        private val educative = Educative()

        fun insert(number: Int) {
            // mine.insert(number)
            educative.insert(number)
        }

        fun findMedian(): Double {
            // return mine.findMedian()
            return educative.findMedian()
        }
    }

    class Educative {
        private val minHeap = PriorityQueue<Int>()
        private val maxHeap = PriorityQueue<Int> { o1, o2 -> o2 - o1 }

        fun insert(number: Int) {
            // 1. insert: maxHeap will contain small entries, and minHeap all big entries
            // the very first entry, and those that are smaller than maxHeap max go into maxHeap
            if (maxHeap.isEmpty() || number <= maxHeap.peek()) {
                maxHeap.offer(number)
            } else {
                // the entries that are bigger than maxHeap max go to minHeap
                minHeap.offer(number)
            }

            // 2: balance: so that maxHeap either has 0 or 1 more entries than minHeap
            // maxHeap cannot have more than 1 element than minHeap
            if (maxHeap.size > minHeap.size + 1) {
                minHeap.offer(maxHeap.poll())
            } else if (maxHeap.size < minHeap.size) {
                // maxHeap cannot have fewer elements than minHeap
                maxHeap.offer(minHeap.poll())
            }
        }

        fun findMedian(): Double {
            return if (maxHeap.size == minHeap.size) {
                (maxHeap.peek() + minHeap.peek()) / 2.0
            } else {
                // because max-heap will have one more element than the min-heap
                maxHeap.peek().toDouble()
            }
        }
    }

    class Mine {
        private val minHeap = PriorityQueue<Int>()
        private val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())
        private var tmp = 0
        private var count = 0

        fun insert(number: Int) {
            if (count == 0) {
                tmp = number
            } else if (count == 1) {
                val min = minOf(tmp, number)
                val max = maxOf(tmp, number)
                minHeap.offer(max)
                maxHeap.offer(min)
            } else {
                if (number < maxHeap.peek()) {
                    maxHeap.offer(number)
                    while (maxHeap.size >= minHeap.size + 2) {
                        minHeap.offer(maxHeap.poll())
                    }
                } else {
                    minHeap.offer(number)
                    while (minHeap.size >= maxHeap.size + 2) {
                        maxHeap.offer(minHeap.poll())
                    }
                }
            }
            count++
        }

        fun findMedian(): Double {
            return if (minHeap.size == maxHeap.size) {
                (minHeap.peek() + maxHeap.peek()) / 2.toDouble()
            } else if (minHeap.size > maxHeap.size) {
                minHeap.peek().toDouble()
            } else {
                maxHeap.peek().toDouble()
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val stream = MedianOfAStream()
            stream.insert(3)
            stream.insert(1)
            println("The median is: " + stream.findMedian())
            stream.insert(5)
            println("The median is: " + stream.findMedian())
            stream.insert(4)
            println("The median is: " + stream.findMedian())
        }
    }
}