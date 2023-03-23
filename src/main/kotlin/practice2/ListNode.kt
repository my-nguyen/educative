package practice2

data class ListNode(var value: Int = 0, var next: ListNode? = null) {
    override fun toString() = value.toString()

    fun print() {
        var current: ListNode? = this
        while (current != null) {
            print("$current, ")
            current = current.next
        }
        // println()
    }

    companion object {
        fun build(array: IntArray): ListNode {
            val dummy = ListNode(0)
            var current = dummy
            for (number in array) {
                current.next = ListNode(number)
                current = current.next!!
            }
            return dummy.next!!
        }
    }
}
