package practice1.modified_binary_search

class BinarySearch {
    fun search(array: IntArray, key: Int): Int {
        return if (array.first() < array.last())
            searchAscend(array, key)
        else
            searchDescend(array, key)
    }

    fun searchAscend(array: IntArray, key: Int): Int {
        var low = 0
        var high = array.lastIndex
        while (low <= high) {
            val mid = low + (high-low)/2
            when {
                array[mid] < key -> low = mid + 1
                array[mid] > key -> high = mid - 1
                else -> return mid
            }
        }
        return -1
    }

    fun searchDescend(array: IntArray, key: Int): Int {
        var low = 0
        var high = array.lastIndex
        while (low <= high) {
            val mid = low + (high-low)/2
            when {
                array[mid] > key -> low = mid + 1
                array[mid] < key -> high = mid - 1
                else -> return mid
            }
        }
        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(4,6,10), intArrayOf(1,2,3,4,5,6,7), intArrayOf(10,6,4), intArrayOf(10,6,4)
            )
            val keys = arrayOf(10, 5, 10, 4)
            for (i in arrays.indices) {
                val index = BinarySearch().search(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, index: $index")
            }
        }
    }
}