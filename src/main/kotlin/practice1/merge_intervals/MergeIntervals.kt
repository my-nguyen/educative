package practice1.merge_intervals

import practice1.Interval

class MergeIntervals {
    fun merge(intervals: MutableList<Interval>): List<Interval> {
        intervals.sortWith { a, b -> a.start - b.start }

        var current = intervals[0].copy()
        val merged = mutableListOf<Interval>()
        merged.add(current)
        for (i in 1 until intervals.size) { // intArrayOf(6, 7), intArrayOf(2, 4), intArrayOf(5, 9)
            if (intervals[i].start <= current.end)
                current.end = maxOf(current.end, intervals[i].end)
            else {
                current = intervals[i].copy()
                merged.add(current)
            }
        }
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
                val merged = MergeIntervals().merge(intervals)
                println("intervals: $intervals, merged: $merged")
            }
        }
    }
}