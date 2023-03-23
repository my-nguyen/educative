package patterns_for_coding_questions._6_in_place_reversal_of_a_linked_list

import patterns_for_coding_questions.ListNode

class ReverseAlternatingKElementSublist {
    fun reverse(head: ListNode, k: Int): ListNode {
        // return mine(head, k)
        return educative(head, k)
    }

    // from educative
    // The problem follows the In-place Reversal of a LinkedList pattern and is quite similar to Reverse every K-element
    // Sub-list. The only difference is that we have to skip ‘k’ alternating elements. We can follow a similar approach,
    // and in each iteration after reversing ‘k’ elements, we will skip the next ‘k’ elements.
    private fun educative(head: ListNode, k: Int): ListNode {
        if (k <= 1 || head == null) {
            return head
        }

        var previous: ListNode? = null
        var current: ListNode? = head
        var list = head
        while (current != null) {
            val preP = previous
            var atQ = current
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

            if (preP != null) {
                preP.next = previous
            } else {
                list = previous!!
            }

            atQ!!.next = current

            for (i in 1..k) {
                if (current == null) {
                    break
                } else {
                    previous = current
                    current = current.next
                }
            }
        }
        return list
    }

    // copied from educative and written from memory
    private fun mine(head: ListNode, k: Int): ListNode? {
        var previous: ListNode? = null
        var current: ListNode? = head
        var result: ListNode? = head
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
                result = previous
            } else {
                preStart.next = previous
            }
            previous = atEnd

            for (i in 1..k) {
                if (current == null) {
                    break
                } else {
                    previous = current
                    current = current.next
                }
            }
        }
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = intArrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)
            val lengths = arrayOf(1,2,3,4,5)
            for (length in lengths) {
                val head = ListNode.build(array)
                print("original: ")
                head.print()
                val reversed = ReverseAlternatingKElementSublist().reverse(head, length)
                print("reversed: ")
                reversed?.print()
            }
        }
    }
}