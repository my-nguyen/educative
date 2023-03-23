package patterns_for_coding_questions._3_fast_slow_pointers

import patterns_for_coding_questions.ListNode

class RearrangeALinkedList {
    // from educative
    // This problem shares similarities with Palindrome LinkedList. To rearrange the given LinkedList we will follow
    // the following steps:
    // 1. We can use the Fast & Slow pointers method similar to Middle of the LinkedList to find the middle node of
    //    the LinkedList.
    // 2. Once we have the middle of the LinkedList, we will reverse the second half of the LinkedList.
    // 3. Finally, we’ll iterate through the first half and the reversed second half to produce a LinkedList in
    // the required order
    fun reorder(head: ListNode?) {
        if (head == null || head.next == null) {
            return
        }

        // find the middle of the LinkedList
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
        }

        // slow is now pointing to the middle node
        var headSecondHalf = reverse(slow) // reverse the second half
        var headFirstHalf = head

        // rearrange to produce the LinkedList in the required order
        while (headFirstHalf != null && headSecondHalf != null) {
            var temp = headFirstHalf.next
            headFirstHalf.next = headSecondHalf
            headFirstHalf = temp
            temp = headSecondHalf.next
            headSecondHalf.next = headFirstHalf
            headSecondHalf = temp
        }

        // set the next of the last node to 'null'
        if (headFirstHalf != null) {
            headFirstHalf.next = null
        }
    }

    private fun reverse(head: ListNode?): ListNode? {
        var head = head
        var prev: ListNode? = null
        while (head != null) {
            val next = head.next
            head.next = prev
            prev = head
            head = next
        }
        return prev
    }

}

fun main(args: Array<String>) {
    val arrays = arrayOf(intArrayOf(2,4,6,8,10,12), intArrayOf(2,4,6,8,10))
    for (array in arrays) {
        val head = ListNode.build(array)
        RearrangeALinkedList().reorder(head)
        head.print()
    }
}
