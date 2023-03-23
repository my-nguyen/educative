package practice2.top_k_elements

import java.util.*

class KthLargestNumberInStream {
    fun KthLargestNumberInStream(array: IntArray, k: Int) {
    }

    fun add(number: Int): Int {
        return 0
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(3,1,5,12,2,11), intArrayOf(3,5,7,9,11,13))
            val tops = arrayOf(4,4)
            val numbers = arrayOf(intArrayOf(6,6,6), intArrayOf(6,10,10,10))
            val stream = KthLargestNumberInStream()
            for (i in arrays.indices) {
                stream.KthLargestNumberInStream(arrays[i], tops[i])
                println("array: ${arrays[i].contentToString()}, top: ${tops[i]}")
                for (number in numbers[i])
                    println("added ${number}: ${stream.add(number)}")
            }
        }
    }
}