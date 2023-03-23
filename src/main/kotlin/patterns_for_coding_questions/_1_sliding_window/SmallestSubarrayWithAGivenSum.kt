package patterns_for_coding_questions._1_sliding_window

class SmallestSubarrayWithAGivenSum {
    fun findMinSubArray(S: Int, arr: IntArray): Int {
        var left = 0
        var sum = 0
        var min = Integer.MAX_VALUE
        // 2,1,5,2,3,2; S=7
        for (right in arr.indices) { // 0,1,2,3,4,5
            sum += arr[right] // 2,3,8,8,5,7
            while (sum >= S) {
                min = minOf(min, right-left+1) // 2
                sum -= arr[left] // 2
                left++ // 1,2,3
            }
        }
        return if (min == Integer.MAX_VALUE) 0 else min
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val target = arrayOf(7,7,8)
            val arrays = arrayOf(intArrayOf(2,1,5,2,3,2), intArrayOf(2,1,5,2,8), intArrayOf(3,4,1,1,6))
            for (i in target.indices) {
                val min = SmallestSubarrayWithAGivenSum().findMinSubArray(target[i], arrays[i])
                println("S: ${target[i]}, array: ${arrays[i].contentToString()}, min: $min")
            }
        }
    }
}