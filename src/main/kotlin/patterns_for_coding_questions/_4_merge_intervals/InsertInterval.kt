package patterns_for_coding_questions._4_merge_intervals

import patterns_for_coding_questions.Interval
import java.util.*

class InsertInterval {
    // from educative
    // If the given list was not sorted, we could have simply appended the new interval to it and used the merge()
    // function from Merge Intervals. But since the given list is sorted, we should try to come up with a solution
    // better than O(N∗logN)
    // When inserting a new interval in a sorted list, we need to first find the correct index where the new interval
    // can be placed. In other words, we need to skip all the intervals which end before the start of the new interval.
    // So we can iterate through the given sorted listed of intervals and skip all the intervals with the following condition:
    //    intervals[i].end < newInterval.start
    // Once we have found the correct place, we can follow an approach similar to Merge Intervals to insert and/or merge
    // the new interval. Let’s call the new interval ‘a’ and the first interval with the above condition ‘b’. There are
    // five possibilities:
    // 1. 'a' and 'b' don't overlap, we simply insert 'a'
    // 2. a.start < b.start, a.end < b.end, the new merged interval will be c(a.start, b.end)
    // 3. a.start < b.start, b.end < a.end, the new merged interval will be c(a.start, a.end)
    // 3. b.start < a.start, b.end < a.end, the new merged interval will be c(b.start, a.end)
    // 5. b.start < a.start, a.end < b.end, the new merged interval will be c(b.start, b.end)
    // The above clearly shows the merging approach. To handle all four merging scenarios, we need to do something like this:
    //    c.start = min(a.start, b.start)
    //    c.end = max(a.end, b.end)
    // Our overall algorithm will look like this:
    // 1. Skip all intervals which end before the start of the new interval, i.e., skip all intervals with the following condition:
    //    intervals[i].end < newInterval.start
    // 2. Let’s call the last interval ‘b’ that does not satisfy the above condition. If ‘b’ overlaps with the new
    //    interval (a) (i.e. b.start <= a.end), we need to merge them into a new interval ‘c’:
    //    c.start = min(a.start, b.start)
    //    c.end = max(a.end, b.end)
    // 3. We will repeat the above two steps to merge ‘c’ with the next overlapping interval.
    fun insert(intervals: List<Interval>, interval: Interval): List<Interval> {
        if (intervals.isNullOrEmpty()) {
            return Arrays.asList(interval)
        }

        val merged = mutableListOf<Interval>()
        var i = 0
        // skip (and save) all intervals that come before the new interval
        while (i < intervals.size && intervals[i].end < interval.start) {
            merged.add(intervals[i])
            i++
        }
        // print("one", merged)

        // merge all intervals that overlap with the new interval
        while (i < intervals.size && intervals[i].start <= interval.end) {
            interval.start = minOf(interval.start, intervals[i].start)
            interval.end = maxOf(interval.end, intervals[i].end)
            i++
        }
        // print("two", merged)

        // insert the new patterns_for_coding_questions.Interval
        merged.add(interval)
        // print("three", merged)

        // add all the remaining intervals
        while (i < intervals.size) {
            merged.add(intervals[i])
            i++
        }
        // print("four", merged)

        return merged
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1, 3), intArrayOf(5, 7), intArrayOf(8, 12)),
                arrayOf(intArrayOf(1, 3), intArrayOf(5, 7), intArrayOf(8, 12)),
                arrayOf(intArrayOf(2, 3), intArrayOf(5, 7)),
            )
            val pairs = arrayOf(
                intArrayOf(4, 6), intArrayOf(4, 10), intArrayOf(1, 4)
            )
            for (i in arrays.indices) {
                val intervals = Interval.build(arrays[i])
                val interval = Interval(pairs[i][0], pairs[i][1])
                val merged = InsertInterval().insert(intervals, interval)
                println("intervals: $intervals, new: $interval, merged: $merged")
            }
        }
    }
}