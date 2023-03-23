package practice1.k_way_merge

import practice1.ListNode
import java.util.*

class MergeKSortedLists {
    fun merge(heads: Array<ListNode>): ListNode {
        val minHeap = PriorityQueue<ListNode> { a, b -> a.value - b.value }
        for (head in heads)
            minHeap.add(head)

        var head: ListNode? = null
        var tail: ListNode? = null
        while (minHeap.isNotEmpty()) {
            val node = minHeap.poll()
            if (head == null) {
                head = node
                tail = node
            } else {
                tail!!.next = node
                tail = tail!!.next
            }
            if (node.next != null)
                minHeap.add(node.next)
        }
        return head!!
    }

    fun merge2(array: Array<ListNode>): ListNode {
        val minHeap = PriorityQueue<ListNode> { a, b -> a.value - b.value }
        for (head in array)
            minHeap.add(head)

        var head: ListNode? = null
        var tail: ListNode? = null
        while (minHeap.isNotEmpty()) {
            val node = minHeap.poll()
            if (head == null) {
                head = node
                tail = node
            } else {
                tail?.next = node
                tail = tail?.next
            }
            if (node.next != null)
                minHeap.add(node.next)
        }
        return head!!
    }

    fun merge1(array: Array<ListNode>): ListNode {
        val minHeap = PriorityQueue<ListNode> { a, b -> a.value - b.value }
        for (list in array) {
            var current : ListNode? = list
            while (current != null) {
                minHeap.add(current)
                current = current.next
            }
        }
        val head = minHeap.poll()
        var current = head
        while (minHeap.isNotEmpty()) {
            current.next = minHeap.poll()
            current = current.next
        }
        return head
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val data = arrayOf(
                arrayOf(intArrayOf(2,6,8), intArrayOf(3,6,7), intArrayOf(1,3,4)),
                arrayOf(intArrayOf(5,8,9), intArrayOf(1,7))
            )
            for (arrays in data) {
                val array = Array(arrays.size) { ListNode() }
                print("unmerged lists: ")
                for (i in arrays.indices) {
                    array[i] = ListNode.build(arrays[i])
                    array[i].print()
                    print(" ** ")
                }
                println()
                val merged = MergeKSortedLists().merge(array)
                print("merged list: ")
                merged.print()
                println()
            }
        }
    }
}