package patterns_for_coding_questions._2_two_pointers

class DutchNationalFlagProblem {
    fun sort(array: IntArray) {
        // mine(array)
        educative(array)
    }

    private fun educative(array: IntArray) {
        // from index 0 to 'low' are 0's, from 'low' to 'high' are 1's, and from 'high' to lastIndex are 2's
        var left = 0
        var right = array.lastIndex
        var current = 0
        while (current <= right) {
            when (array[current]) {
                0 -> {
                    // current element is a 0: swap it with the left element and increment both current and left
                    swap(array, current, left)
                    current++
                    left++
                }
                1 -> {
                    // current element is a 1: no swap
                    current++
                }
                else -> {
                    // current element is a 2: swap it with the right elemtn and decrement right
                    swap(array, current, right)
                    right--
                }
            }
        }
    }

    private fun mine(array: IntArray) {
        val start = sort(array, 0, 0)
        sort(array, start, 1)
    }

    private fun sort(array: IntArray, start: Int, value: Int): Int {
        var left = start
        var right = array.lastIndex
        while (left < right) {
            while (left < right && array[left] == value) {
                left++
            }
            while (right > left && array[right] != value) {
                right--
            }
            if (left < right) {
                swap(array, left, right)
            }
        }
        return left
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1, 0, 2, 1, 0), intArrayOf(2, 2, 0, 1, 2, 0))
            for (array in arrays) {
                print("original: ${array.contentToString()}, ")
                DutchNationalFlagProblem().sort(array)
                println("sorted: ${array.contentToString()}")
            }
        }
    }
}