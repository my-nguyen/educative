package practice1.modified_binary_search

class SearchRotatedArray {
    fun search(array: IntArray, key: Int): Int {
        if (array.size == 1)
            return if (array[0] == key) 0 else -1

        val pivot = findPivot(array)
        val left = search(array, key, 0, pivot)
        return if (left == -1)
            search(array, key, pivot, array.lastIndex)
        else
            left
    }

    fun findPivot(array: IntArray): Int {
        var low = 0
        var high = array.lastIndex
        while (low <= high) {
            val mid = low + (high-low)/2
            if (array[mid] > array[mid+1])
                return mid + 1
            if (array[low] < array[mid])
                low = mid
            else
                high = mid
        }
        return low
    }

    fun search(array: IntArray, key: Int, start: Int, end: Int): Int {
        var low = start
        var high = end
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

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(10,15,1,3,8), intArrayOf(4,5,7,9,10,-1,2),
                intArrayOf(4,5,6,7,0,1,2), intArrayOf(4,5,6,7,0,1,2), intArrayOf(1),
                intArrayOf(7,9,11,13,15,17,19,1,2,3,4,5,6), intArrayOf(7,9,11,13,15,17,19,1,2,3,4,5,6),
                intArrayOf(7,9,11,13,15,17,19,1,2,3,4,5,6), intArrayOf(7,9,11,13,15,17,19,1,2,3,4,5,6)
            )
            val keys = arrayOf(15,10,0,3,0,3,13,0,8)
            for (i in arrays.indices) {
                val index = SearchRotatedArray().search(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, index: $index")
            }
        }
    }
}