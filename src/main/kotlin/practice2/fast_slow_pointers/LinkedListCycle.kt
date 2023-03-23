package practice2.fast_slow_pointers

import practice2.ListNode

class LinkedListCycle {
    fun hasCycle(head: ListNode): Boolean {
        var slow = head
        var fast: ListNode? = head
        while (fast?.next != null) {
            fast = fast.next!!.next
            slow = slow.next!!
            if (fast == slow)
                return true
        }
        return false
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
            println("LinkedList has cycle: " + LinkedListCycle().hasCycle(head))
            head.next!!.next!!.next!!.next!!.next!!.next = head.next!!.next
            println("LinkedList has cycle: " + LinkedListCycle().hasCycle(head))
            head.next!!.next!!.next!!.next!!.next!!.next = head.next!!.next!!.next
            println("LinkedList has cycle: " + LinkedListCycle().hasCycle(head))
        }
    }
}