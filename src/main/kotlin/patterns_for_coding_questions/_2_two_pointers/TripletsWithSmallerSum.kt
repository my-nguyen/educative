package patterns_for_coding_questions._2_two_pointers

class TripletsWithSmallerSum {
    fun searchTriplets(array: IntArray, target: Int): Int {
        // return mine(array, target)
        return educative(array, target)
    }

    // this solution is O(n^2)
    private fun educative(array: IntArray, target: Int): Int {
        array.sort()
        var count = 0
        for (i in 0..array.size - 2) {
            var left = i + 1
            var right = array.lastIndex
            while (left < right) {
                if (array[i] + array[left] + array[right] < target) {
                    // since array[left] <= array[right], the sum of array[i], array[left] and any element
                    // between (left+1, right) will be less than target. this observation saves us one loop
                    count += right - left
                    left++
                } else {
                    right--
                }
            }
        }
        return count
    }

    // this solution is O(n^3)
    private fun mine(array: IntArray, target: Int): Int {
        array.sort()
        var count = 0
        for (i in 0..array.size - 2) {
            for (left in (i + 1)..array.lastIndex) {
                for (right in (array.lastIndex) downTo left + 1) {
                    if (array[i] + array[left] + array[right] < target) {
                        // println("triplet: $i:${array[i]}, $left:${array[left]}, $right:${array[right]}")
                        count++
                    }
                }
            }
        }
        return count
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(-1, 0, 2, 3), intArrayOf(-1, 4, 2, 1, 3))
            val targets = arrayOf(3, 5)
            for (i in arrays.indices) {
                val count = TripletsWithSmallerSum().searchTriplets(arrays[i], targets[i])
                println("array: ${arrays[i].contentToString()}, target: ${targets[i]}, count: $count")
            }
        }
    }
}