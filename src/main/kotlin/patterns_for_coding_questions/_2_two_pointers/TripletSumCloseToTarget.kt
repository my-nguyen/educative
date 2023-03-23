package patterns_for_coding_questions._2_two_pointers

import kotlin.math.abs

class TripletSumCloseToTarget {
    fun searchTriplet(array: IntArray, target: Int): Int {
        array.sort()
        // println("array: ${array.contentToString()}, target: $target")
        var minDiff = Integer.MAX_VALUE
        var closestSum = Integer.MAX_VALUE
        for (i in 0..array.size - 2) {
            var left = i + 1
            var right = array.lastIndex
            val goal = target - array[i]
            while (left < right) {
                val sum = array[left] + array[right]
                if (sum == goal) {
                    // println("sum==goal $goal, i: [$i, ${array[i]}], left: [$left, ${array[left]}], right: [$right, ${array[right]}]")
                    return sum
                } else {
                    // println("i: $i:${array[i]}, left: $left:${array[left]}, right: $right:${array[right]}")
                    // println("target: $target, goal: $goal, sum: $sum")
                    val diff = abs(goal - sum)
                    // println("PRE: minDiff: $minDiff, closestSum: $closestSum")
                    if (diff < minDiff) {
                        minDiff = diff
                        closestSum = sum + array[i]
                    } else if (diff == minDiff && sum < closestSum) {
                        closestSum = sum + array[i]
                    }
                    // println("POST: minDiff: $minDiff, closestSum: $closestSum")
                    if (sum < goal) {
                        left++
                    } else {
                        right--
                    }
                }
            }
        }
        return closestSum
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(-2, 0, 1, 2), intArrayOf(-3, -1, 1, 2), intArrayOf(1, 0, 1, 1))
            val targets = arrayOf(2, 1, 100)
            for (i in arrays.indices) {
                val triplet = TripletSumCloseToTarget().searchTriplet(arrays[i], targets[i])
                println("array: ${arrays[i].contentToString()}, target: ${targets[i]}, triplet: $triplet")
            }
        }
    }
}