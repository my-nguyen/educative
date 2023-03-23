package patterns_for_coding_questions._11_modified_binary_search

class OrderAgnosticBinarySearch {
    fun search(array: IntArray, key: Int): Int {
        if (array.first() < array.last()) {
            var left = 0
            var right = array.lastIndex
            while (left < right) {
                val mid = (left + right) / 2
                when {
                    array[mid] < key -> left = mid + 1
                    array[mid] > key -> right = mid - 1
                    else -> return mid
                }
            }
            return left
        } else {
            var left = 0
            var right = array.lastIndex
            while (left < right) {
                val mid = (left + right) / 2
                when {
                    array[mid] > key -> left = mid + 1
                    array[mid] < key -> right = mid - 1
                    else -> return mid
                }
            }
            return left
        }
    }

    companion object {
        @JvmStatic
        fun main() {
            val arrays = arrayOf(
                intArrayOf(4,6,10), intArrayOf(1,2,3,4,5,6,7), intArrayOf(10,6,4), intArrayOf(10,6,4)
            )
            val keys = arrayOf(10, 5, 10, 4)
            for (i in arrays.indices) {
                val index = OrderAgnosticBinarySearch().search(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, index: $index")
            }
        }
    }
}