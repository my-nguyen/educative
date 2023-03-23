package practice2.sliding_window

class ReplacingOnes {
    fun findLength(array: IntArray, replace: Int): Int {
        // return solution(array, replace)
        // return recall1(array, replace)
        // return recall2(array, replace)
        return recall3(array, replace)
    }

    fun solution(arr: IntArray, k: Int): Int {
        var i = 0
        var maxLength = 0
        var ones = 0
        // try to extend the range [windowStart, windowEnd]
        for (j in arr.indices) {
            if (arr[j] == 1)
                ones++
            // current window size is from windowStart to windowEnd, overall we have a maximum of 1s
            // repeating a maximum of 'maxOnesCount' times, this means that we can have a window with
            // 'maxOnesCount' 1s and the remaining are 0s which should replace with 1s.
            // now, if the remaining 0s are more than 'k', it is the time to shrink the window as we
            // are not allowed to replace more than 'k' Os
            if (j - i + 1 > ones + k) {
                if (arr[i] == 1)
                    ones--
                i++
            }
            maxLength = Math.max(maxLength, j - i + 1)
        }
        return maxLength
    }

    fun recall1(array: IntArray, replace: Int): Int {
        var i = 0
        var ones = 0
        var maxFrequency = 0
        var maxLength = 0
        for (j in array.indices) {
            if (array[j] == 1)
                ones++
            maxFrequency = maxOf(maxFrequency, ones)

            while (j-i+1 > maxFrequency+replace) {
                if (array[i] == 1)
                    ones--
                i++
            }
            maxLength = maxOf(maxLength, j-i+1)
        }
        return maxLength
    }

    fun recall2(array: IntArray, replace: Int): Int {
        var i = 0
        var ones = 0
        var maxLength = 0
        var maxFrequency = 0
        for (j in array.indices) {
            if (array[j] == 1)
                ones++
            maxFrequency = maxOf(maxFrequency, ones)
            while (j-i+1 > maxFrequency+replace) {
                if (array[i] == 1)
                    ones--
                i++
            }
            maxLength = maxOf(maxLength, j-i+1)
        }
        return maxLength
    }

    fun recall3(array: IntArray, replace: Int): Int {
        var i = 0
        var ones = 0
        var maxFrequency = 0
        var maxLength = 0
        for (j in array.indices) {
            if (array[j] == 1)
                ones++
            maxFrequency = maxOf(maxFrequency, ones)
            while (j-i+1 > maxFrequency+replace) {
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