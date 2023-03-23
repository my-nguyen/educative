package practice1.modified_binary_search

class MinimumDifference {
    fun searchMinDiffElement(array: IntArray, key: Int): Int {
        if (key <= array.first())
            return array.first()
        if (key >= array.last())
            return array.last()

        var low = 0
        var high = array.lastIndex
        while (low <= high) {
            val mid = low + (high-low)/2
            when {
                array[mid] < key -> low = mid + 1
                array[mid] > key -> high = mid - 1
                else -> return array[mid]
            }
        }
        val one = kotlin.math.abs(array[low] - key)
        val two = kotlin.math.abs(array[high] - key)
        return if (one < two) array[low] else array[high]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(4,6,10), intArrayOf(4,6,10), intArrayOf(1,3,8,10,15), intArrayOf(4,6,10)
            )
            val keys = arrayOf(7, 4, 12, 17)
            for (i in arrays.indices) {
                val element = MinimumDifference().searchMinDiffElement(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, element: $element")
            }
        }
    }
}