package practice1.sliding_window

class LongestSubstringKDistinct {
    fun findLength(string: String, size: Int): Int {
        var i = 0
        val map = mutableMapOf<Char, Int>()
        var maxSubstring = 0
        for (j in string.indices) { // "araaci" ***** 2
            val char = string[j]
            val count = map.getOrDefault(char, 0)
            map[char] = count + 1

            while (map.size > size) {
                val lchar = string[i]
                val lcount = map[lchar]
                map[lchar] = lcount!! - 1
                if (map[lchar] == 0)
                    map.remove(lchar)
                i++
            }
            maxSubstring = maxOf(maxSubstring, j-i+1)
        }
        return maxSubstring
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("araaci", "araaci", "cbbebi")
            val counts = arrayOf(2, 1, 3)
            for (i in strings.indices) {
                val max = LongestSubstringKDistinct().findLength(strings[i], counts[i])
                println("string: ${strings[i]}, length: ${counts[i]}, max: $max")
            }
        }
    }
}