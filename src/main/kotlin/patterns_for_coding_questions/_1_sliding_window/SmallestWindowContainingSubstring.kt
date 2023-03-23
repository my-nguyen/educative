package patterns_for_coding_questions._1_sliding_window

class SmallestWindowContainingSubstring {
    // from educative
    // This problem follows the Sliding Window pattern and has a lot of similarities with Permutation in a String with
    // one difference. In this problem, we need to find a substring having all characters of the pattern which means
    // that the required substring can have some additional characters and doesn’t need to be a permutation of the
    // pattern. Here is how we will manage these differences:
    // 1. We will keep a running count of every matching instance of a character.
    // 2. Whenever we have matched all the characters, we will try to shrink the window from the beginning, keeping
    //    track of the smallest substring that has all the matching characters.
    // 3. We will stop the shrinking process as soon as we remove a matched character from the sliding window. One thing
    //    to note here is that we could have redundant matching characters, e.g., we might have two ‘a’ in the sliding
    //    window when we only need one ‘a’. In that case, when we encounter the first ‘a’, we will simply shrink the
    //    window without decrementing the matched count. We will decrement the matched count when the second ‘a’ goes
    //    out of the window.
    fun findSubstring(string: String, pattern: String): String {
        val map = mutableMapOf<Char, Int>()
        for (c in pattern) {
            val count = map.getOrDefault(c, 0)
            map[c] = count + 1
        }

        var left = 0
        var matched = 0
        var minLength = string.length + 1
        var start = 0
        // try to extend the range [left, right]
        for (right in string.indices) {
            val rightChar = string[right]
            if (map.containsKey(rightChar)) {
                val rightCount = map[rightChar]!!
                map[rightChar] = rightCount - 1
                if (rightCount >= 1) // count every matching of a character
                    matched++
            }

            // shrink the window if we can, finish as soon as we remove a matched character
            while (matched == pattern.length) {
                val window = right - left + 1
                if (minLength > window) {
                    minLength = window
                    start = left
                }
                val leftChar = string[left]
                if (map.containsKey(leftChar)) {
                    // note that we could have redundant matching characters, therefore we'll decrement the
                    // matched count only when a useful occurrence of a matched character is going out of the window
                    val leftCount = map[leftChar]!!
                    if (leftCount == 0)
                        matched--
                    map[leftChar] = leftCount + 1
                }
                left++
            }
        }
        return if (minLength > string.length) "" else string.substring(start, start+minLength)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("aabdec", "abdbca", "adcad")
            val patterns = arrayOf("abc", "abc", "abc")
            for (i in strings.indices) {
                val substring = SmallestWindowContainingSubstring().findSubstring(strings[i], patterns[i])
                println("string: ${strings[i]}, pattern: ${patterns[i]}, substring: $substring")
            }
        }
    }
}