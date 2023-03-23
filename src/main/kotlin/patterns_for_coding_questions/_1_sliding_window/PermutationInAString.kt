package patterns_for_coding_questions._1_sliding_window

class PermutationInAString {
    fun findPermutation(string: String, pattern: String): Boolean {
        // return mine1(string, pattern)
        return mine2(string, pattern)
        // return educative1(string, pattern)
        // return educative2(string, pattern)
    }

    private fun mine1(string: String, pattern: String): Boolean {
        val counts = IntArray(26)
        for (c in pattern) {
            val index = c - 'a'
            counts[index]++
        }

        for (i in 0..(string.length - pattern.length)) {
            val c = string[i]
            val index = c - 'a'
            if (counts[index] > 0) {
                val substring = string.substring(i, i + pattern.length)
                if (isPermutation(substring, counts.clone())) {
                    return true
                }
            }
        }
        return false
    }

    private fun mine2(string: String, pattern: String): Boolean {
        val map = mutableMapOf<Char, Int>()
        for (c in pattern) {
            val count = map.getOrDefault(c, 0)
            map[c] = count + 1
        }

        for (i in 0..(string.length - pattern.length)) {
            val c = string[i]
            if (map[c] != null && map[c]!! > 0) {
                val substring = string.substring(i, i + pattern.length)
                if (isPermutation(substring, map.toMutableMap())) {
                    return true
                }
            }
        }
        return false
    }

    // This problem follows the Sliding Window pattern, and we can use a similar sliding window strategy as discussed in
    // Longest Substring with K Distinct Characters. We can use a HashMap to remember the frequencies of all characters
    // in the given pattern. Our goal will be to match all the characters from this HashMap with a sliding window in the
    // given string. Here are the steps of our algorithm:
    // 1. Create a HashMap to calculate the frequencies of all characters in the pattern.
    // 2. Iterate through the string, adding one character at a time in the sliding window.
    // 3. If the character being added matches a character in the HashMap, decrement its frequency in the map. If the
    //    character frequency becomes zero, we got a complete match.
    // 4. If at any time, the number of characters matched is equal to the number of distinct characters in the pattern
    //    (i.e., total characters in the HashMap), we have gotten our required permutation.
    // 5. If the window size is greater than the length of the pattern, shrink the window to make it equal to the
    //    pattern’s size. At the same time, if the character going out was part of the pattern, put it back in the
    //    frequency HashMap.
    private fun educative1(string: String, pattern: String): Boolean {
        val map = mutableMapOf<Char, Int>()
        for (chr in pattern) {
            map[chr] = map.getOrDefault(chr, 0) + 1
        }

        var left = 0
        // count of all matches of all characters
        var matched = 0
        for (right in string.indices) {
            val rightChar = string[right]
            if (map.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                map[rightChar] = map[rightChar]!! - 1
                if (map[rightChar] == 0) {
                    // current character's frequency is completely matched
                    matched++
                }
            }
            if (matched == map.size) {
                // if the number of characters matched is the same as that in map, then we have a permutation
                return true
            }
            if (right >= pattern.length - 1) {
                // shrink the window by one character
                val leftChar = string[left]
                if (map.containsKey(leftChar)) {
                    if (map[leftChar] == 0) {
                        // before putting the character back, decrement the matched count
                        matched--
                    }
                    // put the character back for matching
                    map[leftChar] = map[leftChar]!! + 1
                }
                left++
            }
        }

        return false
    }

    private fun educative2(string: String, pattern: String): Boolean {
        val map = mutableMapOf<Char, Int>()
        for (c in pattern) {
            val count = map.getOrDefault(c, 0)
            map[c] = count + 1
        }

        var left = 0
        for (right in string.indices) {
            if (right - left + 1 > pattern.length) {
                val leftChar = string[left]
                if (map.containsKey(leftChar)) {
                    val leftCount = map[leftChar]!!
                    map[leftChar] = leftCount + 1
                }
                left++
            }

            val rightChar = string[right]
            if (map.containsKey(rightChar)) {
                map[rightChar] = map[rightChar]!! - 1
                if (isPermutation(map)) {
                    return true
                }
            }
        }
        return false
    }

    private fun isPermutation(map: Map<Char, Int>): Boolean {
        for ((k, v) in map) {
            if (v != 0) {
                return false
            }
        }
        return true
    }

    private fun isPermutation(string: String, counts: IntArray): Boolean {
        for (c in string) {
            val index = c - 'a'
            if (counts[index] == 0) {
                return false
            } else {
                counts[index]--
            }
        }
        return true
    }

    private fun isPermutation(string: String, map: MutableMap<Char, Int>): Boolean {
        for (c in string) {
            val count = map[c]
            if (count == null || count == 0) {
                return false
            } else {
                map[c] = count - 1
            }
        }
        return true
    }

    private fun print(map: Map<Char, Int>) {
        for ((k, v) in map) {
            print("$k->$v, ")
        }
        println()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("oidbcaf", "odicf", "bcdxabcdy", "aaacb", "bcdxabcdy")
            val patterns = arrayOf("abc", "dc", "bcdyabcdx", "abc", "bcdyabcdx")
            for (i in strings.indices) {
                val isPermutation = PermutationInAString().findPermutation(strings[i], patterns[i])
                println("string: ${strings[i]}, pattern: ${patterns[i]}, is permutation? $isPermutation")
            }
        }
    }
}