package patterns_for_coding_questions._16_topological_sort_graph

import java.util.*


class TasksScheduling {
    fun isSchedulingPossible(tasks: Int, prerequisites: Array<IntArray>): Boolean {
        val sortedOrder = mutableListOf<Int>()
        if (tasks <= 0)
            return false

        // a. Initialize the graph
        val inDegree = HashMap<Int, Int>() // count of incoming edges for every vertex
        val graph = HashMap<Int, MutableList<Int>>() // adjacency list graph
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

        // if sortedOrder doesn't contain all tasks, there is a cyclic dependency between tasks, therefore, we
        // will not be able to schedule all tasks
        return sortedOrder.size == tasks
    }
}