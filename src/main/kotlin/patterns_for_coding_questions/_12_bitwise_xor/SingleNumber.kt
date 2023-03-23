package patterns_for_coding_questions._12_bitwise_xor

class SingleNumber {
    fun findSingleNumber(array: IntArray): Int {
        // return useSet(array)
        return useXor(array)
    }

    private fun useSet(array: IntArray): Int {
        val set = mutableSetOf<Int>()
        for (number in array) {
            if (set.contains(number)) {
                set.remove(number)
            } else {
                set.add(number)
            }
        }
        return set.first()
    }

    private fun useXor(array: IntArray): Int {
        var current = 0
        for (number in array) {
            current = current xor number
        }
        return current
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1, 4, 2, 1, 3, 2, 3), intArrayOf(7, 9, 7))
            for (array in arrays) {
                val number = SingleNumber().findSingleNumber(array)
                println("array: ${array.contentToString()}, single number: $number")
            }
        }
    }
}