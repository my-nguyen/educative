package patterns_for_coding_questions._13_top_k_elements

import java.util.*

class ConnectRopes {
    fun minimumCostToConnectRopes(lengths: IntArray): Int {
        val minHeap = PriorityQueue<Int>()
        // add all ropes to the min heap
        for (length in lengths) {
            minHeap.offer(length)
        }

        var cost = 0
        // go through the values of the heap, in each step take the top (lowest) 2 rope lengths from the min heap
        // connect them and push the result back to the min heap.
        // keep doing this until the heap is left with only one rope
        while (minHeap.size > 1) {
            val sum = minHeap.poll() + minHeap.poll()
            cost += sum
            minHeap.offer(sum)
        }
        return cost
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1, 3, 11, 5), intArrayOf(3, 4, 5, 6), intArrayOf(1, 3, 11, 5, 2)
            )
            for (lengths in arrays) {
                val cost = ConnectRopes().minimumCostToConnectRopes(lengths)
                println("lengths: ${lengths.contentToString()}, minimum cost: $cost")
            }
        }
    }
}