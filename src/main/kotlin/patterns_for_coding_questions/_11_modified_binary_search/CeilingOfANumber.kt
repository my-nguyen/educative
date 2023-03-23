package patterns_for_coding_questions._11_modified_binary_search

class CeilingOfANumber {
    fun searchCeilingOfANumber(array: IntArray, key: Int): Int {
        if (array.last() < key) {
            return -1
        }

        var left = 0
        var right = array.lastIndex
        while (left <= right) {
            val mid = left + (right-left)/2
            if (array[mid] < key) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
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