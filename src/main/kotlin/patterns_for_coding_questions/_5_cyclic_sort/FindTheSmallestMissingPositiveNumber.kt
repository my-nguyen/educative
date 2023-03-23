package patterns_for_coding_questions._5_cyclic_sort

class FindTheSmallestMissingPositiveNumber {
    // from educative
    // This problem follows the Cyclic Sort pattern and shares similarities with Find the Missing Number with one big
    // difference. In this problem, the numbers are not bound by any range so we can have any number in the input array.
    // However, we will follow a similar approach though as discussed in Find the Missing Number to place the numbers
    // on their correct indices and ignore all numbers that are out of the range of the array (i.e., all negative numbers
    // and all numbers greater than or equal to the length of the array). Once we are done with the cyclic sort we will
    // iterate the array and the first index that does not have the correct number will be the smallest missing positive
    // number!
    fun findNumber(array: IntArray): Int {
        for (i in array.indices) {
            // ignore all numbers that are out of the range of the array (all negative numbers and all numbers greater
            // than or equal to the length of the array)
            while (array[i] > 0 && array[i] <= array.size && array[i] != array[array[i] - 1]) {
                // place the numbers on their correct indices
                swap(array, i, array[i] - 1)
            }
        }

        for (i in array.indices) {
            // the first index that does not have the correct number will be the smallest missing positive number
            if (i + 1 != array[i]) {
                return i + 1
            }
        }
        return array.size + 1
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(-3, 1, 5, 4, 2), intArrayOf(3, -2, 0, 1, 2), intArrayOf(3, 2, 5, 1))
            for (array in arrays) {
                print("array: ${array.contentToString()}")
                val missing = FindTheSmallestMissingPositiveNumber().findNumber(array)
                println(", sorted: ${array.contentToString()}, missing positive: $missing")
            }
        }
    }
}