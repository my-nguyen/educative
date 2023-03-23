package patterns_for_coding_questions._16_topological_sort_graph

import java.util.*


class AlienDictionary {
    fun findOrder(words: Array<String>?): String? {
        if (words == null || words.size == 0)
            return ""

        // a. Initialize the graph
        val inDegree = mutableMapOf<Char, Int>() // count of incoming edges for every vertex
        val graph = mutableMapOf<Char, MutableList<Char>>() // adjacency list graph
        for (word in words) {
            for (character in word.toCharArray()) {
                inDegree[character] = 0
                graph[character] = ArrayList()
            }
        }

        // b. Build the graph
        for (i in 0 until words.size - 1) {
            val w1 = words[i]
            val w2 = words[i + 1] // find ordering of characters from adjacent words
            for (j in 0 until minOf(w1.length, w2.length)) {
                val parent = w1[j]
                val child = w2[j]
                if (parent != child) { // if the two characters are different
                    graph[parent]!!.add(child) // put the child into it's parent's list
                    inDegree[child] = inDegree[child]!! + 1 // increment child's inDegree
                    break // only the first different character between the two words will help us find the order
                }
            }
        }

        // c. Find all sources i.e., all vertices with 0 in-degrees
        val sources = LinkedList<Char>()
        for ((key, value) in inDegree) {
            if (value == 0)
                sources.add(key)
        }

        // d. For each source, add it to the sortedOrder and subtract one from all of its children's in-degrees
        // if a child's in-degree becomes zero, add it to the sources queue
        val sortedOrder = StringBuilder()
        while (!sources.isEmpty()) {
            val vertex = sources.poll()
            sortedOrder.append(vertex)
            val children: List<Char> = graph[vertex]!! // get the node's children to decrement their in-degrees
            for (child in children) {
                inDegree[child] = inDegree[child]!! - 1
                if (inDegree[child] == 0)
                    sources.add(child)
            }
        }

        // if sortedOrder doesn't contain all characters, there is a cyclic dependency between characters, therefore, we
        // will not be able to find the correct ordering of the characters
        return if (sortedOrder.length != inDegree.size)
            ""
        else
            sortedOrder.toString()
    }
}