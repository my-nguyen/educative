package patterns_for_coding_questions._10_subsets

import java.util.*

class UniqueGeneralizedAbbreviations {
    // i don't understand this solution
    // from educative
    // This problem follows the patterns_for_coding_questions.Subsets pattern and can be mapped to Balanced Parentheses. We can follow a similar BFS approach.
    // Let’s take Example-1 mentioned above to generate all unique generalized abbreviations. Following a BFS approach,
    // we will abbreviate one character at a time. At each step we have two options:
    // * Abbreviate the current character, or
    // * Add the current character to the output and skip abbreviation.
    // Following these two rules, let’s abbreviate BAT:
    // 1. Start with an empty word: “”
    // 2. At every step, we will take all the combinations from the previous step and apply the two abbreviation rules
    //    to the next character.
    // 3. Take the empty word from the previous step and add the first character to it. We can either abbreviate
    //    the character or add it (by skipping abbreviation). This gives us two new words: _, B.
    // 4. In the next iteration, let’s add the second character. Applying the two rules on _ will give us _ _ and 1A.
    //    Applying the above rules to the other combination B gives us B_ and BA.
    // 5. The next iteration will give us: _ _ _, 2T, 1A_, 1AT, B _ _, B1T, BA_, BAT
    // 6. The final iteration will give us:3, 2T, 1A1, 1AT, B2, B1T, BA1, BAT

    data class Abbreviation(val string: StringBuilder, val start: Int, val count: Int)

    fun generateGeneralizedAbbreviation(word: String): List<String> {
        val abbreviations = mutableListOf<String>()
        val queue = LinkedList<Abbreviation>()
        val emptyAbbrev = Abbreviation(StringBuilder(), 0, 0)
        queue.offer(emptyAbbrev)
        while (queue.isNotEmpty()) {
            val topAbbrev = queue.poll()
            if (topAbbrev.start == word.length) {
                if (topAbbrev.count != 0) {
                    topAbbrev.string.append(topAbbrev.count)
                }
                abbreviations.add(topAbbrev.string.toString())
            } else {
                // continue abbreviating by incrementing the current abbreviation count
                val newAbbrev1 = Abbreviation(StringBuilder(topAbbrev.string), topAbbrev.start + 1, topAbbrev.count + 1)
                queue.offer(newAbbrev1)

                // restart abbreviating, append the count and the current character to the string
                if (topAbbrev.count != 0) {
                    topAbbrev.string.append(topAbbrev.count)
                }
                val string = StringBuilder(topAbbrev.string).append(word[topAbbrev.start])
                val newAbbrev2 = Abbreviation(string, topAbbrev.start + 1, 0)
                queue.offer(newAbbrev2)
            }
        }
        return abbreviations
    }

    companion object {
        @JvmStatic
        fun main() {
            val words = arrayOf("BAT", "code")
            for (word in words) {
                val abbreviations = UniqueGeneralizedAbbreviations().generateGeneralizedAbbreviation(word)
                println("word: $word, abbreviations: $abbreviations")
            }
        }
    }
}
