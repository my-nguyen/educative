package patterns_for_coding_questions._15_01_knapsack_dynamic_programming

import kotlin.math.abs

class MinimumSubsetSumDifference {
    fun canPartition(numbers: IntArray): Int {
        // return bruteForce(numbers)
        val sum = numbers.sum()
        // return memoized(numbers, Array(numbers.size) { Array<Int?>(sum + 1) { null } })
        return tabulized(numbers, sum)
    }

    private fun bruteForce(numbers: IntArray, index: Int = 0, sum1: Int = 0, sum2: Int = 0): Int {
        // base case
        if (index == numbers.size)
            return abs(sum1 - sum2)

        // recursive call WITH the current element in the first set
        val diff1 = bruteForce(numbers, index + 1, sum1 + numbers[index], sum2)
        // recursive call WITH the current element in the second set
        val diff2 = bruteForce(numbers, index + 1, sum1, sum2 + numbers[index])

        return minOf(diff1, diff2)
    }

    private fun memoized(numbers: IntArray, memo: Array<Array<Int?>>, index: Int = 0, sum1: Int = 0, sum2: Int = 0): Int {
        // base check
        if (index == numbers.size)
            return abs(sum1 - sum2)

        // check if we have not already processed similar problem
        if (memo[index][sum1] == null) {
            // recursive call after including the number at the currentIndex in the first set
            val diff1 = memoized(numbers, memo, index + 1, sum1 + numbers[index], sum2)
            // recursive call after including the number at the currentIndex in the second set
            val diff2 = memoized(numbers, memo, index + 1, sum1, sum2 + numbers[index])

            memo[index][sum1] = Math.min(diff1, diff2)
        }
        return memo[index][sum1]!!
    }

    fun tabulized(numbers: IntArray, sum: Int): Int {
        val table = Array(numbers.size) { BooleanArray(sum/2 + 1) }

        // populate the sum=0 columns, as we can always form '0' sum with an empty set
        for (i in numbers.indices)
            table[i][0] = true

        // with only one number, we can form a subset only when the required sum is equal to that number
        for (j in 1..sum / 2)
            table[0][j] = numbers[0] == j

        // process all subsets for all sums
        for (i in 1 until numbers.size) {
            for (j in 1..sum / 2) {
                if (table[i - 1][j]) {
                    // if we can get the sum 's' without the number at index 'i'
                    table[i][j] = table[i - 1][j]
                } else if (j >= numbers[i]) {
                    // else include the number and see if we can find a subset to get the remaining sum
                    table[i][j] = table[i - 1][j - numbers[i]]
                }
            }
        }
        var sum1 = 0
        // Find the largest index in the last row which is true
        for (i in sum / 2 downTo 0) {
            if (table[table.lastIndex][i]) {
                sum1 = i
                break
            }
        }
        val sum2 = sum - sum1

        return abs(sum2 - sum1)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,2,3,9), intArrayOf(1,2,7,1,5), intArrayOf(1,3,100,4))
            for (array in arrays) {
                print("array: ${array.contentToString()}, ")
                val minDiff = MinimumSubsetSumDifference().canPartition(array)
                println("minimum subset sum difference: $minDiff")
            }
        }
    }
}