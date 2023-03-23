package patterns_for_coding_questions._4_merge_intervals

import patterns_for_coding_questions.Interval

class MergeIntervals2 {
    fun getOverlaps(intervals: MutableList<Interval>): List<Pair<Interval, Interval>> {
        intervals.sortWith { o1, o2 -> o1.start - o2.start }
        val list = mutableListOf<Pair<Interval, Interval>>()
        for (i in 0 until intervals.lastIndex) {
            for (j in i + 1..intervals.lastIndex) {
                if (intervals[i].end >= intervals[j].start) {
                    val pair = Pair(intervals[i], intervals[j])
                    list.add(pair)
                }
            }
        }
        return list
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
                print("intervals: ")
                for (interval in intervals) {
                    print("$interval, ")
                }
                val pairs = MergeIntervals2().getOverlaps(intervals)
                print("overlaps: ")
                for (pair in pairs) {
                    print("{${pair.first}, ${pair.second}}, ")
                }
                println()
            }
        }
    }
}