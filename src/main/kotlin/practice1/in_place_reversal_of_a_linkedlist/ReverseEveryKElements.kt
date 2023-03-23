package practice1.in_place_reversal_of_a_linkedlist

import practice1.ListNode

class ReverseEveryKElements {
    fun reverse(head: ListNode, count: Int): ListNode {
        return head
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = intArrayOf(1,2,3,4,5,6,7,8,9)
            val counts = intArrayOf(2,3,4,5,6)
            for (count in counts) {
                val head = ListNode.build(array)
                print("original: ")
                head.print()
                var reversed = ReverseEveryKElements().reverse(head, count)
                print("count: $count, reversed: ")
                reversed?.print()
                println()
            }
        }
    }
}