package patterns_for_coding_questions._15_01_knapsack_dynamic_programming


class Knapsack {
    fun solveKnapsack(profits: IntArray, weights: IntArray, capacity: Int): Int {
        // return bruteForce(profits, weights, capacity)
        // return memoized(profits, weights, capacity)
        return tabulized(profits, weights, capacity)
    }

    // A basic brute-force solution could be to try all combinations of the given items (as we did above), allowing us
    // to choose the one with maximum profit and a weight that doesn’t exceed ‘C’. Take the example of four items
    // (A, B, C, and D), as shown in the diagram below. To try all the combinations, our algorithm will look like:
    // for each item 'i'
    //     create a new set which INCLUDES item 'i' if the total weight does not exceed the capacity, and recursively process the remaining capacity and items
    //     create a new set WITHOUT item 'i', and recursively process the remaining items
    // return the set from the above two sets with higher profit
    // The above algorithm’s time complexity is exponential O(2^n), where ‘n’ represents the total number of items.
    // This can also be confirmed from the above recursion tree. As we can see, we will have a total of ‘31’ recursive
    // calls – calculated through (2^n) + (2^n) - 1, which is asymptotically equivalent to O(2^n).
    // The space complexity is O(n). This space will be used to store the recursion stack. Since the recursive algorithm
    // works in a depth-first fashion, which means that we can’t have more than ‘n’ recursive calls on the call stack
    // at any time.
    private fun bruteForce(profits: IntArray, weights: IntArray, capacity: Int, currentIndex: Int = 0): Int {
        // base checks
        if (capacity <= 0 || currentIndex >= profits.size)
            return 0

        // recursive call WITH the current element
        // if the weight of the current element exceeds the capacity, discard it
        val profit1 = if (weights[currentIndex] <= capacity) {
            profits[currentIndex] + bruteForce(profits, weights, capacity - weights[currentIndex], currentIndex + 1)
        } else {
            0
        }
        // recursive call WITHOUT the current element
        val profit2 = bruteForce(profits, weights, capacity, currentIndex + 1)

        return maxOf(profit1, profit2)
    }

    // Memoization is when we store the results of all the previously solved sub-problems and return the results from
    // memory if we encounter a problem that has already been solved.
    // Since we have two changing values (capacity and currentIndex) in our recursive function knapsackRecursive(),
    // we can use a two-dimensional array to store the results of all the solved sub-problems. As mentioned above,
    // we need to store results for every sub-array (i.e., for every possible index ‘i’) and every possible capacity ‘c.’
    // Since our memoization array dp[profits.length][capacity+1] stores the results for all subproblems, we can conclude
    // that we will not have more than N∗C subproblems (where ‘N’ is the number of items and ‘C’ is the knapsack capacity).
    // This means that our time complexity will be O(N∗C).
    // The above algorithm will use O(N∗C) space for the memoization array. Other than that, we will use O(N) space for
    // the recursion call-stack. So the total space complexity will be O(N∗C+N), which is asymptotically equivalent to
    // O(N∗C).
    /*fun memoization(profits: IntArray, weights: IntArray, capacity: Int): Int {
        val dp = Array(profits.size) { Array<Int?>(capacity+1) { null } }
        return memoization(dp, profits, weights, capacity, 0)
    }*/
    private fun memoized(profits: IntArray, weights: IntArray, capacity: Int, currentIndex: Int = 0, memo: Array<Array<Int?>> = Array(profits.size) { Array<Int?>(capacity+1) { null } }): Int {
        // base checks
        if (capacity <= 0 || currentIndex >= profits.size)
            return 0

        // if we have already solved a similar problem, return the result from memory
        if (memo[currentIndex][capacity] != null)
            return memo[currentIndex][capacity]!!

        // recursive call WITH the current element
        // if the weight of the current element exceeds the capacity, discard it
        val profit1 = if (weights[currentIndex] <= capacity) {
            profits[currentIndex] + memoized(profits, weights, capacity - weights[currentIndex], currentIndex + 1, memo)
        } else {
            0
        }
        // recursive call WITHOUT the current element
        val profit2 = memoized(profits, weights, capacity, currentIndex + 1, memo)

        memo[currentIndex][capacity] = maxOf(profit1, profit2)
        return memo[currentIndex][capacity]!!
    }

    // Let’s try to populate our dp[][] array from the above solution by working in a bottom-up fashion. Essentially,
    // we want to find the maximum profit for every sub-array and every possible capacity. This means that dp[i][c] will
    // represent the maximum knapsack profit for capacity ‘c’ calculated from the first ‘i’ items.
    // So, for each item at index ‘i’ (0 <= i < items.length) and capacity ‘c’ (0 <= c <= capacity), we have two options:
    // 1. Exclude the item at index ‘i.’ In this case, we will take whatever profit we get from the sub-array excluding
    //    this item => dp[i-1][c]
    // 2. Include the item at index ‘i’ if its weight is not more than the capacity. In this case, we include its profit
    //    plus whatever profit we get from the remaining capacity and from remaining items => profit[i] + dp[i-1][c-weight[i]]
    // Finally, our optimal solution will be maximum of the above two values:
    //    dp[i][c] = max (dp[i-1][c], profit[i] + dp[i-1][c-weight[i]])
    // The above solution has the time and space complexity of O(N∗C), where ‘N’ represents total items, and ‘C’ is
    // the maximum capacity.
    fun tabulized(profits: IntArray, weights: IntArray, capacity: Int): Int {
        // base checks
        if (capacity <= 0 || profits.isEmpty() || weights.size != profits.size)
            return 0

        val n = profits.size
        val dp = Array(n) { IntArray(capacity + 1) }

        // if we have only one weight, we will take it if it is not more than the capacity
        for (j in 0..capacity) {
            if (weights[0] <= j) {
                dp[0][j] = profits[0]
            }
        }

        // process all sub-arrays for all the capacities
        for (i in 1 until n) {
            for (j in 1..capacity) {
                // include the item, if it is not more than the capacity
                val profit1 = if (weights[i] <= j) {
                    profits[i] + dp[i - 1][j - weights[i]]
                } else {
                    0
                }
                // exclude the item
                val profit2 = dp[i - 1][j]

                dp[i][j] = maxOf(profit1, profit2)
            }
        }

        // maximum profit will be at the bottom-right corner.
        return dp[n - 1][capacity]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val ks = Knapsack()
            val profits = intArrayOf(1, 6, 10, 16)
            val weights = intArrayOf(1, 2, 3, 5)
            val capacities = arrayOf(7, 6)
            for (capacity in capacities) {
                print("profits: ${profits.contentToString()}, weights: ${weights.contentToString()}, capacity: $capacity, ")
                val maxProfit = ks.solveKnapsack(profits, weights, capacity)
                println("knapsack profit: $maxProfit")
            }
        }
    }
}