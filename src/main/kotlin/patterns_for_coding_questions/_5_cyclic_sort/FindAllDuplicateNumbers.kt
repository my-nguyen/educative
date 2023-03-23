package patterns_for_coding_questions._5_cyclic_sort

class FindAllDuplicateNumbers {
    // from educative:
    // This problem follows the Cyclic Sort pattern and shares similarities with Find the Duplicate Number. Following
    // a similar approach, we will place each number at its correct index. After that, we will iterate through the array
    // to find all numbers that are not at the correct indices. All these numbers are duplicates.
    fun findNumbers(array: IntArray): List<Int> {
       return mine(array)
    }

    private fun mine(array: IntArray): List<Int> {
        for (i in array.indices) {
            while (array[i]-1 != i && array[array[i]-1] != array[i]) {
                swap(array, i, array[i]-1)
            }
        }

        val duplicates = mutableListOf<Int>()
        for (i in array.indices) {
            if (array[i]-1 != i) {
                duplicates.add(array[i])
            }
        }
        return duplicates
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(3,4,4,5,5), intArrayOf(5,4,7,2,3,5,3))
            for (array in arrays) {
                print("array: ${array.contentToString()}")
                val duplicates = FindAllDuplicateNumbers().findNumbers(array)
                println(", sorted: ${array.contentToString()}, duplicates: $duplicates")
            }
        }
    }
}