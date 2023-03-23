package practice1.fast_slow_pointers

import practice1.ListNode

class MiddleOfLinkedList {
    fun findMiddle(head: ListNode): ListNode {
        var fast : ListNode? = head
        var slow = head
        do {
            fast = fast!!.next!!.next
            slow = slow.next!!
        } while (fast != null && fast!!.next != null)
        return slow
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,2,3,4,5), intArrayOf(1,2,3,4,5,6), intArrayOf(1,2,3,4,5,6,7),
            )
            for (array in arrays) {
                val head = ListNode.build(array)
                print("list: ")
                head.print()
                val middle = MiddleOfLinkedList().findMiddle(head)
                println("middle: $middle")
            }
        }
    }
}