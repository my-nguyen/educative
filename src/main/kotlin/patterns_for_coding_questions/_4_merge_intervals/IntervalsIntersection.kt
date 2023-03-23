package patterns_for_coding_questions._4_merge_intervals

import patterns_for_coding_questions.Interval

class IntervalsIntersection {
    // from educative
    // This problem follows the Merge Intervals pattern. As we have discussed under InsertInterval, there are five
    // overlapping possibilities between two intervals ‘a’ and ‘b’. A close observation will tell us that whenever
    // the two intervals overlap, one of the interval’s start time lies within the other interval. This rule can help us
    // identify if any two intervals overlap or not.
    // 1. 'a' and 'b' don't overlap
    // 2. 'a' and 'b' overlap. 'b' start time lies within 'a'; 'b' end time lies outside 'a'
    // 3. 'a' and 'b' overlap. 'b' start time lies within 'a'; 'b' end time lies within 'a'
    // 4. 'a' and 'b' overlap. 'a' start time lies within 'b'; 'a' end time lies outside 'b'
    // 5. 'a' and 'b' overlap. 'a' start time lies within 'b'; 'a' end time lies within 'b'
    // Now, if we have found that the two intervals overlap, how can we find the overlapped part?
    // Again from the above diagram, the overlapping interval will be equal to:
    //    start = max(a.start, b.start)
    //    end = min(a.end, b.end)
    // That is, the highest start time and the lowest end time will be the overlapping interval.
    // So our algorithm will be to iterate through both the lists together to see if any two intervals overlap. If two
    // intervals overlap, we will insert the overlapped part into a result list and move on to the next interval which is
    // finishing early.
    fun merge(in1: Array<Interval>, in2: Array<Interval>): Array<Interval> {
        val list = mutableListOf<Interval>()
        var i = 0
        var j = 0
        while (i < in1.size && j < in2.size) {
            // check if the interval arr[i] intersects with arr2[j]
            // check if one of the interval's start time lies within the other interval
            if ((in1[i].start >= in2[j].start && in1[i].start <= in2[j].end) ||
                (in2[j].start >= in1[i].start && in2[j].start <= in1[i].end)) {
                // store the intersection part
                val start = maxOf(in1[i].start, in2[j].start)
                val end = minOf(in1[i].end, in2[j].end)
                val interval = Interval(start, end)
                list.add(interval)
            }

            // move next from the interval which is finishing first
            if (in1[i].end < in2[j].end)
                i++
            else
                j++
        }

        return list.toTypedArray()
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
            val data1 = arrayOf(
                arrayOf(intArrayOf(1, 3), intArrayOf(5, 6), intArrayOf(7, 9)),
                arrayOf(intArrayOf(1, 3), intArrayOf(5, 7), intArrayOf(9, 12)),
            )
            val data2 = arrayOf(
                arrayOf(intArrayOf(2, 3), intArrayOf(5, 7)),
                arrayOf(intArrayOf(5, 10))
            )
            val intersection = IntervalsIntersection()
            for (i in data1.indices) {
                val list1 = Interval.build(data1[i])
                val list2 = Interval.build(data2[i])
                intersection.print("intervals1", list1)
                intersection.print(", intervals2", list2)
                val merged = intersection.merge(list1.toTypedArray(), list2.toTypedArray())
                intersection.print(", merged", merged.toList())
                println()
            }
        }
    }
}