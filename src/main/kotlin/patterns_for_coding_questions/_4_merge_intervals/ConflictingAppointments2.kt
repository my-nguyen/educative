package patterns_for_coding_questions._4_merge_intervals

import patterns_for_coding_questions.Interval

class ConflictingAppointments2 {
    fun findConflictingAppointments(intervals: MutableList<Interval>): List<Pair<Interval, Interval>> {
        // so intervals must be declared as MutableList<patterns_for_coding_questions.Interval>
        intervals.sortWith { o1, o2 -> o1.start - o2.start }
        val list = mutableListOf<Pair<Interval, Interval>>()
        for (i in 0 until intervals.lastIndex) {
            if (intervals[i + 1].start < intervals[i].end) {
                val pair = Pair(intervals[i], intervals[i + 1])
                list.add(pair)
            }
        }
        return list
    }

    private fun print(label: String, intervals: List<Interval>) {
        print("$label: {")
        for (interval in intervals) {
            print("$interval, ")
        }
        print("}")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1, 4), intArrayOf(2, 5), intArrayOf(7, 9)),
                arrayOf(intArrayOf(6, 7), intArrayOf(2, 4), intArrayOf(8, 12)),
                arrayOf(intArrayOf(4, 5), intArrayOf(2, 3), intArrayOf(3, 6)),
            )
            val conflict = ConflictingAppointments2()
            for (array in arrays) {
                val intervals = Interval.build(array)
                conflict.print("intervals", intervals)
                val conflicts = conflict.findConflictingAppointments(intervals)
                print(", conflicts: ")
                if (conflicts.isEmpty()) {
                    println("none")
                } else {
                    for (conflict in conflicts) {
                        print("${conflict.first} and ${conflict.second}, ")
                    }
                    println()
                }
            }
        }
    }
}