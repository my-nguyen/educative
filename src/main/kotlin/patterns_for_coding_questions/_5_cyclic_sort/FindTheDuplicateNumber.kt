package patterns_for_coding_questions._5_cyclic_sort

class FindTheDuplicateNumber {
    fun findNumber(array: IntArray): Int {
        // return mine(array)
        return educative(array)
    }

    private fun educative(array: IntArray): Int {
        for (i in array.indices) {
            if (array[i] - 1 != i) {
                if (array[array[i] - 1] != array[i]) {
                    swap(array, i, array[i] - 1)
                } else {
                    // found the duplicate: return right here. there's no need to wait and look for it in another loop
                    // as in mine version
                    return array[i]
                }
            }
        }
        return -1
    }

    private fun mine(array: IntArray): Int {
        for (i in array.indices) { // 1,4,4,3,2
            while (array[i] - 1 != i && array[array[i] - 1] != array[i]) {
                swap(array, i, array[i] - 1)
            }
        }

        for (i in array.indices) {
            if (array[i] - 1 != i) {
                return array[i]
            }
        }
        return 0
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1, 4, 4, 3, 2), intArrayOf(2, 1, 3, 3, 5, 4), intArrayOf(2, 4, 1, 4, 4))
            for (array in arrays) {
                print("array: ${array.contentToString()}")
                val duplicate = FindTheDuplicateNumber().findNumber(array)
                println(", sorted: ${array.contentToString()}, duplicate: $duplicate")
            }
        }
    }
}