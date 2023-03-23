package patterns_for_coding_questions._15_01_knapsack_dynamic_programming

class CountOfSubsetSum {
    fun countSubsets(numbers: IntArray, sum: Int): Int {
        // return bruteForce(numbers, sum)
        // return memoized(numbers, sum, 0)
        return tabulized(numbers, sum)
    }

    private fun bruteForce(numbers: IntArray, sum: Int, index: Int = 0): Int {
        // base cases
        if (sum == 0)
            return 1
        if (numbers.isEmpty() || index >= numbers.size)
            return 0

        // recursive call after selecting the number at the currentIndex
        // if the number at currentIndex exceeds the sum, we shouldn't process this
        var sum1 = 0
        if (numbers[index] <= sum)
            sum1 = bruteForce(numbers, sum - numbers[index], index + 1)
        // recursive call after excluding the number at the currentIndex
        val sum2 = bruteForce(numbers, sum, index + 1)

        return sum1 + sum2
    }

    private fun memoized(numbers: IntArray, sum: Int, index: Int, memo: Array<Array<Int?>> = Array(numbers.size) { Array(sum + 1) { null } }): Int {
        // base cases
        if (sum == 0)
            return 1
        if (numbers.isEmpty() || index >= numbers.size)
            return 0

        // check if we have not already processed a similar problem
        if (memo[index][sum] == null) {
            // recursive call after choosing the number at the currentIndex
            // if the number at currentIndex exceeds the sum, we shouldn't process this
            val sum1 = if (numbers[index] <= sum)
                memoized(numbers, sum - numbers[index], index + 1, memo)
            else
                0

            // recursive call after excluding the number at the currentIndex
            val sum2 = memoized(numbers, sum, index + 1, memo)

            memo[index][sum] = sum1 + sum2
        }
        return memo[index][sum]!!
    }

    fun tabulized(numbers: IntArray, sum: Int): Int {
        val dp = Array(numbers.size) { IntArray(sum + 1) }

        // populate the sum=0 columns, as we will always have an empty set for zero sum
        for (i in numbers.indices)
            dp[i][0] = 1

        // with only one number, we can form a subset only when the required sum is equal to its value
        for (j in 1..sum)
            dp[0][j] = if (numbers[0] == j) 1 else 0

        // process all subsets for all sums
        for (i in 1 until numbers.size) {
            for (j in 1..sum) {
                // exclude the number
                dp[i][j] = dp[i - 1][j]
                // include the number, if it does not exceed the sum
                if (j >= numbers[i])
                    dp[i][j] += dp[i - 1][j - numbers[i]]
            }
        }

        // the bottom-right corner will have our answer.
        return dp[numbers.size - 1][sum]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val numbers = arrayOf(intArrayOf(1,1,2,3), intArrayOf(1,2,7,1,5))
            val sums = arrayOf(4, 9)
            for (i in numbers.indices) {
                print("numbers: ${numbers[i].contentToString()}, sum: ${sums[i]}, ")
                val count = CountOfSubsetSum().countSubsets(numbers[i], sums[i])
                println("count subsets: $count")
            }
        }
    }
}