package patterns_for_coding_questions._16_topological_sort_graph

import java.util.*


class ReconstructingASequence {
    fun canConstruct(originalSeq: IntArray, sequences: Array<IntArray>): Boolean {
        val sortedOrder: MutableList<Int> = ArrayList()
        if (originalSeq.isEmpty())
            return false

        // a. Initialize the graph
        val inDegree = HashMap<Int, Int>() // count of incoming edges for every vertex
        val graph = HashMap<Int, MutableList<Int>>() // adjacency list graph
        for (seq in sequences) {
            for (i in seq.indices) {
                inDegree.putIfAbsent(seq[i], 0)
                graph.putIfAbsent(seq[i], ArrayList())
            }
        }

        // b. Build the graph
        for (seq in sequences) {
            for (i in 1 until seq.size) {
                val parent = seq[i - 1]
                val child = seq[i]
                graph[parent]!!.add(child)
                inDegree[child] = inDegree[child]!! + 1
            }
        }

        // if we don't have ordering rules for all the numbers we'll not able to uniquely construct the sequence
        if (inDegree.size != originalSeq.size)
            return false

        // c. Find all sources i.e., all vertices with 0 in-degrees
        val sources: Queue<Int> = LinkedList()
        for ((key, value) in inDegree) {
            if (value == 0)
                sources.add(key)
        }

        // d. For each source, add it to the sortedOrder and subtract one from all of its children's in-degrees
        // if a child's in-degree becomes zero, add it to the sources queue
        while (!sources.isEmpty()) {
            if (sources.size > 1)
                return false // more than one sources mean, there is more than one way to reconstruct the sequence
            if (originalSeq[sortedOrder.size] != sources.peek())
                return false // the next source (or number) is different from the original sequence
            val vertex = sources.poll()
            sortedOrder.add(vertex)
            val children: List<Int> = graph[vertex]!! // get the node's children to decrement their in-degrees
            for (child in children) {
                inDegree[child] = inDegree[child]!! - 1
                if (inDegree[child] == 0)
                    sources.add(child)
            }
        }

        // if sortedOrder's size is not equal to original sequence's size, there is no unique way to construct
        return sortedOrder.size == originalSeq.size
    }
}