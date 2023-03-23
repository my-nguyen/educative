package practice1.cyclic_sort

class AllMissingNumbers {
    fun findNumbers(array: IntArray): List<Int> {
        for (i in array.indices) {
            while (array[i]-1 != i && array[array[i]-1] != array[i])
                swap(array, i, array[i]-1)
        }
        val missing = mutableListOf<Int>()
        for (i in array.indices) {
            if (array[i] != i+1)
                missing.add(i+1)
        }
        return missing
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
                print("array: ${array.contentToString()}, ")
                val missingNumbers = AllMissingNumbers().findNumbers(array)
                println("sorted: ${array.contentToString()}, missing: $missingNumbers")
            }
        }
    }
}