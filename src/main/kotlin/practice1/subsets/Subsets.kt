package practice1.subsets

import java.util.*

class Subsets {
    fun findSubsets(array: IntArray): List<List<Int>> {
        val queue = LinkedList<List<Int>>()
        queue.add(mutableListOf())
        for (number in array) {
            val size = queue.size
            for (i in 0 until size) {
                val old = queue.poll()
                val new = old.toMutableList()
                new.add(number)
                queue.add(old)
                queue.add(new)
            }
        }
        return queue.toList()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,3), intArrayOf(1,5,3), /*intArrayOf(1,3,3)*/)
            for (array in arrays) {
                print("array: ${array.contentToString()}, ")
                val subsets = Subsets().findSubsets(array)
                print("subsets: ")
                for (subset in subsets) {
                    print("$subset, ")
                }
                println()
            }
        }
    }
}