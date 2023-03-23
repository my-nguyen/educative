package practice1.sliding_window

class MinSizeSubArraySum {
    fun findMinSubArray(target: Int, array: IntArray): Int {
        var i = 0
        var j = 0
        var sum = 0
        var minLength = array.size
        while (j < array.size) { // 2,1,5,2,3,2 *** 7
            sum += array[j]
            j++
            while (sum >= target) {
                minLength = minOf(minLength, j-i)
                sum -= array[i]
                i++
            }
        }
        return minLength
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val target = arrayOf(7,7,8)
            val arrays = arrayOf(intArrayOf(2,1,5,2,3,2), intArrayOf(2,1,5,2,8), intArrayOf(3,4,1,1,6))
            for (i in target.indices) {
                val min = MinSizeSubArraySum().findMinSubArray(target[i], arrays[i])
                println("S: ${target[i]}, array: ${arrays[i].contentToString()}, min: $min")
            }
        }
    }
}