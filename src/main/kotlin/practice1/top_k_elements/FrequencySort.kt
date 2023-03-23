package practice1.top_k_elements

import java.lang.StringBuilder
import java.util.*

class FrequencySort {
    fun sortCharacterByFrequency(string: String): String {
        val map = mutableMapOf<Char, Int>()
        for (c in string) {
            val count = map.getOrDefault(c, 0)
            map[c] = count + 1
        }

        val maxHeap = PriorityQueue<Char> { a, b -> map[b]!! - map[a]!! }
        for (c in map.keys)
            maxHeap.add(c)

        val result = StringBuilder()
        while (maxHeap.isNotEmpty()) {
            val c = maxHeap.poll()
            val i = map[c]!!
            val string = c.toString().repeat(i)
            result.append(string)
        }
        return result.toString()
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