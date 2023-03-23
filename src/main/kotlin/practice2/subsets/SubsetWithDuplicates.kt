package practice2.subsets

import java.util.*

class SubsetWithDuplicates {
    fun findSubsets(array: IntArray): List<List<Int>> {
        return listOf()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,3,3), intArrayOf(1,5,3,3))
            for (array in arrays) {
                print("array: ${array.contentToString()}, ")
                val subsets = SubsetWithDuplicates().findSubsets(array)
                print("subsets: ")
                for (subset in subsets) {
                    print("$subset, ")
                }
                println()
            }
        }
    }
}