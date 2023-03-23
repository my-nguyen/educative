package practice1.sliding_window

class NoRepeatSubstring {
    fun findLength(string: String): Int {
        var i = 0
        val map = mutableMapOf<Char, Int>()
        var maxLength = 0
        for (j in string.indices) {
            val rchar = string[j]
            val rcount = map.getOrDefault(rchar, 0)
            map[rchar] = rcount + 1
            while (map[rchar]!! > 1) {
                val lchar = string[i]
                if (lchar == rchar)
                    map[lchar] = map[lchar]!! - 1
                else
                    map.remove(lchar)
                i++
            }
            maxLength = maxOf(maxLength, j-i+1)
        }
        return maxLength
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("aabccbb", "abbbb", "abccde", "abacdaefgahijka")
            for (string in strings) {
                val length = NoRepeatSubstring().findLength(string)
                println("string: $string, length: $length")
            }
        }
    }
}