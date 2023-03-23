package practice2.cyclic_sort

class FindAllDuplicate {
    fun findNumbers(array: IntArray): List<Int> {
        for (i in array.indices) {
            while (array[i]-1 != i && array[array[i]-1] != array[i])
                swap(array, array[i]-1, i)
        }
        val result = mutableListOf<Int>()
        for (i in array.indices) {
            if (array[i]-1 != i)
                result.add(array[i])
        }
        return result
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
                print("array: ${array.contentToString()}, ")
                val duplicates = FindAllDuplicate().findNumbers(array)
                println("sorted: ${array.contentToString()}, duplicates: $duplicates")
            }
        }
    }
}