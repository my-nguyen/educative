package patterns_for_coding_questions._2_two_pointers

class QuadrupleSumToTarget {
    fun searchQuadruplets(array: IntArray, target: Int): List<IntArray> {
        return mine(array, target)
    }

    private fun mine(array: IntArray, target: Int): List<IntArray> {
        array.sort()
        val quads = mutableListOf<IntArray>()
        for (first in 0..array.size - 3) {
            for (second in first + 1..array.size - 2) {
                var third = second + 1
                var fourth = array.lastIndex
                while (third < fourth) {
                    val sum = array[first] + array[second] + array[third] + array[fourth]
                    when {
                        sum == target -> {
                            val quad = intArrayOf(array[first], array[second], array[third], array[fourth])
                            quads.add(quad)

                            third++
                            fourth--
                        }
                        sum < target -> {
                            third++
                        }
                        else -> {
                            fourth--
                        }
                    }
                }
            }
        }
        return quads
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(4, 1, 2, -1, 1, -3), intArrayOf(2, 0, -1, 1, -2, 2))
            val targets = arrayOf(1, 2)
            for (i in arrays.indices) {
                print("array: ${arrays[i].contentToString()}, ")
                val quads = QuadrupleSumToTarget().searchQuadruplets(arrays[i], targets[i])
                print("sorted: ${arrays[i].contentToString()}, target: ${targets[i]}, quads: ")
                for (quad in quads) {
                    print("${quad.contentToString()}, ")
                }
                println()
            }
        }
    }
}