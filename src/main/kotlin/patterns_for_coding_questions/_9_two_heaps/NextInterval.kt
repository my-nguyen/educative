package patterns_for_coding_questions._9_two_heaps

import patterns_for_coding_questions.Interval
import java.util.*

class NextInterval {
    // i don't understand this solution
    // from educative
    // A brute force solution could be to take one interval at a time and go through all the other intervals to find
    // the next interval. This algorithm will take O(N^2) where ‘N’ is the total number of intervals. Can we do better
    // than that?
    // We can utilize the Two Heaps approach. We can push all intervals into two heaps: one heap to sort the intervals
    // on maximum start time (let’s call it maxStartHeap) and the other on maximum end time (let’s call it maxEndHeap).
    // We can then iterate through all intervals of the `maxEndHeap’ to find their next interval. Our algorithm will
    // have the following steps:
    // 1. Take out the top (having highest end) interval from the maxEndHeap to find its next interval. Let’s call
    //    this interval topEnd.
    // 2. Find an interval in the maxStartHeap with the closest start greater than or equal to the start of topEnd.
    //    Since maxStartHeap is sorted by ‘start’ of intervals, it is easy to find the interval with the highest ‘start’.
    //    Let’s call this interval topStart.
    // 3. Add the index of topStart in the result array as the next interval of topEnd. If we can’t find the next
    //    interval, add ‘-1’ in the result array.
    // 4. Put the topStart back in the maxStartHeap, as it could be the next interval of other intervals.
    // 5. Repeat the steps 1-4 until we have no intervals left in maxEndHeap
    fun findNextInterval(intervals: Array<Interval>): IntArray {
        val n = intervals.size
        val maxStart = PriorityQueue<Int>(n) { i1, i2 -> intervals[i2].start - intervals[i1].start }
        val maxEnd = PriorityQueue<Int>(n) { i1, i2 -> intervals[i2].end - intervals[i1].end }
        for (i in 0 until n) {
            maxStart.offer(i)
            maxEnd.offer(i)
        }

        val result = IntArray(n)
        // go through all the intervals to find each interval's next interval
        for (i in 0 until n) {
            // find the next interval of the interval which has the highest 'end'
            val topEnd = maxEnd.poll()
            // defaults to -1
            result[topEnd] = -1
            if (intervals[maxStart.peek()].start >= intervals[topEnd].end) {
                var topStart = maxStart.poll()
                // find the the interval that has the closest 'start'
                while (maxStart.isNotEmpty() && intervals[maxStart.peek()].start >= intervals[topEnd].end) {
                    topStart = maxStart.poll()
                }
                result[topEnd] = topStart
                // put the interval back as it could be the next interval of other intervals
                maxStart.add(topStart)
            }
        }
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(2,3), intArrayOf(3,4), intArrayOf(5,6)),
                arrayOf(intArrayOf(3,4), intArrayOf(1,5), intArrayOf(4,6)),
            )
            for (array in arrays) {
                val intervals = mutableListOf<Interval>()
                for (pair in array) {
                    val interval = Interval(pair[0], pair[1])
                    intervals.add(interval)
                }
                val result = NextInterval().findNextInterval(intervals.toTypedArray())
                println("next interval indices: ${result.contentToString()}")
            }
        }
    }
}