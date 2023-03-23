package patterns_for_coding_questions._6_in_place_reversal_of_a_linked_list

import patterns_for_coding_questions.ListNode

class RotateALinkedList {
    // from educative
    // Another way of defining the rotation is to take the sub-list of ‘k’ ending nodes of the LinkedList and connect them
    // to the beginning. Other than that we have to do three more things:
    // 1. Connect the last node of the LinkedList to the head, because the list will have a different tail after the rotation.
    // 2. The new head of the LinkedList will be the node at the beginning of the sublist.
    // 3. The node right before the start of sub-list will be the new tail of the rotated LinkedList.
    fun rotate(head: ListNode, rotations: Int): ListNode {
        if (head == null || head.next == null || rotations <= 0) {
            return head
        }

        var last = head
        var length = 1
        // count the total number of nodes
        while (last.next != null) {
            last = last.next!!
            length++
        }

        // connect the last node with the head to make it a circular list
        last.next = head
        val modular = rotations % length
        val skip = length - modular
        var lastRotated = head
        for (i in 0 until skip-1) {
            lastRotated = lastRotated.next!!
        }

        // 'lastRotated.next' is pointing to the sub-list of 'k' ending nodes
        val list = lastRotated.next
        lastRotated.next = null
        return list!!
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = intArrayOf(1,2,3,4,5,6,7,8,9)
            val rotations = arrayOf(1,2,3,4,5,6,7,8,9,10)
            for (rotation in rotations) {
                val head = ListNode.build(array)
                print("original: ")
                head.print()
                val rotated = RotateALinkedList().rotate(head, rotation)
                print("rotate $rotation: ")
                rotated.print()
            }
        }
    }
}