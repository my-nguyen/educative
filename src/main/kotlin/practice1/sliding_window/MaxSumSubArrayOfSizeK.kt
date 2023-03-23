package practice1.sliding_window

class MaxSumSubArrayOfSizeK {
    fun findMaxSumSubArray(k: Int, array: IntArray): Int {
        var sum = array.copyOfRange(0, k).sum()
        var maxSum = sum
        for (i in k until array.size) {
            sum -= array[i-k]
            sum += array[i]
            maxSum = maxOf(maxSum, sum)
        }
        return maxSum
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val ks = arrayOf(3, 2)
            val arrays = arrayOf(intArrayOf(2,1,5,1,3,2), intArrayOf(2,3,4,1,5))
            for (i in ks.indices) {
                val max = MaxSumSubArrayOfSizeK().findMaxSumSubArray(ks[i], arrays[i])
                println("k: ${ks[i]}, array: ${arrays[i].contentToString()}, maxSum: $max")
            }
        }
    }
}