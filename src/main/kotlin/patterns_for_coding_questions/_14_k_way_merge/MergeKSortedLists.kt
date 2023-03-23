package patterns_for_coding_questions._14_k_way_merge

import patterns_for_coding_questions.ListNode
import java.util.*

class MergeKSortedLists {
    fun merge(lists: Array<ListNode>): ListNode {
        return mine(lists)
    }

    // from educative
    // A brute force solution could be to add all elements of the given ‘K’ lists to one list and sort it. If there are
    // a total of ‘N’ elements in all the input lists, then the brute force solution will have a time complexity of
    // O(N∗logN) as we will need to sort the merged list. Can we do better than this? How can we utilize the fact that
    // the input lists are individually sorted?
    // If we have to find the smallest element of all the input lists, we have to compare only the smallest (i.e. the first)
    // element of all the lists. Once we have the smallest element, we can put it in the merged list. Following a similar
    // pattern, we can then find the next smallest element of all the lists to add it to the merged list.
    // The best data structure that comes to mind to find the smallest number among a set of ‘K’ numbers is a Heap.
    // Let’s see how can we use a heap to find a better algorithm.
    // 1. We can insert the first element of each array in a Min Heap.
    // 2. After this, we can take out the smallest (top) element from the heap and add it to the merged list.
    // 3. After removing the smallest element from the heap, we can insert the next element of the same list into the heap.
    // 4. We can repeat steps 2 and 3 to populate the merged list in sorted order.
    // Let’s take the Example-1 mentioned above to go through each step of our algorithm:
    // Given lists: L1=[2, 6, 8], L2=[3, 6, 7], L3=[1, 3, 4]
    // 1. After inserting the 1st element of each list, the heap will have the following elements:
    // 2. We’ll take the top number from the heap, insert it into the merged list and add the next number in the heap.
    // 3. Again, we’ll take the top element of the heap, insert it into the merged list and add the next number to the heap.
    // 4. Repeating the above step, take the top element of the heap, insert it into the merged list and add the next
    //    number to the heap. As there are two 3s in the heap, we can pick anyone but we need to take the next element
    //    from the corresponding list to insert in the heap.
    // We’ll repeat the above step to populate our merged array.
    private fun educative(lists: Array<ListNode>): ListNode {
        val minHeap = PriorityQueue { n1: ListNode?, n2: ListNode? -> n1!!.value - n2!!.value }

        // put the root of each list in the min heap
        for (root in lists) {
            if (root != null) {
                minHeap.add(root)
            }
        }

        // take the smallest (top) element form the min-heap and add it to the result;
        // if the top element has a next element add it to the heap
        var resultHead: ListNode? = null
        var resultTail: ListNode? = null
        while (minHeap.isNotEmpty()) {
            val node = minHeap.poll()
            if (resultHead == null) {
                resultTail = node
                resultHead = resultTail
            } else {
                resultTail!!.next = node
                resultTail = resultTail!!.next
            }
            if (node!!.next != null) {
                minHeap.add(node!!.next)
            }
        }

        return resultHead!!
    }

    private fun mine(lists: Array<ListNode>): ListNode {
        val minHeap = PriorityQueue<ListNode> { e1, e2 -> e1.value - e2.value }

        // store the first (smallest) entry from each list into minHeap
        for (list in lists) {
            minHeap.offer(list)
        }

        val dummy = ListNode(-1)
        var merged = dummy
        while (minHeap.isNotEmpty()) {
            // take the smallest entry of all
            var list = minHeap.poll()
            // save the entry in the merged list
            merged.next = list
            merged = list
            // store in minHeap the next entry from the same list that contains the smallest entry
            if (list.next != null) {
                list = list.next
                minHeap.offer(list)
            }
        }
        return dummy.next!!
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val data = arrayOf(
                arrayOf(intArrayOf(2, 6, 8), intArrayOf(3, 6, 7), intArrayOf(1, 3, 4)),
                arrayOf(intArrayOf(5, 8, 9), intArrayOf(1, 7))
            )
            for (arrays in data) {
                val lists = mutableListOf<ListNode>()
                for (array in arrays) {
                    val list = ListNode.build(array)
                    lists.add(list)
                }
                val merged = MergeKSortedLists().merge(lists.toTypedArray())
                merged.print()
            }
        }
    }
}