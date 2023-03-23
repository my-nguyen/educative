package patterns_for_coding_questions._6_in_place_reversal_of_a_linked_list

import patterns_for_coding_questions.ListNode

class ReverseALinkedList {
    fun reverse(head: ListNode): ListNode? {
        var current: ListNode? = head
        var previous: ListNode? = null
        while (current != null) {
            val tmp: ListNode? = current.next
            current.next = previous
            previous = current
            current = tmp
        }
        return previous!!
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = intArrayOf(2,4,6,8,10)
            val head = ListNode.build(array)
            print("head: ")
            head.print()

            var reversed = ReverseALinkedList().reverse(head)
            print("reversed: ")
            reversed?.print()
        }
    }
}