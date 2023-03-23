package practice2.sliding_window

class NoRepeatSubstring {
    fun findLength(string: String): Int { // abccde
        var i = 0
        val map = mutableMapOf<Char, Int>()
        var max = 0
        for (j in string.indices) {   // 0,1
            val charJ = string[j]     // a,b
            val countJ = map.getOrDefault(charJ, 0)
            map[charJ] = countJ + 1   // a:1,b:1
            while (map[charJ]!! > 1) {   //
                val charI = string[i] //
                if (charI != charJ)
                    map.remove(charI) //
                else
                    map[charJ] = countJ //
                i++                     //
            }
            max = maxOf(max, j-i+1) // 1
        }
        return max
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