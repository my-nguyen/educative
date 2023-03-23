package patterns_for_coding_questions._11_modified_binary_search

class SearchBitonicArray {
    fun search(array: IntArray, key: Int): Int {
        return mine(array, key)
    }

    // from educative
    // The problem follows the Binary Search pattern. Since Binary Search helps us efficiently find a number in a sorted
    // array we can use a modified version of the Binary Search to find the ‘key’ in the bitonic array.
    // Here is how we can search in a bitonic array:
    // 1. First, we can find the index of the maximum value of the bitonic array, similar to Bitonic Array Maximum.
    //    Let’s call the index of the maximum number maxIndex.
    // 2. Now, we can break the array into two sub-arrays:
    //    * Array from index ‘0’ to maxIndex, sorted in ascending order.
    //    * Array from index maxIndex+1 to array_length-1, sorted in descending order.
    // 3. We can then call Binary Search separately in these two arrays to search the ‘key’. We can use the same
    //    Order-agnostic Binary Search for searching.
    private fun educative(array: IntArray, key: Int): Int {
        return mine(array, key)
    }

    private fun mine(array: IntArray, key: Int): Int {
        val peak = findPeak(array)
        val left = findAscending(array, 0, peak, key)
        return if (left != -1) {
            left
        } else {
            findDescending(array, peak, array.lastIndex, key)
        }
    }

    private fun findPeak(array: IntArray): Int {
        var left = 0
        var right = array.lastIndex
        while (left < right) {
            val mid = left + (right-left)/2
            if (array[mid] < array[mid+1]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return right
    }

    private fun findAscending(array: IntArray, start: Int, end: Int, key: Int): Int {
        var left = start
        var right = end
        while (left <= right) {
            val mid = left + (right-left)/2
            when {
                array[mid] < key -> left = mid + 1
                array[mid] > key -> right = mid - 1
                else -> return mid
            }
        }
        return -1
    }

    private fun findDescending(array: IntArray, start: Int, end: Int, key: Int): Int {
        var left = start
        var right = end
        while (left <= right) {
            val mid = left + (right-left)/2
            when {
                array[mid] > key -> left = mid + 1
                array[mid] < key -> right = mid - 1
                else -> return mid
            }
        }
        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,3,8,4,3), intArrayOf(3,8,3,1), intArrayOf(1,3,8,12), intArrayOf(10,9,8))
            val keys = arrayOf(4, 8, 12, 10)
            for (i in arrays.indices) {
                val index = SearchBitonicArray().search(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, index: $index")
            }
        }
    }
}