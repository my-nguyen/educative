package practice2.modified_binary_search

class MinimumDifference {
    fun searchMinDiffElement(array: IntArray, key: Int): Int {
        if (key >= array.last())
            return array.last()
        if (key <= array.first())
            return array.first()

        var low = 0
        var high = array.lastIndex
        var minDiff = Int.MAX_VALUE
        var result = array.first()
        while (low <= high) {
            val mid = low + (high-low)/2
            if (array[mid] == key)
                return key

            val diff = kotlin.math.abs(array[mid] - key)
            if (diff < minDiff) {
                minDiff = diff
                result = array[mid]
            }
            if (array[mid] < key)
                low = mid + 1
            else
                high = mid - 1
        }
        return result
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