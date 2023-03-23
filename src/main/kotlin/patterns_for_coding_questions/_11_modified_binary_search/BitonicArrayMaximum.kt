package patterns_for_coding_questions._11_modified_binary_search

class BitonicArrayMaximum {
    fun findMax(array: IntArray): Int {
        return NickWhite(array)
    }

    // from educative
    // A bitonic array is a sorted array; the only difference is that its first part is sorted in ascending order and
    // the second part is sorted in descending order. We can use a similar approach as discussed in Order-agnostic
    // Binary Search. Since no two consecutive numbers are same (as the array is monotonically increasing or decreasing),
    // whenever we calculate the middle, we can compare the numbers pointed out by the index middle and middle+1 to find
    // if we are in the ascending or the descending part. So:
    // 1. If arr[middle] > arr[middle + 1], we are in the second (descending) part of the bitonic array. Therefore,
    //    our required number could either be pointed out by middle or will be before middle. This means we will be
    //    doing: end = middle.
    // 2. If arr[middle] < arr[middle + 1], we are in the first (ascending) part of the bitonic array. Therefore,
    //    the required number will be after middle. This means we will be doing: start = middle + 1.
    // We can break when start == end. Due to the two points mentioned above, both start and end will be pointing at
    // the maximum number of the bitonic array.
    private fun educative(array: IntArray): Int {
        return NickWhite(array)
    }

    private fun NickWhite(array: IntArray): Int {
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
        return array[right]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,3,8,12,4,2), intArrayOf(3,8,3,1), intArrayOf(1,3,8,12), intArrayOf(10,9,8))
            for (array in arrays) {
                val peak = BitonicArrayMaximum().findMax(array)
                println("array: ${array.contentToString()}, peak: $peak")
            }
        }
    }
}