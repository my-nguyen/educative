package patterns_for_coding_questions._15_01_knapsack_dynamic_programming

class TargetSum {
    fun findTargetSubsets(numbers: IntArray, s: Int): Int {
        val sum = numbers.sum()

        // if 's + totalSum' is odd, we can't find a subset with sum equal to '(s + totalSum) / 2'
        return if (sum < s || (s + sum) % 2 == 1)
            0
        else {
            // tabulized(numbers, (s+sum) / 2)
            optimized(numbers, (s+sum) / 2)
        }
    }

    // this function is exactly similar to what we have in 'Count of Subset Sum' problem.
    private fun tabulized(numbers: IntArray, sum: Int): Int {
        val table = Array(numbers.size) { IntArray(sum + 1) }

        // populate the sum=0 columns, as we will always have an empty set for zero sum
        for (i in numbers.indices)
            table[i][0] = 1

        // with only one number, we can form a subset only when the required sum is equal to the number
        for (j in 1..sum)
            table[0][j] = if (numbers[0] == j) 1 else 0

        // process all subsets for all sums
        for (i in 1 until numbers.size) {
            for (j in 1..sum) {
                table[i][j] = table[i - 1][j]
                if (j >= numbers[i])
                    table[i][j] += table[i - 1][j - numbers[i]]
            }
        }

        // the bottom-right corner will have our answer.
        return table[numbers.size - 1][sum]
    }

    // this function is exactly similar to what we have in 'Count of Subset Sum' problem.
    private fun optimized(numbers: IntArray, sum: Int): Int {
        val dp = IntArray(sum + 1)
        dp[0] = 1

        // with only one number, we can form a subset only when the required sum is equal to the number
        for (j in 1..sum)
            dp[j] = if (numbers[0] == j) 1 else 0

        // process all subsets for all sums
        for (i in 1 until numbers.size) {
            for (j in sum downTo 0) {
                if (j >= numbers[i])
                    dp[j] += dp[j - numbers[i]]
            }
        }
        return dp[sum]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val numbers = arrayOf(intArrayOf(1,1,2,3), intArrayOf(1,2,7,1))
            val sum = arrayOf(1, 9)
            for (i in numbers.indices) {
                print("numbers: ${numbers[i].contentToString()}, sum: ${sum[i]}, ")
                val count = TargetSum().findTargetSubsets(numbers[i], sum[i])
                println("count: $count")
            }
        }
    }
}