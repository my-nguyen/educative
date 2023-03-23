package grokking_the_coding_interview__3_fast_slow_pointers

import patterns_for_coding_questions.ListNode

class StartOfLinkedListCycle {
    fun findCycleStart(head: ListNode): ListNode {
        // return mine(head)
        return educative(head)
    }

    private fun educative(head: ListNode): ListNode {
        // calculate the length of the cycle, whose existence is guaranteed.
        val length = cycleLength(head)

        // advance 'fast' one full cycle length ahead of 'slow'
        var fast = head
        for (i in 1..length) {
            fast = fast.next!!
        }
        var slow = head

        // advance both 'fast' and 'slow' at the same pace. when they meet again, that's the head of the cycle.
        while (slow != fast) {
            slow = slow.next!!
            fast = fast.next!!
        }
        return slow
    }

    private fun cycleLength(head: ListNode): Int {
        var slow = head
        var fast = head
        // 'fast' goes twice as fast as 'slow'
        while (fast != null && fast!!.next != null) {
            fast = fast.next!!.next!!
            slow = slow.next!!
            // if there's a cycle, eventually slow will catch up with fast
            if (fast == slow) {
                return getLength(fast)
            }
        }
        return 0
    }

    private fun getLength(head: ListNode): Int {
        var current = head
        var length = 0
        // keep advancing current until it cycles back to head again, keeping count along the way.
        do {
            current = current.next!!
            length++
        } while (current != head)
        println("length: $length")
        return length
    }

    private fun mine(head: ListNode): ListNode {
        var current = head
        val set = mutableSetOf<ListNode>()
        while (!set.contains(current)) {
            set.add(current)
            current = current.next!!
        }
        return current
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

            head.next!!.next!!.next!!.next!!.next!!.next = head!!.next!!.next

            /*val tmp = ListNode(1)
            tmp.next = ListNode(2)
            tmp.next!!.next = tmp
            val start = tmp*/
            val start = StartOfLinkedListCycle().findCycleStart(head)
            println("cycle start: ${start.value}")
        }
    }
}