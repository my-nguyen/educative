package patterns_for_coding_questions._1_sliding_window

class LongestSubstringWithKDistinctCharacters {
    fun findLength(str: String, k: Int): Int {
        // return leetcode(str, k)
        return educative(str, k)
    }

    private fun educative(str: String, k: Int): Int {
        var left = 0
        var max = 0
        val map = mutableMapOf<Char, Int>()
        for (right in str.indices) {
            val c = str[right]
            val count = map.getOrDefault(c, 0)
            map[c] = count + 1
            while (map.size > k) {
                val leftC = str[left]
                val leftCount = map[leftC]!! - 1
                if (leftCount == 0) {
                    map.remove(leftC)
                } else {
                    map[leftC] = leftCount
                }
                max = maxOf(max, right-left)
                left++
            }
        }
        return max
    }
    private fun leetcode(str: String, k: Int): Int { // araaci, 2
        var left = 0
        var max = 0
        val map = mutableMapOf<Char, Int>()
        for (right in str.indices) { // 0,1,2,3,4,5
            val c = str[right] // a,r,a,a,c,i
            map[c] = right // a->3,r->1,c->4,i->5
            if (map.size > k) { // 1,2,2,2,3,3
                val leftIndex = map.values.minOrNull()!! // [1,3,4]=1;[3,4,5]=3
                val leftChar = str[leftIndex] // r,a
                map.remove(leftChar) // a->3,c->4;c->4,i->5
                max = maxOf(max, right-left) // 4,4
                left = leftIndex + 1 // 2,4
            }
        }
        return max
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("araaci", "araaci", "cbbebi")
            val lengths = arrayOf(2, 1, 3)
            for (i in strings.indices) {
                val max = LongestSubstringWithKDistinctCharacters().findLength(strings[i], lengths[i])
                println("string: ${strings[i]}, length: ${lengths[i]}, max: $max")
            }
        }
    }
}