package patterns_for_coding_questions._2_two_pointers

class RemoveDuplicates2 {
    fun remove(array: IntArray, key: Int): Int {
        var left = 0
        for (right in array.indices) {
            if (array[right] != key) {
                array[left] = array[right]
                left++
            }
        }
        return left
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(3, 2, 3, 6, 3, 10, 9, 3), intArrayOf(2, 11, 2, 2, 1))
            val keys = arrayOf(3, 2)
            for (i in arrays.indices) {
                val length = RemoveDuplicates2().remove(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, length: $length")
            }
        }
    }
}