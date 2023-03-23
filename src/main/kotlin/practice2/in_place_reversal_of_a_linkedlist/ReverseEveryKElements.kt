import practice2.ListNode

class ReverseEveryKElements {
    fun reverse(head: ListNode, count: Int): ListNode {
        return sample(head, count)
    }

    fun sample(head: ListNode, count: Int): ListNode {
        if (count <= 1 || head == null) {
            return head
        }

        var previous: ListNode? = null
        var current: ListNode? = head
        var list = head
        while (true) {
            val preStart = previous
            var end = current
            for (i in 1..count) {
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