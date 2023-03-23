package patterns_for_coding_questions._11_modified_binary_search

class RotationCount {
    fun countRotations(array: IntArray): Int {
        // return NickWhite(array)
        return educative(array)
    }

    // from educative
    // This problem follows the Binary Search pattern. We can use a similar strategy as discussed in Search in Rotated
    // Array.
    // In this problem, actually, we are asked to find the index of the minimum element. The number of times the minimum
    // element is moved to the right will be equal to the number of rotations. An interesting fact about the minimum
    // element is that it is the only element in the given array which is smaller than its previous element. Since
    // the array is sorted in ascending order, all other elements are bigger than their previous element.
    // After calculating the middle, we can compare the number at index middle with its previous and next number.
    // This will give us two options:
    // 1. If arr[middle] > arr[middle + 1], then the element at middle + 1 is the smallest.
    // 2. If arr[middle - 1] > arr[middle], then the element at middle is the smallest.
    // To adjust the ranges we can follow the same approach as discussed in Search in Rotated Array. Comparing
    // the numbers at indices start and middle will give us two options:
    // 1. If arr[start] < arr[middle], the numbers from start to middle are sorted.
    // 2. Else, the numbers from middle + 1 to end are sorted.
    private fun educative(array: IntArray): Int {
        var start = 0
        var end = array.lastIndex
        while (start < end) {
            val mid = start + (end - start) / 2
            // check the 2 adjacent elements at mid to see if one of them is the target
            if (mid < end && array[mid] > array[mid + 1]) {
                // if mid is greater than the next element, then the element at middle + 1 is the smallest
                return mid + 1
            } else if (mid > start && array[mid - 1] > array[mid]) {
                // if mid is smaller than the previous element, then the element at middle is the smallest
                return mid
            }

            if (array[start] < array[mid]) {
                // left side is sorted, so the pivot is on right side
                start = mid + 1
            } else {
                // right side is sorted, so the pivot is on the left side
                end = mid - 1
            }
        }

        // the array has not been rotated
        return 0
    }

    private fun NickWhite(array: IntArray): Int {
        var left = 0
        var right = array.lastIndex
        while (left < right) {
            val mid = left + (right - left) / 2
            if (array[mid] > array[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(10, 15, 1, 3, 8), intArrayOf(4, 5, 7, 9, 10, -1, 2), intArrayOf(1, 3, 8, 10)
            )
            for (array in arrays) {
                val rotations = RotationCount().countRotations(array)
                println("array: ${array.contentToString()}, rotations: $rotations")
            }
        }
    }
}