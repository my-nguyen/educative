package patterns_for_coding_questions._13_top_k_elements

import java.util.*

class RearrangeString {
    fun rearrangeString(input: String): String {
        // return mine(input)
        return educative(input)
    }

    // from educative
    // This problem follows the Top ‘K’ Numbers pattern. We can follow a greedy approach to find an arrangement of
    // the given string where no two same characters come next to each other.
    // We can work in a stepwise fashion to create a string with all characters from the input string. Following a greedy
    // approach, we should first append the most frequent characters to the output strings, for which we can use a
    // Max Heap. By appending the most frequent character first, we have the best chance to find a string where no two
    // same characters come next to each other.
    // So in each step, we should append one occurrence of the highest frequency character to the output string. We will
    // not put this character back in the heap to ensure that no two same characters are adjacent to each other. In the
    // next step, we should process the next most frequent character from the heap in the same way and then, at the end
    // of this step, insert the character from the previous step back to the heap after decrementing its frequency.
    // Following this algorithm, if we can append all the characters from the input string to the output string, we would
    // have successfully found an arrangement of the given string where no two same characters appeared adjacent to each other.
    private fun educative(string: String): String {
        val charFrequency = mutableMapOf<Char, Int>()
        for (chr in string) {
            charFrequency[chr] = charFrequency.getOrDefault(chr, 0) + 1
        }

        // create a max heap of map entries so we can update entries value while popping entries from the heap
        val maxHeap = PriorityQueue<MutableMap.MutableEntry<Char, Int>> { (_, v1), (_, v2) -> v2 - v1 }

        // add all characters to the max heap
        maxHeap.addAll(charFrequency.entries)

        // use 2 entries, previous and current, to put alternate characters in result string
        var previous: MutableMap.MutableEntry<Char, Int>? = null
        val result = StringBuilder(string.length)
        while (maxHeap.isNotEmpty()) {
            val current = maxHeap.poll()
            // add the previous entry back in the heap if its frequency is greater than zero
            if (previous != null && previous.value > 0) {
                maxHeap.offer(previous)
            }
            // append the current character to the result string and decrement its count
            result.append(current.key)
            current.setValue(current.value - 1)
            previous = current
        }

        // if we were successful in appending all the characters to the result string, return it
        return if (result.length == string.length) result.toString() else ""
    }

    // incomplete solution
    private fun mine(input: String): String {
        val map = mutableMapOf<Char, Int>()
        for (c in input) {
            val count = map.getOrDefault(c, 0)
            map[c] = count + 1
        }

        val maxHeap = PriorityQueue<Char>() { a, b -> map[b]!! - map[a]!! }
        for ((k, v) in map) {
            maxHeap.offer(k)
        }

        val sb = StringBuilder()
        return sb.toString()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("aappp", "Programming", "aapa")
            for (string in strings) {
                val rearranged = RearrangeString().rearrangeString(string)
                println("string: $string, rearranged: $rearranged")
            }
        }
    }
}