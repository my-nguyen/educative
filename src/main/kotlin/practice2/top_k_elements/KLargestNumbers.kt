package practice2.top_k_elements

import java.util.*

class KLargestNumbers {
    fun findKLargestNumbers(array: IntArray, top: Int): List<Int> {
        // return find1(array, top)
        return find2(array, top)
    }

    private fun find1(array: IntArray, top: Int): List<Int> {
        val maxQueue = PriorityQueue<Int> { a, b -> b - a }
        for (number in array) {
            maxQueue.add(number)
        }
        val result = mutableListOf<Int>()
        for (i in 0 until top) {
            result.add(maxQueue.poll())
        }
        return result
    }

    private fun find2(array: IntArray, top: Int): List<Int> {
        val minQueue = PriorityQueue<Int>()
        for (number in array) {
            if (minQueue.size < top)
                minQueue.add(number)
            else {
                if (number > minQueue.peek()) {
                    minQueue.poll()
                    minQueue.add(number)
                }
            }
        }
        return minQueue.toList()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val tmp = IntArray(25) { it }
            tmp.shuffle()
            val arrays = arrayOf(
                intArrayOf(3,1,5,12,2,11), intArrayOf(5,12,11,-1,12), tmp, tmp, tmp, tmp
                // intArrayOf(5,2,7,1,9,10,15,3,28,19,4,6,8,11,16,20,13,1,12,14,17),
            )
            val tops = arrayOf(3,3,5,7,10,12)
            for (i in arrays.indices) {
                val numbers = KLargestNumbers().findKLargestNumbers(arrays[i], tops[i])
                println("array: ${arrays[i].contentToString()}, top: ${tops[i]}, numbers: $numbers")
            }
        }
    }
}