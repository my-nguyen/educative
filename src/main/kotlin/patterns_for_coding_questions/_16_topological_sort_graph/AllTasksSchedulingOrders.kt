package patterns_for_coding_questions._16_topological_sort_graph

import java.util.*


class AllTasksSchedulingOrders {
    fun printOrders(tasks: Int, prerequisites: Array<IntArray>) {
        val sortedOrder = mutableListOf<Int>()
        if (tasks <= 0)
            return

        // a. Initialize the graph
        val inDegree = mutableMapOf<Int, Int>() // count of incoming edges for every vertex
        val graph = mutableMapOf<Int, MutableList<Int>>() // adjacency list graph
        for (i in 0 until tasks) {
            inDegree[i] = 0
            graph[i] = ArrayList()
        }

        // b. Build the graph
        for (i in prerequisites.indices) {
            val parent = prerequisites[i][0]
            val child = prerequisites[i][1]
            graph[parent]!!.add(child) // put the child into it's parent's list
            inDegree[child] = inDegree[child]!! + 1 // increment child's inDegree
        }

        // c. Find all sources i.e., all vertices with 0 in-degrees
        val sources = LinkedList<Int>()
        for ((key, value) in inDegree) {
            if (value == 0)
                sources.add(key)
        }
        printAllTopologicalSorts(graph, inDegree, sources, sortedOrder)
    }

    private fun printAllTopologicalSorts(graph: MutableMap<Int, MutableList<Int>>, inDegree: MutableMap<Int, Int>,
                                         sources: Queue<Int>, sortedOrder: MutableList<Int>) {
        if (!sources.isEmpty()) {
            for (vertex in sources) {
                sortedOrder.add(vertex)
                val sourcesForNextCall = cloneQueue(sources)
                // only remove the current source, all other sources should remain in the queue for the next call
                sourcesForNextCall.remove(vertex)
                val children = graph[vertex]!! // get the node's children to decrement their in-degrees
                for (child in children) {
                    inDegree[child] = inDegree[child]!! - 1
                    if (inDegree[child] == 0)
                        sourcesForNextCall.add(child) // save the new source for the next call
                }

                // recursive call to print other orderings from the remaining (and new) sources
                printAllTopologicalSorts(graph, inDegree, sourcesForNextCall, sortedOrder)

                // backtrack, remove the vertex from the sorted order and put all of its children back to consider
                // the next source instead of the current vertex
                sortedOrder.remove(vertex)
                for (child in children)
                    inDegree[child] = inDegree[child]!! + 1
            }
        }

        // if sortedOrder doesn't contain all tasks, either we've a cyclic dependency between tasks, or
        // we have not processed all the tasks in this recursive call
        if (sortedOrder.size == inDegree.size)
            println(sortedOrder)
    }

    // makes a deep copy of the queue
    private fun cloneQueue(queue: Queue<Int>): Queue<Int> {
        val clone: Queue<Int> = LinkedList()
        for (num in queue)
            clone.add(num)
        return clone
    }
}