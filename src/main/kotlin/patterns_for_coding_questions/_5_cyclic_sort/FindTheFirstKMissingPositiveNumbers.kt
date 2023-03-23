package patterns_for_coding_questions._5_cyclic_sort

class FindTheFirstKMissingPositiveNumbers {
    fun findNumbers(nums: IntArray, count: Int): List<Int> {
        // return mine(nums, count)
        return educative(nums, count)
    }

    // from educative
    // This problem follows the Cyclic Sort pattern and shares similarities with Find the Smallest Missing Positive Number.
    // The only difference is that, in this problem, we need to find the first ‘k’ missing numbers compared to only
    // the first missing number.
    // We will follow a similar approach as discussed in Find the Smallest Missing Positive Number to place the numbers
    // on their correct indices and ignore all numbers that are out of the range of the array. Once we are done with
    // the cyclic sort we will iterate through the array to find indices that do not have the correct numbers.
    // If we are not able to find ‘k’ missing numbers from the array, we need to add additional numbers to the output array.
    // To find these additional numbers we will use the length of the array. For example, if the length of the array is 4,
    // the next missing numbers will be 4, 5, 6 and so on. One tricky aspect is that any of these additional numbers
    // could be part of the array. Remember, while sorting, we ignored all numbers that are greater than or equal to the length of the array. So all indices that have the missing numbers could possibly have these additional numbers. To handle this, we must keep track of all numbers from those indices that have missing numbers. Let’s understand this with an example:
    //    nums: [2, 1, 3, 6, 5], k =2
    // After the cyclic sort our array will look like:
    //    nums: [1, 2, 3, 6, 5]
    // From the sorted array we can see that the first missing number is ‘4’ (as we have ‘6’ on the fourth index) but
    // to find the second missing number we need to remember that the array does contain ‘6’. Hence, the next missing
    // number is ‘7’
    private fun educative(array: IntArray, total: Int): List<Int> {
        for (i in array.indices) {
            // ignore all numbers that are out of the range of the array
            while (array[i] > 0 && array[i] <= array.size && array[i] != array[array[i] - 1]) {
                // place the numbers on their correct indices
                swap(array, i, array[i]-1)
            }
        }

        val missing = mutableListOf<Int>()
        val extra = mutableSetOf<Int>()
        // iterate through the array
        for (i in array.indices) {
            // find indices that do not have the correct numbers
            if (array[i]-1 != i) {
                // save any positive number that's missing in sorted array
                missing.add(i+1)
                // extra contains negative numbers and numbers that's been correctly placed
                extra.add(array[i])
                if (missing.size == total) {
                    break
                }
            }
        }

        var i = 1
        // fill up to 'total' missing numbers
        while (missing.size < total) {
            // start at 1 more than array size
            val candidate = i + array.size
            // ignore if the array contains the candidate number
            if (!extra.contains(candidate)) {
                missing.add(candidate)
            }
            i++
        }
        return missing
    }

    // incorrect solution
    private fun mine(array: IntArray, total: Int): List<Int> {
        for (i in array.indices) {
            while (array[i] > 0 && array[i] <= array.size && array[array[i]-1] != array[i]) {
                swap(array, i, array[i]-1)
            }
        }

        val missing = mutableListOf<Int>()
        for (i in array.indices) {
            if (array[i]-1 != i) {
                missing.add(i + 1)
                break
            }
        }
        var count = 1
        var i = array.size + 1
        while (count < total) {
            missing.add(i)
            i++
            count++
        }
        return missing
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(3,-1,4,5,5), intArrayOf(2,3,4), intArrayOf(-2,-3,4))
            val counts = arrayOf(3, 3, 2)
            for (i in arrays.indices) {
                print("array: ${arrays[i].contentToString()}")
                val missing = FindTheFirstKMissingPositiveNumbers().findNumbers(arrays[i], counts[i])
                println(", sorted: ${arrays[i].contentToString()}, missing: $missing")
            }
        }
    }
}