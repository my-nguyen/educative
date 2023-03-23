package practice2.sliding_window

class MaxSumSubArrayOfSizeK {
    fun findMaxSumSubArray(size: Int, array: IntArray): Int {
        var sum = array.slice(0 until size).sum()
        var max = sum
        for (i in size until array.size) {
            sum += array[i] - array[i-size]
            max = maxOf(max, sum)
        }
        return max
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(2,4,7,8,3,5,1,2,3,6,9,3,2,3,1,6,3,8,8,4,9,9,3,3,4,2,6),
                // intArrayOf(2,1,5,1,3,2), intArrayOf(2,3,4,1,5)
            )
            val sizes = arrayOf(3, 2)
            for (i in arrays.indices) {
                print("size: ${sizes[i]}, arraY: ${arrays[i].contentToString()}, ")
                val max = MaxSumSubArrayOfSizeK().findMaxSumSubArray(sizes[i], arrays[i])
                println("max sum subarray: $max")
            }
        }
    }
}