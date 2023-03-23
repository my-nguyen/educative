package patterns_for_coding_questions._13_top_k_elements

import java.util.*


class RearrangeStringKDistanceApart {
    fun reorganizeString(string: String, distance: Int): String {
        // return mine(string, distance)
        return educative(string, distance)
    }

    // from educative
    // This problem follows the Top ‘K’ Numbers pattern and is quite similar to Rearrange String. The only difference is
    // that in the ‘Rearrange String’ the same characters need not be adjacent i.e., they should be at least ‘2’ distance
    // apart (in other words, there should be at least one character between two same characters), while in the current
    // problem, the same characters should be ‘K’ distance apart.
    // Following a similar approach, since we were inserting a character back in the heap in the next iteration, in this
    // problem, we will re-insert the character after ‘K’ iterations. We can keep track of previous characters in a queue
    // to insert them back in the heap after ‘K’ iterations.
    private fun educative(string: String, k: Int): String {
        if (k <= 1) {
            return string
        }

        val charFrequencies = mutableMapOf<Char, Int>()
        for (chr in string) {
            charFrequencies[chr] = charFrequencies.getOrDefault(chr, 0) + 1
        }

        val maxHeap = PriorityQueue<MutableMap.MutableEntry<Char, Int>> { (_, v1), (_, v2) -> v2 - v1 }

        // add all characters to the max heap
        maxHeap.addAll(charFrequencies.entries)

        // use a queue to ensure the characters are separated 'k' apart from each other
        val queue = LinkedList<MutableMap.MutableEntry<Char, Int>>()
        val result = StringBuilder()
        while (maxHeap.isNotEmpty()) {
            val currentEntry = maxHeap.poll()
            // append the current character to the result string and decrement its count
            result.append(currentEntry.key)
            currentEntry.setValue(currentEntry.value - 1)

            queue.offer(currentEntry)
            // use a queue to keep used entries out of circulation. when queue reaches k entries, the first entry can be
            // put back into circulation (the max-heap). that way, repeating characters are kept 'k' distance apart
            if (queue.size == k) {
                val entry = queue.poll()
                if (entry.value > 0) {
                    maxHeap.add(entry)
                }
            }
        }

        // if we were successful in appending all the characters to the result string, return it
        return if (result.length == string.length) result.toString() else ""
    }

    // incorrect solution
    private fun mine(string: String, distance: Int): String {
        val map = mutableMapOf<Char, Int>()
        for (c in string) {
            val count = map.getOrDefault(c, 0)
            map[c] = count + 1
        }

        val maxHeap = PriorityQueue<MutableMap.MutableEntry<Char, Int>> { (_, v1), (_, v2) -> v2 - v1 }
        maxHeap.addAll(map.entries)

        val entries = arrayOfNulls<MutableMap.MutableEntry<Char, Int>>(distance)
        var i = 0
        val sb = StringBuilder()
        while (maxHeap.isNotEmpty()) {
            val currentEntry = maxHeap.poll()
            currentEntry.setValue(currentEntry.value - 1)
            entries[i] = currentEntry
            i = next(i, distance)
            sb.append(currentEntry.key)

            val previousEntry = entries[previous(i, distance)]
            if (previousEntry != null && previousEntry.value > 0) {
                maxHeap.offer(previousEntry)
            }
        }

        return if (sb.length == string.length) sb.toString() else ""
    }

    private fun previous(i: Int, k: Int) = (i - 1 + k) % k
    private fun next(i: Int, k: Int) = (i + 1) % k

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("mmpp", "Programming", "aab", "aappa")
            val distances = arrayOf(2, 3, 2, 3)
            for (i in strings.indices) {
                val rearranged = RearrangeStringKDistanceApart().reorganizeString(strings[i], distances[i])
                println("string: ${strings[i]}, distance: ${distances[i]}, rearranged: $rearranged")
            }
        }
    }
}