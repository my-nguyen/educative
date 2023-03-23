package practice2.cyclic_sort

class CyclicSort {
    fun sort(array: IntArray) {
        for (i in array.indices) {
            while (array[i]-1 != i)
                swap(array, array[i]-1, i)
        }
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(3,1,5,4,2), intArrayOf(2,6,4,3,1,5), intArrayOf(1,5,6,4,3,2))
            for (array in arrays) {
                print("array: ${array.contentToString()}, ")
                CyclicSort().sort(array)
                println("sorted: ${array.contentToString()}")
            }
        }
    }
}