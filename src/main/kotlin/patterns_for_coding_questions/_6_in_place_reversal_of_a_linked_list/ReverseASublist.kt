package patterns_for_coding_questions._6_in_place_reversal_of_a_linked_list

import patterns_for_coding_questions.ListNode

class ReverseASublist {
    // from educative:
    // The problem follows the In-place Reversal of a LinkedList pattern. We can use a similar approach as discussed
    // in Reverse a LinkedList. Here are the steps we need to follow:
    // 1. Skip the first p-1 nodes, to reach the node at position p.
    // 2. Remember the node at position p-1 to be used later to connect with the reversed sub-list.
    // 3. Next, reverse the nodes from p to q using the same approach discussed in Reverse a LinkedList.
    // 4. Connect the p-1 and q+1 nodes to the reversed sub-list.
    fun reverse(head: ListNode, p: Int, q: Int): ListNode? {
        if (p == q) {
            return head
        }

        // skip the first p-1 nodes
        var current: ListNode? = head
        var previous: ListNode? = null
        for (i in 1 until p) {
            if (current == null) {
                break
            } else {
                previous = current
                current = current.next
            }
        }

        // we are interested in three parts of the LinkedList, part before index 'p', part between 'p' and 'q',
        // and the part after index 'q'

        // 'left' points to the node at index 'p-1'
        val left = previous
        // 'right' now points to the head to reverse at index 'p', but after the reverse it will point to the end
        // of the reversed list at index 'q'
        // 'current' now points to the head to reverse at index 'p', but after the reverse it will point to
        // one node beyond the reversed list at index 'q+1'
        val right = current
        println("right, pre-reverse: $right, current: $current")
        // reverse nodes between 'p' and 'q'; current now points to p-th node
        for (i in p..q) {
            if (current == null) {
                break
            } else {
                val tmp: ListNode? = current.next
                current.next = previous
                previous = current
                current = tmp
            }
        }
        // after reversing the LinkedList 'current' will become the last node of the sub-list
        println("right, post-reverse: $right, current: $current")

        var list: ListNode? = head
        // connect with the left part, before 'p'
        if (left != null) {
            // 'previous' is now the first node of the sub-list
            left.next = previous
        } else {
            // this means p == 1: we need to change the head of the LinkedList
            list = previous
        }

        // connect with the right part
        // 'right' points to the end of the reversed list at index 'q', and 'current' points to one node beyond
        // the reversed list at index 'q+1'
        right?.next = current

        return list!!
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = intArrayOf(1,2,3,4,5,6,7,8,9)
            val starts = arrayOf(3,3,1,1,4,4,5,5,/*10*/)
            val ends =   arrayOf(7,9,7,9,4,5,8,9,10)
            for (i in starts.indices) {
                val head = ListNode.build(array)
                print("original: ")
                head.print()
                var reversed = ReverseASublist().reverse(head, starts[i], ends[i])
                println("from ${starts[i]} to ${ends[i]}")
                print("reversed: ")
                reversed!!.print()
            }
        }
    }
}