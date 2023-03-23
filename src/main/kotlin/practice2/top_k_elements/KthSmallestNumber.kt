package practice2.top_k_elements

import java.util.*

class KthSmallestNumber {
    fun findKthSmallestNumber(array: IntArray, smallest: Int): Int {
        return 0
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = IntArray(15) { it }
            array.shuffle()
            val arrays = arrayOf(
                intArrayOf(1,5,12,2,11,5), intArrayOf(1,5,12,2,11,5), intArrayOf(5,12,11,-1,12), array, array, array
            )
            val bottoms = arrayOf(3, 4, 3, 5, 8, 10)
            for (i in arrays.indices) {
                val smallest = KthSmallestNumber().findKthSmallestNumber(arrays[i], bottoms[i])
                println("array: ${arrays[i].contentToString()}, bottom: ${bottoms[i]}, smallest: $smallest")
            }
        }
    }
}