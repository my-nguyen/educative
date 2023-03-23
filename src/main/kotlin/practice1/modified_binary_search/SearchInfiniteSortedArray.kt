package practice1.modified_binary_search

class SearchInfiniteSortedArray {
    class ArrayReader(val array: IntArray) {
        fun get(index: Int) = if (index >= array.size) Int.MAX_VALUE else array[index]
    }

    fun search(reader: ArrayReader, key: Int): Int {
        var size = 1
        while (reader.get(size-1) != Int.MAX_VALUE)
            size *= 2

        var low = 0
        var high = size - 1
        while (low <= high) {
            val mid = low + (high-low)/2
            when {
                reader.get(mid) < key -> low = mid + 1
                reader.get(mid) > key -> high = mid - 1
                else -> return mid
            }
        }
        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30),
                intArrayOf(4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30),
                intArrayOf(1, 3, 8, 10, 15),
                intArrayOf(1, 3, 8, 10, 15),
            )
            val keys = arrayOf(16, 11, 15, 200)
            for (i in arrays.indices) {
                val reader = ArrayReader(arrays[i])
                val index = SearchInfiniteSortedArray().search(reader, keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, index: $index")
                // println("array: ${arrays[i].contentToString()}, right: ${patterns_for_coding_questions.SearchInASortedInfiniteArray().findRight(reader)}")
            }
        }
    }
}