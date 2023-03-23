package patterns_for_coding_questions._15_01_knapsack_dynamic_programming

class EqualSubsetSumPartition {
    fun canPartition(numbers: IntArray): Boolean {
        val sum = numbers.sum()

        return if (sum % 2 != 0)
            // if 'sum' is a an odd number, then it's impossible to have two subsets with equal sum
            false
        else {
            // return bruteForce(numbers, sum/2)
            // return memoized(numbers, sum/2)
            return tabulized(numbers, sum/2)
        }
    }

    private fun bruteForce(num: IntArray, sum: Int, index: Int = 0): Boolean {
        return when {
            // base case
            sum == 0 -> true
            // base case
            num.isEmpty() || index >= num.size -> false
            // if the current element exceeds the capacity, skip it, otherwise make a recursive call WITH the current element
            num[index] <= sum && bruteForce(num, sum - num[index], index + 1) -> true
            // recursive call WITHOUT the current element
            else -> bruteForce(num, sum, index + 1)
        }
    }

    private fun memoized(numbers: IntArray, sum: Int, index: Int = 0, memo: Array<Array<Boolean?>> = Array(numbers.size) { Array(sum + 1) { null } }): Boolean {
        // base cases
        if (sum == 0)
            return true
        if (numbers.isEmpty() || index >= numbers.size)
            return false

        // if we have already solved a similar problem
        if (memo[index][sum] != null)
            return memo[index][sum]!!

        // recursive call WITH the current element
        // if the current element exceeds the sum, discard it
        if (numbers[index] <= sum) {
            if (memoized(numbers, sum - numbers[index], index + 1, memo)) {
                memo[index][sum] = true
                return true
            }
        }

        // recursive call WITHOUT the current element
        memo[index][sum] = memoized(numbers, sum, index + 1, memo)
        return memo[index][sum]!!
    }

    fun tabulized(num: IntArray, sum: Int): Boolean {
        val n = num.size
        val table = Array(n) { BooleanArray(sum + 1) }

        // populate the sum=0 columns, as we can always for '0' sum with an empty set
        for (i in num.indices)
            table[i][0] = true

        // with only one number, we can form a subset only when the required sum is equal to its value
        for (j in 1..sum) {
            table[0][j] = num[0] == j
        }

        // process all subsets for all sums
        for (i in 1 until n) {
            for (j in 1..sum) {
                if (table[i - 1][j]) {
                    // if we can get the sum 's' without the number at index 'i'
                    table[i][j] = table[i - 1][j]
                } else if (j >= num[i]) {
                    // else if we can find a subset to get the remaining sum
                    table[i][j] = table[i - 1][j - num[i]]
                }
            }
        }

        // the bottom-right corner will have our answer.
        return table[n - 1][sum]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,2,3,4), intArrayOf(1,1,3,4,7), intArrayOf(2,3,4,6))
            for (array in arrays) {
                print("array: ${array.contentToString()}, ")
                val canPartition = EqualSubsetSumPartition().canPartition(array)
                println("can partition? $canPartition")
            }
        }
    }
}