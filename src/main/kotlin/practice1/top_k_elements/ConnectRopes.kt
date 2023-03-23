package practice1.top_k_elements

import java.util.*

class ConnectRopes {
    fun minimumCostToConnectRopes(ropeLengths: IntArray): Int {
        val minHeap = PriorityQueue<Int>()
        for (length in ropeLengths)
            minHeap.add(length)

        val costs = mutableListOf<Int>()
        while (minHeap.size >= 2) {
            val cost = minHeap.poll() + minHeap.poll()
            costs.add(cost)
            minHeap.add(cost)
        }
        return costs.sum()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,3,11,5), intArrayOf(3,4,5,6), intArrayOf(1,3,11,5,2)
            )
            for (lengths in arrays) {
                val cost = ConnectRopes().minimumCostToConnectRopes(lengths)
                println("lengths: ${lengths.contentToString()}, minimum cost: $cost")
            }
        }
    }
}