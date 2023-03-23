package practice2.sliding_window

class LongestSubstringKDistinct {
    fun findLength(string: String, size: Int): Int { // araaci; 2
        var i = 0
        var max = 0
        val map = mutableMapOf<Char, Int>()
        for (j in string.indices) {         // 0,1,2,3,4
            val charJ = string[j]
            val countJ = map.getOrDefault(charJ, 0)
            map[charJ] = countJ + 1     // a->3,r->1,c->1
            while (map.size > size) {  // 1,2,2,2,3,3
                val charI = string[i]  // a,r
                val countI = map[charI]!! // 3,1
                map[charI] = countI - 1  // 2,0
                if (map[charI] == 0)  // a->2,c->1
                    map.remove(charI)
                i++                      // 1,2
            }
            max = maxOf(max, j-i+1) // 1,2,3,4
        }
        return max
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