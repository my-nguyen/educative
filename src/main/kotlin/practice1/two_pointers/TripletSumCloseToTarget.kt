package practice1.two_pointers

import kotlin.math.abs

class TripletSumCloseToTarget {
    fun searchTriplet(array: IntArray, targetSum: Int): Int {
        array.sort()

        var minDiff = Int.MAX_VALUE
        val triplets = mutableListOf<IntArray>()
        for (i in 0 until array.size-2) {
            var j = i + 1
            var k = array.lastIndex
            while (j < k) {
                val sum = array[i] + array[j] + array[k]
                val diff = abs(targetSum - sum)
                // println("i: $i, sum: $sum, diff: $diff, minDiff: $minDiff")
                if (diff <= minDiff) {
                    if (diff < minDiff) {
                        triplets.clear()
                        minDiff = diff
                    }
                    val triplet = intArrayOf(array[i], array[j], array[k])
                    triplets.add(triplet)
                    // println("added triplet: ${triplet.contentToString()}")
                }
                when {
                    sum < targetSum -> j++
                    sum > targetSum -> k--
                    else -> {
                        j++
                        k--
                    }
                }
                // println("i: $i, j: $j, k: $k, sum: $sum")
            }
        }
        val sums = triplets.map { it.sum() }
        return sums.minOrNull()!!
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