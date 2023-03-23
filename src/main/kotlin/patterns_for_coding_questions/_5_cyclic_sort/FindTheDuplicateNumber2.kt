package patterns_for_coding_questions._5_cyclic_sort

class FindTheDuplicateNumber2 {
    // from educative.
    // While doing the cyclic sort, we realized that the array will have a cycle due to the duplicate number and that
    // the start of the cycle will always point to the duplicate number. This means that we can use the fast & the slow
    // pointer method to find the duplicate number or the start of the cycle similar to Start of LinkedList Cycle.
    // i don't understand why the duplicate number is at the start of the cycle.
    fun findDuplicate(array: IntArray): Int {
        // find the cycle
        var slow = 0
        var fast = 0
        do {
            slow = array[slow]
            fast = array[array[fast]]
        } while (slow != fast)

        // find the cycle length
        var current = array[slow]
        var length = 0
        do {
            current = array[current]
            length++
        } while (current != array[slow])

        // find the start of the cycle by advancing pointer2 'length' nodes ahead of pointer1 before advancing both
        // at the same pace. when they meet it's the start of the cycle.
        var pointer1 = array[0]
        var pointer2 = array[0]
        while (length > 0) {
            pointer2 = array[pointer2]
            length--
        }
        while (pointer1 != pointer2) {
            pointer1 = array[pointer1]
            pointer2 = array[pointer2]
        }

        // the duplicate number is at the start of the cycle
        return pointer1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,4,4,3,2), intArrayOf(2,1,3,3,5,4), intArrayOf(2,4,1,4,4))
            for (array in arrays) {
                print("array: ${array.contentToString()}")
                val duplicate = FindTheDuplicateNumber2().findDuplicate(array)
                println(", sorted: ${array.contentToString()}, duplicate: $duplicate")
            }
        }
    }
}