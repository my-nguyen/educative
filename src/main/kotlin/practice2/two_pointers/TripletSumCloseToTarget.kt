package practice2.two_pointers

import kotlin.math.abs

class TripletSumCloseToTarget {
    fun searchTriplet(array: IntArray, target: Int): Int { // -2,0,1,2; 2
        array.sort() // -2,0,1,2
        var minDiff = Int.MAX_VALUE
        var minSum = Int.MAX_VALUE
        for (i in 0 until array.size-2) { // 0
            var j = i + 1 // 1
            var k = array.lastIndex // 3
            while (j < k) { // 2 < 3
                val sum = array[i] + array[j] + array[k] // 0, 1
                val diff = abs(sum - target) // 1
                if (diff < minDiff || (diff == minDiff && sum < minSum)) {
                    minDiff = diff // 2, 1
                    minSum = sum // 0, 1
                }
                when {
                    sum < target -> j++ // 2, 3
                    sum > target -> k--
                    else -> break
                }
            }
        }
        return minSum
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(-2,0,1,2), intArrayOf(-3,-1,1,2), intArrayOf(1,0,1,1))
            val targets = arrayOf(2, 1, 100)
            for (i in arrays.indices) {
                val triplet = TripletSumCloseToTarget().searchTriplet(arrays[i], targets[i])
                println("array: ${arrays[i].contentToString()}, target: ${targets[i]}, triplet: $triplet")
            }
        }
    }
}