package patterns_for_coding_questions._1_sliding_window

class LongestSubstringWithSameLettersAfterReplacement {
    // from educative
    // This problem follows the Sliding Window pattern, and we can use a similar dynamic sliding window strategy as
    // discussed in No-repeat Substring. We can use a HashMap to count the frequency of each letter.
    // * We will iterate through the string to add one letter at a time in the window.
    // * We will also keep track of the count of the maximum repeating letter in any window (let’s call it maxRepeatLetterCount).
    // * So, at any time, we know that we do have a window with one letter repeating maxRepeatLetterCount times; this
    //   means we should try to replace the remaining letters.
    //   x If the remaining letters are less than or equal to ‘k’, we can replace them all.
    //   x If we have more than ‘k’ remaining letters, we should shrink the window as we cannot replace more than ‘k’ letters.
    // While shrinking the window, we don’t need to update maxRepeatLetterCount (hence, it represents the maximum
    // repeating count of ANY letter for ANY window). Why don’t we need to update this count when we shrink the window?
    // Since we have to replace all the remaining letters to get the longest substring having the same letter in any
    // window, we can’t get a better answer from any other window even though all occurrences of the letter with
    // frequency maxRepeatLetterCount is not in the current window
    fun findLength(str: String, k: Int): Int {
        var left = 0
        var maxLength = 0
        var maxCount = 0
        // keep track of each letter frequency
        val map = mutableMapOf<Char, Int>()
        for (right in str.indices) {
            val rightChar = str[right]
            val rightCount = map.getOrDefault(rightChar, 0)
            map[rightChar] = rightCount + 1
            // key point 1: record the highest frequency of all characters within the current window
            maxCount = maxOf(maxCount, map[rightChar]!!)

            // the current window between left and right includes a letter that repeats 'maxCount' times
            // plus other letters with smaller frequencies. if the other letters are more than 'k',
            // then we need to shrink the window by removing its leftmost letter
            val windowLength = right - left + 1
            val otherChars = windowLength - maxCount
            // key point 2: (highest frequency + other characters frequency) = current window length
            //              so: other characters frequency = current window length - highest frequency
            if (otherChars > k) {
                val leftChar = str[left]
                map[leftChar] = map[leftChar]!! - 1
                left++
            }
            maxLength = maxOf(maxLength, right - left + 1)
        }

        return maxLength
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("aabccbb", "abbcb", "abccde")
            val replaces = arrayOf(2, 1, 1)
            for (i in strings.indices) {
                val length = LongestSubstringWithSameLettersAfterReplacement().findLength(strings[i], replaces[i])
                println("string: ${strings[i]}, replace: ${replaces[i]}, length: $length")
            }
        }
    }
}