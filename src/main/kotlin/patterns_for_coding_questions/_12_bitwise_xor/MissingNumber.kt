package patterns_for_coding_questions._12_bitwise_xor

class MissingNumber {
    fun findMissingNumber(arr: IntArray): Int {
        return straightForwward(arr)
    }

    // What could go wrong with this algorithm?
    // While finding the sum of numbers from 1 to n, we can get integer overflow when n is large.
    private fun straightForwward(arr: IntArray): Int {
        val n = arr.size + 1
        // find sum of all numbers from 1 to n.
        var s1 = 0
        for (i in 1..n) s1 += i

        // subtract all numbers in input from sum.
        for (num in arr) s1 -= num

        // s1, now, is the missing number
        return s1
    }

    // How can we avoid this? Can XOR help us here?
    // Remember the important property of XOR that it returns 0 if both the bits in comparison are the same.
    // In other words, XOR of a number with itself will always result in 0. This means that if we XOR all the numbers
    // in the input array with all numbers from the range 1 to n then each number in the input is going to get zeroed out
    // except the missing number. Following are the set of steps to find the missing number using XOR:
    // 1. XOR all the numbers from 1 to n, let’s call it x1.
    // 2. XOR all the numbers in the input array, let’s call it x2.
    // 3. The missing number can be found by x1 XOR x2.
    private fun educative(arr: IntArray): Int {
        val n: Int = arr.lastIndex
        // find sum of all numbers from 1 to n.
        var x1 = 1
        for (i in 2..n) {
            x1 = x1 xor i
        }

        // x2 represents XOR of all values in arr
        var x2 = arr[0]
        for (i in 1 until n - 1) {
            x2 = x2 xor arr[i]
        }

        // missing number is the xor of x1 and x2
        return x1 xor x2
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arr = intArrayOf(1, 5, 2, 6, 4)
            print("Missing number is: " + MissingNumber().findMissingNumber(arr))
        }
    }
}