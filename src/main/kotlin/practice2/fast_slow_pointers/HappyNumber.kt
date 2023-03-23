package practice2.fast_slow_pointers

class HappyNumber {
    fun find(number: Int): Boolean {
        var fast = number
        var slow = number
        do {
            fast = next(next(fast))
            slow = next(slow)
            // println("fast: $fast, slow: $slow")
        } while (fast != slow)
        return slow == 1
    }

    private fun next(number: Int): Int {
        var copy = number
        var result = 0
        while (copy != 0) {
            val digit = copy % 10
            result += digit * digit
            copy /= 10
            // println("digit: $digit, result: $result, copy: $copy")
        }
        // println("number: $number, result: $result")
        return result
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