package practice1.bitwise_xor

class SingleNumber {
    fun findSingleNumber(array: IntArray): Int {
        var number = array[0]
        for (i in 1 until array.size) {
            number = number xor array[i]
        }
        return number
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,4,2,1,3,2,3), intArrayOf(7,9,7))
            for (array in arrays) {
                val number = SingleNumber().findSingleNumber(array)
                println("array: ${array.contentToString()}, single number: $number")
            }
        }
    }
}