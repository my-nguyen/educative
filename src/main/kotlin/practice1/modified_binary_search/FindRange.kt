package practice1.modified_binary_search

class FindRange {
    fun findRange(array: IntArray, key: Int): IntArray {
        val index = findLocation(array, key)
        if (index == -1)
            return intArrayOf(-1, -1)
        val left = findLeft(array, key, index)
        val right = findRight(array, key, index)
        return intArrayOf(left, right)
    }

    fun findLocation(array: IntArray, key: Int): Int {
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

    fun findRight(array: IntArray, key: Int, index: Int): Int {
        var low = index
        var high = array.lastIndex
        while (low <= high) {
            val mid = low + (high-low)/2
            if (array[mid] <= key)
                low = mid + 1
            else
                high = mid - 1
        }
        return high
    }

    fun findLeft(array: IntArray, key: Int, index: Int): Int {
        var low = 0
        var high = index
        while (low <= high) {
            val mid = low + (high-low)/2
            if (array[mid] < key)
                low = mid + 1
            else
                high = mid - 1
        }
        return low
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(4,6,6,6,9), intArrayOf(1,3,8,10,15), intArrayOf(1,3,8,10,15)
            )
            val keys = arrayOf(6, 10, 12)
            for (i in arrays.indices) {
                val range = FindRange().findRange(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, range: ${range.contentToString()}")
            }
        }
    }
}