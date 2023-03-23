package practice2.cyclic_sort

class MissingNumber {
    fun findMissingNumber(array: IntArray): Int {
        for (i in array.indices) {
            while (array[i] < array.size && array[i] != i)
                swap(array, array[i], i)
        }
        for (i in array.indices) {
            if (array[i] != i)
                return i
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
            val arrays = arrayOf(intArrayOf(4,0,3,1), intArrayOf(8,3,5,2,4,6,0,1))
            for (array in arrays) {
                print("array: ${array.contentToString()}, ")
                val missing = MissingNumber().findMissingNumber(array)
                println("sorted: ${array.contentToString()}, missing: $missing")
            }
        }
    }
}