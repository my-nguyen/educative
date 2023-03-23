package practice1.k_way_merge

import java.util.*

class KthSmallestInSortedMatrix {
    data class Entry(val i: Int, val j: Int)

    fun findKthSmallest(matrix: Array<IntArray>, k: Int): Int {
        val minHeap = PriorityQueue<Entry> { a, b -> matrix[a.i][a.j] - matrix[b.i][b.j] }
        for (i in matrix.indices)
            minHeap.add(Entry(i, 0))

        val sorted = mutableListOf<Int>()
        while (minHeap.isNotEmpty()) {
            val entry = minHeap.poll()
            val row = entry.i
            val col = entry.j
            sorted.add(matrix[row][col])
            if (col < matrix.lastIndex)
                minHeap.add(Entry(row, col+1))
        }

        return sorted[k - 1]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val matrix = arrayOf(
                intArrayOf(2,6,8), intArrayOf(3,7,10), intArrayOf(5,8,11)
            )
            val k = 5
            val smallest = KthSmallestInSortedMatrix().findKthSmallest(matrix, k)
            println("matrix:")
            for (row in matrix) {
                println(row.contentToString())
            }
            println("k: $k, smallest: $smallest")
        }
    }
}