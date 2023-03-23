package patterns_for_coding_questions._11_modified_binary_search

class NumberRange {
    fun findRange(array: IntArray, key: Int): IntArray {
        // return NickWhite(array, key)
        return educative(array, key)
    }

    // from educative
    // The problem follows the Binary Search pattern. Since Binary Search helps us find a number in a sorted array
    // efficiently, we can use a modified version of the Binary Search to find the first and the last position of a number.
    // We can use a similar approach as discussed in Order-agnostic Binary Search. We will try to search for the ‘key’
    // in the given array; if the ‘key’ is found (i.e. key == arr[middle) we have two options:
    // 1. When trying to find the first position of the ‘key’, we can update end = middle - 1 to see if the key is
    //    present before middle.
    // 2. When trying to find the last position of the ‘key’, we can update start = middle + 1 to see if the key is
    //    present after middle.
    // In both cases, we will keep track of the last position where we found the ‘key’. These positions will be the
    // required range.
    private fun educative(array: IntArray, key: Int): IntArray {
        val left = search(array, key, false)
        return if (left == -1) {
            intArrayOf(-1, -1)
        } else {
            val right = search(array, key, true)
            intArrayOf(left, right)
        }
    }

    private fun search(array: IntArray, key: Int, findMax: Boolean): Int {
        var left = 0
        var right = array.lastIndex
        var index = -1
        while (left <= right) {
            val mid = left + (right-left)/2
            if (array[mid] > key) {
                right = mid - 1
            } else if (array[mid] < key) {
                left = mid + 1
            } else {
                index = mid
                if (findMax) {
                    // search forward to find the last index of 'key'
                    left = mid + 1
                } else {
                    // search backward to find the first index of 'key'
                    right = mid - 1
                }
            }
        }
        return index
    }

    private fun NickWhite(array: IntArray, key: Int): IntArray {
        val left = findLeft(array, key)
        return if (left == -1) {
            intArrayOf(-1, -1)
        } else {
            val right = findRight(array, key)
            intArrayOf(left, right)
        }
    }

    private fun findLeft(array: IntArray, key: Int): Int {
        var left = 0
        var right = array.lastIndex
        var index = -1
        while (left <= right) {
            val mid = left + (right-left)/2
            if (array[mid] >= key) {
                right = mid - 1
                if (array[mid] == key) {
                    index = mid
                }
            } else {
                left = mid + 1
            }
        }
        return index
    }

    private fun findRight(array: IntArray, key: Int): Int {
        var left = 0
        var right = array.lastIndex
        var index = -1
        while (left <= right) {
            val mid = left + (right-left)/2
            if (array[mid] <= key) {
                left = mid + 1
                if (array[mid] == key) {
                    index = mid
                }
            } else {
                right = mid - 1
            }
        }
        return index
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(4,6,6,6,9), intArrayOf(1,3,8,10,15), intArrayOf(1,3,8,10,15)
            )
            val keys = arrayOf(6, 10, 12)
            for (i in arrays.indices) {
                val range = NumberRange().findRange(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, range: ${range.contentToString()}")
            }
        }
    }
}