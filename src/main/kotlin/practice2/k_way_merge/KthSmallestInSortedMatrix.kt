package practice2.k_way_merge

import java.util.*

class KthSmallestInSortedMatrix {
    data class Entry(val row: Int, val col: Int)

    fun findKthSmallest(matrix: Array<IntArray>, k: Int): Int {
        val minQueue = PriorityQueue<Entry> { a, b -> matrix[a.row][a.col] - matrix[b.row][b.col]}
        for (i in matrix.indices)
            minQueue.add(Entry(i, 0))

        val list = mutableListOf<Int>()
        while (minQueue.isNotEmpty()) {
            val entry = minQueue.poll()
            val row = entry.row
            val col = entry.col
            list.add(matrix[row][col])
            if (col < matrix[row].lastIndex)
                minQueue.add(Entry(row, col+1))
        }
        return list[k-1]
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