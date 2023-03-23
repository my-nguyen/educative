package patterns_for_coding_questions

data class ListNode(val value: Int, var next: ListNode? = null) {
    override fun toString() = "$value"

    fun print() {
        var current: ListNode? = this
        while (current != null) {
            print("${current.value} ")
            current = current.next
        }
        println()
    }

    companion object {
        fun build(array: IntArray): ListNode {
            val dummy = ListNode(-1)
            var current = dummy
            for (number in array) {
                current.next = ListNode(number)
                current = current.next!!
            }
            return dummy.next!!
        }
    }
}
