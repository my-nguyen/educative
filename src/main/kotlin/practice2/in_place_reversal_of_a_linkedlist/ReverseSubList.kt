package practice1.in_place_reversal_of_a_linkedlist

import practice1.ListNode

class ReverseSubList {
    fun reverse(head: ListNode, start: Int, end: Int): ListNode {
        // return practice1(head, start, end)
        // return practice2(head, start, end)
        // return practice3(head, start, end)
        // return practice4(head, start, end)
        return practice5(head, start, end)
    }

    fun practice5(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current!!.next
        }

        val left = previous
        val right = current

        for (i in start..end) {
            val tmp = current!!.next
            current.next = previous
            previous = current
            current = tmp
        }

        right!!.next = current
        if (left == null)
            return previous!!
        else {
            left.next = previous
            return head
        }
    }

    fun practice4(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current!!.next
        }

        val left = previous
        val right = current

        for (i in start..end) {
            val tmp = current!!.next
            current.next = previous
            previous = current
            current = tmp
        }

        right!!.next = current
        if (left == null)
            return previous!!
        else {
            left.next = previous
            return head
        }
    }

    fun practice3(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current!!.next
        }

        val left = previous
        val right = current

        for (i in start..end) {
            val tmp = current!!.next
            current.next = previous
            previous = current
            current = tmp
        }

        right!!.next = current
        if (left == null)
            return previous!!
        else {
            left.next = previous
            return head
        }
    }

    fun practice2(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current!!.next
        }

        val left = previous
        val right = current

        for (i in start..end) {
            val tmp = current!!.next
            current.next = previous
            previous = current
            current = tmp
        }

        right!!.next = current
        if (left == null)
            return previous!!
        else {
            left.next = previous
            return head
        }
    }

    fun practice1(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current!!.next
        }

        val before = previous
        val after = current

        for (i in start..end) {
            val tmp = current!!.next
            current.next = previous
            previous = current
            current = tmp
        }

        after!!.next = current
        return if (before == null) {
            previous!!
        } else {
            before.next = previous
            head
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = intArrayOf(1,2,3,4,5,6,7,8,9)
            val starts = arrayOf(3,3,1,1,4,4,5,5)
            val ends =   arrayOf(7,9,7,9,4,5,8,9)
            for (i in starts.indices) {
                val head = ListNode.build(array)
                print("original: ")
                head.print()
                var reversed = ReverseSubList().reverse(head, starts[i], ends[i])
                print(", from ${starts[i]} to ${ends[i]}")
                print(", reversed: ")
                reversed.print()
                println()
            }
        }
    }
}