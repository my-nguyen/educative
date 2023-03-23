package practice2.merge_intervals

import practice1.Interval

class MergeIntervals {
    fun merge(intervals: MutableList<Interval>): List<Interval> {
        intervals.sortWith { a, b -> a.start - b.start }

        val interval = Interval(intervals[0].start, intervals[0].end)
        val merged = mutableListOf<Interval>()
        for (i in 1 until intervals.size) {
            if (intervals[i].start <= interval.end)
                interval.end = maxOf(interval.end, intervals[i].end)
            else {
                merged.add(interval.copy())
                interval.start = intervals[i].start
                interval.end = intervals[i].end
            }
        }
        merged.add(interval)
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
            for (array in arrays) {
                val intervals = Interval.build(array)
                print("intervals: $intervals, ")
                val merged = MergeIntervals().merge(intervals)
                println("sorted: $intervals, merged: $merged")
            }
        }
    }
}