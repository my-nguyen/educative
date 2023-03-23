import practice2.ListNode

class ReverseLinkedList {
    fun reverse(head: ListNode): ListNode {
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
            val array = intArrayOf(2,4,6,8,10)
            val head = ListNode.build(array)
            print("head: ")
            head.print()

            var reversed = ReverseLinkedList().reverse(head)
            print("reversed: ")
            reversed?.print()
        }
    }
}