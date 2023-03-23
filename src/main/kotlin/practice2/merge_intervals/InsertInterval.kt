package practice2.merge_intervals

import practice1.Interval


class InsertInterval {
    fun insert(intervals: MutableList<Interval>, newInterval: Interval): List<Interval> {
        val inserted = mutableListOf<Interval>()
        var i = 0
        while (i < intervals.size && intervals[i].end < newInterval.start) {
            inserted.add(intervals[i])  // inserted=[[1,3]]
            i++
        }
        while (i < intervals.size && intervals[i].start <= newInterval.end) { // 2 < 4
            newInterval.start = minOf(newInterval.start, intervals[i].start) // start=1
            newInterval.end = maxOf(newInterval.end, intervals[i].end)       // end=4
            i++                                                              // i=1
        }
        inserted.add(newInterval) // inserted=[[1,4], [5,7]]
        for (j in i until intervals.size)
            inserted.add(intervals[i])
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