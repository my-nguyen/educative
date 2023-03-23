package patterns_for_coding_questions._3_fast_slow_pointers

import patterns_for_coding_questions.ListNode

class PalindromeLinkedList {
    fun isPalindrome(head: ListNode): Boolean {
        val half = findHalf(head)
        val reversed = reverse(half)
        return isEqual(head, reversed)
    }

    private fun findHalf(head: ListNode): ListNode {
        var slow = head
        var fast: ListNode? = head
        while (fast != null && fast.next != null) {
            fast = fast.next!!.next
            slow = slow.next!!
        }
        return slow
    }

    private fun reverse(head: ListNode): ListNode {
        var previous: ListNode? = null
        var current: ListNode? = head
        while (current != null) {
            var tmp = current.next
            current.next = previous
            previous = current
            current = tmp
        }
        return previous!!
    }

    private fun isEqual(h1: ListNode, h2: ListNode): Boolean {
        var n1: ListNode? = h1
        var n2: ListNode? = h2
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                return false
            } else {
                n1 = n1.next
                n2 = n2.next
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(2,4,6,4,2), intArrayOf(2,4,6,4,2,2))
            for (array in arrays) {
                val head = ListNode.build(array)
                val isPalindrome = PalindromeLinkedList().isPalindrome(head)
                println("list: ${array.contentToString()}, is palindrome? $isPalindrome")
            }
        }
    }
}