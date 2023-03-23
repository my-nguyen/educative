package practice1.merge_intervals

import practice1.Interval


class InsertInterval {
    fun insert(intervals: MutableList<Interval>, newInterval: Interval): List<Interval> {
        intervals.add(newInterval)
        intervals.sortWith { a, b -> a.start - b.start }
        // println("merged sorted: $intervals")

        val inserted = mutableListOf<Interval>()
        var current = intervals[0].copy()
        inserted.add(current)
        for (i in 1 until intervals.size) {
            if (intervals[i].start <= current.end) {
                current.end = maxOf(intervals[i].end, intervals[i - 1].end)
                // println("i: $i, int: ${intervals[i]}, current.end: ${current.end}")
            } else {
                current = intervals[i].copy()
                inserted.add(current)
                // println("inserted $current")
            }
        }
        return inserted
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
                val newInterval = Interval(pairs[i][0], pairs[i][1])
                print("intervals: $intervals, new: $newInterval, ")
                val inserted = InsertInterval().insert(intervals, newInterval)
                println("inserted: $inserted")
            }
        }
    }
}