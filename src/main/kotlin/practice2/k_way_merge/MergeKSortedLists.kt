package practice2.k_way_merge

import practice1.ListNode
import java.util.*

class MergeKSortedLists {
    fun merge(heads: Array<ListNode>): ListNode {
        // return practice1(heads)
        // return practice2(heads)
        // return practice3(heads)
        return practice4(heads)
    }

    fun practice4(heads: Array<ListNode>): ListNode {
        val minQueue = PriorityQueue<ListNode> { a, b -> a.value - b.value }
        for (head in heads)
            minQueue.add(head)

        var head: ListNode? = null
        var tail: ListNode? = null
        while (minQueue.isNotEmpty()) {
            val node = minQueue.poll()
            if (head == null) {
                head = node
                tail = node
            } else {
                tail!!.next = node
                tail = tail.next
            }
            if (node.next != null)
                minQueue.add(node.next)
        }
        return head!!
    }

    fun practice3(heads: Array<ListNode>): ListNode {
        val minQueue = PriorityQueue<ListNode> { a, b -> a.value - b.value }
        for (head in heads)
            minQueue.add(head)

        var head: ListNode? = null
        var tail: ListNode? = null
        while (minQueue.isNotEmpty()) {
            val node = minQueue.poll()
            if (head == null) {
                head = node
                tail = node
            } else {
                tail!!.next = node
                tail = tail.next
            }
            if (node.next != null)
                minQueue.add(node.next)
        }
        return head!!
    }

    fun practice2(heads: Array<ListNode>): ListNode {
        val minQueue = PriorityQueue<ListNode> { a, b -> a.value - b.value }
        for (head in heads)
            minQueue.add(head)

        val dummy = ListNode(0)
        var current = dummy
        while (minQueue.isNotEmpty()) {
            val top = minQueue.poll()
            current.next = ListNode(top.value)
            current = current.next!!
            if (top.next != null)
                minQueue.add(top.next)
        }
        return dummy.next!!
    }

    fun practice1(heads: Array<ListNode>): ListNode {
        val minQueue = PriorityQueue<ListNode> { a, b -> a.value - b.value }
        for (head in heads)
            minQueue.add(head)
        val dummy = ListNode(0)
        var current = dummy
        while (minQueue.isNotEmpty()) {
            val node = minQueue.poll()
            current.next = ListNode(node.value)
            current = current.next!!
            if (node.next != null)
                minQueue.add(node.next)
        }
        return dummy.next!!
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val data = arrayOf(
                arrayOf(intArrayOf(2,6,8), intArrayOf(3,6,7), intArrayOf(1,3,4)),
                arrayOf(intArrayOf(5,8,9), intArrayOf(1,7)),
                arrayOf(intArrayOf(1,3,5,7,9), intArrayOf(0,2,4,6,8), intArrayOf(1,4,7), intArrayOf(0,3,6,9), intArrayOf(1,5,9), intArrayOf(0,4,8))
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