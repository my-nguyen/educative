package patterns_for_coding_questions._16_topological_sort_graph

import java.util.*


class TopologicalSort {
    fun sort(vertices: Int, edges: Array<IntArray>): List<Int>? {
        val sortedOrder = mutableListOf<Int>()
        if (vertices <= 0)
            return sortedOrder

        // a. Initialize the graph
        val inDegree = mutableMapOf<Int, Int>() // count of incoming edges for every vertex
        val graph = HashMap<Int, MutableList<Int>>() // adjacency list graph
        for (i in 0 until vertices) {
            inDegree[i] = 0
            graph[i] = ArrayList()
        }

        // b. Build the graph
        for (i in edges.indices) {
            val parent = edges[i][0]
            val child = edges[i][1]
            graph[parent]!!.add(child) // put the child into it's parent's list
            inDegree[child] = inDegree[child]!! + 1 // increment child's inDegree
        }

        // c. Find all sources i.e., all vertices with 0 in-degrees
        val sources = LinkedList<Int>()
        for ((key, value) in inDegree) {
            if (value == 0)
                sources.add(key)
        }

        // d. For each source, add it to the sortedOrder and subtract one from all of its children's in-degrees
        // if a child's in-degree becomes zero, add it to the sources queue
        while (!sources.isEmpty()) {
            val vertex = sources.poll()
            sortedOrder.add(vertex)
            val children: List<Int> = graph[vertex]!! // get the node's children to decrement their in-degrees
            for (child in children) {
                inDegree[child] = inDegree[child]!! - 1
                if (inDegree[child] == 0)
                    sources.add(child)
            }
        }
        return if (sortedOrder.size != vertices) ArrayList() else sortedOrder
    }
}