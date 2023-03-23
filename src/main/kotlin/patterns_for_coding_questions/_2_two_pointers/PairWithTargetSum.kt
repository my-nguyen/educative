package patterns_for_coding_questions._2_two_pointers

class PairWithTargetSum {
    fun search(array: IntArray, target: Int): IntArray {
        var left = 0
        var right = array.lastIndex
        while (array[left]+array[right] != target) {
            if (array[left]+array[right] < target) {
                left++
            } else {
                right--
            }
        }
        return intArrayOf(left, right)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1, 2, 3, 4, 6), intArrayOf(2, 5, 9, 11))
            val targets = arrayOf(6, 11)
            for (i in arrays.indices) {
                val pair = PairWithTargetSum().search(arrays[i], targets[i])
                println("array: ${arrays[i].contentToString()}, target: ${targets[i]}, pair: ${pair.contentToString()}")
            }
        }
    }
}