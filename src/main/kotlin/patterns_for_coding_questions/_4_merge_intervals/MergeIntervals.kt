package patterns_for_coding_questions._4_merge_intervals

import patterns_for_coding_questions.Interval

class MergeIntervals {
    // from educative
    // Let’s take the example of two intervals (‘a’ and ‘b’) such that a.start <= b.start. There are four possible scenarios:
    // 1. 'a' and 'b' do not overlap
    // 2. some part of 'b' overlaps with 'a'
    // 3. 'a' fully overlaps 'b'
    // 4. 'b' fully overlaps 'a' but both have same start time
    // Our goal is to merge the intervals whenever they overlap. For the above-mentioned three overlapping scenarios
    // (2, 3, and 4), this is how we will merge them:
    // 2. c(a.start, b.end)
    // 3. c(a.start, a.end)
    // 4. c(a.start, b.end)
    // This clearly shows a merging approach. Our algorithm will look like this:
    // 1. Sort the intervals on the start time to ensure a.start <= b.start
    // 2. If ‘a’ overlaps ‘b’ (i.e. b.start <= a.end), we need to merge them into a new interval ‘c’ such that:
    //    c.start = a.start
    //    c.end = max(a.end, b.end)
    // We will keep repeating the above two steps to merge ‘c’ with the next interval if it overlaps with ‘c’.
    fun merge(intervals: MutableList<Interval>): List<Interval> {
        if (intervals.size < 2) {
            return intervals
        }

        // sort the intervals by start time
        intervals.sortWith { o1, o2 -> o1.start - o2.start }
        return merge2(intervals)
    }

    private fun merge1(intervals: List<Interval>): List<Interval> {
        val merged = mutableListOf<Interval>()
        val iterator = intervals.iterator()
        var interval = iterator.next()
        var start = interval.start
        var end = interval.end
        while (iterator.hasNext()) {
            interval = iterator.next()
            if (interval.start <= end) {
                // overlapping intervals, adjust the 'end'
                end = maxOf(end, interval.end)
            } else {
                // non-overlapping interval, add the previous interval and reset
                merged.add(Interval(start, end))
                start = interval.start
                end = interval.end
            }
        }
        // add the last interval
        merged.add(Interval(start, end))
        return merged
    }

    private fun merge2(intervals: List<Interval>): List<Interval> {
        val merged = mutableListOf<Interval>()
        var start = intervals[0].start
        var end = intervals[0].end
        for (i in 1..intervals.lastIndex) {
            val interval = intervals[i]
            if (interval.start <= end) {
                // overlapping intervals: adjust the 'end'
                end = maxOf(end, interval.end)
            } else {
                // non-overlapping intervals: save the previous interval and reset 'start' and 'end'
                merged.add(Interval(start, end))
                start = interval.start
                end = interval.end
            }
        }
        // add the last interval
        merged.add(Interval(start, end))
        return merged
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1, 4), intArrayOf(2, 5), intArrayOf(7, 9)),
                arrayOf(intArrayOf(6, 7), intArrayOf(2, 4), intArrayOf(5, 9)),
                arrayOf(intArrayOf(1, 4), intArrayOf(2, 6), intArrayOf(3, 5)),
            )
            val merge = MergeIntervals()
            for (array in arrays) {
                val intervals = Interval.build(array)
                val merged = merge.merge(intervals)
                println("intervals: $intervals, merged: $merged")
            }
        }
    }
}