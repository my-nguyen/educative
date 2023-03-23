package practice2.top_k_elements

import java.util.*

class SumOfElements {
    fun findSumOfElements(array: IntArray, start: Int, end: Int): Int {
        return 0
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,3,12,5,15,11), intArrayOf(3,5,8,7)
            )
            val starts = arrayOf(3, 1)
            val ends = arrayOf(6, 4)
            for (i in arrays.indices) {
                val sum = SumOfElements().findSumOfElements(arrays[i], starts[i], ends[i])
                println("array: ${arrays[i].contentToString()}, start: ${starts[i]}, end: ${ends[i]}, sum: $sum")
            }
        }
    }
}