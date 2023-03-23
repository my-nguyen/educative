package practice1.modified_binary_search

class CeilingOfANumber {
    fun searchCeilingOfANumber(array: IntArray, key: Int): Int {
        if (key > array.last())
            return -1

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
        return low
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(4,6,10), intArrayOf(1,3,8,10,15), intArrayOf(4,6,10), intArrayOf(4,6,10)
            )
            val keys = arrayOf(6, 12, 17, -1)
            for (i in arrays.indices) {
                val index = CeilingOfANumber().searchCeilingOfANumber(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, index: $index")
            }
        }
    }
}