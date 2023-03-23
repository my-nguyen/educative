package patterns_for_coding_questions._10_subsets

import java.util.*

class Permutations {
    fun findPermutations(numbers: IntArray): List<List<Int>> {
        // return mine(numbers)
        return educative(numbers)
    }

    // from educative
    // This problem follows the Subsets pattern and we can follow a similar Breadth First Search (BFS) approach. However,
    // unlike Subsets, every permutation must contain all the numbers.
    // Let’s take the example-1 mentioned above to generate all the permutations. Following a BFS approach, we will
    // consider one number at a time:
    // 1. If the given set is empty then we have only an empty permutation set: []
    // 2. Let’s add the first element (1), the permutations will be: [1]
    // 3. Let’s add the second element (3), the permutations will be: [3,1], [1,3]
    // 4. Let’s add the third element (5), the permutations will be: [5,3,1], [3,5,1], [3,1,5], [5,1,3], [1,5,3], [1,3,5]
    // Let’s analyze the permutations in the 3rd and 4th step. How can we generate permutations in the 4th step from
    // the permutations of the 3rd step?
    // If we look closely, we will realize that when we add a new number (5), we take each permutation of the previous
    // step and insert the new number in every position to generate the new permutations. For example, inserting ‘5’
    // in different positions of [3,1] will give us the following permutations:
    // 1. Inserting ‘5’ before ‘3’: [5,3,1]
    // 2. Inserting ‘5’ between ‘3’ and ‘1’: [3,5,1]
    // 3. Inserting ‘5’ after ‘1’: [3,1,5]
    private fun educative(array: IntArray): List<List<Int>> {
        val permutations = mutableListOf<List<Int>>()
        val queue = LinkedList<List<Int>>()
        // add empty list into queue to start BFS rolling
        val empty = mutableListOf<Int>()
        queue.add(empty)
        for (number in array) {
            // take all existing permutations and add the current number to create new permutations
            val size = queue.size
            for (i in 1..size) {
                val top = queue.poll()
                // println("element $number, queue size $size, i $i, queued: $queued")
                // create a new permutation by adding the current number at every position
                for (j in 0..top.size) {
                    val permutation = top.toMutableList()
                    // add new number into permutation at position j which goes from 0 to queued.size
                    // so if previous = [1] and number = 3, this will create 2 new permutations [3,1] and [1,3]
                    permutation.add(j, number)
                    // print("j: $j, queued.size: ${queued.size}, element $number, new permutation: $permutation, ")
                    if (permutation.size == array.size) {
                        // if permutation contains all elements from array, then save permutation in result list
                        permutations.add(permutation)
                    } else {
                        // otherwise, push permutation into queue for processing at the next outer loop iteration
                        queue.add(permutation)
                    }
                }
            }
        }
        return permutations
    }

    // incomplete solution
    private fun mine(numbers: IntArray): List<List<Int>> {
        val permutations = mutableListOf<List<Int>>()
        for (i in numbers.indices) {
            val subarray = subarray(numbers, i)
            val permutation = mutableListOf<Int>()
            permutation.add(numbers[i])
            permutations.add(permutation)
        }
        return permutations
    }

    private fun subarray(numbers: IntArray, index: Int): IntArray {
        val subarray = IntArray(numbers.size)
        var j = 0
        for (i in numbers.indices) {
            if (i != index) {
                subarray[j] = numbers[i]
                j++
            }
        }
        return subarray
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(intArrayOf(1, 3, 5), /*intArrayOf(1, 2, 3)*/)
            for (numbers in array) {
                val permutations = Permutations().findPermutations(numbers)
                for (permutation in permutations) {
                    print("$permutation, ")
                }
                println()
            }
        }
    }
}