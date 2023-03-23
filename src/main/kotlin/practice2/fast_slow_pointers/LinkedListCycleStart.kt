package practice2.fast_slow_pointers

import practice1.ListNode

class LinkedListCycleStart {
    fun findCycleStart(head: ListNode): ListNode {
        val cycle = findCycle(head)
        val length = getLength(cycle)
        return findStart(head, length)
    }

    fun findCycle(head: ListNode): ListNode {
        var slow = head
        var fast = head
        do {
            fast = fast.next!!.next!!
            slow = slow.next!!
        } while (fast != slow)
        return fast
    }

    fun getLength(node: ListNode): Int {
        var current = node
        var count = 0
        do {
            current = current.next!!
            count++
        } while (current != node)
        return count
    }

    fun findStart(head: ListNode, length: Int): ListNode {
        var fast = head
        for (i in 0 until length)
            fast = fast.next!!
        var slow = head
        while (slow != fast) {
            slow = slow.next!!
            fast = fast.next!!
        }
        return slow
    }
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val head = ListNode(1)
            head.next = ListNode(2)
            head.next!!.next = ListNode(3)
            head.next!!.next!!.next = ListNode(4)
            head.next!!.next!!.next!!.next = ListNode(5)
            head.next!!.next!!.next!!.next!!.next = ListNode(6)
            head.next!!.next!!.next!!.next!!.next!!.next = head!!.next!!.next

            val start = LinkedListCycleStart().findCycleStart(head)
            println("cycle start: ${start.value}")
        }
    }
}