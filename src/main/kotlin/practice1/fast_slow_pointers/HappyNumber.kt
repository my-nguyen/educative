package practice1.fast_slow_pointers

class HappyNumber {
    fun find(number: Int): Boolean {
        var fast = number
        var slow = number
        do {
            fast = next(next(fast))
            slow = next(slow)
        } while (slow != fast)
        return slow == 1
    }

    fun next(number: Int): Int {
        var copy = number
        var sum = 0
        while (copy > 0) {
            val remainder = copy % 10
            sum += remainder * remainder
            copy /= 10
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