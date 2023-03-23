package practice2.sliding_window

class MinSizeSubArraySum {
    fun findMinSubArray(target: Int, array: IntArray): Int {
        var i = 0
        var sum = 0
        var min = Int.MAX_VALUE
        for (j in array.indices) {   // 2,1,5,2,8; 7
            sum += array[j]          // 2,3,8,8,10
            while (sum >= target) {
                min = minOf(min, j-i+1) // 3,3,2,2,1
                sum -= array[i]            // 6,7,2,8,0
                i++                        // 1,2,3,4,5
            }
        }
        return if (min == Int.MAX_VALUE) 0 else min
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val targets = arrayOf(7,7,8)
            val arrays = arrayOf(intArrayOf(2,1,5,2,3,2), intArrayOf(2,1,5,2,8), intArrayOf(3,4,1,1,6))
            for (i in targets.indices) {
                val min = MinSizeSubArraySum().findMinSubArray(targets[i], arrays[i])
                println("S: ${targets[i]}, array: ${arrays[i].contentToString()}, min: $min")
            }
        }
    }
}