package practice2.merge_intervals

import practice1.Interval

class IntervalsIntersection {
    fun merge(array1: Array<Interval>, array2: Array<Interval>): Array<Interval> {
        // return sample(array1, array2)
        // return practice1(array1, array2)
        // return practice2(array1, array2)
        // return practice3(array1, array2)
        return practice4(array1, array2)
    }

    fun practice4(array1: Array<Interval>, array2: Array<Interval>): Array<Interval> {
        val result = mutableListOf<Interval>()
        var i = 0
        var j = 0
        while (i < array1.size && j < array2.size) {
            val interval1 = array1[i]
            val interval2 = array2[j]
            if (interval1.start in interval2.start..interval2.end || interval2.start in interval1.start..interval1.end) {
                val start = maxOf(interval1.start, interval2.start)
                val end = minOf(interval1.end, interval2.end)
                val interval = Interval(start, end)
                result.add(interval)
            }
            if (interval1.end < interval2.end)
                i++
            else
                j++
        }
        return result.toTypedArray()
    }

    fun practice3(array1: Array<Interval>, array2: Array<Interval>): Array<Interval> {
        val result = mutableListOf<Interval>()
        var i = 0
        var j = 0
        while (i < array1.size && j < array2.size) {
            val interval1 = array1[i]
            val interval2 = array2[j]
            if (interval1.start in interval2.start..interval2.end || interval2.start in interval1.start..interval1.end) {
                val start = maxOf(interval1.start, interval2.start)
                val end = minOf(interval1.end, interval2.end)
                val interval = Interval(start, end)
                result.add(interval)
            }
            if (interval1.end < interval2.end)
                i++
            else
                j++
        }
        return result.toTypedArray()
    }

    fun practice2(array1: Array<Interval>, array2: Array<Interval>): Array<Interval> {
        val result = mutableListOf<Interval>()
        var i = 0
        var j = 0
        while (i < array1.size && j < array2.size) {
            if (array1[i].start in array2[j].start..array2[j].end || array2[j].start in array1[i].start..array1[i].end) {
                val start = maxOf(array1[i].start, array2[j].start)
                val end = minOf(array1[i].end, array2[j].end)
                val interval = Interval(start, end)
                result.add(interval)
            }
            if (array1[i].end < array2[j].end)
                i++
            else
                j++
        }
        return result.toTypedArray()
    }

    fun practice1(array1: Array<Interval>, array2: Array<Interval>): Array<Interval> {
        val result = mutableListOf<Interval>()
        var i = 0
        var j = 0
        while (i < array1.size && j < array2.size) {
            if (array1[i].start in array2[j].start..array2[j].end || array2[j].start in array1[i].start..array1[i].end) {
                val start = maxOf(array1[i].start, array2[j].start)
                val end = minOf(array1[i].end, array2[j].end)
                val interval = Interval(start, end)
                result.add(interval)
            }
            if (array1[i].end < array2[j].end)
                i++
            else
                j++
        }
        return result.toTypedArray()
    }

    fun sample(array1: Array<Interval>, array2: Array<Interval>): Array<Interval> {
        val merged = mutableListOf<Interval>()
        var i1 = 0
        var i2 = 0
        while (i1 < array1.size && i2 < array2.size) {
            if (array1[i1].start in array2[i2].start..array2[i2].end || array2[i2].start in array1[i1].start..array1[i1].end) {
                val start = maxOf(array1[i1].start, array2[i2].start)
                val end = minOf(array1[i1].end, array2[i2].end)
                val interval = Interval(start, end)
                merged.add(interval)
            }
            if (array1[i1].end < array2[i2].end)
                i1++
            else
                i2++
        }
        return merged.toTypedArray()
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
            for (i in data1.indices) {
                val list1 = Interval.build(data1[i])
                val list2 = Interval.build(data2[i])
                print("intervals1: $list1")
                print(", intervals2: $list2, ")
                val intersection = IntervalsIntersection().merge(list1.toTypedArray(), list2.toTypedArray())
                println("intersection ${intersection.toList()}")
                // println()
            }
        }
    }
}