package patterns_for_coding_questions._2_two_pointers

class TripletsWithSmallerSum2 {
    fun searchTriplets(array: IntArray, target: Int): List<IntArray> {
        array.sort()
        val triplets = mutableListOf<IntArray>()
        for (i in 0..array.size - 2) {
            var left = i + 1
            var right = array.lastIndex
            while (left < right) {
                if (array[i] + array[left] + array[right] < target) {
                    // since array[left] <= array[right], the sum of array[i], array[left] and any element
                    // between (left+1, right) will be less than target. this observation saves us from a loop
                    for (j in (left + 1)..right) {
                        val triplet = intArrayOf(array[i], array[left], array[j])
                        triplets.add(triplet)
                    }
                    left++
                } else {
                    right--
                }
            }
        }
        return triplets
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(-1, 0, 2, 3), intArrayOf(-1, 4, 2, 1, 3))
            val targets = arrayOf(3, 5)
            for (i in arrays.indices) {
                val triplets = TripletsWithSmallerSum2().searchTriplets(arrays[i], targets[i])
                print("array: ${arrays[i].contentToString()}, target: ${targets[i]}, count: ")
                for (triplet in triplets) {
                    print("${triplet.contentToString()}, ")
                }
                println()
            }
        }
    }
}