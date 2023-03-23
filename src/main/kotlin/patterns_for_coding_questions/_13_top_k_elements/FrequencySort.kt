package patterns_for_coding_questions._13_top_k_elements

import java.util.*

class FrequencySort {
    fun sortCharacterByFrequency(string: String): String {
        val map = mutableMapOf<Char, Int>()
        for (c in string) {
            val count = map.getOrDefault(c, 0)
            map[c] = count + 1
        }

        val maxHeap = PriorityQueue<Char>() { a, b -> map[b]!! - map[a]!! }
        /*for ((k, v) in map) {
            maxHeap.offer(k)
        }*/
        maxHeap.addAll(map.keys)

        val sb = StringBuilder()
        while (maxHeap.isNotEmpty()) {
            val c = maxHeap.poll()
            for (i in 1..map[c]!!) {
                sb.append(c)
            }
        }
        return sb.toString()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("Programming", "abcbab")
            for (string in strings) {
                val sorted = FrequencySort().sortCharacterByFrequency(string)
                println("input: $string, output: $sorted")
            }
        }
    }
}