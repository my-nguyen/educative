package practice2.merge_intervals

import practice1.Interval

class ConflictingAppointments {
    fun canAttendAllAppointments(intervals: MutableList<Interval>): Boolean {
        intervals.sortWith { a, b -> a.start - b.start }
        var current = intervals[0]
        for (i in 1 until intervals.size) {
            if (intervals[i].start < current.end)
                return false
            else
                current = intervals[i]
        }
        return true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1, 4), intArrayOf(2, 5), intArrayOf(7, 9)),
                arrayOf(intArrayOf(6, 7), intArrayOf(2, 4), intArrayOf(8, 12)),
                arrayOf(intArrayOf(4, 5), intArrayOf(2, 3), intArrayOf(3, 6)),
            )
            for (array in arrays) {
                val intervals = Interval.build(array)
                print("intervals: $intervals, ")
                val canAttendAll = ConflictingAppointments().canAttendAllAppointments(intervals)
                println("sorted: $intervals, can attend all? $canAttendAll")
            }
        }
    }
}