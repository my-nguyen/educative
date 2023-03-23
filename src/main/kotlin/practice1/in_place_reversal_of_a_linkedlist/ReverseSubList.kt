import practice2.ListNode

class ReverseSubList {
    fun reverse(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current?.next!!
        }

        val left = previous
        val right = current
        for (i in start..end) {
            val tmp = current?.next
            current?.next = previous
            previous = current
            current = tmp
        }

        right?.next = current
        val reversed = if (left == null)
            previous
        else {
            left.next = previous
            head
        }
        return reversed!!
    }

    fun reverse6(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current?.next!!
        }

        val preStart = previous
        val atEnd = current
        for (i in start..end) {
            val tmp = current?.next
            current?.next = previous
            previous = current
            current = tmp
        }

        atEnd?.next = current
        val reversed = if (preStart == null)
            previous
        else {
            preStart.next = previous
            head
        }
        return reversed!!
    }

    fun reverse5(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current?.next
        }

        val left = previous
        val right = current

        for (i in start..end) {
            val tmp: ListNode? = current?.next
            current?.next = previous
            previous = current
            current = tmp
        }

        right?.next = current
        val reversed = if (left == null)
            previous
        else {
            left.next = previous
            head
        }
        return reversed!!
    }

    fun reverse4(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current?.next
        }

        val left = previous
        val right = current

        for (i in start..end) {
            val tmp: ListNode? = current?.next
            current?.next = previous
            previous = current
            current = tmp
        }

        right?.next = current
        val reversed = if (left == null)
            previous
        else {
            left.next = previous
            head
        }

        return reversed!!
    }
    fun reverse3(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current!!.next
        }

        val left = previous
        val right = current

        for (i in start..end) {
            val tmp: ListNode? = current!!.next
            current.next = previous
            previous = current
            current = tmp
        }

        right!!.next = current
        val reversed = if (left == null)
            previous
        else {
            left.next = previous
            head
        }

        return reversed!!
    }

    fun reverse2(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current!!.next!!
        }

        val left = previous
        val right = current

        for (i in start..end) {
            val tmp: ListNode? = current!!.next
            current.next = previous
            previous = current
            current = tmp
        }

        right!!.next = current
        val reversed = if (left == null)
            previous
        else {
            left.next = previous
            head
        }

        return reversed!!
    }

    fun reverse1(head: ListNode, start: Int, end: Int): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        for (i in 1 until start) {
            previous = current
            current = current!!.next
        }

        val left = previous
        val right = current

        for (i in start..end) {
            val tmp: ListNode? = current!!.next
            current.next = previous
            previous = current
            current = tmp
        }

        right!!.next = current
        val reversed = if (left == null)
            previous
        else {
            left.next = previous
            head
        }
        return reversed!!
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