package patterns_for_coding_questions._16_topological_sort_graph

import java.util.*


class MinimumHeightTrees {
    fun findTrees(nodes: Int, edges: Array<IntArray>): List<Int>? {
        val minHeightTrees: MutableList<Int> = ArrayList()
        if (nodes <= 0)
            return minHeightTrees

        // with only one node, since its in-degree will be 0, therefore, we need to handle it separately
        if (nodes == 1) {
            minHeightTrees.add(0)
            return minHeightTrees
        }

        // a. Initialize the graph
        val inDegree = HashMap<Int, Int>() // count of incoming edges for every vertex
        val graph = HashMap<Int, MutableList<Int>>() // adjacency list graph
        for (i in 0 until nodes) {
            inDegree[i] = 0
            graph[i] = ArrayList()
        }

        // b. Build the graph
        for (i in edges.indices) {
            val n1 = edges[i][0]
            val n2 = edges[i][1]
            // since this is an undirected graph, therefore, add a link for both the nodes
            graph[n1]!!.add(n2)
            graph[n2]!!.add(n1)
            // increment the in-degrees of both the nodes
            inDegree[n1] = inDegree[n1]!! + 1
            inDegree[n2] = inDegree[n2]!! + 1
        }

        // c. Find all leaves i.e., all nodes with only 1 in-degree
        val leaves = LinkedList<Int>()
        for ((key, value) in inDegree) {
            if (value == 1)
                leaves.add(key)
        }

        // d. Remove leaves level by level and subtract each leave's children's in-degrees.
        // Repeat this until we are left with 1 or 2 nodes, which will be our answer.
        // Any node that has already been a leaf cannot be the root of a minimum height tree, because
        // its adjacent non-leaf node will always be a better candidate.
        var totalNodes = nodes
        while (totalNodes > 2) {
            val leavesSize = leaves.size
            totalNodes -= leavesSize
            for (i in 0 until leavesSize) {
                val vertex = leaves.poll()
                val children: List<Int> = graph[vertex]!!
                for (child in children) {
                    inDegree[child] = inDegree[child]!! - 1
                    if (inDegree[child] == 1) // if the child has become a leaf
                        leaves.add(child)
                }
            }
        }
        minHeightTrees.addAll(leaves)
        return minHeightTrees
    }
}