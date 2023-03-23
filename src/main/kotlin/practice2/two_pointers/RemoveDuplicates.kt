package practice2.two_pointers

class RemoveDuplicates {
    fun remove(array: IntArray): Int {  // 2,3,3,3,6,9,9
        var i = 0
        for (j in array.indices) {      // 0,1,2,3,4,5
            if (array[j] != array[i]) { // 3 != 2
                i++                     // 1,2,3
                array[i] = array[j]     // 2,3,6,9
            }
        }
        return i + 1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(2,3,3,3,6,9,9), intArrayOf(2,2,2,11))
            for (array in arrays) {
                val length = RemoveDuplicates().remove(array)
                println("array: ${array.contentToString()}, length: $length")
            }
        }
    }
}