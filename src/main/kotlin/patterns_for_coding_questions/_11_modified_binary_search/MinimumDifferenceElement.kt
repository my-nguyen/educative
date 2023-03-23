package patterns_for_coding_questions._11_modified_binary_search

import kotlin.math.abs

class MinimumDifferenceElement {
    fun searchMinDiffElement(array: IntArray, key: Int): Int {
        return mine(array, key)
    }

    // from educative
    // The problem follows the Binary Search pattern. Since Binary Search helps us find a number in a sorted array
    // efficiently, we can use a modified version of the Binary Search to find the number that has the minimum difference
    // with the given ‘key’.
    // We can use a similar approach as discussed in Order-agnostic Binary Search. We will try to search for the ‘key’
    // in the given array. If we find the ‘key’ we will return it as the minimum difference number. If we can’t find
    // the ‘key’, (at the end of the loop) we can find the differences between the ‘key’ and the numbers pointed out by
    // indices start and end, as these two numbers will be closest to the ‘key’. The number that gives minimum difference
    // will be our required number.
    private fun educative(array: IntArray, key: Int): Int {
        if (key >= array.last()) {
            return array.lastIndex
        } else if (key <= array.first()) {
            return 0
        }

        var left = 0
        var right = array.lastIndex
        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                array[mid] < key -> left = mid + 1
                array[mid] > key -> right = mid - 1
                else -> return array[mid]
            }
        }

        // at the end of the while loop, 'left == right+1', and key is not found in the array: return the element
        // which is closest to the 'key'
        return if ((array[left] - key) < (key - array[right])) array[left] else array[right]
    }

    private fun mine(array: IntArray, key: Int): Int {
        if (key >= array.last()) {
            return array.lastIndex
        } else if (key <= array.first()) {
            return 0
        }

        var left = 0
        var right = array.lastIndex
        var minDiff = Integer.MAX_VALUE
        var minIndex = -1
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (array[mid] == key) {
                return array[mid]
            } else {
                val diff = abs(key - array[mid])
                if (diff < minDiff) {
                    minDiff = diff
                    minIndex = mid
                }
                if (array[mid] < key) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
        }
        return array[minIndex]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(4, 6, 10), intArrayOf(4, 6, 10), intArrayOf(1, 3, 8, 10, 15), intArrayOf(4, 6, 10)
            )
            val keys = arrayOf(7, 4, 12, 17)
            for (i in arrays.indices) {
                val element = MinimumDifferenceElement().searchMinDiffElement(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, element: $element")
            }
        }
    }
}