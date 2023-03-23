package patterns_for_coding_questions._1_sliding_window

class MaximumSumSubarrayOfSizeK {
    fun findMaxSumSubArray(slice: Int, array: IntArray): Int {
        var sum = 0
        for (i in 0 until slice) {
            sum += array[i]
        }
        var maxSum = sum
        for (i in slice until array.size) {
            sum -= array[i-slice]
            sum += array[i]
            maxSum = maxOf(sum, maxSum)
        }
        return maxSum
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val slices = arrayOf(3, 2)
            val arrays = arrayOf(intArrayOf(2,1,5,1,3,2), intArrayOf(2,3,4,1,5))
            for (i in slices.indices) {
                val max = MaximumSumSubarrayOfSizeK().findMaxSumSubArray(slices[i], arrays[i])
                println("k: ${slices[i]}, array: ${arrays[i].contentToString()}, maxSum: $max")
            }
        }
    }
}