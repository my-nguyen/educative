package practice2.sliding_window


class CharacterReplacement {
    fun findLength(string: String, replace: Int): Int {
        // return solution(string, replace)
        // return recall1(string, replace)
        return recall2(string, replace)
    }

    fun recall2(string: String, replace: Int): Int {
        var i = 0
        val map = mutableMapOf<Char, Int>()
        var maxFrequency = 0
        var maxLength = 0
        for (j in string.indices) {
            val charJ = string[j]
            map[charJ] = map.getOrDefault(charJ, 0) + 1
            maxFrequency = maxOf(maxFrequency, map[charJ]!!)

            while (j-i+1 > maxFrequency+replace) {
                val charI = string[i]
                map[charI] = map[charI]!! - 1
                i++
            }
            maxLength = maxOf(maxLength, j-i+1)
        }
        return maxLength
    }

    fun recall1(string: String, replace: Int): Int {
        var i = 0
        val map = mutableMapOf<Char, Int>()
        var maxFrequency = 0
        var maxLength = 0
        for (j in string.indices) {
            val charJ = string[j]
            map[charJ] = map.getOrDefault(charJ, 0) + 1
            maxFrequency = maxOf(maxFrequency, map[charJ]!!)

            while (j-i+1 > replace+maxFrequency) {
                val charI = string[i]
                map[charI] = map[charI]!! - 1
                i++
            }
            maxLength = maxOf(maxLength, j-i+1)
        }
        return maxLength
    }

    fun solution(string: String, replace: Int): Int {
        var i = 0
        var maxLength = 0
        var maxFrequency = 0
        val map = mutableMapOf<Char, Int>()
        // try to extend the range [windowStart, windowEnd]
        for (j in string.indices) {
            val charJ = string[j]
            map[charJ] = map.getOrDefault(charJ, 0) + 1
            maxFrequency = maxOf(maxFrequency, map[charJ]!!)
            // current window size is from windowStart to windowEnd, overall we have a letter which is
            // repeating 'maxRepeatLetterCount' times, this means we can have a window which has one letter
            // repeating 'maxRepeatLetterCount' times and the remaining letters we should replace.
            // if the remaining letters are more than 'k', it is the time to shrink the window as we
            // are not allowed to replace more than 'k' letters
            if (j - i + 1 > replace + maxFrequency) {
                val charI = string[i]
                map[charI] = map[charI]!! - 1
                i++
            }
            maxLength = maxOf(maxLength, j - i + 1)
        }
        return maxLength
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("aabccbb", "abbcb", "abccde")
            val replaces = arrayOf(2, 1, 1)
            for (i in strings.indices) {
                val length = CharacterReplacement().findLength(strings[i], replaces[i])
                println("string: ${strings[i]}, replace: ${replaces[i]}, length: $length")
            }
        }
    }
}