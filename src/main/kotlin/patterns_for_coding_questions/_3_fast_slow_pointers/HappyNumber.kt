package patterns_for_coding_questions._3_fast_slow_pointers

class HappyNumber {
    fun find(number: Int): Boolean {
        // return mine(number)
        return educative(number)
    }

    // The process, defined above, to find out if a number is a happy number or not, always ends in a cycle. If the number
    // is a happy number, the process will be stuck in a cycle on number ‘1,’ and if the number is not a happy number
    // then the process will be stuck in a cycle with a set of numbers. As we saw in Example-2 while determining if '12'
    // is a happy number or not, our process will get stuck in a cycle with the following numbers:
    // 89 -> 145 -> 42 -> 20 -> 4 -> 16 -> 37 -> 58 -> 89
    // We saw in the LinkedList Cycle problem that we can use the Fast & Slow pointers method to find a cycle among
    // a set of elements. As we have described above, each number will definitely have a cycle. Therefore, we will use
    // the same fast & slow pointer strategy to find the cycle and once the cycle is found, we will see if the cycle is
    // stuck on number ‘1’ to find out if the number is happy or not.
    fun educative(number: Int): Boolean {
        var slow = number
        var fast = number
        do {
            fast = next(next(fast))
            slow = next(slow)
        } while (fast != slow)
        return fast == 1
    }

    fun mine(number: Int): Boolean {
        val set = mutableSetOf<Int>()
        var current = number
        while (current != 1 && !set.contains(current)) {
            set.add(current)
            current = next(current)
        }
        return current == 1
    }

    private fun next(number: Int): Int {
        var sum = 0
        var current = number
        while (current != 0) {
            val remain = current % 10
            sum += remain * remain
            current /= 10
        }
        return sum
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val numbers = arrayOf(23, 12)
            for (number in numbers) {
                val isHappy = HappyNumber().find(number)
                println("number: $number, is happy? $isHappy")
            }
        }
    }
}