package practice1.two_pointers

class RemoveDuplicates {
    fun remove(array: IntArray): Int {
        var i = 0
        for (j in i+1 until array.size) {
            if (array[i] != array[j]) {
                i++
                array[i] = array[j]
            }
        }
        return i+1
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