package practice2.top_k_elements

import java.lang.StringBuilder
import java.util.*

class FrequencySort {
    fun sortCharacterByFrequency(string: String): String {
        return ""
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val string = "abbcccddddeeeeeffffffggggggghhhhhhhhiiiiiiiiijjjjjjjjjjkkkkkkkkkkkllllllllllllmmmmmmmmmmmmm"
            val strings = arrayOf("Programming", "abcbab", string)
            for (string in strings) {
                val sorted = FrequencySort().sortCharacterByFrequency(string)
                println("input: $string, output: $sorted")
            }
        }
    }
}