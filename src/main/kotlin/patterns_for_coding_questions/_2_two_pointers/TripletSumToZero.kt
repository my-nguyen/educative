package patterns_for_coding_questions._2_two_pointers

class TripletSumToZero {
    fun searchTriplets(array: IntArray): List<IntArray> {
        // return mySearch(array)
        return educative(array)
    }

    // This problem follows the Two Pointers pattern and shares similarities with Pair with Target Sum. A couple of
    // differences are that the input array is not sorted and instead of a pair we need to find triplets with a target
    // sum of zero.
    // To follow a similar approach, first, we will sort the array and then iterate through it taking one number at
    // a time. Let’s say during our iteration we are at number ‘X’, so we need to find ‘Y’ and ‘Z’ such that X+Y+Z==0.
    // At this stage, our problem translates into finding a pair whose sum is equal to “−X” (as from the above equation
    // Y+Z==−X).
    // Another difference from Pair with Target Sum is that we need to find all the unique triplets. To handle this,
    // we have to skip any duplicate number. Since we will be sorting the array, so all the duplicate numbers will be
    // next to each other and are easier to skip.
    private fun educative(array: IntArray): List<IntArray> {
        // sort array to facilitate looking for array[left] and array[right] to add to array[i]
        array.sort()
        val triplets = mutableListOf<IntArray>()
        for (i in 0..(array.lastIndex - 2)) {
            // skip duplicate values
            if (array[i] != array[i + 1]) {
                var left = i + 1
                var right = array.lastIndex
                while (left < right) {
                    val sum = array[left] + array[right]
                    when {
                        sum == -array[i] -> {
                            val triplet = intArrayOf(array[i], array[left], array[right])
                            triplets.add(triplet)
                            // array[left] and array[right] are taken; must move left and right
                            left++
                            right--

                            // skip past the duplicates if any
                            while (left < right && array[left] == array[left - 1]) {
                                left++
                            }
                            while (left < right && array[right] == array[right + 1]) {
                                right--
                            }
                        }
                        sum < -array[i] -> {
                            left++
                        }
                        else -> {
                            right--
                        }
                    }
                }
            }
        }
        return triplets
    }

    // incorrect
    private fun mySearch(array: IntArray): List<IntArray> {
        val indices = mutableMapOf<Int, MutableList<Int>>()
        for ((i, v) in array.withIndex()) {
            var list = indices[v]
            if (list == null) { // -3->0, 0->1, 1->2,5, 2->3, -1->4, -2->6
                list = mutableListOf()
                indices[v] = list
            }
            list.add(i)
        }

        val triplets = mutableListOf<IntArray>()
        for (i in array.indices) { // 0
            val sumOfTwo = -array[i] // 3
            for (j in (i + 1)..array.lastIndex) { // 1, 2
                val complement = sumOfTwo - array[j] // 3, 2
                if (indices.containsKey(complement)) {
                    val triplet = intArrayOf(array[i], array[j], complement)
                    triplets.add(triplet)
                }
            }
        }
        return triplets
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(-3, 0, 1, 2, -1, 1, -2), intArrayOf(-5, 2, -1, -2, 3))
            for (array in arrays) {
                val triplets = TripletSumToZero().searchTriplets(array)
                print("array: ${array.contentToString()}, triplets: ")
                for (triplet in triplets) {
                    print("${triplet.contentToString()}, ")
                }
                println()
            }
        }
    }
}