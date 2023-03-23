package patterns_for_coding_questions._4_merge_intervals

import patterns_for_coding_questions.Interval
import java.util.*

class EmployeeFreeTime {
    data class EmployeeInterval(var interval: Interval, val employeeIndex: Int, val intervalIndex: Int)

    fun findEmployeeFreeTime(schedule: List<List<Interval>>): List<Interval> {
        // return mine(schedule)
        return educative(schedule)
    }

    // Solution #
    // This problem follows the Merge Intervals pattern. Let’s take the above-mentioned example (2) and visually draw it:
    //    Input: Employee Working Hours=[[[1,3], [9,12]], [[2,4]], [[6,8]]]
    //    Output: [4,6], [8,9]
    // One simple solution can be to put all employees’ working hours in a list and sort them on the start time. Then
    // we can iterate through the list to find the gaps. Let’s dig deeper. Sorting the intervals of the above example
    // will give us:
    //    [1,3], [2,4], [6,8], [9,12]
    // We can now iterate through these intervals, and whenever we find non-overlapping intervals (e.g., [2,4] and [6,8]),
    // we can calculate a free interval (e.g., [4,6]). This algorithm will take O(N∗logN) time, where ‘N’ is the total
    // number of intervals. This time is needed because we need to sort all the intervals. The space complexity will be
    // O(N), which is needed for sorting. Can we find a better solution?
    // Using a Heap to Sort the Intervals #
    // One fact that we are not utilizing is that each employee list is individually sorted!
    // How about we take the first interval of each employee and insert it in a Min Heap. This Min Heap can always give
    // us the interval with the smallest start time. Once we have the smallest start-time interval, we can then compare
    // it with the next smallest start-time interval (again from the Heap) to find the gap. This interval comparison is
    // similar to what we suggested in the previous approach.
    // Whenever we take an interval out of the Min Heap, we can insert the same employee’s next interval. This also means
    // that we need to know which interval belongs to which employee.
    fun educative(schedule: List<List<Interval>>): List<Interval> {
        // PriorityQueue to store one interval from each employee
        val minHeap = PriorityQueue<EmployeeInterval> { i1, i2 -> i1.interval.start - i2.interval.start }

        // insert the first interval of each employee to the queue
        for (i in schedule.indices) {
            val interval = EmployeeInterval(schedule[i][0], i, 0)
            minHeap.offer(interval)
        }

        val result = mutableListOf<Interval>()
        var previous = minHeap.peek()
        while (minHeap.isNotEmpty()) {
            val top = minHeap.poll()
            if (previous.interval.end < top.interval.start) {
                // if previous is not overlapping with the next interval, insert a free interval
                val interval = Interval(previous.interval.end, top.interval.start)
                result.add(interval)
                previous.interval = top.interval
            } else if (previous.interval.end < top.interval.end) {
                // overlapping intervals, update the previousInterval if needed
                previous.interval = top.interval
            }

            // if there are more intervals available for the same employee, add their next interval
            val employeeSchedule = schedule[top.employeeIndex]
            if (employeeSchedule.size > top.intervalIndex + 1) {
                val interval = employeeSchedule[top.intervalIndex + 1]
                val employeeIndex = top.employeeIndex
                val intervalIndex = top.intervalIndex + 1
                val employee = EmployeeInterval(interval, employeeIndex, intervalIndex)
                minHeap.offer(employee)
            }
        }
        return result
    }

    fun mine(schedule: List<List<Interval>>): List<Interval> {
        // convert the 2D schedule into a 1D list of all intervals
        var intervals = mutableListOf<Interval>()
        for (list in schedule) {
            for (interval in list) {
                intervals.add(interval)
            }
        }
        // sort this 1D list, for merging purpose
        intervals.sortWith { i1, i2 -> i1.start - i2.start }
        // print("sorted", intervals)

        // merge the 1D list of intervals
        val merged = mutableListOf<Interval>()
        var start = intervals[0].start
        var end = intervals[0].end
        for (i in 1..intervals.lastIndex) {
            val interval = intervals[i]
            if (end >= interval.start) {
                end = maxOf(end, interval.end)
            } else {
                merged.add(Interval(start, end))
                start = interval.start
                end = interval.end
            }
        }
        merged.add(Interval(start, end))
        // print("merged", merged)

        // create a list of gaps based on the end time and start time of consecutive intervals
        val gaps = mutableListOf<Interval>()
        for (i in 0 until merged.lastIndex) {
            val interval = Interval(merged[i].end, merged[i + 1].start)
            gaps.add(interval)
        }
        return gaps
    }

    private fun print(label: String, intervals: List<Interval>) {
        print("$label: {")
        for (interval in intervals) {
            print("$interval, ")
        }
        println("}")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val data = arrayOf(
                arrayOf(
                    arrayOf(intArrayOf(1, 3), intArrayOf(5, 6)),
                    arrayOf(intArrayOf(2, 3), intArrayOf(6, 8))
                ),
                arrayOf(
                    arrayOf(intArrayOf(1, 3), intArrayOf(9, 12)),
                    arrayOf(intArrayOf(2, 4), intArrayOf(6, 8))
                ),
                arrayOf(
                    arrayOf(intArrayOf(1, 3), intArrayOf(2, 4)),
                    arrayOf(intArrayOf(3, 5), intArrayOf(7, 9))
                ),
            )
            for (arrays in data) {
                val schedule = mutableListOf<List<Interval>>()
                for (array in arrays) {
                    val intervals = Interval.build(array)
                    schedule.add(intervals)
                }
                print("schedule: $schedule")
                val freeTime = EmployeeFreeTime().findEmployeeFreeTime(schedule)
                println(", free time: $freeTime")
            }
        }
    }
}