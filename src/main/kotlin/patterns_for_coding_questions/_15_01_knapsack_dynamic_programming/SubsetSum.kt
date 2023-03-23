package patterns_for_coding_questions._15_01_knapsack_dynamic_programming

class SubsetSum {
    fun canPartition(numbers: IntArray, sum: Int): Boolean {
        return tabulized(numbers, sum)
    }

    fun tabulized(numbers: IntArray, sum: Int): Boolean {
        val table = Array(numbers.size) { BooleanArray(sum + 1) }

        // populate the sum=0 columns, as we can always form '0' sum with an empty set
        for (i in numbers.indices)
            table[i][0] = true

        // with only one number, we can form a subset only when the required sum is equal to its value
        for (j in 1..sum) {
            table[0][j] = numbers[0] == j
        }

        // process all subsets for all sums
        for (i in 1 until numbers.size) {
            for (j in 1..sum) {
                if (table[i - 1][j]) {
                    // if we can get the sum 's' without the number at index 'i'
                    table[i][j] = table[i - 1][j]
                } else if (j >= numbers[i]) {
                    // else include the number and see if we can find a subset to get the remaining sum
                    table[i][j] = table[i - 1][j - numbers[i]]
                }
            }
        }

        // the bottom-right corner will have our answer.
        return table[numbers.size - 1][sum]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val numbers = arrayOf(intArrayOf(1,2,3,7), intArrayOf(1,2,7,1,5), intArrayOf(1,3,4,8))
            val sums = arrayOf(6, 10, 6)
            for (i in numbers.indices) {
                print("numbers: ${numbers[i].contentToString()}, sums: ${sums[i]}, ")
                val canPartition = SubsetSum().canPartition(numbers[i], sums[i])
                println("can partition? $canPartition")
            }
        }
    }
}