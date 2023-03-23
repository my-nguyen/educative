package practice1.subsets

import java.util.*

class Permutations {
    fun findPermutations(array: IntArray): List<List<Int>> {
        val queue = LinkedList<List<Int>>()
        queue.add(mutableListOf())
        val result = mutableListOf<List<Int>>()
        for (number in array) {
            val size = queue.size
            for (i in 0 until size) {
                val top = queue.poll()
                for (j in 0..top.size) {
                    val list = top.toMutableList()
                    list.add(j, number)

                    if (list.size == array.size)
                        result.add(list)
                    else
                        queue.add(list)
                }
            }
        }
        return result
    }

    fun findPermutations2(array: IntArray): List<List<Int>> {
        val queue = LinkedList<List<Int>>()
        queue.add(mutableListOf())
        val result = mutableListOf<List<Int>>()
        for (number in array) {
            val size = queue.size
            for (i in 0 until size) {
                val top = queue.poll()
                for (j in 0..top.size) {
                    val list = top.toMutableList()
                    list.add(j, number)

                    if (list.size == array.size)
                        result.add(list)
                    else
                        queue.add(list)
                }
            }
        }
        return result
    }

    fun findPermutations1(array: IntArray): List<List<Int>> {
        val results = mutableListOf<List<Int>>()
        val queue = LinkedList<List<Int>>()
        queue.add(mutableListOf())
        for (number in array) {
            val size = queue.size
            for (i in 0 until size) {
                val top = queue.poll()
                for (j in 0..top.size) {
                    val list = top.toMutableList()
                    list.add(j, number)

                    if (list.size == array.size)
                        results.add(list)
                    else
                        queue.add(list)
                }
            }
        }
        return results
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,3,5), intArrayOf(1,2,3))
            for (array in arrays) {
                print("array: ${array.contentToString()}, ")
                val permutations = Permutations().findPermutations(array)
                print("permutations: ")
                for (permutation in permutations) {
                    print("$permutation, ")
                }
                println()
            }
        }
    }
}