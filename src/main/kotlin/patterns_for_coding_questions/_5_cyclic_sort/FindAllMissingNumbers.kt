package patterns_for_coding_questions._5_cyclic_sort

class FindAllMissingNumbers {
    // from educative
    // This problem follows the Cyclic Sort pattern and shares similarities with Find the Missing Number with one
    // difference. In this problem, there can be many duplicates whereas in ‘Find the Missing Number’ there were no
    // duplicates and the range was greater than the length of the array.
    // However, we will follow a similar approach though as discussed in Find the Missing Number to place the numbers on
    // their correct indices. Once we are done with the cyclic sort we will iterate the array to find all indices that
    // are missing the correct numbers.
    fun findNumbers(array: IntArray): List<Int> {
        /*var i = 0
        while (i < array.size) {
            if (array[i] != array[array[i] - 1]) {
                swap(array, i, array[i]-1)
            } else {
                i++
            }
        }*/
        for (i in array.indices) {
            println("i: $i, array[i]: ${array[i]}, array[array[i] - 1]: ${array[array[i] - 1]}")
            // if element is out of order, it'll be swapped to the correct position
            // if element is in order, then either it's in its correct position or a duplicate number
            // and the duplicate will occupy the position of a missing number
            while (array[i] != array[array[i] - 1]) {
                // print("swapped at $i and ${array[i]-1}")
                swap(array, i, array[i]-1)
                // println(", post-swap: ${array.contentToString()}")
            }
        }

        val missingNumbers = mutableListOf<Int>()
        for (j in array.indices) {
            // the duplicate numbers are ones not in their correct positions
            if (array[j] != j + 1) {
                missingNumbers.add(j + 1)
            }
        }
        return missingNumbers
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(2,3,1,8,2,3,5,1), intArrayOf(2,4,1,2), intArrayOf(2,3,2,1))
            for (array in arrays) {
                print("array: ${array.contentToString()}")
                val missingNumbers = FindAllMissingNumbers().findNumbers(array)
                println(", sorted: ${array.contentToString()}, missing: $missingNumbers")
            }
        }
    }
}