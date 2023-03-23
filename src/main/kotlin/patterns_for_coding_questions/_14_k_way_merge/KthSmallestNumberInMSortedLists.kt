package patterns_for_coding_questions._14_k_way_merge

import java.util.*

class KthSmallestNumberInMSortedLists {
    fun findKthSmallest(lists: List<Array<Int>>, k: Int): Int {
        // return mine(lists, k)
        return educative(lists, k)
    }

    // from educative
    // This problem follows the K-way merge pattern and we can follow a similar approach as discussed in Merge K Sorted Lists.
    // We can start merging all the arrays, but instead of inserting numbers into a merged list, we will keep count
    // to see how many elements have been inserted in the merged list. Once that count is equal to ‘K’, we have found
    // our required number.
    // A big difference from Merge K Sorted Lists is that in this problem, the input is a list of arrays compared to
    // LinkedLists. This means that when we want to push the next number in the heap we need to know what the index of
    // the current number in the current array was. To handle this, we will need to keep track of the array and
    // the element indices.
    data class Node(var elementIndex: Int, val arrayIndex: Int)
    private fun educative(lists: List<Array<Int>>, k: Int): Int {
        val minHeap = PriorityQueue<Node> { (e1, a1), (e2, a2) -> lists[a1][e1] - lists[a2][e2] }

        // put the 1st element of each array in the min heap
        for (i in lists.indices) {
            if (lists[i] != null) {
                minHeap.add(Node(0, i))
            }
        }

        // take the smallest (top) element form the min heap, if the running count is equal to k return the number
        // if the array of the top element has more elements, add the next element to the heap
        var numberCount = 0
        var result = 0
        while (!minHeap.isEmpty()) {
            val node = minHeap.poll()
            result = lists[node.arrayIndex][node.elementIndex]
            if (++numberCount == k) {
                break
            }
            node.elementIndex++
            if (lists[node.arrayIndex].size > node.elementIndex) {
                minHeap.add(node)
            }
        }
        return result
    }

    data class Entry(val value: Int, val i: Int, val j: Int)
    private fun mine(lists: List<Array<Int>>, k: Int): Int {
        val minHeap = PriorityQueue<Entry> { e1, e2 -> e1.value - e2.value }

        for (i in lists.indices) {
            val entry = Entry(lists[i][0], i, 0)
            minHeap.offer(entry)
        }

        for (h in 1 until k) {
            val top = minHeap.poll()
            val i = top.i
            val j = top.j
            if (j < lists[i].size) {
                val value = lists[i][j+1]
                val entry = Entry(value, i, j+1)
                minHeap.offer(entry)
            }
        }

        return minHeap.poll().value
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(arrayOf(2, 6, 8), arrayOf(3, 6, 7), arrayOf(1, 3, 4)),
                arrayOf(arrayOf(5, 8, 9), arrayOf(1, 7))
            )
            val Ks = arrayOf(5, 3)
            for (i in arrays.indices) {
                val lists = mutableListOf<Array<Int>>()
                for (array in arrays[i]) {
                    lists.add(array)
                }
                val smallest = KthSmallestNumberInMSortedLists().findKthSmallest(lists, Ks[i])
                println("smallest: $smallest")
            }
        }
    }
}