package patterns_for_coding_questions._1_sliding_window

class LongestSubarrayWithOnesAfterReplacement {
    fun findLength(array: IntArray, replace: Int): Int {
        var left = 0
        var onesCount = 0
        var maxLength = 0
        for (right in array.indices) {
            if (array[right] == 1) {
                onesCount++
            }

            var zeroesCount = right - left + 1 - onesCount
            if (zeroesCount > replace) {
                if (array[left] == 1) {
                    onesCount--;
                } else {
                    zeroesCount--;
                }
                left++;
            }
            maxLength = maxOf(maxLength, right-left+1)
        }
        return maxLength
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(0,1,1,0,0,0,1,1,0,1,1), intArrayOf(0,1,0,0,1,1,0,1,1,0,0,1,1))
            val replaces = arrayOf(2, 3)
            for (i in arrays.indices) {
                val length = LongestSubarrayWithOnesAfterReplacement().findLength(arrays[i], replaces[i])
                println("array: ${arrays[i].contentToString()}, replace: ${replaces[i]}, length: $length")
            }
        }
    }
}