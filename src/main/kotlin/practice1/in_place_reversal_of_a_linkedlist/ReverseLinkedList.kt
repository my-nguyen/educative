package practice1.in_place_reversal_of_a_linkedlist

import practice1.ListNode

class ReverseLinkedList {
    fun reverse(head: ListNode): ListNode {
        // return practice1(head)
        return practice2(head)
    }

    private fun practice2(head: ListNode): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        while (current != null) {
            val tmp = current.next
            current.next = previous
            previous = current
            current = tmp
        }
        return previous!!
    }

    private fun practice1(head: ListNode): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        while (current != null) {
            val tmp = current.next
            current.next = previous
            previous = current
            current = tmp
        }
        return previous!!
    }
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,2,3,4,5,6,7,8,9), intArrayOf(1,2,3,4,5,6,7,8),
                intArrayOf(2,4,6,8,10)
            )
            for (array in arrays) {
                val head = ListNode.build(array)
                print("head: ")
                head.print()
                var reversed = ReverseLinkedList().reverse(head)
                print("reversed: ")
                reversed.print()
                println()
            }
        }
    }
}