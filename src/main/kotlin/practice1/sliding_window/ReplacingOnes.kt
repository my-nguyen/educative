package practice1.sliding_window

class ReplacingOnes {
    fun findLength(array: IntArray, replace: Int): Int {
        var i = 0
        var ones = 0
        var maxLength = 0
        for (j in array.indices) {
            if (array[j] == 1)
                ones++
            while (j-i+1 > ones+replace) {
                if (array[i] == 1)
                    ones--
                i++
            }
            maxLength = maxOf(maxLength, j-i+1)
        }
        return maxLength
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(0,1,1,0,0,0,1,1,0,1,1), intArrayOf(0,1,0,0,1,1,0,1,1,0,0,1,1))
            val replaces = arrayOf(2, 3)
            for (i in arrays.indices) {
                val length = ReplacingOnes().findLength(arrays[i], replaces[i])
                println("array: ${arrays[i].contentToString()}, replace: ${replaces[i]}, length: $length")
            }
        }
    }
}