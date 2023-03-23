package patterns_for_coding_questions._11_modified_binary_search

class SearchInRotatedArray2 {
    fun search(array: IntArray, key: Int): Int {
        return educative(array, key)
    }

    // from educative
    // The only problematic scenario is when the numbers at indices start, middle, and end are the same, as in this case,
    // we can’t decide which part of the array is sorted. In such a case, the best we can do is to skip one number from
    // both ends: start = start + 1 & end = end - 1.
    private fun educative(array: IntArray, key: Int): Int {
        var left = 0
        var right: Int = array.lastIndex
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (array[mid] == key) {
                return mid
            }

            // the only difference from the previous solution: if numbers at indexes left, mid, and right are same,
            // we can't choose a side. so the best we can do is to skip one number from both ends as key != arr[mid]
            if (array[left] == array[mid] && array[right] == array[mid]) {
                ++left
                --right
            } else if (array[left] <= array[mid]) { // left side is sorted in ascending order
                if (key >= array[left] && key < array[mid]) {
                    right = mid - 1
                } else { //key > arr[mid]
                    left = mid + 1
                }
            } else { // right side is sorted in ascending order
                if (key > array[mid] && key <= array[right]) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
        }

        // we are not able to find the element in the given array
        return -1
    }
}

fun main() {
    val arrays = arrayOf(
        intArrayOf(3, 7, 3, 3, 3),
    )
    val keys = arrayOf(7)
    for (i in arrays.indices) {
        val index = SearchInRotatedArray2().search(arrays[i], keys[i])
        println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, index: $index")
    }
}