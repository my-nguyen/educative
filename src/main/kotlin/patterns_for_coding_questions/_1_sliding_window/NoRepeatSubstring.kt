package patterns_for_coding_questions._1_sliding_window

class NoRepeatSubstring {
    fun findLength(str: String): Int {
        // map from character to its index within string
        val map = mutableMapOf<Char, Int>()
        var left = 0
        var length = 0
        for (right in str.indices) {
            val c = str[right]
            // character and its index already exists in map
            if (map.containsKey(c)) {
                // update max length if any
                length = maxOf(length, right-left)
                // retrieve the previous index of current character
                val leftIndex = map[c]!!
                // move left pointer past that previous index
                left = leftIndex + 1
                // println("c: $c, leftIndex: $leftIndex, length: $length, left: $left")
            }
            // save current character with its index
            map[c] = right
        }
        return length
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