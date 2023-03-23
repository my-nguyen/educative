package practice2.top_k_elements

import java.util.*

class TopKFrequentNumbers {
    fun findTopKFrequentNumbers(array: IntArray, top: Int): List<Int> {
        return listOf()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = intArrayOf(1,2,2,3,3,3,4,4,4,4,5,5,5,5,5,6,6,6,6,6,6,7,7,7,7,7,7,7,8,8,8,8,8,8,8,8)
            val arrays = arrayOf(
                intArrayOf(1,3,5,12,11,12,11), intArrayOf(5,12,11,3,11),
                array, array, array, array, array, array
            )
            val tops = arrayOf(2, 2, 1, 2, 3, 4, 5, 6)
            for (i in arrays.indices) {
                val numbers = TopKFrequentNumbers().findTopKFrequentNumbers(arrays[i], tops[i])
                println("array: ${arrays[i].contentToString()}, top: ${tops[i]}, frequent numbers: $numbers")
            }
        }
    }
}