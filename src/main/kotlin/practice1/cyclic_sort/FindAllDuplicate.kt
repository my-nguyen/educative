package practice1.cyclic_sort

class FindAllDuplicate {
    fun findNumbers(array: IntArray): List<Int> {
        val numbers = mutableListOf<Int>()
        for (i in array.indices) {
            while (array[i]-1 != i) {
                if (array[array[i]-1] == array[i]) {
                    numbers.add(array[i])
                    break
                }
                swap(array, i, array[i]-1)
            }
        }
        return numbers
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