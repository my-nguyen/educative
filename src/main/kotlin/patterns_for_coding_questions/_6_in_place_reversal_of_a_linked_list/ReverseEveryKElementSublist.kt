package patterns_for_coding_questions._6_in_place_reversal_of_a_linked_list

import patterns_for_coding_questions.ListNode

class ReverseEveryKElementSublist {
    fun reverse(head: ListNode, k: Int): ListNode? {
        return mine(head, k)
        // return educative(head, k)
    }

    // from educative
    // The problem follows the In-place Reversal of a LinkedList pattern and is quite similar to Reverse a Sub-list.
    // The only difference is that we have to reverse all the sub-lists. We can use the same approach, starting with
    // the first sub-list (i.e. p=1, q=k) and keep reversing all the sublists of size ‘k’.
    private fun educative(head: ListNode, k: Int): ListNode? {
        if (k <= 1 || head == null) {
            return head
        }

        var previous: ListNode? = null
        var current: ListNode? = head
        var list = head
        while (true) {
            val preStart = previous
            var end = current
            for (i in 1..k) {
                if (current == null) {
                    break
                } else {
                    val tmp: ListNode? = current.next
                    current.next = previous
                    previous = current
                    current = tmp
                }
            }

            if (preStart != null) {
                preStart.next = previous
            } else {
                list = previous!!
            }

            end!!.next = current

            if (current == null) {
                break
            } else {
                previous = end
            }
        }
        return list
    }

    // copied from educative mostly
    private fun mine(head: ListNode, k: Int): ListNode? {
        var previous: ListNode? = null
        var current: ListNode? = head
        var list: ListNode? = head
        while (current != null) {
            val preStart = previous
            val atEnd = current

            for (i in 1..k) {
                if (current == null) {
                    break
                } else {
                    val tmp = current.next
                    current.next = previous
                    previous = current
                    current = tmp
                }
            }

            atEnd.next = current
            if (preStart == null) {
                list = previous
            } else {
                preStart.next = previous
            }

            previous = atEnd
        }
        return list
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = intArrayOf(1,2,3,4,5,6,7,8)
            val head = ListNode.build(array)
            print("original: ")
            head.print()
            var reversed = ReverseEveryKElementSublist().reverse(head, 3)
            print("reversed: ")
            reversed?.print()
        }
    }
}