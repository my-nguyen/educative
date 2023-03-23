package patterns_for_coding_questions._11_modified_binary_search

class SearchInRotatedArray {
    fun search(array: IntArray, key: Int): Int {
        return educative(array, key)
        // return NickWhite(array, key)
    }

    // from educative
    // The problem follows the Binary Search pattern. We can use a similar approach as discussed in Order-agnostic
    // Binary Search and modify it similar to Search Bitonic Array to search for the ‘key’ in the rotated array.
    // After calculating the middle, we can compare the numbers at indices start and middle. This will give us two options:
    // 1. If arr[start] <= arr[middle], the numbers from start to middle are sorted in ascending order.
    // 2. Else (arr[start] > arr[middle]), the numbers from middle+1 to end are sorted in ascending order.
    // Once we know which part of the array is sorted, it is easy to adjust our ranges. For example, if option-1 is true,
    // we have two choices:
    // 1. By comparing the ‘key’ with the numbers at index start and middle we can easily find out if the ‘key’ lies
    //    between indices start and middle; if it does, we can skip the second part => end = middle -1.
    // 2. Else, we can skip the first part => start = middle + 1.
    private fun educative(array: IntArray, key: Int): Int {
        var left = 0
        var right = array.lastIndex
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (array[mid] == key) {
                return mid
            }

            if (array[left] <= array[mid]) {
                // left side (between left and mid) is sorted in ascending order
                if (array[left] <= key && key < array[mid]) {
                    // key is between left and mid
                    right = mid - 1
                } else {
                    // key is between mid and right
                    left = mid + 1
                }
            } else {
                // array[left] > array[mid]: array[mid] is the smallest element, and right side (between mid and right)
                // is sorted in ascending order
                if (array[mid] < key && key <= array[right]) {
                    // key is between mid and right
                    left = mid + 1
                } else {
                    // key is between left and mid
                    right = mid - 1
                }
            }
        }

        // we are not able to find the element in the given array
        return -1
    }

    private fun NickWhite(array: IntArray, target: Int): Int {
        if (array.isEmpty()) {
            return -1
        }

        var left = 0
        var right = array.lastIndex
        // modify binary search to look for the smallest element
        while (left < right) {
            val mid = left + (right - left) / 2
            if (array[mid] > array[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        // found the smallest element
        val smallest = left
        // decide whether the target is to the right or to the left of smallest element
        if (array[smallest] <= target && target <= array.last()) {
            left = smallest
            right = array.lastIndex
        } else {
            left = 0
            right = smallest
        }

        // do the normal binary search
        while (left <= right) {
            val midpoint = left + (right - left) / 2
            when {
                array[midpoint] < target -> left = midpoint + 1
                array[midpoint] > target -> right = midpoint - 1
                else -> return midpoint
            }
        }
        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(10, 15, 1, 3, 8),
                intArrayOf(4, 5, 7, 9, 10, -1, 2),
                intArrayOf(4, 5, 6, 7, 0, 1, 2),
                intArrayOf(4, 5, 6, 7, 0, 1, 2),
                intArrayOf(1)
            )
            val keys = arrayOf(15, 10, 0, 3, 0)
            for (i in arrays.indices) {
                val index = SearchInRotatedArray().search(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, index: $index")
            }
        }
    }
}