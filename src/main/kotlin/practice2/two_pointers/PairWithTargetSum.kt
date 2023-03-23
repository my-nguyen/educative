package practice2.two_pointers

class PairWithTargetSum {
    fun search(array: IntArray, target: Int): IntArray {
        var i = 0
        var j = array.lastIndex
        while (i < j) {
            val sum = array[i] + array[j]
            when {
                sum < target -> i++
                sum > target -> j--
                else -> break
            }
        }
        return intArrayOf(i, j)
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