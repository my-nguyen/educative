package practice2.k_way_merge

import java.util.*

class KthSmallestInMSortedArrays {
    data class Entry(val row: Int, val col: Int)

    fun findKthSmallest(arrays: List<Array<Int>>, index: Int): Int {
        // return practice1(arrays, index)
        // return practice2(arrays, index)
        // return practice3(arrays, index)
        return practice4(arrays, index)
    }

    fun practice4(arrays: List<Array<Int>>, index: Int): Int {
        val minQueue = PriorityQueue<Entry> { a, b -> arrays[a.row][a.col] - arrays[b.row][b.col] }
        for (i in arrays.indices)
            minQueue.add(Entry(i, 0))

        val list = mutableListOf<Int>()
        while (minQueue.isNotEmpty()) {
            val entry = minQueue.poll()
            val row = entry.row
            val col = entry.col
            list.add(arrays[row][col])
            if (col < arrays[row].lastIndex)
                minQueue.add(Entry(row, col+1))
        }
        return list[index-1]
    }

    fun practice3(arrays: List<Array<Int>>, index: Int): Int {
        val minQueue = PriorityQueue<Entry> { a, b -> arrays[a.row][a.col] - arrays[b.row][b.col] }
        for (i in arrays.indices)
            minQueue.add(Entry(i, 0))

        val list = mutableListOf<Int>()
        while (minQueue.isNotEmpty()) {
            val entry = minQueue.poll()
            val row = entry.row
            val col = entry.col
            list.add(arrays[row][col])
            if (col < arrays[row].lastIndex)
                minQueue.add(Entry(row, col+1))
        }
        return list[index-1]
    }

    fun practice2(arrays: List<Array<Int>>, index: Int): Int {
        val minQueue = PriorityQueue<Entry> { a, b -> arrays[a.row][a.col] - arrays[b.row][b.col] }
        for (i in arrays.indices)
            minQueue.add(Entry(i, 0))

        val list = mutableListOf<Int>()
        while (minQueue.isNotEmpty()) {
            val entry = minQueue.poll()
            val row = entry.row
            val col = entry.col
            list.add(arrays[row][col])
            if (col < arrays[row].lastIndex)
                minQueue.add(Entry(row, col+1))
        }
        return list[index-1]
    }

    fun practice1(arrays: List<Array<Int>>, index: Int): Int {
        val minQueue = PriorityQueue<Entry> { a, b -> arrays[a.row][a.col] - arrays[b.row][b.col] }
        for (i in arrays.indices)
            minQueue.add(Entry(i, 0))

        val list = mutableListOf<Int>()
        while (minQueue.isNotEmpty()) {
            val entry = minQueue.poll()
            val row = entry.row
            val col = entry.col
            list.add(arrays[row][col])
            if (col < arrays[row].lastIndex)
                minQueue.add(Entry(row, col+1))
        }
        println("list: $list")
        return list[index - 1]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val data = arrayOf(
                arrayOf(arrayOf(2,6,8), arrayOf(3,6,7), arrayOf(1,3,4)),
                arrayOf(arrayOf(5,8,9), arrayOf(1,7)),
                arrayOf(arrayOf(0,1,2,3,4,5,6,7,8,9), arrayOf(0,2,4,6,8), arrayOf(0,3,6,9), arrayOf(0,4,8), arrayOf(0,5), arrayOf(0,6)),
                arrayOf(arrayOf(0,1,2,3,4,5,6,7,8,9), arrayOf(0,2,4,6,8), arrayOf(0,3,6,9), arrayOf(0,4,8), arrayOf(0,5), arrayOf(0,6)),
                arrayOf(arrayOf(0,1,2,3,4,5,6,7,8,9), arrayOf(0,2,4,6,8), arrayOf(0,3,6,9), arrayOf(0,4,8), arrayOf(0,5), arrayOf(0,6)),
                arrayOf(arrayOf(0,1,2,3,4,5,6,7,8,9), arrayOf(0,2,4,6,8), arrayOf(0,3,6,9), arrayOf(0,4,8), arrayOf(0,5), arrayOf(0,6)),
                arrayOf(arrayOf(0,1,2,3,4,5,6,7,8,9), arrayOf(0,2,4,6,8), arrayOf(0,3,6,9), arrayOf(0,4,8), arrayOf(0,5), arrayOf(0,6)),
            )
            val indices = arrayOf(5, 3, 3, 5, 7, 9, 11)
            for (i in data.indices) {
                val arrays = mutableListOf<Array<Int>>()
                print("unmerged arrays: ")
                for (array in data[i]) {
                    arrays.add(array)
                    print("${array.contentToString()} *** ")
                }
                val smallest = KthSmallestInMSortedArrays().findKthSmallest(arrays, indices[i])
                println("k: ${indices[i]}, smallest: $smallest")
            }
        }
    }
}