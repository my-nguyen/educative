package practice2.cyclic_sort

class FindDuplicate {
    fun findNumber(array: IntArray): Int {
        for (i in array.indices) { // 1,4,4,3,2
            while (array[i]-1 != i && array[array[i]-1] != array[i])
                swap(array, array[i]-1, i)
        }
        for (i in array.indices) {
            if (array[i]-1 != i)
                return array[i]
        }
        return -1
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,4,4,3,2), intArrayOf(2,1,3,3,5,4), intArrayOf(2,4,1,4,4))
            for (array in arrays) {
                print("array: ${array.contentToString()}, ")
                val duplicate = FindDuplicate().findNumber(array)
                println("sorted: ${array.contentToString()}, duplicate: $duplicate")
            }
        }
    }
}