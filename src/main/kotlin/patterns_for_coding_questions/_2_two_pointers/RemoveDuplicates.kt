package patterns_for_coding_questions._2_two_pointers

class RemoveDuplicates {
    fun remove(array: IntArray): Int {
        return educative(array)
    }

    private fun educative(array: IntArray): Int {
        var left = 0
        for (right in array.indices) {
            if (array[left] != array[right]) {
                left++
                array[left] = array[right]
            }
        }
        return left + 1
    }

    private fun NickWhite(array: IntArray): Int {
        var left = 0
        var right = 1
        while (right < array.size) {
            while (right < array.size && array[right] == array[left]) {
                right++
            }
            if (right < array.size) {
                left++
                array[left] = array[right]
            }
        }
        return left + 1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(2, 3, 3, 3, 6, 9, 9), intArrayOf(2, 2, 2, 11))
            for (array in arrays) {
                val length = RemoveDuplicates().remove(array)
                println("array: ${array.contentToString()}, length: $length")
            }
        }
    }
}