package patterns_for_coding_questions._5_cyclic_sort


class FindTheMissingNumber {
    fun findMissingNumber(array: IntArray): Int {
        return educative(array)
    }

    // from educative
    // This problem follows the Cyclic Sort pattern. Since the input array contains unique numbers from the range 0 to ‘n’,
    // we can use a similar strategy as discussed in Cyclic Sort to place the numbers on their correct index. Once we have
    // every number in its correct place, we can iterate the array to find the index which does not have the correct number,
    // and that index will be our missing number.
    // However, there are two differences with Cyclic Sort:
    // 1. In this problem, the numbers are ranged from ‘0’ to ‘n’, compared to ‘1’ to ‘n’ in the Cyclic Sort. This will
    //    make two changes in our algorithm:
    //    * In this problem, each number should be equal to its index, compared to index - 1 in the Cyclic Sort.
    //      Therefore => nums[i] == nums[nums[i]]
    //    * Since the array will have ‘n’ numbers, which means array indices will range from 0 to ‘n-1’. Therefore,
    //      we will ignore the number ‘n’ as we can’t place it in the array, so => nums[i] < nums.length
    // 2. Say we are at index i. If we swap the number at index i to place it at the correct index, we can still have
    //    the wrong number at index i. This was true in Cyclic Sort too. It didn’t cause any problems in Cyclic Sort as
    //    over there, we made sure to place one number at its correct place in each step, but that wouldn’t be enough
    //    in this problem as we have one extra number due to the larger range. Therefore, we will not move to the next
    //    number after the swap until we have a correct number at the index i.
    private fun educative(array: IntArray): Int {
        for (i in array.indices) {
            while (array[i] < array.size && array[i] != array[array[i]]) {
                swap(array, i, array[i])
            }
        }

        // now that every number is in its correct place, we can iterate the array to find the index which does not have
        // the correct number, and that index will be our missing number.
        for (j in array.indices) {
            if (j != array[j]) {
                return j
            }
        }
        return array.size
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(4,0,3,1), intArrayOf(8,3,5,2,4,6,0,1))
            for (array in arrays) {
                print("array: ${array.contentToString()}")
                val missing = FindTheMissingNumber().findMissingNumber(array)
                println(", sorted: ${array.contentToString()}, missing: $missing")
            }
        }
    }
}