package patterns_for_coding_questions._14_k_way_merge

import java.util.*


class SmallestNumberRange {
    // i don't understand this solution
    // from educative
    // This problem follows the K-way merge pattern and we can follow a similar approach as discussed in Merge K Sorted Lists.
    // We can start by inserting the first number from all the arrays in a min-heap. We will keep track of the largest
    // number that we have inserted in the heap (let’s call it currentMaxNumber).
    // In a loop, we’ll take the smallest (top) element from the min-heap and currentMaxNumber has the largest element
    // that we inserted in the heap. If these two numbers give us a smaller range, we’ll update our range. Finally,
    // if the array of the top element has more elements, we’ll insert the next element to the heap.
    // We can finish searching the minimum range as soon as an array is completed or, in other terms, the heap has less
    // than ‘M’ elements.
    data class Node(var elementIndex: Int, val arrayIndex: Int)

    fun findSmallestRange(lists: List<Array<Int>>): IntArray {
        val minHeap = PriorityQueue<Node> { (e1, a1), (e2, a2) ->
            lists[a1][e1] - lists[a2][e2]
        }

        var rangeStart = 0
        var rangeEnd = Int.MAX_VALUE
        var currentMaxNumber = Int.MIN_VALUE
        // put the 1st element of each array into the min heap
        for (i in lists.indices) {
            if (lists[i] != null) {
                minHeap.add(Node(0, i))
                // keep track of the max number in the min-heap
                currentMaxNumber = maxOf(currentMaxNumber, lists[i][0])
            }
        }

        // take the smallest (top) element from the min heap. if it gives us smaller range, update the ranges.
        // if the array of the top element has more elements, insert the next element in the heap
        while (minHeap.size == lists.size) {
            val node = minHeap.poll()
            if (rangeEnd - rangeStart > currentMaxNumber - lists[node.arrayIndex][node.elementIndex]) {
                rangeStart = lists[node.arrayIndex][node.elementIndex]
                rangeEnd = currentMaxNumber
            }
            node.elementIndex++
            if (lists[node.arrayIndex].size > node.elementIndex) {
                minHeap.add(node) // insert the next element in the heap
                currentMaxNumber = maxOf(currentMaxNumber, lists[node.arrayIndex][node.elementIndex])
            }
        }
        return intArrayOf(rangeStart, rangeEnd)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val data = arrayOf(
                arrayOf(arrayOf(1, 5, 8), arrayOf(4, 12), arrayOf(7, 8, 10)),
                arrayOf(arrayOf(1, 9), arrayOf(4, 12), arrayOf(7, 10, 16))
            )
            for (arrays in data) {
                val lists = mutableListOf<Array<Int>>()
                for (array in arrays) {
                    lists.add(array)
                }
                val smallest = SmallestNumberRange().findSmallestRange(lists)
                println("smallest: ${smallest.contentToString()}")
            }
        }
    }
}