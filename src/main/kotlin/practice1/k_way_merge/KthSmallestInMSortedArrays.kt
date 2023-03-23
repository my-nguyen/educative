package practice1.k_way_merge

import java.util.*

class KthSmallestInMSortedArrays {
    data class Entry(val i: Int, val j: Int)

    fun findKthSmallest(arrays: List<Array<Int>>, kth: Int): Int {
        val minHeap = PriorityQueue<Entry> { a, b -> arrays[a.i][a.j] - arrays[b.i][b.j] }
        for (i in arrays.indices)
            minHeap.add(Entry(i, 0))

        val sorted = mutableListOf<Int>()
        while (minHeap.isNotEmpty()) {
            val e = minHeap.poll()
            sorted.add(arrays[e.i][e.j])
            if (e.j < arrays[e.i].lastIndex)
                minHeap.add(Entry(e.i, e.j+1))
        }
        return sorted[kth-1]
    }

    fun findKthSmallest1(arrays: List<Array<Int>>, kth: Int): Int {
        val minHeap = PriorityQueue<Entry> { a, b -> arrays[a.i][a.j] - arrays[b.i][b.j] }
        for (i in arrays.indices)
            minHeap.add(Entry(i, 0))

        val sorted = mutableListOf<Int>()
        while (minHeap.isNotEmpty()) {
            val entry = minHeap.poll()
            sorted.add(arrays[entry.i][entry.j])
            if (entry.j < arrays[entry.i].lastIndex)
                minHeap.add(Entry(entry.i, entry.j+1))
        }

        println("sorted: $sorted, k: $kth")
        return sorted[kth - 1]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val data = arrayOf(
                arrayOf(arrayOf(2,6,8), arrayOf(3,6,7), arrayOf(1,3,4)),
                arrayOf(arrayOf(5,8,9), arrayOf(1,7))
            )
            val Ks = arrayOf(5, 3)
            for (i in data.indices) {
                val arrays = mutableListOf<Array<Int>>()
                print("unmerged arrays: ")
                for (array in data[i]) {
                    arrays.add(array)
                    print("${array.contentToString()} *** ")
                }
                val smallest = KthSmallestInMSortedArrays().findKthSmallest(arrays, Ks[i])
                println("k: ${Ks[i]}, smallest: $smallest")
            }
        }
    }
}